package com.github.zgeeks.ads.auth.server.config;


import com.github.zgeeks.ads.auth.JwtUserDetailsService;
import com.github.zgeeks.ads.auth.JwtUserRepository;
import com.github.zgeeks.ads.auth.server.authentication.UsernamePasswordAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoClientFactoryBean;
import org.springframework.security.authentication.AuthenticationProvider;

@Configuration
public class AuthProviderConfig {

    @Autowired
    protected UserConfig userConfig;

    @Autowired
    protected PropertyConfig config;

    @Autowired
    protected JwtUserRepository jwtUserRepository;

    public @Bean MongoClientFactoryBean mongo() {
        MongoClientFactoryBean mongo = new MongoClientFactoryBean();
        mongo.setHost("mongodb");
        return mongo;
    }

    @Bean JwtUserDetailsService userDetailsService() {
        return new JwtUserDetailsService(jwtUserRepository);
    }

    @Bean AuthenticationProvider authenticationProvider() {
        return new UsernamePasswordAuthenticationProvider(userDetailsService());
    }
}

