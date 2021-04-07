package com.rainng.rainngojtoy.controller;

import com.alibaba.fastjson.JSONObject;
import com.rainng.jerry.mvc.annotation.HttpPost;
import com.rainng.rainngojtoy.manager.LanguageManager;
import com.rainng.rainngojtoy.manager.UserManager;
import com.rainng.rainngojtoy.manager.annotation.Auth;
import com.rainng.rainngojtoy.models.dto.ResultDTO;
import com.rainng.rainngojtoy.service.SolutionService;
import org.springframework.stereotype.Controller;

@Controller
public class SolutionController extends BaseController {
    private final UserManager userManager = UserManager.getInstance();
    private final LanguageManager languageManager = LanguageManager.getInstance();
    private final SolutionService solutionService = SolutionService.getInstance();

    /**
     * 提交代码
     *
     * @param data 请求数据
     */
    @Auth
    @HttpPost
    public ResultDTO submitSolution(JSONObject data) {
        Integer problemId = data.getInteger("problemId");
        String sourceCode = data.getString("sourceCode");
        Integer language = data.getInteger("language");
        Integer userId = userManager.getUserStatus(getHttpContext().getSession()).getUserId();

        if (problemId == null || language == null) {
            return failedResult("题目Id和语言不能为空");
        }
        if (isNullOrEmpty(sourceCode)) {
            return failedResult("源代码不能为空");
        }
        if (userId <= 0) {
            return failedResult("用户名错误");
        }
        if (!languageManager.containsLang(language)) {
            return failedResult("编程语言不存在");
        }

        return solutionService.submitSolution(problemId, userId, language, sourceCode);
    }

    /**
     * 获取提交错误
     *
     * @param id 提交Id
     */
    public ResultDTO getError(Integer id) {
        return solutionService.getError(id);
    }

    /**
     * 获取提交
     *
     * @param id 提交Id
     */
    @Auth
    public ResultDTO getSolution(Integer id) {
        Integer userId = userManager.getUserStatus(getHttpContext().getSession()).getUserId();
        Boolean isAdmin = userManager.getUserStatus(getHttpContext().getSession()).getAdmin();
        if (userId <= 0) {
            return failedResult("当前登录账户不是提交者");
        }

        return solutionService.getSolution(id, userId, isAdmin);
    }
}
