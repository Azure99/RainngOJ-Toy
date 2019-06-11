package com.rainng.rainngojtoy.controller;

import com.alibaba.fastjson.JSONObject;
import com.rainng.rainngojtoy.manager.annotation.Admin;
import com.rainng.rainngojtoy.manager.annotation.Auth;
import com.rainng.rainngojtoy.models.dto.ResultDTO;
import com.rainng.rainngojtoy.models.entity.ProblemEntity;
import com.rainng.rainngojtoy.service.ProblemService;
import org.springframework.stereotype.Controller;

@Controller
public class ProblemController extends BaseController {
    private final ProblemService problemService = ProblemService.getInstance();

    /**
     * 获取分页信息
     */
    public ResultDTO getPageInfo() {
        return problemService.getPageInfo();
    }

    /**
     * 获取题目页面
     *
     * @param index 页面索引
     */
    public ResultDTO getProblemPage(Integer index) {
        return problemService.getProblemPage(index);
    }

    /**
     * 获取题目
     *
     * @param id 题目Id
     */
    @Auth
    public ResultDTO getProblem(Integer id) {
        return problemService.getProblemById(id);
    }

    /**
     * 更新题目
     *
     * @param data 请求数据
     */
    @Admin
    public ResultDTO updateProblem(JSONObject data) {
        ProblemEntity problem = data.toJavaObject(ProblemEntity.class);

        if (problem.getId() == null || problem.getTitle() == null || problem.getDescription() == null ||
                problem.getInput() == null || problem.getOutput() == null || problem.getTimeLimit() == null ||
                problem.getMemoryLimit() == null || problem.getDbJudge() == null || problem.getSampleInput() == null ||
                problem.getSampleOutput() == null) {
            return failedResult("参数不能为空");
        }

        if (problem.getTimeLimit() <= 0 || problem.getMemoryLimit() <= 0) {
            return failedResult("时间和内存限制必须大于等于0");
        }

        return problemService.updateProblem(problem);
    }
}
