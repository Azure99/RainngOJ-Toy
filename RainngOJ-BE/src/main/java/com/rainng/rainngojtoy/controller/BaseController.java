package com.rainng.rainngojtoy.controller;

import com.rainng.jerry.mouse.http.HttpContext;
import com.rainng.jerry.mouse.http.map.HttpSessionMap;
import com.rainng.jerry.webapi.Controller;
import com.rainng.jerry.webapi.annotation.Route;
import com.rainng.jerry.webapi.result.IResult;
import com.rainng.rainngojtoy.manager.UserManager;
import com.rainng.rainngojtoy.manager.annotation.Admin;
import com.rainng.rainngojtoy.manager.annotation.Auth;
import com.rainng.rainngojtoy.models.dto.ResultDTO;

import java.lang.reflect.Method;

@Route("/api/v1")
public class BaseController extends Controller {
    private final UserManager userManager = UserManager.getInstance();

    /**
     * 请求过滤(Auth 与 Admin注解授权验证)
     */
    @Override
    protected IResult beforeExecute(HttpContext context, Method method, Object[] argValues) {
        try {
            Thread.sleep(150);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        boolean needAuth = method.getDeclaredAnnotation(Auth.class) != null;
        if (needAuth && !checkLogin()) {
            return json(new ResultDTO(ResultDTO.NO_LOGIN, "请先登录", null));
        }

        boolean needAdmin = method.getDeclaredAnnotation(Admin.class) != null;
        if (needAdmin && !checkAdmin()) {
            return json(new ResultDTO(ResultDTO.NO_PERMISSION, "您不是管理员", null));
        }

        return null;
    }

    /**
     * 检查是否登录
     *
     * @return 是否登录
     */
    private boolean checkLogin() {
        HttpSessionMap session = getHttpContext().getSession();
        return userManager.getLoginStatus(session).getLogin();
    }

    /**
     * 检查是否为管理员
     *
     * @return 是否为管理员
     */
    private boolean checkAdmin() {
        HttpSessionMap session = getHttpContext().getSession();
        return userManager.getLoginStatus(session).getAdmin();
    }

    /**
     * 构造数据传递DTO
     *
     * @param data 数据
     * @return ResultDTO
     */
    protected ResultDTO result(Object data) {
        return new ResultDTO(ResultDTO.SUCCESS, "success", data);
    }

    /**
     * 构造数据传递DTO
     *
     * @param data    数据
     * @param message 消息
     * @return ResultDTO
     */
    protected ResultDTO result(Object data, String message) {
        return new ResultDTO(ResultDTO.SUCCESS, message, data);
    }

    /**
     * 构造失败数据传递DTO
     *
     * @param message 消息
     * @return ResultDTO
     */
    protected ResultDTO failedResult(String message) {
        return new ResultDTO(ResultDTO.FAIL, message, null);
    }

    /**
     * 构造失败数据传递DTO
     *
     * @param message 消息
     * @param data    数据
     * @return ResultDTO
     */
    protected ResultDTO failedResult(String message, Object data) {
        return new ResultDTO(ResultDTO.FAIL, message, data);
    }

    /**
     * 判断是否为Null或空字符串
     *
     * @param str 待判断字符串
     * @return 是否为Null或空字符串
     */
    protected boolean isNullOrEmpty(String str) {
        return "".equals(str);
    }
}
