package com.rainng.rainngojtoy.service;

import com.rainng.rainngojtoy.dao.ProblemDAO;
import com.rainng.rainngojtoy.dao.SolutionDAO;
import com.rainng.rainngojtoy.dao.SolutionErrorDAO;
import com.rainng.rainngojtoy.dao.UserDAO;
import com.rainng.rainngojtoy.models.dto.ResultDTO;
import com.rainng.rainngojtoy.models.entity.ProblemEntity;
import com.rainng.rainngojtoy.models.entity.SolutionEntity;
import com.rainng.rainngojtoy.models.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class SolutionService extends BaseService {
    private static SolutionService instance;

    public static SolutionService getInstance() {
        return instance;
    }

    @PostConstruct
    public void init() {
        instance = this;
    }

    @Autowired
    private ProblemDAO problemDAO;
    @Autowired
    private SolutionDAO solutionDAO;
    @Autowired
    private SolutionErrorDAO solutionErrorDAO;
    @Autowired
    private UserDAO userDAO;

    public ResultDTO submitSolution(Integer problemId, Integer userId, Integer language, String sourceCode) {
        ProblemEntity problem = problemDAO.getProblemById(problemId);
        UserEntity user = userDAO.getUserById(userId);

        if (problemDAO.getProblemById(problemId) == null) {
            return failedResult("题目不存在");
        }
        if (user == null) {
            return failedResult("用户不存在");
        }

        SolutionEntity solution = new SolutionEntity();
        solution.setProblemId(problemId);
        solution.setUserId(userId);
        solution.setLanguage(language);
        solution.setSourceCode(sourceCode);
        solution.setCodeLength(sourceCode.length());
        solutionDAO.insertSolution(solution);

        user.setSubmitCount(user.getSubmitCount() + 1);
        userDAO.updateUser(user);
        problem.setSubmitCount(problem.getSubmitCount() + 1);
        problemDAO.updateProblem(problem);

        return result("提交成功");
    }

    public ResultDTO getError(Integer id) {
        return result(solutionErrorDAO.getSolutionErrorById(id));
    }

    public ResultDTO getSolution(Integer id, Integer userId, Boolean isAdmin) {
        SolutionEntity solution = solutionDAO.getSolutionById(id);

        if (solution == null) {
            return failedResult("提交不存在");
        }
        if (!solution.getUserId().equals(userId) && !isAdmin) {
            return failedResult("您没有权限查看此提交");
        }

        return result(solution);
    }
}
