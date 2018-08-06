package com.github.zgeeks.ads.auth.core;

import org.immutables.value.Value;

import java.util.List;

@Value.Immutable
public interface AdminUser {
    String username();

    String password();

    List<String> roles();
}
