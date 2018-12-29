package com.webconfig;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import chok.security.MyAccessDecisionManager;
import chok.security.MyAccessDeniedHandler;
import chok.security.MyInvocationSecurityMetadataSourceService;

@EnableOAuth2Sso
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
	@Autowired
	private Environment	env;
	
	// chok.security.ignore-uris 默认值
	private static String[] IGNORE_URIS = { "/", "/logout", "/error", "/index*", "/**/home/menu", "/error", "/staticexternal/**", "/staticinternal/**" };
	@PostConstruct
	public void init()
	{
		String customIgnoreUris = env.getProperty("chok.security.ignore-uris");
		if (null != customIgnoreUris)
			IGNORE_URIS = customIgnoreUris.trim().split(",");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception
	{
		// 忽略静态资源（所有静态资源不走security过滤器链）
		web.ignoring().antMatchers(IGNORE_URIS);
	}
	
	////////////////////////////////////////////////////////////
	// Oauth2 SSO 认证
	////////////////////////////////////////////////////////////

	@Autowired
	private MyAccessDeniedHandler myAccessDeniedHandler;
	@Autowired
	private MyAccessDecisionManager myAccessDecisionManager;	
	@Autowired
	private MyInvocationSecurityMetadataSourceService myInvocationSecurityMetadataSourceService;
	
	@Override
	public void configure(HttpSecurity http) throws Exception
	{
		http
		// 所有请求必须登录认证
		.authorizeRequests()
		.antMatchers(IGNORE_URIS).permitAll()
        .anyRequest().authenticated()
        // 采用默认FilterSecurityInterceptor装配自定义的规则过滤器
		.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>()
		{
			/**
			 * 由于自实现FilterSecurityInterceptor装配规则过滤器，web.ignoring().antMatchers不能ignore静态资源
			 * 所以采用默认FilterSecurityInterceptor装配规则过滤器，web.ignoring().antMatchers可以ignore静态资源
			 */
			public <O extends FilterSecurityInterceptor> O postProcess(O fsi)
			{
				fsi.setSecurityMetadataSource(myInvocationSecurityMetadataSourceService);
				fsi.setAccessDecisionManager(myAccessDecisionManager);
				return fsi;
			}
		})
		// 注销
		.and().logout().logoutSuccessUrl(env.getProperty("chok.oauth2.server.logout-uri"))
		.deleteCookies()
		.clearAuthentication(true)
		.invalidateHttpSession(true)
		// 40X 异常处理
		.and().exceptionHandling()
		// 401，不需要，@EnableOAuth2Sso会跳转至oauth2服务器端认证
		// .authenticationEntryPoint(myAuthenticationEntryPoint)
		// 403
		.accessDeniedHandler(myAccessDeniedHandler);
	}

	////////////////////////////////////////////////////////////
	// Http 表单认证
	////////////////////////////////////////////////////////////
	// @Autowired
	// private Environment env;
	//
	// @Override
	// protected void configure(HttpSecurity http) throws Exception
	// {
	// http.csrf().disable();
	// http
	// .authorizeRequests()
	//// .antMatchers("/home","/logout").permitAll()
	// .anyRequest()
	// .authenticated()
	// .and()
	// .formLogin()
	// .loginPage("/login")
	// .permitAll()
	// .and()
	// .logout() //手动logout：http://{domain}/login?logout
	// .permitAll();
	// }
	//
//	 @Bean
//	 public UserDetailsService userDetailsService()
//	 {
//	 // Get the user credentials from the console (or any other source):
//	 String username = env.getProperty("spring.security.user.name");
//	 String password = env.getProperty("spring.security.user.password");
//	
//	 // Set the inMemoryAuthentication object with the given credentials:
//	 InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//	 String encodedPassword = passwordEncoder().encode(password);
//	 manager.createUser(User.withUsername(username).password(encodedPassword).roles("ADMIN").build());
//	 return manager;
//	 }
//	
//	 @Bean
//	 public PasswordEncoder passwordEncoder()
//	 {
//	 return new BCryptPasswordEncoder();
//	 }

	////////////////////////////////////////////////////////////
	// HttpBasic 认证
	////////////////////////////////////////////////////////////
	// @Autowired
	// private Environment env;
	//
	// @Override
	// protected void configure(HttpSecurity http) throws Exception
	// {
	// http.authorizeRequests().antMatchers("/**").authenticated().and().httpBasic();
	// http.csrf().disable();
	// }
	//
	// @Bean
	// public UserDetailsService userDetailsService()
	// {
	// // Get the user credentials from the console (or any other source):
	// String username = env.getProperty("spring.security.user.name");
	// String password = env.getProperty("spring.security.user.password");
	//
	// // Set the inMemoryAuthentication object with the given credentials:
	// InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
	// String encodedPassword = passwordEncoder().encode(password);
	// manager.createUser(User.withUsername(username).password(encodedPassword).roles("ADMIN").build());
	// return manager;
	// }
	//
	// @Bean
	// public PasswordEncoder passwordEncoder()
	// {
	// return new BCryptPasswordEncoder();
	// }

}