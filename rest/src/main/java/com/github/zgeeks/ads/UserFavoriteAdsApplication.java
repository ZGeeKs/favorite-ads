package com.github.zgeeks.ads;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.github.zgeeks.ads.config", "com.github.zgeeks.ads.rest" })
public class UserFavoriteAdsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserFavoriteAdsApplication.class, args);
	}
}
