package com.dealflowbus.databasemainreader.exceptions.SecurityConfig;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;




@Configuration
public class DBWebSecurityConfiguration extends ResourceServerConfigurerAdapter {


    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/manage/health").permitAll().anyRequest().authenticated();
    
    }
    
    @Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
	     
    	resources.resourceId("externalwebapp");
	       
	}

  

}