package com.jenius.web.service;

import com.jenius.web.mate.User;

import java.util.List;

/**
 * Created by liyongjun on 17/3/23.
 */
public interface UserService {
    User check(User user);

    List<User> getUserAll();

    User getUserById(int id);

    void updateUserInfo(User user);
}
