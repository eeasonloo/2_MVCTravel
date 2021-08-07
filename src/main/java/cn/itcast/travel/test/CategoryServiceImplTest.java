package cn.itcast.travel.test;

import cn.itcast.travel.service.impl.CategoryServiceImpl;
import org.junit.Test;

public class CategoryServiceImplTest {

    @Test
    public void readFromRedis(){
        CategoryServiceImpl cs = new CategoryServiceImpl();

        cs.findCategoryList();

    }

}
