package com.admin.action;

import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
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
	private final Log log = LogFactory.getLog(getClass());
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
	MessageSource source;
	@Autowired
	LoadBalancerClient	loadBalancerClient;
	@Autowired
	RestTemplate		restTemplate;
	
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
		// 通过微服务获取App授权
		ServiceInstance serviceInstance = loadBalancerClient.choose(MENU_SERVICE_ID);
		String url = MENU_PROTOCOL + "://" + serviceInstance.getHost() + ":" + serviceInstance.getPort() + MENU_URI;
		log.info("Rest url => " + url);
		JSONObject jo = restTemplate.postForObject(url, req.getParameterValueMap(false, true), JSONObject.class);
		log.info("Rest result <= " + jo);
		printJson(jo);
	}
}