package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

import java.sql.Connection;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();
    @Override
    public boolean registerUser(User user) {
        User u=userDao.findByUsername(user.getUsername());
        if(u!=null){
            //用户名存在，注册失败
            return false;
        }else{
            //用户不存在，可以注册

            //2、保存用户信息
            //2.1、设置激活码，唯一字符串使用UUID
            user.setCode(UuidUtil.getUuid());
            //2.2、设置激活状态
            user.setStatus("N");
            userDao.save(user);
            //3、发送激活的邮件
            //邮件的正文
            String content="<a href='http://localhost:8080/travel/activeUserServlet?code="+user.getCode()+"'>点击激活[旅游网站]</a>";
            MailUtils.sendMail(user.getEmail(),content,"激活邮件");
            return true;
        }

    }

    @Override
    public boolean active(String code) {
        //根据激活码查询用户对象
        User user=userDao.findByCode(code);
        if(user != null){
            //调用dao,修改激活状态
            userDao.updateStatus(user);
            return true;
        }else{
            return false;
        }

    }

    @Override
    public User login(User user)
    {
        return userDao.findByUsernameAndPassword(user.getUsername(),user.getPassword());
    }
}
