package com.github.zgeeks.ads.config;

import com.github.zgeeks.ads.ProbeResources;
import com.github.zgeeks.ads.rest.JerseyObjectMapperProvider;
import com.github.zgeeks.ads.rest.UserFavoritesResources;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        packages("io.swagger.jaxrs.listing");
        register(UserFavoritesResources.class);
        register(ProbeResources.class);
        register(JerseyObjectMapperProvider.class);
    }
}
