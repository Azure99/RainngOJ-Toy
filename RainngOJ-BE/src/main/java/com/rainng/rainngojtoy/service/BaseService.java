package com.rainng.rainngojtoy.service;

import com.rainng.rainngojtoy.models.dto.ResultDTO;
import org.springframework.stereotype.Service;

@Service
public class BaseService {
    protected ResultDTO result(Object data) {
        return new ResultDTO(ResultDTO.SUCCESS, "success", data);
    }

    protected ResultDTO result(Object data, String message) {
        return new ResultDTO(ResultDTO.SUCCESS, message, data);
    }

    protected ResultDTO failedResult(String message) {
        return new ResultDTO(ResultDTO.FAIL, message, null);
    }

    protected ResultDTO failedResult(String message, Object data) {
        return new ResultDTO(ResultDTO.FAIL, message, data);
    }

    protected boolean isNullOrEmpty(String str) {
        return "".equals(str);
    }
}
