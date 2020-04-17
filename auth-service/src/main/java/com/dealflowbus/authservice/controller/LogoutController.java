package com.dealflowbus.authservice.controller;

import com.dealflowbus.authservice.config.AuthorizationServerConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LogoutController {


    private final AuthorizationServerConfiguration authorizationServerConfiguration;

    public LogoutController(AuthorizationServerConfiguration authorizationServerConfiguration) {
        this.authorizationServerConfiguration = authorizationServerConfiguration;
    }

    @RequestMapping(value = "oauth/logmeout", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request) {


        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            String token = authHeader.replace("bearer", "").trim();

            TokenStore tokenStore = authorizationServerConfiguration.jdbcTokenStore();

            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
            //OAuth2RefreshToken oAuth2RefreshToken = tokenStore.readRefreshToken(token);
            tokenStore.removeAccessToken(oAuth2AccessToken);
            //tokenStore.removeRefreshToken(oAuth2RefreshToken);
        }
    }
}
