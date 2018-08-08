package com.github.zgeeks.ads.auth.core;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface JwtUserRepository extends MongoRepository<com.github.zgeeks.ads.auth.core.ImmutableJwtUser, String> {

    Optional<JwtUser> findByUsername(String username);
    Optional<JwtUser> findByUsernameAndPassword(String username, String password);
}
