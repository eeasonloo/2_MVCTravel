package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;
import com.sun.org.apache.xpath.internal.operations.Bool;

public interface UserDao {
    User findByUsername(String username);

    Boolean registerUser(User user);

    User findUserByCode(String code);

    Integer activateUser(User u);

    User findUserByUsernameAndPassword(User loginUser);
}
