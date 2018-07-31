package com.github.zgeeks.ads.persistance;

import com.github.zgeeks.ads.ImmutableUserFavorites;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserFavoritesRepository extends MongoRepository<ImmutableUserFavorites, String> {

    List<ImmutableUserFavorites> findByUserId(String userId);
}
