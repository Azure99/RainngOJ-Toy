package com.rainng.rainngojtoy.controller;

import com.alibaba.fastjson.JSONObject;
import com.rainng.rainngojtoy.manager.JudgeManager;
import com.rainng.rainngojtoy.models.dto.JudgeResultDTO;
import com.rainng.rainngojtoy.models.dto.ResultDTO;
import com.rainng.rainngojtoy.service.JudgeService;
import org.springframework.stereotype.Controller;

@Controller
public class JudgeController extends BaseController {
    private final JudgeManager judgeManager = JudgeManager.getInstance();
    private final JudgeService judgeService = JudgeService.getInstance();

    /**
     * 评测机获取Pending的提交列表
     *
     * @param data 请求数据
     */
    public ResultDTO getPending(JSONObject data) {
        if (!judgeManager.authJudger(data)) {
            return wrongTokenResult();
        }

        return judgeService.getPending();
    }

    /**
     * 评测机获取题目测试数据
     *
     * @param data 请求数据
     */
    public ResultDTO getTestData(JSONObject data) {
        if (!judgeManager.authJudger(data)) {
            return wrongTokenResult();
        }

        // 日后实现
        return failedResult("No test data");
    }

    /**
     * 评测机提交评测结果
     *
     * @param data 请求数据
     */
    public ResultDTO submitResult(JSONObject data) {
        if (!judgeManager.authJudger(data)) {
            return wrongTokenResult();
        }

        JSONObject result = data.getJSONObject("result");
        JudgeResultDTO resultDTO = result.toJavaObject(JudgeResultDTO.class);

        return result(judgeService.submitResult(resultDTO));
    }

    private ResultDTO wrongTokenResult() {
        return new ResultDTO(ResultDTO.WRONG_TOKEN, "Wrong token", null);
    }
}
