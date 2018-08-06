package com.github.zgeeks.ads.auth.server.config;


import com.github.zgeeks.ads.auth.core.JwtUserDetailsService;
import com.github.zgeeks.ads.auth.core.JwtUserRepository;
import com.github.zgeeks.ads.auth.server.authentication.UsernamePasswordAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;

@Configuration
public class AuthProviderConfig {


    @Autowired
    protected UserConfig userConfig;

    @Autowired
    protected PropertyConfig config;

    @Autowired
    protected JwtUserRepository jwtUserRepository;

    @Bean JwtUserDetailsService userDetailsService() {
        return new JwtUserDetailsService(jwtUserRepository);
    }

    @Bean AuthenticationProvider authenticationProvider() {
        return new UsernamePasswordAuthenticationProvider(userDetailsService());
    }

}

