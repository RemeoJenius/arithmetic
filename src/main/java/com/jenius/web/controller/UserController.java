package com.jenius.web.controller;

import com.jenius.web.service.impl.UserServiceImpl;
import com.jenius.web.mate.User;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liyongjun on 17/3/23.
 */
@Api(value = "user")
@Controller
public class UserController {
    @Resource(name = "userServiceImpl")
    private UserServiceImpl userServiceImpl;

    @RequestMapping("login")
    @ResponseBody
    public Map login(@ModelAttribute("user") User user, HttpSession session) {

        Map<String, Object> map = new HashMap<String, Object>();
        User u = userServiceImpl.check(user);
        if (u != null) {
            session.setAttribute("user", u);
            map.put("msg", "登陆成功");
            map.put("user", user);
        } else {
            map.put("msg", "用户名或密码错误");
        }
//        return userServiceImpl.check(user);

        return map;
    }

    @RequestMapping("getUserAll")
    @ResponseBody
    public List<User> getUserAll(HttpServletResponse response) {
        response.addHeader("Access-Control-Allow-Origin","127.0.0.1");
        return userServiceImpl.getUserAll();
    }

    @RequestMapping("getUserById")
    @ResponseBody
    @ApiOperation(value = "根据ID获取用户信息", httpMethod = "GET", notes = "get user by id", response = User.class)
    public User getUserById(@ApiParam(required = true, value = "用户ID", name = "userId") @RequestParam(value = "id") Integer id) {
        return userServiceImpl.getUserById(id);
    }

    @RequestMapping("updateUserInfo")
    @ResponseBody
    public Map<String,Object> updateUserInfo(HttpSession session ,@ModelAttribute("user") User user){
        Map<String,Object> map = new HashMap<String,Object>();
        User user1 = (User)session.getAttribute("user");
        user.setId(user1.getId());
        userServiceImpl.updateUserInfo(user);
        map.put("success","success");
        return map;
    }
    @RequestMapping("initData")
    @ResponseBody
    public Map<String,Object> initData(HttpSession session){
        Map<String,Object> map = new HashMap<String,Object>();
        User user = (User) session.getAttribute("user");
        if(user != null){
            System.out.println(user.getUsername());
            map.put("user",user);
        }
        return map;
    }
}
