package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {
    @Override
    public int findTotalCount(int cid,String rname)
    {
        Connection conn = null;

        try
        {
            conn = JDBCUtils.getConnection();
            String sql="select count(*) from tab_route ";
            PreparedStatement pstmt=null;
            if(rname != null && rname.length() >0){
                sql += "where cid=? and rname like ?";
                pstmt=conn.prepareStatement(sql);
                pstmt.setObject(1,cid);
                pstmt.setObject(2,"%"+rname+"%");
            }else{
                sql += " where cid=?";
                pstmt=conn.prepareStatement(sql);
                pstmt.setObject(1,cid);
            }
            //执行sql
            ResultSet rs = pstmt.executeQuery();
            //处理结果
            if(rs.next())
            {
                //通过结果集的字段序号拿取数据，序号从1开始
                int count = rs.getInt(1);
                return count;
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

        return 0;
    }

    @Override
    public List<Route> findByPage(int cid, int currentIndex, int pageSize,String rname)
    {
        Connection conn = null;

        try
        {
            conn = JDBCUtils.getConnection();
            String sql="select * from tab_route ";
            PreparedStatement pstmt=null;
            if(rname != null && rname.length() > 0){
                sql += " where cid=? and rname like ? limit ?,?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setObject(1,cid);
                pstmt.setObject(2,"%"+rname+"%");
                pstmt.setObject(3,currentIndex);
                pstmt.setObject(4,pageSize);
            }else{
                sql += " where cid=? limit ?,?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setObject(1,cid);
                pstmt.setObject(2,currentIndex);
                pstmt.setObject(3,pageSize);
            }
            //执行sql
            ResultSet rs = pstmt.executeQuery();
            //处理结果
            List<Route> routeList = new ArrayList<Route>();
            while(rs.next())
            {
                Route route = new Route();
                route.setRid(rs.getInt(1));
                route.setRname(rs.getString(2));
                route.setPrice(rs.getDouble(3));
                route.setRouteIntroduce(rs.getString(4));
                route.setCid(rs.getInt(9));
                route.setRimage(rs.getString(10));

                routeList.add(route);
            }

            return routeList;
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
