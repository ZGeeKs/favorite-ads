package com.github.zgeeks.ads.rest;

import com.github.zgeeks.ads.UserFavoriteAdsApplication;
import com.sun.org.apache.xerces.internal.parsers.SecurityConfiguration;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

public class WebInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application
            .web(WebApplicationType.SERVLET)
            .sources(UserFavoriteAdsApplication.class, SecurityConfiguration.class);
    }
}
