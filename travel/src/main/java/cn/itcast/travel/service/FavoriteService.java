package cn.itcast.travel.service;

public interface FavoriteService {
    boolean isFavorite(int rid,int uid);
    void add(int rid,int uid);
}
