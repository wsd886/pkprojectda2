package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

public interface RouteService {
    PageBean pageQuery(int cid,int currentPage,int pageSize,String rname);

    Route findOne(int rid);
}
