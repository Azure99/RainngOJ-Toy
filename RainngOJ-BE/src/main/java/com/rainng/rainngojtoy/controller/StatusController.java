package com.rainng.rainngojtoy.controller;

import com.rainng.rainngojtoy.models.dto.ResultDTO;
import com.rainng.rainngojtoy.service.StatusService;
import org.springframework.stereotype.Controller;

@Controller
public class StatusController extends BaseController {
    private StatusService statusService = StatusService.getInstance();

    /**
     * 获取分页信息
     */
    public ResultDTO getPageInfo(Integer problemId, String username, Integer result, Integer language) {
        return statusService.getPageInfo(problemId, username, result, language);
    }

    /**
     * 获取状态页面(搜索)
     *
     * @param index     页面索引
     * @param problemId 题目Id
     * @param username    用户名
     * @param result    评测结果
     * @param language  语言
     */
    public ResultDTO getStatusPage(Integer index, Integer problemId, String username, Integer result, Integer language) {
        return statusService.getStatusPage(index, problemId, username, result, language);
    }

    /**
     * 根据Id列表获取状态列表
     *
     * @param ids Id列表
     */
    public ResultDTO getStatusByIds(String ids) {
        String[] idsSplit = ids.split(",");
        Integer[] idArray = new Integer[idsSplit.length];
        for (int i = 0; i < idsSplit.length; i++) {
            idArray[i] = Integer.valueOf(idsSplit[i]);
        }

        return statusService.listStatusByIds(idArray);
    }

    /**
     * 获取状态饼图统计信息
     */
    public ResultDTO getStatusPieCount() {
        return statusService.getStatusPieCount();
    }
}
