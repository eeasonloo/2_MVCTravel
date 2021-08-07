package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

public class UserServiceImpl implements UserService {

    private UserDao userDao= new UserDaoImpl();
    @Override
    public boolean register(User user) {
        // Check Username Exist or not
        User u = userDao.findByUsername(user.getUsername());

        if(u != null){
            return false;
        }

        // Insert User Info
        user.setCode(UuidUtil.getUuid());
        user.setStatus("N");

       /* String content ="<a href ='http://localhost/travel/user/active?code='" + user.getCode() + "> click this link to activate </a>";
        MailUtils.sendMail(user.getEmail(),content, "Activation Mail");*/

        System.out.println("http://localhost/travel/user/activate?code=" + user.getCode());

        Boolean flag = userDao.registerUser(user);

        return true;
    }

    @Override
    public boolean activateUser(String code) {
        int i = 0;

        // 1. findUserByCode
        User u =userDao.findUserByCode(code);

        // 2. set User.status to "Y"
        if(u!=null){
            i =userDao.activateUser(u);
        }

        if(i > 0){
            return true;
        }
        return false;
    }

    @Override
    public User loginUser(User loginUser) {

        User loginReturnUser = userDao.findUserByUsernameAndPassword(loginUser);
        return loginReturnUser;
    }
}
