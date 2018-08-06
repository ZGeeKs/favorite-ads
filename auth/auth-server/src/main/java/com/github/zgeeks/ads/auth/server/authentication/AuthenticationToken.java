package com.github.zgeeks.ads.auth.server.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;

public class AuthenticationToken extends UsernamePasswordAuthenticationToken {

    public AuthenticationToken(String principal) {
        super(principal, "", Collections.emptyList());
    }
}
