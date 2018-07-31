package com.github.zgeeks.ads.rest;

import com.github.zgeeks.ads.Ad;
import com.github.zgeeks.ads.FavoritesService;
import com.github.zgeeks.ads.ImmutableUserFavorites;
import com.github.zgeeks.ads.handler.UsersApi;
import com.github.zgeeks.ads.model.FavoriteAd;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.time.Instant;
import java.time.ZoneOffset;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.github.zgeeks.ads.model.FavoriteAd.*;


public class UserFavoritesResources implements UsersApi {

    private final FavoritesService favoritesService;
    private final Supplier<Instant> clock;

    public UserFavoritesResources(FavoritesService favoritesService, Supplier<Instant> clock) {
        this.favoritesService = favoritesService;
        this.clock = clock;
    }

    @Override
    public Response favorites(String userId) {
        return Response.accepted().entity(favoritesService.userFavorites(userId)
            .stream()
            .flatMap(userFavorites -> userFavorites.favorites().stream())
            .map(ad -> new FavoriteAdBuilder()
                    .withContent(ad.content())
                    .withCreationDate(ad.createdDate())
                    .withId(ad.id())
                    .withTitle(ad.title())
                    .create())
            .collect(Collectors.toList())).build();
    }

    @Override
    public Response saveFavoriteAd(String userId, FavoriteAd body) {
        favoritesService.save(ImmutableUserFavorites
            .builder()
            .userId(userId)
            .addFavorites(Ad.create(body.getId(),
                body.getTitle(),
                body.getContent(),
                clock.get().now()))
            .build()
        );
        return Response.status(Response.Status.CREATED).build();
    }
}
