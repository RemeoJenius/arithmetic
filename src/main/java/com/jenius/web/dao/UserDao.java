package com.jenius.web.dao;

import com.jenius.web.mate.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by liyongjun on 17/3/23.
 */
public interface UserDao {

    User check(@Param("user") User user);

    List<User> getUserAll();

    User getUserById(int id);

    void updateUserInfo(@Param("user") User user);
}
