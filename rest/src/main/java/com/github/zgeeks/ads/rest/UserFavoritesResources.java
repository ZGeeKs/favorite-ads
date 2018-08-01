package com.github.zgeeks.ads.rest;

import com.github.zgeeks.ads.Ad;
import com.github.zgeeks.ads.FavoritesService;
import com.github.zgeeks.ads.ImmutableUserFavorites;
import com.github.zgeeks.ads.handler.UsersApi;
import com.github.zgeeks.ads.model.FavoriteAd;

import javax.ws.rs.core.Response;
import java.time.Instant;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.github.zgeeks.ads.model.FavoriteAd.*;


public class UserFavoritesResources implements UsersApi {

    private final FavoritesService favoritesService;
    private final Supplier<Instant> clock;
    private final Supplier<String> uuidGenerator;

    public UserFavoritesResources(FavoritesService favoritesService, Supplier<Instant> clock, Supplier<String> uuidGenerator) {
        this.favoritesService = favoritesService;
        this.clock = clock;
        this.uuidGenerator = uuidGenerator;
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
            .addFavorites(Ad.create(uuidGenerator.get(),
                body.getTitle(),
                body.getContent(),
                clock.get().now()))
            .build()
        );
        return Response.status(Response.Status.CREATED).build();
    }
}
