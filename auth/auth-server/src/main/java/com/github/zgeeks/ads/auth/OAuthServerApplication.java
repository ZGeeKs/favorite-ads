package com.github.zgeeks.ads.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class OAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OAuthServerApplication.class, args);
    }
}