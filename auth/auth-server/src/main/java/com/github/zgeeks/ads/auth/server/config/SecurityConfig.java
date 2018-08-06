package com.github.zgeeks.ads.auth.server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

/**
 * Defines authentication and authorization rules for customers.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String GRANT_TYPE_FILTER = "grantTypeFilter";

    public SecurityConfig() {
        // Sonar
    }

//    @Order(5)
//    @Configuration
//    public static class AdminBasic extends WebSecurityConfigurerAdapter {
//
//        @Bean
//        public AdminUserSettings adminUsers() {
//            return HoconSettingsFactory.from("admin-users.conf").load(AdminUserSettings.class);
//        }
//
//        @Override
//        public void configure(AuthenticationManagerBuilder authBuilder) throws Exception {
//            InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder> auth =
//                    authBuilder.inMemoryAuthentication();
//
//            adminUsers().users().forEach(u ->
//               auth.withUser(u.username())
//                       .password(u.password())
//                       .roles(u.roles().toArray(new String[0])));
//        }
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//
//            http.antMatcher("/admin/**")
//                    .authorizeRequests()
//                        .anyRequest().hasRole("ADMIN")
//                        .and()
//                    .httpBasic().and()
//                    .csrf().disable();
//        }
//    }

//    @Order(10)
//    @Configuration
//    public static class RestBasic extends WebSecurityConfigurerAdapter {
//
//        @Autowired
//        private ClientRepository clientRepository;
//
//        @Override
//        public void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.userDetailsService(new BapiClientDetailsService(clientRepository));
//        }
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//
//            http.requestMatchers()
//                .antMatchers("/users")
//                    .and()
//                .authorizeRequests()
//                    .anyRequest().authenticated()
//                    .and()
//                .httpBasic()
//                    .and()
//                .csrf().disable();
//        }
//    }

    @Order(15)
    @Configuration
    @EnableResourceServer
    public static class RestOAuth extends ResourceServerConfigurerAdapter {

        @Autowired
        private OAuth2ResourceConfig oAuth2ResourceConfig;

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
            resources.tokenServices(oAuth2ResourceConfig.tokenServices());
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/users/me")
                    .authorizeRequests()
                        .anyRequest().access("#oauth2.hasScope('user')")
                        .and()
                    .csrf().disable();
        }
    }

    @Order(20)
    @Configuration
    public static class Web extends WebSecurityConfigurerAdapter {

        @Autowired
        private AuthProviderConfig authProviderConfig;

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.authenticationProvider(authProviderConfig.authenticationProvider());
        }

        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring()
                    .antMatchers("/static/**")
                    .antMatchers("/webjars/**");
        }


        @Bean
        public LoginUrlAuthenticationEntryPoint authenticationEntryPoint() {
            LoginUrlAuthenticationEntryPoint entryPoint = new LoginUrlAuthenticationEntryPoint("/login");
            // when the client calls "/oauth/authorize" he sees content of "/login" without redirection
            entryPoint.setUseForward(true);
            return entryPoint;
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/login").permitAll()
                .anyRequest().denyAll() // fallback
                    .and()
                    .csrf().disable()
                    .exceptionHandling()
                    .authenticationEntryPoint(authenticationEntryPoint())
                    .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        }

        @Override
        @Bean
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }
    }

}
