package com.github.zgeeks.ads.auth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    protected OAuth2ResourceConfig resourceConfig;

    @Autowired
    protected PropertyConfig propertyConfig;

    @Autowired
    private SecurityConfig.Web webConfig;

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // no security changes
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(webConfig.authenticationManagerBean());
        endpoints.tokenServices(resourceConfig.tokenServices());
    }
}
