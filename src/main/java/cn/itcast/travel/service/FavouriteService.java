package cn.itcast.travel.service;

public interface FavouriteService {
    Boolean isFavourite(int rid, int uid);

    void addFavourite(int rid, int uid);
}
