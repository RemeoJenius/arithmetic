package com.jenius.web.service.impl;

import com.jenius.web.dao.UserDao;
import com.jenius.web.dao.cahce.RedisDao;
import com.jenius.web.mate.User;
import com.jenius.web.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liyongjun on id7/3/23.
 */
@Service
public class UserServiceImpl implements UserService{

    @Resource
    private UserDao userDao;
    
    @Resource
    private RedisDao redisDao;
    public User check(User user) {

        return userDao.check(user);
    }

    public List<User> getUserAll() {
        return userDao.getUserAll();
    }

    public User getUserById(int id) {
        User user = redisDao.getUseById(id);
        if (user == null) {
            user = userDao.getUserById(id);
            if (user != null){
                String result = redisDao.putUserList(user);
                user = redisDao.getUseById(id);
            }
        }
        return user;
    }

    public void updateUserInfo(User user) {
        userDao.updateUserInfo(user);
    }
}
