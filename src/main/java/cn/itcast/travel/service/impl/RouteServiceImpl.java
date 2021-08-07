package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavouriteDao;
import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.dao.impl.FaouriteDaoImpl;
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

    private RouteDao routeDao = new RouteDaoImpl();
    private SellerDao sellerDao = new SellerDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private FavouriteDao favouriteDao = new FaouriteDaoImpl();


    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        PageBean<Route> pageBean = new PageBean<>();

        int totalCount = routeDao.findTotalCount(cid,rname);
        int totalPage = totalCount % pageSize == 0 ? totalCount/pageSize : totalCount/pageSize+1;
        int start = (currentPage-1) * pageSize;
        List<Route> routeList = routeDao.findByPage(cid,pageSize,start,rname);

        pageBean.setTotalCount(totalCount);
        pageBean.setTotalPage(totalPage);
        pageBean.setCurrentPage(currentPage);
        pageBean.setPageSize(pageSize);
        pageBean.setList(routeList);

        return pageBean;
    }

    @Override
    public Route findOne(String rid) {
        //1.查tab_route
        Route route = routeDao.findOne(Integer.parseInt(rid));

        //2.用route.sid,查tab_seller
        Seller seller = sellerDao.findOne(route.getSid());
        route.setSeller(seller);

        //3.用route.rid,查tab_routeimg
        List<RouteImg> routeImgList = routeImgDao.findAll(route.getRid());
        route.setRouteImgList(routeImgList);

        //4.用route.rid,查tab_favorite(count*)
        int count = favouriteDao.countFavourite(Integer.parseInt(rid));
        route.setCount(count);
        return route;
    }
}
