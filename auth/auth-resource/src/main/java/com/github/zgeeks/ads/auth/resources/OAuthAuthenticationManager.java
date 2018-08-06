package com.github.zgeeks.ads.auth.resources;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;

class OAuthAuthenticationManager implements AuthenticationManager, InitializingBean {

    private final OAuth2AuthenticationManager delegate;
    private final ResourceServerTokenServices tokenServices;

    public OAuthAuthenticationManager(ResourceServerTokenServices tokenServices) {
        this.tokenServices = tokenServices;
        this.delegate = new OAuth2AuthenticationManager();
        delegate.setTokenServices(tokenServices);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final OAuth2Authentication result = (OAuth2Authentication) delegate.authenticate(authentication);
        final OAuth2AccessToken token = tokenServices.readAccessToken((String) authentication.getPrincipal());
        ((OAuth2AuthenticationDetails)result.getDetails()).setDecodedDetails(token.getAdditionalInformation());

        return result;
    }

    @Override
    public void afterPropertiesSet() {
        delegate.afterPropertiesSet();
    }
}
