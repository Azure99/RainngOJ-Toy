package com.rainng.rainngojtoy.service;

import com.rainng.jerry.mouse.http.map.HttpSessionMap;
import com.rainng.rainngojtoy.dao.UserDAO;
import com.rainng.rainngojtoy.manager.UserManager;
import com.rainng.rainngojtoy.models.dto.ResultDTO;
import com.rainng.rainngojtoy.models.dto.TopUserDTO;
import com.rainng.rainngojtoy.models.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService extends BaseService {
    private static UserService instance;

    public static UserService getInstance() {
        return instance;
    }

    @PostConstruct
    public void init() {
        instance = this;
    }

    @Autowired
    private UserManager userManager;
    @Autowired
    private UserDAO userDAO;

    public ResultDTO getUserById(Integer id) {
        return result(userDAO.getUserById(id));
    }

    public ResultDTO listTopUsers() {
        List<UserEntity> entities = userDAO.listTopUsers();

        List<TopUserDTO> dtoList = new ArrayList<>();
        for (int i = 0; i < entities.size(); i++) {
            UserEntity entity = entities.get(i);

            TopUserDTO dto = new TopUserDTO();
            dto.setRank(i + 1);
            dto.setUsername(entity.getUsername());
            dto.setSolvedCount(entity.getSolvedCount());

            dtoList.add(dto);
        }

        return result(dtoList);
    }

    public ResultDTO login(HttpSessionMap session, String username, String password) {
        UserEntity userEntity = userDAO.getUserByUsername(username);
        if (userEntity == null) {
            return failedResult("用户不存在");
        }
        if (userEntity.getLocked()) {
            return failedResult("用户被锁定");
        }
        String hashPassword = userManager.hashPassword(password);
        if (!hashPassword.equals(userEntity.getPassword())) {
            return failedResult("密码错误");
        }

        userManager.setUserStatus(session, userEntity.getId(), username, userEntity.getAdmin());

        return result(userManager.getLoginStatus(session));
    }

    public ResultDTO getLoginStatus(HttpSessionMap session) {
        return result(userManager.getLoginStatus(session));
    }

    public ResultDTO logout(HttpSessionMap session) {
        if (userManager.removeLoginStatus(session)) {
            return result("注销成功");
        }

        return result("您已经离线, 无需注销");
    }

    public ResultDTO register(String username, String password, String nickname, String email, String school) {
        if (userDAO.getUserByUsername(username) != null) {
            return failedResult("用户名已存在");
        }

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(userManager.hashPassword(password));
        user.setNickname(nickname);
        user.setEmail(email);
        user.setSchool(school);

        try {
            userDAO.insertUser(user);
        } catch (Exception ex) {
            return failedResult("注册失败: " + ex.getMessage());
        }

        return result("注册成功");
    }

    public ResultDTO lockOrUnlock(Integer id) {
        UserEntity user = userDAO.getUserById(id);
        if (user == null) {
            return failedResult("用户不存在");
        }

        user.setLocked(!user.getLocked());
        userDAO.updateUser(user);

        return result(user.getLocked() ? "locked" : "unlocked");
    }
}
