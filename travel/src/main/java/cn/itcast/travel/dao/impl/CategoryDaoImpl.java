package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class CategoryDaoImpl implements CategoryDao {

    @Override
    public List<Category> findAll() {
        Connection conn=null;
        List<Category> list=new ArrayList<Category>();
        try {
            conn= JDBCUtils.getConnection();
            String sql="select * from tab_category order by cid asc";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.executeQuery();
            ResultSet rs=pstmt.executeQuery();
            while(rs.next()){
                Category c=new Category();
                c.setCid(rs.getInt("cid"));
                c.setCname(rs.getString("cname"));
                list.add(c);
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
        return list;
    }
}
