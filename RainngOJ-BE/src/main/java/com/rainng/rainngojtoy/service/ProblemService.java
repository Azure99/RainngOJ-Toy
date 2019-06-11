package com.rainng.rainngojtoy.service;

import com.rainng.rainngojtoy.dao.ProblemDAO;
import com.rainng.rainngojtoy.models.dto.PageInfoDTO;
import com.rainng.rainngojtoy.models.dto.ResultDTO;
import com.rainng.rainngojtoy.models.entity.ProblemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ProblemService extends BaseService {
    private static ProblemService instance;

    public static ProblemService getInstance() {
        return instance;
    }

    @PostConstruct
    public void init() {
        instance = this;
    }

    @Autowired
    private ProblemDAO problemDAO;

    public ResultDTO getPageInfo() {
        PageInfoDTO pageInfoDTO = new PageInfoDTO(problemDAO.countProblems(), problemDAO.getPageSize());
        return result(pageInfoDTO);
    }

    public ResultDTO getProblemPage(Integer pageIndex) {
        return result(problemDAO.getProblemPage(pageIndex));
    }

    public ResultDTO getProblemById(Integer id) {
        ProblemEntity problemEntity = problemDAO.getProblemById(id);
        if (problemEntity == null) {
            return failedResult("题目" + id + "不存在");
        }

        return result(problemDAO.getProblemById(id));
    }

    public ResultDTO updateProblem(ProblemEntity requestProb) {
        ProblemEntity problem = problemDAO.getProblemById(requestProb.getId());
        if (problem == null) {
            return failedResult("题目不存在");
        }

        problem.setTitle(requestProb.getTitle());
        problem.setDescription(requestProb.getDescription());
        problem.setInput(requestProb.getInput());
        problem.setOutput(requestProb.getOutput());
        problem.setSampleInput(requestProb.getSampleInput());
        problem.setSampleOutput(requestProb.getSampleOutput());
        problem.setTimeLimit(requestProb.getTimeLimit());
        problem.setMemoryLimit(requestProb.getMemoryLimit());
        problem.setDbJudge(requestProb.getDbJudge());

        problemDAO.updateProblem(problem);

        return result("更新成功");
    }
}
