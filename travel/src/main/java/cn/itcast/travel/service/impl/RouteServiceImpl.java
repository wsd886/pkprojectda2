package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import cn.itcast.travel.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private RouteImgDao routeImgDao=new RouteImgDaoImpl();
    private SellerDao sellerDao=new SellerDaoImpl();
    private RouteDao routeDao=new RouteDaoImpl();
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

    @Override
    public Route findOne(int rid) {
        //1.根据rid去tab_route表查询route对象
        Route route = routeDao.findOne(rid);
        //2.根据rid去tab_route_img表查询图片集合
        List<RouteImg> routeImgList = routeImgDao.findByRid(rid);
        //2.1、将集合设置到route对象中
        route.setRouteImgList(routeImgList);
        //3.根据sid去tab_seller表查询seller对象
        Seller seller = sellerDao.findBySid(route.getSid());
        //3.1、将卖家对象设置到route对象中
        route.setSeller(seller);

        return route;
    }
}
