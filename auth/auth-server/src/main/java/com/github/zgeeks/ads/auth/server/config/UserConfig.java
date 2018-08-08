package com.github.zgeeks.ads.auth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Autowired
    protected PropertyConfig propertyConfig;
}
