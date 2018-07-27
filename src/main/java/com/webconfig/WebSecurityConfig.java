package com.webconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
	////////////////////////////////////////////////////////////
	// HttpBasic 认证
	////////////////////////////////////////////////////////////
	@Autowired
	private Environment env;

	@Override
	protected void configure(HttpSecurity http) throws Exception
	{
		http.authorizeRequests().antMatchers("/**").authenticated().and().httpBasic();
		http.csrf().disable();
	}

	@Bean
	public UserDetailsService userDetailsService()
	{
		// Get the user credentials from the console (or any other source):
		String username = env.getProperty("spring.security.user.name");
		String password = env.getProperty("spring.security.user.password");

		// Set the inMemoryAuthentication object with the given credentials:
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		String encodedPassword = passwordEncoder().encode(password);
		manager.createUser(User.withUsername(username).password(encodedPassword).roles("ADMIN").build());
		return manager;
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	////////////////////////////////////////////////////////////
	//	 自定义认证，开发中...
	////////////////////////////////////////////////////////////
//	@Autowired
//	MyUserDetailsService service;
//	@Autowired
//    private AuthenticationEntryPoint authenticationEntryPoint;
//	
//	@Override
//	protected void configure(HttpSecurity http) throws Exception
//	{
//		// super.configure(http);
//
////		 http
//////		 .csrf().disable() // ajax请求需禁用CSRF
////		 .authorizeRequests()
////		 .anyRequest().authenticated()//.antMatchers("/login","/static/**").permitAll()
////		 .and()
////		 .formLogin().and()
////		 .exceptionHandling()
////         .defaultAuthenticationEntryPointFor(authenticationEntryPoint, new AjaxRequestMatcher())
////         .and()
////		 .httpBasic();
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
////		http
////		.authorizeRequests()
////		.anyRequest().authenticated()
////		.antMatchers("/login","/css/**", "/js/**","/fonts/**").permitAll()
////		.and()
////		 .formLogin()
////		 .loginPage("/login").
////		 and()
////		 .httpBasic()
////		 .and()
////		.exceptionHandling().authenticationEntryPoint((request, response, authException) ->
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
////				response.sendRedirect("/chok-demo/login");
////			}
////		});
//	}
//
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception
//	{
////		PasswordEncoder passwordEncoder = PasswordEncoderFactory.createDelegatingPasswordEncoder();
////		auth.userDetailsService(service).passwordEncoder(passwordEncoder);
////		auth.userDetailsService(service).passwordEncoder(new Md5PasswordEncoder());
//		auth.inMemoryAuthentication().withUser("root").password("root").roles("USER");
//	}
//
//    @Override
//    public void configure(WebSecurity web) throws Exception 
//    {
//        web.ignoring().antMatchers("/static");
//    }
	
}