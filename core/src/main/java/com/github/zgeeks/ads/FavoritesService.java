package com.github.zgeeks.ads;

import com.github.zgeeks.ads.persistance.UserFavoritesRepository;

import java.util.List;

public class FavoritesService {

    private final UserFavoritesRepository adsRepository;

    public FavoritesService(UserFavoritesRepository adsRepository) {
        this.adsRepository = adsRepository;
    }

    public List<ImmutableUserFavorites> userFavorites(String userId) {
        return adsRepository.findByUserId(userId);
    }

    public void save(ImmutableUserFavorites ad) {
        adsRepository.save(ad);
    }

    public void delete(ImmutableUserFavorites ad) {
        adsRepository.delete(ad);
    }
}
