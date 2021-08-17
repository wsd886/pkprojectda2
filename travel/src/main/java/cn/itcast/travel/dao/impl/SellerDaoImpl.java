package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SellerDaoImpl implements SellerDao
{

    /**
     * 根据sid查询卖家信息
     *
     * @param sid
     * @return
     */
    @Override
    public Seller findBySid(int sid)
    {
        Connection conn = null;

        try
        {
            conn = JDBCUtils.getConnection();
            String sql = "select * from tab_seller where sid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,sid);
            //执行sql
            ResultSet rs = pstmt.executeQuery();
            //处理结果
            if(rs.next())
            {
                Seller seller = new Seller();

                seller.setSid(sid);
                seller.setSname(rs.getString("sname"));
                seller.setConsphone(rs.getString("consphone"));
                seller.setAddress(rs.getString("address"));

                return seller;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                conn.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }

        return null;
    }
}