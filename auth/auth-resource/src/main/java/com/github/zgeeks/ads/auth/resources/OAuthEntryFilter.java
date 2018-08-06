package com.github.zgeeks.ads.auth.resources;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationProcessingFilter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

public class OAuthEntryFilter extends OAuth2AuthenticationProcessingFilter {

    public OAuthEntryFilter(String secret) {

        try {
            setAuthenticationManager(authManager(tokenServices(tokenStore(secret))));
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        super.afterPropertiesSet();
    }

    private static AuthenticationManager authManager(ResourceServerTokenServices tokenServices) {

        final OAuthAuthenticationManager authManager = new OAuthAuthenticationManager(tokenServices);
        authManager.afterPropertiesSet();

        return authManager;
    }

    private static ResourceServerTokenServices tokenServices(TokenStore store) throws Exception {
        final DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setTokenStore(store);
        tokenServices.afterPropertiesSet();

        return tokenServices;
    }

    private static TokenStore tokenStore(String secret) throws Exception {
        final JwtAccessTokenConverter jwtTokenEnhancer = new JwtAccessTokenConverter();
        jwtTokenEnhancer.setSigningKey(secret);
        jwtTokenEnhancer.afterPropertiesSet();

        return new JwtTokenStore(jwtTokenEnhancer);
    }

}
