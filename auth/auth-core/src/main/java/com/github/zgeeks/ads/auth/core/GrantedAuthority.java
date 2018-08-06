package com.github.zgeeks.ads.auth.core;

import org.immutables.value.Value;

@Value.Immutable
public interface GrantedAuthority {
    String authority();
}
