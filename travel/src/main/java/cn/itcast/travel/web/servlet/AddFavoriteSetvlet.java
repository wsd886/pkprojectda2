package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/addFavoriteServlet")
public class AddFavoriteSetvlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取线路rid
        String rid=request.getParameter("rid");
        //2、获取当前登录用户信息
        User user = (User)request.getSession().getAttribute("user");
        int uid;
        if(user == null){
            return;
        }else{
            //用户已经登陆额
            uid=user.getUid();

        }
        //调用service
        FavoriteService favoriteService=new FavoriteServiceImpl();
        favoriteService.add(Integer.parseInt(rid),uid);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
