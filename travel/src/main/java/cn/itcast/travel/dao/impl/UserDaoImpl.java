package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    @Override
    public User findByUsername(String username) {
        Connection conn=null;
        try {
            //1、获取连接对昂
            conn=JDBCUtils.getConnection();
            String sql="select * from tab_user where username=?";
            //2、创建执行sql的对象
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setObject(1,username);   //将第一个问好替换成实际传过来的值
            //3、执行sql
            pstmt.executeQuery();//查询操作调用执行sql对象的executeQuery()的方法；如果用增删改用executeUpdate
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                //如果用户传入的用户名查出来了数据，说明该用户名已注册
                return new User();//return一个空的对象，说明查出了对象
            }else{
                //如果用户传入的用户名没有查出来了数据，说明该用户名未注册
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void save(User user) {
        Connection conn=null;
        try {
            //获取连接对象
            conn = JDBCUtils.getConnection();
            String sql="insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setObject(1,user.getUsername());
            pstm.setObject(2,user.getPassword());
            pstm.setObject(3,user.getName());
            pstm.setObject(4,user.getBirthday());
            pstm.setObject(5,user.getSex());
            pstm.setObject(6,user.getTelephone());
            pstm.setObject(7,user.getEmail());
            pstm.setObject(8,user.getStatus());
            pstm.setObject(9,user.getCode());
            pstm.executeUpdate();
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

    @Override
    public void updateStatus(User user) {
        Connection conn=null;

        try {
            conn=JDBCUtils.getConnection();
            String sql="update tab_user set status='Y' where code=?";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setObject(1,user.getCode());
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

    @Override
    public User findByCode(String code) {
        Connection conn=null;
        try {
            conn=JDBCUtils.getConnection();
            String sql="select * from tab_user where code=?";
            PreparedStatement pstmt=conn.prepareStatement(sql);
            pstmt.setObject(1,code);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                User user=new User();
                user.setCode(rs.getString("code"));
                return user;
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
    public User findByUsernameAndPassword(String username,String password) {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            String sql = "select * from tab_user where username=? and password=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setObject(1, username);
            pstmt.setObject(2, password);
            //执行sql
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setStatus(rs.getString("status"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setName(rs.getString("name"));

                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;


    }}
