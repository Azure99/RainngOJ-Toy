package com.rainng.rainngojtoy.models.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("roj_user")
public class UserEntity {
    private static final String ID = "user_id";
    private static final String USERNAME = "user_username";
    private static final String PASSWORD = "user_password";
    private static final String NICKNAME = "user_nickname";
    private static final String EMAIL = "user_email";
    private static final String SCHOOL = "user_school";
    private static final String SUBMIT_COUNT = "user_submit_count";
    private static final String SOLVED_COUNT = "user_solved_count";
    private static final String IS_LOCKED = "user_is_locked";
    private static final String IS_ADMIN = "user_is_admin";

    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @TableField(USERNAME)
    private String username;

    @TableField(PASSWORD)
    private String password;

    @TableField(NICKNAME)
    private String nickname;

    @TableField(EMAIL)
    private String email;

    @TableField(SCHOOL)
    private String school;

    @TableField(SUBMIT_COUNT)
    private Integer submitCount;

    @TableField(SOLVED_COUNT)
    private Integer solvedCount;

    @TableField(IS_LOCKED)
    private Boolean locked;

    @TableField(IS_ADMIN)
    private Boolean admin;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Integer getSubmitCount() {
        return submitCount;
    }

    public void setSubmitCount(Integer submitCount) {
        this.submitCount = submitCount;
    }

    public Integer getSolvedCount() {
        return solvedCount;
    }

    public void setSolvedCount(Integer solvedCount) {
        this.solvedCount = solvedCount;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
