package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findCategoryList() {

        Jedis jedis = JedisUtil.getJedis();

//        Set<String> categorySetFromRedis = jedis.zrange("categoryList", 0, -1);

        Set<Tuple> tupleSet = jedis.zrangeWithScores("categoryList", 0, -1);

        List<Category> categoryList = null;

        if(tupleSet ==null || tupleSet.size() == 0){
            System.out.println("Search in Mysql Database");
            categoryList = categoryDao.findCategoryList();

            for (int i = 0; i < categoryList.size(); i++)
                jedis.zadd("categoryList", categoryList.get(i).getCid(), categoryList.get(i).getCname());

            }else{
            System.out.println("Get from Redis");
            categoryList =new ArrayList<Category>();

           /* for (String category : categorySetFromRedis) {
                Category categoryExp = new Category();
                categoryExp.setCname(category);
                categoryList.add(categoryExp);
            }*/

            for (Tuple tuple : tupleSet) {
                Category category = new Category();
                category.setCid((int) tuple.getScore());
                category.setCname(tuple.getElement());
                categoryList.add(category);
            }
        }
            return categoryList;
        }


    }



