package com.github.zgeeks.ads.auth.core;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static java.util.Arrays.*;

public class JwtUserDetailsService implements UserDetailsService {

    private final JwtUserRepository jwtUserRepository;

    public JwtUserDetailsService(JwtUserRepository jwtUserRepository) {
        this.jwtUserRepository = jwtUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<JwtUser> byUsername = jwtUserRepository.findByUsername(username);
        if (byUsername.isPresent()) {
            JwtUser user = byUsername.get();
            return new JwtUserDetails(
                    user.id(), user.username(), user.password(), getAuthority());
        }
        throw new UsernameNotFoundException("Invalid username or password.");
    }

    public JwtUserDetails loadUserByUsernameAndPassword(String username, String password) throws UsernameNotFoundException {
        Optional<JwtUser> byUsernameAndPassword = jwtUserRepository.findByUsernameAndPassword(username, password);
        if (byUsernameAndPassword.isPresent()) {
            JwtUser user = byUsernameAndPassword.get();
            return new JwtUserDetails(user.id(),
                        user.username(), user.password(), getAuthority());
        }
        throw new UsernameNotFoundException("Invalid username or password.");
    }

    private List getAuthority() {
        return asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
