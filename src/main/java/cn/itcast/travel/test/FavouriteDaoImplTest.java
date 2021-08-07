package cn.itcast.travel.test;

import cn.itcast.travel.dao.FavouriteDao;
import cn.itcast.travel.dao.impl.FaouriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import org.junit.Test;

public class FavouriteDaoImplTest {
    FavouriteDao favouriteDao = new FaouriteDaoImpl();

    @Test
    public void isFavourite(){
        Favorite favorite = favouriteDao.isFavourite(1,13);
        System.out.println(favorite);

    }

    @Test
    public void countFavourite(){
        int count = favouriteDao.countFavourite(1);
        System.out.println(count);
    }


    @Test
    public void addFavourite(){
        favouriteDao.addFavourite(7,13);
    }
}
