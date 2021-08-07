package cn.itcast.travel.test;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import org.junit.Test;

import java.util.List;

public class CategoryDaoImplTest {

    @Test
    public void findCategoryListTest(){
        CategoryDao categoryDao = new CategoryDaoImpl();
        List<Category> list = categoryDao.findCategoryList();
        System.out.println(list);
    }
}
