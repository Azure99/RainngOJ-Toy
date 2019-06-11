package com.rainng.rainngojtoy.controller;

import com.alibaba.fastjson.JSONObject;
import com.rainng.jerry.mouse.http.map.HttpSessionMap;
import com.rainng.jerry.webapi.annotation.Post;
import com.rainng.rainngojtoy.manager.annotation.Admin;
import com.rainng.rainngojtoy.models.dto.ResultDTO;
import com.rainng.rainngojtoy.service.UserService;
import org.springframework.stereotype.Controller;

@Controller
public class UserController extends BaseController {
    private final UserService userService = UserService.getInstance();

    public ResultDTO getTopUsers() {
        return userService.listTopUsers();
    }

    /**
     * 用户登录
     *
     * @param data 请求数据
     */
    @Post
    public ResultDTO login(JSONObject data) {
        String username = data.getString("username");
        String password = data.getString("password");

        if (isNullOrEmpty(username) || isNullOrEmpty(password)) {
            return failedResult("用户名或密码不能为空");
        }

        HttpSessionMap session = getHttpContext().getSession();
        return userService.login(session, username, password);
    }

    /**
     * 获取用户登录状态
     */
    public ResultDTO getLoginStatus() {
        HttpSessionMap session = getHttpContext().getSession();
        return userService.getLoginStatus(session);
    }

    /**
     * 用户注销
     */
    public ResultDTO logout() {
        HttpSessionMap session = getHttpContext().getSession();
        return userService.logout(session);
    }

    /**
     * 用户注册
     *
     * @param data 请求数据
     */
    @Post
    public ResultDTO register(JSONObject data) {
        String username = data.getString("username");
        String password = data.getString("password");
        String nickname = data.getString("nickname");
        String email = data.getString("email");
        String school = data.getString("school");

        if (isNullOrEmpty(username) || isNullOrEmpty(password) || isNullOrEmpty(email)) {
            return failedResult("用户名或密码不能为空");
        }

        if (username.length() < 3 || password.length() < 6) {
            return failedResult("用户名或密码过短");
        }

        if (username.length() > 20 || password.length() > 32) {
            return failedResult("用户名或密码长度超限");
        }

        if (nickname.length() > 50 || email.length() > 50 || school.length() > 50) {
            return failedResult("字段长度超限");
        }

        return userService.register(username, password, nickname, email, school);
    }

    /**
     * 锁定或解锁用户
     *
     * @param id 用户Id
     */
    @Admin
    public ResultDTO lockOrUnlock(Integer id) {
        return userService.lockOrUnlock(id);
    }
}
