package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.RouteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/routeServlet")
public class RouteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数
        String cidStr = request.getParameter("cid");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        //接收rname线路参数
        String rname = request.getParameter("rname");
        rname = new String(rname.getBytes("ISO-8859-1"),"UTF-8");
        //2.处理参数
        //类别id
        int cid = 0;
        if(cidStr != null && cidStr.length()>0)
        {
            //Integer.parseInt()将一个字符串转成int类型数据
            cid = Integer.parseInt(cidStr);
        }
        //当前页码，如果不传，则默认认为是第一页
        int currentPage = 1;
        if(currentPageStr != null && currentPageStr.length()>0)
        {
            currentPage = Integer.parseInt(currentPageStr);
        }
        //每页显示的个数，如果不传，默认每页显示5条记录
        int pageSize = 5;
        if(pageSizeStr != null && pageSizeStr.length()>0)
        {
            pageSize = Integer.parseInt(pageSizeStr);
        }

        //3.调用service查询PageBean对象
        RouteService routeService = new RouteServiceImpl();
        PageBean pb = routeService.pageQuery(cid,currentPage,pageSize,rname);

        //4.将PageBean对象转成json并返回给前端
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=UTF-8");
        mapper.writeValue(response.getOutputStream(),pb);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        this.doGet(request,response);
    }
}
