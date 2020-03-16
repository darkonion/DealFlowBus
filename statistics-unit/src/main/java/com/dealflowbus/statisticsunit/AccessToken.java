/*package com.dealflowbus.statisticsunit;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;


public class AccessToken {
	
	public static String getToken() {
		OAuth2AuthenticationDetails a2details = (OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
		
		return a2details.getTokenType().concat(" ").concat(a2details.getTokenValue());
	}

}
*/