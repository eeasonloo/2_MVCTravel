package cn.itcast.travel.test;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.Route;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class RouteDaoImplTest {

    private RouteDao routeDao = new RouteDaoImpl();

    @Test
    public void findTotalCount(){
       int count =  routeDao.findTotalCount(5,"sad");
        System.out.println(count);

    }

    @Test
    public void findByPage() throws UnsupportedEncodingException {

        List<Route> routeList = routeDao.findByPage(5,5,1,"500");

        for (Route route : routeList) {
            System.out.println(route);
        }

    }

    @Test
    public void findOne(){
        Route route = routeDao.findOne(1);
        System.out.println(route);
    }
}
