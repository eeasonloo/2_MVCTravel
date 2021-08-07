package cn.itcast.travel.test;

import cn.itcast.travel.dao.RouteImgDao;
import cn.itcast.travel.dao.impl.RouteImgDaoImpl;
import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import org.junit.Test;

import java.util.List;

public class RouteImgDaoImplTest {

    private RouteImgDao routeImgDao = new RouteImgDaoImpl();

    @Test
    public void findOne(){
        List<RouteImg> routeImgList = routeImgDao.findAll(1);
        System.out.println(routeImgList);
    }
}
