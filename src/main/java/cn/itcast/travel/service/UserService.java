package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface UserService {
    boolean register(User user);

    boolean activateUser(String code);

    User loginUser(User loginUser);
}
