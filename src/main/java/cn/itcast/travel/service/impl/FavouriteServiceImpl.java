package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavouriteDao;
import cn.itcast.travel.dao.impl.FaouriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavouriteService;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class FavouriteServiceImpl implements FavouriteService {


    FavouriteDao favouriteDao = new FaouriteDaoImpl();

    @Override
    public Boolean isFavourite(int rid, int uid) {
        Favorite favorite = favouriteDao.isFavourite(rid,uid);

        if(favorite == null){
            return false;
        }
        return true;
    }

    @Override
    public void addFavourite(int rid, int uid) {

        favouriteDao.addFavourite(rid,uid);
    }
}
