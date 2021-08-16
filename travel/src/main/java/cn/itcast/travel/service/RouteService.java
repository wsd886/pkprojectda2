package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;

public interface RouteService {
    PageBean pageQuery(int cid,int currentPage,int pageSize,String rname);
}
