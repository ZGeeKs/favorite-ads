package com.github.zgeeks.ads.config;

import com.github.zgeeks.ads.FavoritesService;
import com.github.zgeeks.ads.handler.UsersApi;
import com.github.zgeeks.ads.persistance.UserFavoritesRepository;
import com.github.zgeeks.ads.rest.UserFavoritesResources;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;

import javax.inject.Inject;
import java.time.Clock;
import java.time.Instant;
import java.util.UUID;
import java.util.function.Supplier;

@Configuration
public class CoreConfig {

    @Value("${mongodb.host")
    private String host;

    @Inject
    UserFavoritesRepository adsRepository;

    public @Bean MongoClientFactoryBean mongo() {
        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        mongo.setHost("mongodb");
        return mongo;
    }

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
