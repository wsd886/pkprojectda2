package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    int findTotalCount(int cid,String rname);
    List<Route> findByPage(int cid,int currentIndex,int pageSize,String rname);
}
