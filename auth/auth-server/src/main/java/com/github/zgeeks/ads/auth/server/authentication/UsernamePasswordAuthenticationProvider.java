package com.github.zgeeks.ads.auth.server.authentication;

import com.github.zgeeks.ads.auth.core.JwtUserDetails;
import com.github.zgeeks.ads.auth.core.JwtUserDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.util.Assert;

public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final JwtUserDetailsService jwtUserDetailsService;

    public UsernamePasswordAuthenticationProvider(JwtUserDetailsService jwtUserDetailsService) {
        this.jwtUserDetailsService = jwtUserDetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Assert.isInstanceOf(UsernamePasswordAuthenticationToken.class, authentication,
                "Can only process UsernamePasswordAuthenticationToken objects");
        // locate the user and check the password

        JwtUserDetails userDetails = jwtUserDetailsService.loadUserByUsernameAndPassword(authentication.getName(),
                (String) authentication.getCredentials());

        if (userDetails != null) {
            return new AuthenticationToken(userDetails.getUserId());
        }

        throw new BadCredentialsException("Bad credentials");
    }

    @Override
    public final boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
