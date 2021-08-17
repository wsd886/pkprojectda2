package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.util.List;

public interface RouteImgDao
{
    /**
     * 根据route的rid查询缩略图
     * @param rid
     * @return
     */
    List<RouteImg> findByRid(int rid);
}