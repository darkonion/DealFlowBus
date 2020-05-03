package com.dealflowbus.authservice.controller;

import com.dealflowbus.authservice.config.AppConfig;
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
    private final AppConfig appConfig;

    public LogoutController(AuthorizationServerConfiguration authorizationServerConfiguration,
            AppConfig appConfig) {
        this.authorizationServerConfiguration = authorizationServerConfiguration;
        this.appConfig = appConfig;
    }

    @RequestMapping(value = "oauth/logmeout", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public void logout(HttpServletRequest request) {


        String authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            String token = authHeader.replace("bearer", "").trim();

            TokenStore tokenStore = appConfig.jdbcTokenStore();

            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token);
            tokenStore.removeAccessToken(oAuth2AccessToken);
        }
    }

    @RequestMapping("/login")
    public String loginPage() {

        return "login";
    }
}
