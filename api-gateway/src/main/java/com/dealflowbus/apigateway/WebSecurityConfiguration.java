package com.dealflowbus.apigateway;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


@Configuration
@EnableResourceServer
public class WebSecurityConfiguration extends ResourceServerConfigurerAdapter {
	
	@Override
	public void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/oauth/**")  //allow to pass here to check token
			.permitAll()
			.antMatchers("/**")  //any other path requeres access(authentication)
			.authenticated();
	}
	
	@Override
	  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
	       resources.resourceId("externalwebapp");
	  }
}
