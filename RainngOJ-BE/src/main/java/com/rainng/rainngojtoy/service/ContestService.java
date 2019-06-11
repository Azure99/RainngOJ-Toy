package com.rainng.rainngojtoy.service;

import com.rainng.rainngojtoy.dao.ContestDAO;
import com.rainng.rainngojtoy.dao.ContestProblemDAO;
import com.rainng.rainngojtoy.dao.ProblemDAO;
import com.rainng.rainngojtoy.models.dto.ContestDTO;
import com.rainng.rainngojtoy.models.dto.PageInfoDTO;
import com.rainng.rainngojtoy.models.dto.ResultDTO;
import com.rainng.rainngojtoy.models.entity.ContestEntity;
import com.rainng.rainngojtoy.models.entity.ContestProblemEntity;
import com.rainng.rainngojtoy.models.entity.ProblemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Service
public class ContestService extends BaseService {
    private static ContestService instance;

    public static ContestService getInstance() {
        return instance;
    }

    @PostConstruct
    public void init() {
        instance = this;
    }

    @Autowired
    private ContestDAO contestDAO;
    @Autowired
    private ContestProblemDAO contestProblemDAO;
    @Autowired
    private ProblemDAO problemDAO;

    public ResultDTO getPageInfo() {
        return result(new PageInfoDTO(contestDAO.countContests(), contestDAO.getPageSize()));
    }

    public ResultDTO getContestPage(Integer pageIndex) {
        return result(contestDAO.getContestPage(pageIndex));
    }

    public ResultDTO getContest(Integer id) {
        ContestEntity contest = contestDAO.getContestById(id);
        if (contest == null) {
            return failedResult("作业" + id + "不存在");
        }

        Date now = new Date();
        if (now.before(contest.getStartTime()) || now.after(contest.getEndTime())) {
            return failedResult("当前不是作业时间");
        }

        List<ContestProblemEntity> contestProblems = contestProblemDAO.listContestProblemsByContestId(id);
        Integer[] problemIds = new Integer[contestProblems.size()];
        for (int i = 0; i < problemIds.length; i++) {
            problemIds[i] = contestProblems.get(i).getProblemId();
        }

        List<ProblemEntity> problems = problemDAO.listProblemsByIds(problemIds);


        return result(ContestDTO.from(contest, problems));
    }
}
