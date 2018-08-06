package com.github.zgeeks.ads.auth.server.config;

import com.github.zgeeks.ads.auth.core.JwtUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;

@Configuration
public class UserConfig {

    @Autowired
    protected PropertyConfig propertyConfig;

    @Inject
    JwtUserRepository jwtUserRepository;

}
