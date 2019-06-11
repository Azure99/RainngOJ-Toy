package com.rainng.rainngojtoy.controller;

import com.rainng.rainngojtoy.models.dto.ResultDTO;
import com.rainng.rainngojtoy.service.RankService;
import org.springframework.stereotype.Controller;

@Controller
public class RankController extends BaseController {
    private final RankService rankService = RankService.getInstance();

    /**
     * 获取分页信息
     */
    public ResultDTO getPageInfo() {
        return rankService.getPageInfo();
    }

    /**
     * 获取排名页面
     *
     * @param index 页面索引
     */
    public ResultDTO getRankPage(Integer index) {
        return rankService.getRankPage(index);
    }
}
