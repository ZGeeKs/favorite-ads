package com.github.zgeeks.ads.auth.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    UserConfig.class,
    AuthProviderConfig.class,
    OAuth2ResourceConfig.class,
    SecurityConfig.class,
    OAuth2ServerConfig.class,
})
public class ServerConfig {}
