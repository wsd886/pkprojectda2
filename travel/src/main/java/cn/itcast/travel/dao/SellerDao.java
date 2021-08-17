package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

public interface SellerDao
{
    /**
     * 根据sid查询卖家信息
     * @param sid
     * @return
     */
    Seller findBySid(int sid);
}