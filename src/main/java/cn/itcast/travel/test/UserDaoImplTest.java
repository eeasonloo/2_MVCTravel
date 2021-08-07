package cn.itcast.travel.test;

import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;


public class UserDaoImplTest {


    UserDaoImpl userDao = new UserDaoImpl();

    @Test
    public void findByUsername(){
        User user = userDao.findByUsername("LOO");
        System.out.println(user);
    }

    @Test
    public void registerUser(){
        User user = new User();
        user.setUsername("Test");
        user.setPassword("abc");
        user.setEmail("test@123.com");

        userDao.registerUser(user);
    }



}
