package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //0.验证码校验
        String check = request.getParameter("check");//拿到用户输入的验证码
        //从session中获取验证码
        HttpSession session = request.getSession();
        String check_server = (String)session.getAttribute("CHECKCODE_SERVER");//代码生成的验证码
        //移除session中的验证码，为了保存只能使用一次，安全
        session.removeAttribute("CHECKCODE_SERVER");
        //比较，比较用户输入的验证码和代码生成的验证码是否一致
        if(check_server == null || !check_server.equalsIgnoreCase(check))//equalsIgnoreCase方法判断字符串内容是否相等，忽略大小写
        {
            //验证码错误
            ResultInfo resultInfo = new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误！");
            //将info对象转成json
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("application/json;charset=UTF-8");
            mapper.writeValue(response.getOutputStream(),resultInfo);
            return;
        }

        //1.获取用户名和密码数据
        Map<String, String[]> map = request.getParameterMap();
        //封装User对象
        User user = new User();
        try
        {
            BeanUtils.populate(user,map);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //2.调用service查询
        UserService userService = new UserServiceImpl();
        User u = userService.login(user);

        ResultInfo resultInfo = new ResultInfo();
        //3.判断用户是否存在
        if(u == null)
        {
            //用户名或密码错误或未注册
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("用户名或密码错误！");
        }
        //4.判断用户是否激活
        if(u != null && "N".equals(u.getStatus()))
        {
            //用户未激活
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("您尚未激活，请登录邮箱激活！");
        }
        //5.判断登录成功
        if(u != null && "Y".equals(u.getStatus()))
        {
            request.getSession().setAttribute("user",u);
            //登录成功
            resultInfo.setFlag(true);
        }
        //6.响应数据
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(response.getOutputStream(),resultInfo);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.doGet(request,response);
    }
}