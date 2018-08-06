package com.github.zgeeks.ads.auth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import static java.util.Arrays.asList;


@Configuration
public class OAuth2ResourceConfig {

    @Autowired
    protected PropertyConfig propertyConfig;

    @Bean
    public JwtAccessTokenConverter jwtTokenEnhancer() {
        final JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(propertyConfig.serverSettings().getSecret());
        return tokenConverter;
    }

    @Bean
    public JwtTokenStore tokenStore() {
        return new JwtTokenStore(jwtTokenEnhancer());
    }

    @Bean
    public TokenEnhancer chainedTokenEnhancer() throws Exception {
        TokenEnhancerChain chain = new TokenEnhancerChain();
        // the JWT token enhancer must come last as it will
        // encode the token with the signature
        chain.setTokenEnhancers(asList(jwtTokenEnhancer()));
        return chain;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() throws Exception {
        DefaultTokenServices services = new DefaultTokenServices();
        services.setTokenStore(tokenStore());
        services.setTokenEnhancer(chainedTokenEnhancer());
        return services;
    }

}
