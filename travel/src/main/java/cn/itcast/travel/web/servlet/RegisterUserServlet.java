package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.util.Map;

@WebServlet("/registerUserServlet")
public class RegisterUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //验证码校验
        String check=request.getParameter("check");//拿到用户输入的验证码
        //从session中获取验证码
        HttpSession session=request.getSession();
        String check_server=(String)session.getAttribute("CHECKCODE_SERVER");
        //移除session中的验证码，为了安全只能保存一次，安全
        session.removeAttribute("CHECKCODE_SERVER");
        if(check_server == null || !check_server.equalsIgnoreCase(check)){       //equalsIgnoreCase方法判断字符串内容是否相等，忽略大小写
            //验证码错误
            ResultInfo resultInfo=new ResultInfo();
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("验证码错误");
            ObjectMapper mapper = new ObjectMapper();
            response.setContentType("application/json;charset=UTF-8");
            mapper.writeValue(response.getOutputStream(),resultInfo);
            return;
        }
        //1、获取数据
        Map<String,String[]> map = request.getParameterMap();
        //2、封装user对象
        User user=new User();
        try {
            BeanUtils.populate(user,map);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //3、调用service完成注册
        UserService userService = new UserServiceImpl();
        boolean flag = userService.registerUser(user);

        //4、响应结果
        ResultInfo resultInfo = new ResultInfo();
        if(flag){
            //注册成功
            resultInfo.setFlag(true);
        }else{
            //注册失败
            resultInfo.setFlag(false);
            resultInfo.setErrorMsg("注册失败");
        }
        //将info对象转换成json
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(response.getOutputStream(),resultInfo);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       this.doGet(request, response);
    }
}
