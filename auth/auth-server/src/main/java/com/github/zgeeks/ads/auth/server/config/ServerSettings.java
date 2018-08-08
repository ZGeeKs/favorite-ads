package com.github.zgeeks.ads.auth.server.config;

import org.springframework.beans.factory.annotation.Value;

public class ServerSettings {

    @Value("${secret}")
    private String secret;

    @Value("${authentication.code.expiration:10}")
    private Integer authenticationCodeExpirationInSeconds;

    @Value("${authentication.client-id}")
    private String authenticationClientId;


    public String getSecret() {
        return secret;
    }

    public int getAuthenticationCodeExpirationInSeconds() {
        return authenticationCodeExpirationInSeconds;
    }

    public String getAuthenticationClientId() {
        return authenticationClientId;
    }
}
