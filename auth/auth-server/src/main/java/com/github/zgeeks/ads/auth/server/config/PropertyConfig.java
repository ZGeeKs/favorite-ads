package com.github.zgeeks.ads.auth.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.time.Clock;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
@PropertySource("classpath:auth-server.yml")
public class PropertyConfig {

    private static final String ENCODING = "UTF-8";

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setFileEncoding(ENCODING);
        return configurer;
    }

    @Bean
    public ServerSettings serverSettings() {
        return new ServerSettings();
    }

    @Bean
    public Clock timeProvider() {
        return Clock.systemUTC();
    }
}
