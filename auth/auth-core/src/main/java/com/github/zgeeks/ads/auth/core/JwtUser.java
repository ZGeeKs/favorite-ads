package com.github.zgeeks.ads.auth.core;

import org.immutables.value.Value;
import org.springframework.data.annotation.Id;

import java.util.List;

@Value.Immutable
public interface JwtUser {

    @Id
    String id();
    String username();
    String password();
    List<GrantedAuthority> authorities();
}
