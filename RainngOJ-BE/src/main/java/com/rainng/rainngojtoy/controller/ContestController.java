package com.rainng.rainngojtoy.controller;

import com.rainng.rainngojtoy.models.dto.ResultDTO;
import com.rainng.rainngojtoy.service.ContestService;
import org.springframework.stereotype.Controller;

@Controller
public class ContestController extends BaseController {
    private final ContestService contestService = ContestService.getInstance();

    /**
     * 获取分页信息
     */
    public ResultDTO getPageInfo() {
        return contestService.getPageInfo();
    }

    /**
     * 获取竞赛页面
     *
     * @param index 页面索引
     */
    public ResultDTO getContestPage(Integer index) {
        return contestService.getContestPage(index);
    }

    /**
     * 获取竞赛信息
     *
     * @param id 竞赛Id
     */
    public ResultDTO getContest(Integer id) {
        return contestService.getContest(id);
    }
}
