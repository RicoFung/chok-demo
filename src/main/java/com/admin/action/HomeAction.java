package com.admin.action;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import chok.devwork.BaseController;
import chok.util.PropertiesUtil;
import chok.util.http.HttpAction;
import chok.util.http.HttpResult;
import chok.util.http.HttpUtil;

@Scope("prototype")
@Controller
@RequestMapping("/admin/home")
public class HomeAction extends BaseController<Object>
{
	private static Logger log = LoggerFactory.getLogger(HomeAction.class);
	
	@Autowired
	MessageSource source;
	
	@RequestMapping("/query")
	public String query() 
	{
		Locale locale1 = new Locale("en", "GB");
		put("i18nHello1", source.getMessage("hello", null, locale1));
		Locale locale2 = new Locale("fr", "FR");
		put("i18nHello2", source.getMessage("hello", null, locale2));
		return "jsp/admin/home/query";
	}
	
	@RequestMapping("/searchMenu")
	public void searchMenu() 
	{
		Map<String, String> param = new HashMap<String, String>();
		param.put("tc_user_id", req.getString("tc_user_id"));
		param.put("tc_app_id", req.getString("tc_app_id"));
		param.put("tc_name", req.getString("tc_name"));
		HttpResult<String> r = HttpUtil.create(new HttpAction(PropertiesUtil.getValue("nav.menu.search.api"), param), String.class, "GET");
		if(log.isInfoEnabled()) log.info("搜索后 menuJson：" + r.getData());
		printJson(r.getData());
	}
}