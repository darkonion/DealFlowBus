package com.dealflowbus.authservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

import javax.sql.DataSource;

@Configuration
public class AuthorizationServerConfiguration implements AuthorizationServerConfigurer  {


    private final PasswordEncoder passwordEncoder;
    private final DataSource dataSource;
    private final AppConfig appConfig;
    private final AuthenticationManager authenticationManager;


    public AuthorizationServerConfiguration(PasswordEncoder passwordEncoder,
            DataSource dataSource,
            AppConfig appConfig, AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.dataSource = dataSource;
        this.appConfig = appConfig;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()").tokenKeyAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource).passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {

        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(appConfig.jdbcTokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setClientDetailsService(endpoints.getClientDetailsService());

        endpoints.tokenStore(appConfig.jdbcTokenStore());
        endpoints.tokenServices(defaultTokenServices);
        endpoints.authenticationManager(authenticationManager);
    }
}
