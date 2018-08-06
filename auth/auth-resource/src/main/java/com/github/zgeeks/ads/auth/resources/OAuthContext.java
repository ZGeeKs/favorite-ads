package com.github.zgeeks.ads.auth.resources;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class OAuthContext {

    private OAuthContext() {
        // static
    }

    public static Optional<OAuth2Authentication> auth() {
        return Optional
            .ofNullable((OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication());
    }

    public static Map<String, String> details() {

        Map<String, String> details = new HashMap<>();
        auth().ifPresent(x ->
                ((Map<String, Object>)((OAuth2AuthenticationDetails) x.getDetails()).getDecodedDetails())
                    .forEach((k, v) -> details.put(k, String.valueOf(v)))
        );

        return Collections.unmodifiableMap(details);
    }
}
