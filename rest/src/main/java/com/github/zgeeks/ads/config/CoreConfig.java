package com.github.zgeeks.ads.config;

import com.github.zgeeks.ads.FavoritesService;
import com.github.zgeeks.ads.handler.UsersApi;
import com.github.zgeeks.ads.persistance.UserFavoritesRepository;
import com.github.zgeeks.ads.rest.UserFavoritesResources;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.inject.Inject;
import java.time.Clock;
import java.time.Instant;
import java.util.UUID;
import java.util.function.Supplier;

@Configuration
public class CoreConfig {

    @Inject
    UserFavoritesRepository adsRepository;

    @Bean
    public Supplier<Instant> clock() {
        Clock clock = Clock.systemUTC();
        return clock::instant;
    }

    @Bean
    public Supplier<String> idSupplier() {
        return () -> UUID.randomUUID().toString();
    }

    @Bean
    public FavoritesService favoritesService() {
        return new FavoritesService(adsRepository);
    }

    @Bean
    public UsersApi userFavoritesResources() {
        return new UserFavoritesResources(favoritesService(), clock());
    }
}
