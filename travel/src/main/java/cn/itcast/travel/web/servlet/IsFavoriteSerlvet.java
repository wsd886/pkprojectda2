package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;

@WebServlet("/isFavoriteServlet")
public class IsFavoriteSerlvet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取线路rid
        String rid = request.getParameter("rid");
        //获取当前登录的用户user
        User user=(User)request.getSession().getAttribute("user");
        int uid;
        if(user == null){
            //用户尚未登录
            uid = 0;
        }else {
            //用户已经登录
            uid = user.getUid();
        }
        //调用service查询是否收藏
        FavoriteService favoriteService = new FavoriteServiceImpl();
        boolean flag = favoriteService.isFavorite(Integer.parseInt(rid),uid);
        //将FLAG写回客户端
        ResultInfo resultInfo = new ResultInfo();
        resultInfo.setFlag(flag);

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(response.getOutputStream(),resultInfo);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
