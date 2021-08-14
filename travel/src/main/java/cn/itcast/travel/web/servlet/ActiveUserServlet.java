package cn.itcast.travel.web.servlet;

import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1、获取激活码
        String code=request.getParameter("code");
        if(code != null){
            //2、调用servicew完成激活
            UserService userService=new UserServiceImpl();
            boolean flag=userService.active(code);
            String msg="";
            //判断flag
            if(flag){
                    msg="<a href='login.html'>登录</a>!";
            }else{
                //激活失败
                msg="激活失败，请联系公司管理员";
            }
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(msg);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
