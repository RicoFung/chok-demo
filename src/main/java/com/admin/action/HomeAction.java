package com.admin.action;

import java.nio.charset.Charset;
import java.util.Base64;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;

import chok.devwork.BaseController;
import chok.util.PropertiesUtil;

@Scope("prototype")
@Controller
@RequestMapping("/admin/home")
public class HomeAction extends BaseController<Object>
{
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private final static String BASIC_AUTH = "admin:admin";
	
	// chok.security.menu.service-id
	private static String MENU_SERVICE_ID = "eureka-client";
	static
	{
		String customMenuServiceId = PropertiesUtil.getValue("config/", "chok.security.menu.service-id");
		if (null != customMenuServiceId)
		{
			MENU_SERVICE_ID = customMenuServiceId.trim();
		}
	}
	// chok.security.menu.protocol
	private static String MENU_PROTOCOL = "http";
	static
	{
		String customMenuProtocol = PropertiesUtil.getValue("config/", "chok.security.menu.protocol");
		if (null != customMenuProtocol)
		{
			MENU_PROTOCOL = customMenuProtocol.trim();
		}
	}
	// chok.security.menu.uri
	private static String MENU_URI = "/menu";
	static
	{
		String customMenuUri = PropertiesUtil.getValue("config/", "chok.security.menu.uri");
		if (null != customMenuUri)
		{
			MENU_URI = customMenuUri.trim();
		}
	}
	
	@Autowired
	MessageSource		source;
	@Autowired
	LoadBalancerClient	loadBalancerClient;
	@Autowired
	RestTemplate		restTemplate;
	@Autowired
	private HttpSession	httpSession;
	
	@RequestMapping("/query")
	public String query() 
	{
		Locale locale0 = Locale.getDefault();
		put("i18nHello0", source.getMessage("hello", null, locale0));
		Locale locale1 = new Locale("en", "GB");
		put("i18nHello1", source.getMessage("hello", null, locale1));
		Locale locale2 = new Locale("fr", "FR");
		put("i18nHello2", source.getMessage("hello", null, locale2));
		return "jsp/admin/home/query";
	}
	
	@RequestMapping("/menu")
	public void menu() 
	{
		JSONObject menuJson = null;
		// 菜单查询
		if (req.getString("menuname") != null)
		{
			menuJson = callMenuApi();
		}
		// 非菜单查询
		else
		{
			if (httpSession.getAttribute("menu") == null)
			{
				menuJson = callMenuApi();
				httpSession.setAttribute("menu", menuJson);
			}
			else
			{
				menuJson = (JSONObject) httpSession.getAttribute("menu");
			}
		}
		printJson(menuJson);
	}
	
	/**
	 * 通过微服务获取App授权
	 * @return JSONObject
	 */
	private JSONObject callMenuApi()
	{
		JSONObject menuJson = null;
		ServiceInstance serviceInstance = loadBalancerClient.choose(MENU_SERVICE_ID);
		String url = MENU_PROTOCOL + "://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + MENU_URI;
		if (log.isInfoEnabled())
			log.info("Rest url => " + url);
		
		// Basic 认证
		byte[] encodeAuth = Base64.getEncoder().encode(BASIC_AUTH.getBytes(Charset.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodeAuth);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", authHeader);
		
		// FORM提交
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, Object> restParams = new LinkedMultiValueMap<String, Object>();
		req.getParameterValueMap(false, true).forEach((k, v)->{restParams.add(k, v);});
		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<MultiValueMap<String, Object>>(restParams, headers);
		
		// JSON提交
//		headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//		String requestJson = "{\"appid\":\"3\"}";
//		HttpEntity<String> requestEntity = new HttpEntity<String>(requestJson, headers);
		
		ResponseEntity<JSONObject> respJson = restTemplate.exchange(url, HttpMethod.POST, requestEntity, JSONObject.class);
		menuJson = respJson.getBody();
		
		if (log.isInfoEnabled())
			log.info("Rest result <= " + menuJson);
		return menuJson;
	}
	
}