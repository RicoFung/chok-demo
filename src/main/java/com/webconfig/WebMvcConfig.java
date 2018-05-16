package com.webconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import chok.util.PropertiesUtil;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter
{
	/**
	 * 配置默认页
	 */
	@Override
	public void addViewControllers(ViewControllerRegistry registry)
	{
		registry.addViewController("/").setViewName("forward:/index.jsp"); // 设置默认首页(必须加入“forward:”, 否则会访问spring.mvc.view.prefix所指定的目录)
		super.addViewControllers(registry);
	}
	
	/**
	 *  配置虚拟目录，用于非nginx环境
	 */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) 
    {
        /*
        * 说明：增加虚拟路径(经过本人测试：在此处配置的虚拟路径，用springboot内置的tomcat时有效，
        * 用外部的tomcat也有效;所以用到外部的tomcat时不需在tomcat/config下的相应文件配置虚拟路径了,阿里云linux也没问题)
        */
        registry
        .addResourceHandler(PropertiesUtil.getValue("config/", "static.path"))
        .addResourceLocations("file:"+PropertiesUtil.getValue("config/", "static.doBase"));
        super.addResourceHandlers(registry);
    }

    /**
     * 配置多文件上传
     * @return CommonsMultipartResolver
     */
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver multipartResolver()
	{
		CommonsMultipartResolver cmr = new CommonsMultipartResolver();
		// 默认编码
		cmr.setDefaultEncoding("UTF-8");
		// 文件大小最大值. 注意限制的不是针对单个文件，而是所有文件的容量之和
		cmr.setMaxUploadSize(10485760000l);
		// 内存中的最大值
		cmr.setMaxInMemorySize(40960);
		return cmr;
	}
}
