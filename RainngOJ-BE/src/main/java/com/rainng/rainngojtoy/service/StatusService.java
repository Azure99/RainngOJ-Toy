package com.rainng.rainngojtoy.service;

import com.rainng.rainngojtoy.dao.SolutionDAO;
import com.rainng.rainngojtoy.dao.UserDAO;
import com.rainng.rainngojtoy.models.constant.JudgeResultCode;
import com.rainng.rainngojtoy.models.dto.NameValueDTO;
import com.rainng.rainngojtoy.models.dto.PageInfoDTO;
import com.rainng.rainngojtoy.models.dto.ResultDTO;
import com.rainng.rainngojtoy.models.dto.StatusSolutionDTO;
import com.rainng.rainngojtoy.models.entity.SolutionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StatusService extends BaseService {
    private static StatusService instance;

    public static StatusService getInstance() {
        return instance;
    }

    @PostConstruct
    public void init() {
        instance = this;
    }

    @Autowired
    private SolutionDAO solutionDAO;
    @Autowired
    private UserDAO userDAO;

    public ResultDTO getPageInfo() {
        return result(new PageInfoDTO(solutionDAO.countSolutions(), solutionDAO.getPageSize()));
    }

    public ResultDTO getStatusPage(Integer pageIndex, Integer problemId, Integer userId, Integer result, Integer language) {
        List<SolutionEntity> solutions = solutionDAO.getSolutionPage(pageIndex, problemId, userId, result, language);
        List<StatusSolutionDTO> dtoList = new ArrayList<>();
        for (SolutionEntity item : solutions) {
            String username = userDAO.getUsernameById(item.getUserId());
            StatusSolutionDTO dto = StatusSolutionDTO.fromSolutionEntity(item, username);
            dtoList.add(dto);
        }

        return result(dtoList);
    }

    public ResultDTO listStatusByIds(Integer[] ids) {
        return result(solutionDAO.listSolutionsByIds(ids));
    }

    public ResultDTO getStatusPieCount() {
        List<Map<String, Object>> countMapList = solutionDAO.getStatusPieCount();

        return result(getStatusPieCountFromMapList(countMapList));
    }

    private NameValueDTO[] getStatusPieCountFromMapList(List<Map<String, Object>> mapList) {
        Long accepted = 0L;
        Long wrong = 0L;
        Long limitExceed = 0L;
        Long exception = 0L;
        Long other = 0L;

        for (Map<String, Object> item : mapList) {
            Integer res = (Integer) item.get("result");
            Long value = (Long) item.get("count");

            switch (res) {
                case JudgeResultCode.ACCEPTED:
                    accepted += value;
                    break;
                case JudgeResultCode.WRONG_ANSWER:
                case JudgeResultCode.PRESENTATION_ERROR:
                    wrong += value;
                    break;
                case JudgeResultCode.TIME_LIMIT_EXCEED:
                case JudgeResultCode.MEMORY_LIMIT_EXCEED:
                case JudgeResultCode.OUTPUT_LIMIT_EXCEED:
                    limitExceed += value;
                    break;
                case JudgeResultCode.COMPILE_ERROR:
                case JudgeResultCode.RUNTIME_ERROR:
                    exception += value;
                    break;
                default:
                    other += value;
            }
        }

        NameValueDTO[] nameValues = new NameValueDTO[5];
        nameValues[0] = new NameValueDTO("错误", wrong);
        nameValues[1] = new NameValueDTO("正确", accepted);
        nameValues[2] = new NameValueDTO("超限", limitExceed);
        nameValues[3] = new NameValueDTO("异常", exception);
        nameValues[4] = new NameValueDTO("其他", other);

        return nameValues;
    }
}
