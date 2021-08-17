package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RouteImgDaoImpl implements RouteImgDao
{
    /**
     * 根据route的rid查询缩略图
     *
     * @param rid
     * @return
     */
    @Override
    public List<RouteImg> findByRid(int rid)
    {
        Connection conn = null;

        try
        {
            conn = JDBCUtils.getConnection();
            String sql = "select * from tab_route_img where rid=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1,rid);
            //执行sql
            ResultSet rs = pstmt.executeQuery();
            //处理结果
            List<RouteImg> routeImgList = new ArrayList<RouteImg>();
            while (rs.next())
            {
                RouteImg routeImg = new RouteImg();

                routeImg.setRgid(rs.getInt("rgid"));
                routeImg.setRid(rs.getInt("rid"));
                routeImg.setBigPic(rs.getString("bigPic"));
                routeImg.setSmallPic(rs.getString("smallPic"));

                routeImgList.add(routeImg);
            }

            return routeImgList;
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