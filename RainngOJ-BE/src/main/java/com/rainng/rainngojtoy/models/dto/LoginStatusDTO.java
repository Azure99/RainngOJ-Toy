package com.rainng.rainngojtoy.models.dto;

public class LoginStatusDTO {
    private Boolean login = false;
    private String username = "";
    private Boolean admin = false;

    public LoginStatusDTO() {

    }

    public LoginStatusDTO(Boolean isLogin, String username, Boolean isAdmin) {
        this.login = isLogin;
        this.username = username;
        this.admin = isAdmin;
    }

    public Boolean getLogin() {
        return login;
    }

    public void setLogin(Boolean login) {
        this.login = login;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
