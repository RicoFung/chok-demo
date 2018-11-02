package com.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;

import chok.devwork.Result;

@Component
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint 
{
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	private PrintWriter out;
	
	@Autowired
	AjaxRequestMatcher ajaxRequestMatcher;
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException 
	{
		if (ajaxRequestMatcher.matches(request))
		{
			Result r = new Result();
			r.setSuccess(false);
			r.setMsg("没有携带 token 或者 token 无效！(401)");
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/json");
			try
			{
				if(out == null)
				{
					out = response.getWriter();
				}
				out.print(JSON.toJSONString(r));
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
		}
		else
		{
			log.info("(401)");
			response.getWriter().println("401");
		}
	}
}
