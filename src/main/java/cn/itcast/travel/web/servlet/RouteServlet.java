package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavouriteService;
import cn.itcast.travel.service.impl.FavouriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {

    private RouteService routeService = new RouteServiceImpl();
    private FavouriteService favouriteService = new FavouriteServiceImpl() ;

    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cidStr = request.getParameter("cid");
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");

        String rname = request.getParameter("rname");
        rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
        System.out.println(rname);


        int cid = 0;
        int currentPage = 1;
        int pageSize = 5;

        if(cidStr != null && !(cidStr.equals("")) && cidStr.length()>0){
            cid = Integer.parseInt(cidStr);
        }
        if(currentPageStr != null && !(currentPageStr.equals("")) && currentPageStr.length()>0){
            currentPage = Integer.parseInt(currentPageStr);
        }
        if(pageSizeStr != null && !(pageSizeStr.equals("")) && pageSizeStr.length()>0){
            pageSize = Integer.parseInt(pageSizeStr);
        }

        PageBean<Route> pageBean = routeService.pageQuery(cid,currentPage,pageSize, rname);

        writeValue(pageBean,response);

    }

    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Route route = new Route();
        String rid = request.getParameter("rid");
        route = routeService.findOne(rid);
        writeValue(route,response);

    }

    public void isFavourite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String rid = request.getParameter("rid");

        User loginUser = (User) request.getSession().getAttribute("loginUser");

        Boolean isFavourite = false;


        if(loginUser!=null){
            int uid = loginUser.getUid();
            isFavourite =favouriteService.isFavourite(Integer.parseInt(rid),uid);
        }

        writeValue(isFavourite,response);
    }

    public void addFavourite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");

        User loginUser = (User) request.getSession().getAttribute("loginUser");

        if(loginUser!=null){
            int uid = loginUser.getUid();
            favouriteService.addFavourite(Integer.parseInt(rid),uid);
        }else{
            return;
        }
    }
}
