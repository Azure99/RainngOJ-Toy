package com.rainng.rainngojtoy.models.dto;

import java.io.Serializable;

public class ResultDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final int NO_LOGIN = -1;
    public static final int SUCCESS = 0;
    public static final int FAIL = 1;
    public static final int NO_PERMISSION = 2;
    public static final int WRONG_TOKEN = 10;
    public static final int NO_TASK = 11;

    private Integer code;
    private String message;
    private Object data;

    public ResultDTO() {

    }

    public ResultDTO(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
