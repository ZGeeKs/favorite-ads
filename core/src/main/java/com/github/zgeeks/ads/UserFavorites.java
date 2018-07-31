package com.github.zgeeks.ads;

import org.immutables.value.Value;
import org.springframework.data.annotation.Id;

import java.util.List;

@Value.Immutable
public interface UserFavorites {
    @Id
    String userId();
    List<Ad> favorites();
}
