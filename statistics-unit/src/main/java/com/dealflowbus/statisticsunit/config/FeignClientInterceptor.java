package com.dealflowbus.statisticsunit.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

@Component
public class FeignClientInterceptor implements RequestInterceptor {

    private static final String AUTH_HEADER = "Authorization";

    @Override
    public void apply(RequestTemplate template) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getDetails() instanceof OAuth2AuthenticationDetails) {
            OAuth2AuthenticationDetails details =
                    (OAuth2AuthenticationDetails) authentication.getDetails();

            template.header(AUTH_HEADER, String.format("%s %s", details.getTokenType(), details.getTokenValue()));
        } else {
            throw new AuthorizationServiceException("You dont have authorization to perform this operation, token is missing");
        }
    }


}
