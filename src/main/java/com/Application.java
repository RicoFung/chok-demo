package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
// 默认
//@SpringBootApplication
// 需要客制化security时用
@ComponentScan(basePackages={"com","chok.common","chok.security"})
// exclude表示自动配置时排除特定配置（使用CommonsMultipartFile实现多文件上传）
@EnableAutoConfiguration(exclude = { MultipartAutoConfiguration.class })
public class Application extends SpringBootServletInitializer
{
	public static void main(String[] args)
	{
		SpringApplication.run(Application.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		return application.sources(Application.class);
	}
	
	@Bean
	public RestTemplate restTemplate()
	{
		return new RestTemplate();
	}
}
