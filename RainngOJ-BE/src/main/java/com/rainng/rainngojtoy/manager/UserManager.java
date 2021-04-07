package com.rainng.rainngojtoy.manager;

import com.rainng.jerry.mouse.http.map.HttpSessionMap;
import com.rainng.rainngojtoy.models.dto.LoginStatusDTO;
import com.rainng.rainngojtoy.models.pojo.UserStatusPOJO;
import com.rainng.rainngojtoy.util.Md5Encrypt;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class UserManager {
    private static final String USER_PASSWORD_SALT = "__Rai96n1n5g234OJ04_";
    private static final String SESSION_USER_STATUS = "userStatus";

    private static UserManager instance;

    public static UserManager getInstance() {
        return instance;
    }

    @PostConstruct
    public void init() {
        instance = this;
    }

    public void setUserStatus(HttpSessionMap session, Integer userId, String username, Boolean isAdmin) {
        UserStatusPOJO status = new UserStatusPOJO();
        status.setUserId(userId);
        status.setUsername(username);
        status.setAdmin(isAdmin);

        session.put(SESSION_USER_STATUS, status);
    }

    public LoginStatusDTO getLoginStatus(HttpSessionMap session) {
        LoginStatusDTO status = new LoginStatusDTO();
        if (!session.containsKey(SESSION_USER_STATUS)) {
            return status;
        }

        UserStatusPOJO userStatusPOJO = (UserStatusPOJO) session.get(SESSION_USER_STATUS);
        status.setLogin(true);
        status.setUsername(userStatusPOJO.getUsername());
        status.setAdmin(userStatusPOJO.getAdmin());

        return status;
    }

    public UserStatusPOJO getUserStatus(HttpSessionMap session) {
        if (session.containsKey(SESSION_USER_STATUS)) {
            return (UserStatusPOJO) session.get(SESSION_USER_STATUS);
        }

        return new UserStatusPOJO();
    }

    public boolean removeLoginStatus(HttpSessionMap session) {
        if (!session.containsKey(SESSION_USER_STATUS)) {
            return false;
        }

        session.remove(SESSION_USER_STATUS);
        return true;
    }

    public String hashPassword(String password) {
        String hashedPwd = Md5Encrypt.toHexString(password);
        hashedPwd = Md5Encrypt.toHexString(hashedPwd + USER_PASSWORD_SALT);

        return hashedPwd;
    }
}
