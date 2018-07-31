package com.github.zgeeks.ads.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

    private static final String NCS_CART_ROLE = "NCS_CART";

    @Value("${favorite.ads.security.permitted.path}")
    private String[] permittedPaths;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().csrf().disable()
            .authorizeRequests()
            .antMatchers(permittedPaths).permitAll()
            .anyRequest().hasRole(NCS_CART_ROLE)
            .and()
            .httpBasic();
    }

}
