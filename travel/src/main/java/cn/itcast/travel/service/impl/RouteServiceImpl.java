package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    RouteDao routeDao=new RouteDaoImpl();
    @Override
    public PageBean pageQuery(int cid, int currentPage, int pageSize,String rname)
    {
        //准备PageBean对象
        PageBean pb = new PageBean();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示的个数
        pb.setPageSize(pageSize);
        //设置总记录数
        int totalCount = routeDao.findTotalCount(cid,rname);
        pb.setTotalCount(totalCount);
        //设置总页数
        int totalPage = (totalCount % pageSize == 0)?(totalCount / pageSize):((totalCount / pageSize) + 1);//三目运算符
        pb.setTotalPage(totalPage);
        //设置当前页显示的数据集合
        int currentIndex = (currentPage - 1) * pageSize;
        List<Route> routeList = routeDao.findByPage(cid,currentIndex,pageSize,rname);
        pb.setList(routeList);

        return pb;
    }
}
