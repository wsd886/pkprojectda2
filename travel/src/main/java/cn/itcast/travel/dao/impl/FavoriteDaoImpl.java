package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
