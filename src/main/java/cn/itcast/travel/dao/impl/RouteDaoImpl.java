package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import cn.itcast.travel.util.JedisUtil;
import com.alibaba.druid.util.JdbcUtils;
import jdk.nashorn.internal.ir.CallNode;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {

    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int findTotalCount(int cid, String rname) {
/*        String sql = "select count(*) from tab_route where 1=1";
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();

        if(cid != 0 ){
            sb.append(" and cid = ?");
            params.add(cid);
        }

        if(rname != null && !(rname.equals(""))){
            sb.append(" and rname like ?");
            params.add("%"+rname+"%");
        }

        return template.queryForObject(sb.toString(),Integer.class,params.toArray());*/
        //String sql = "select count(*) from tab_route where cid = ?";
        //1.定义sql模板
        String sql = "select count(*) from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();//条件们
        //2.判断参数是否有值
        if(cid != 0){
            sb.append( " and cid = ? ");

            params.add(cid);//添加？对应的值
        }

        if(rname != null && rname.length() > 0){
            sb.append(" and rname like ? ");

            params.add("%"+rname+"%");
        }

        sql = sb.toString();

        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public List<Route> findByPage(int cid, int pageSize, int start, String rname) {

        String sql = "select * from tab_route where 1=1";
        StringBuilder sb =new StringBuilder(sql);

        List params = new ArrayList();

        if(cid !=0){
            sb.append(" and cid = ?");
            params.add(cid);
        }

        if(rname!=null && !(rname.equals(""))){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }

        params.add(start);
        params.add(pageSize);
        sb.append(" limit ?,?");

        /*System.out.println(sb.toString());
        for (Object param : params) {
            System.out.println("1 :" + param);
        }*/
        sql = sb.toString();

        List<Route> routeList=  template.query(sql,new BeanPropertyRowMapper<Route>(Route.class),params.toArray());
        for (Route route : routeList) {
            System.out.println(route);
        }
        return routeList;
    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);
    }
}
