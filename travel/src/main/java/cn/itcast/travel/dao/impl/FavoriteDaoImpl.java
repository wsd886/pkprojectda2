package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;

import java.sql.*;
import java.util.Date;

public class FavoriteDaoImpl implements FavoriteDao {
    @Override
    public Favorite FindByuUidAndRid(int uid, int rid) {
        Connection conn=null;

        try {
            conn= JDBCUtils.getConnection();
            String sql="select * from tab_favorite where uid=? and rid=?";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setObject(1,uid);
            pstmt.setObject(2,rid);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                return new Favorite();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public int FindCountByRid(int rid) {
        Connection conn=null;
        try {
            conn=JDBCUtils.getConnection();
            String sql="select count(*) from tab_favorite where rid=?";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setObject(1,rid);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public void add(int rid, int uid) {
        Connection conn=null;

        try {
            conn=JDBCUtils.getConnection();
            String sql="insert into tab_favorite values(?,?,?)";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setObject(1,rid);
            pstmt.setObject(2,new Date());
            pstmt.setObject(3,uid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
