package cn.itcast.travel.test;

import cn.itcast.travel.dao.impl.SellerDaoImpl;
import cn.itcast.travel.domain.Seller;
import org.junit.Test;

public class SellerDaoImplTest {

    private SellerDaoImpl sellerDao = new SellerDaoImpl();

    @Test
    public void findOne(){
        Seller seller = sellerDao.findOne(1);
        System.out.println(seller);
    }
}
