package com.webconfig;

//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.util.StringUtils;

//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter
//{
//	@Override
//	protected void configure(HttpSecurity http) throws Exception
//	{
//		// super.configure(http);
//
//		 http
//		 .csrf().disable() // ajax请求需禁用CSRF
//		 .authorizeRequests()
//		 .anyRequest().authenticated()
//		 .and()
//		 .formLogin().and()
//		 .httpBasic();
//
////		http.csrf().disable(); // ajax请求需禁用CSRF
//
////		http.exceptionHandling().authenticationEntryPoint((request, response, authException) ->
////		{
////			String requestType = request.getHeader("x-requested-with");
////			if (!StringUtils.isEmpty(requestType))
////			{
////				response.setStatus(HttpServletResponse.SC_OK);
////				response.getWriter().print("{\"invalid_session\": true}");
////				response.getWriter().flush();
////			}
////			else
////			{
////				response.sendRedirect("/security/login");
////			}
////		});
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception
//	{
//		auth.inMemoryAuthentication().withUser("root").password("root").roles("USER");
//	}
//
//}
