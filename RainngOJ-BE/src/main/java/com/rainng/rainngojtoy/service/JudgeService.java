package com.rainng.rainngojtoy.service;

import com.rainng.rainngojtoy.dao.ProblemDAO;
import com.rainng.rainngojtoy.dao.SolutionDAO;
import com.rainng.rainngojtoy.dao.SolutionErrorDAO;
import com.rainng.rainngojtoy.dao.UserDAO;
import com.rainng.rainngojtoy.manager.LanguageManager;
import com.rainng.rainngojtoy.models.constant.JudgeResultCode;
import com.rainng.rainngojtoy.models.dto.JudgeResultDTO;
import com.rainng.rainngojtoy.models.dto.JudgeTaskDTO;
import com.rainng.rainngojtoy.models.dto.ResultDTO;
import com.rainng.rainngojtoy.models.entity.ProblemEntity;
import com.rainng.rainngojtoy.models.entity.SolutionEntity;
import com.rainng.rainngojtoy.models.entity.SolutionErrorEntity;
import com.rainng.rainngojtoy.models.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class JudgeService extends BaseService {
    private static JudgeService instance;

    public static JudgeService getInstance() {
        return instance;
    }

    @PostConstruct
    public void init() {
        instance = this;
    }

    @Autowired
    private LanguageManager languageManager;
    @Autowired
    private ProblemDAO problemDAO;
    @Autowired
    private SolutionDAO solutionDAO;
    @Autowired
    private SolutionErrorDAO solutionErrorDAO;
    @Autowired
    private UserDAO userDAO;

    public ResultDTO getPending() {
        List<SolutionEntity> solutions = solutionDAO.listPendingSolutions();

        List<JudgeTaskDTO> judgeTasks = new ArrayList<>();
        for (SolutionEntity solution : solutions) {
            JudgeTaskDTO task = createJudgeTask(solution);
            if (task == null) {
                continue;
            }

            solution.setResult(JudgeResultCode.JUDGING);
            solutionDAO.updateSolution(solution);
            judgeTasks.add(task);
        }

        if (judgeTasks.size() == 0) {
            return new ResultDTO(ResultDTO.NO_TASK, "No task", null);
        }

        return result(judgeTasks);
    }

    public ResultDTO submitResult(JudgeResultDTO result) {
        SolutionEntity solution = solutionDAO.getSolutionById(result.getSubmitId());
        if (solution == null) {
            return failedResult("No such solution " + result.getSubmitId());
        }

        solution.setResult(result.getResultCode());
        solution.setTimeCost(result.getTimeCost());
        solution.setMemoryCost(result.getMemoryCost());

        // 有错误
        if (!isNullOrEmpty(result.getJudgeDetail())) {
            SolutionErrorEntity solutionError = solutionErrorDAO.getSolutionErrorById(result.getSubmitId());

            if (solutionError == null) {
                solutionError = new SolutionErrorEntity();
                solutionError.setId(result.getSubmitId());
                solutionError.setInfo(result.getJudgeDetail());
                solutionErrorDAO.insertSolutionError(solutionError);
            } else {
                solutionError.setInfo(result.getJudgeDetail());
                solutionErrorDAO.updateSolutionError(solutionError);
            }
        }

        solutionDAO.updateSolution(solution);

        if (result.getResultCode() == JudgeResultCode.ACCEPTED) {
            UserEntity user = userDAO.getUserById(solution.getUserId());
            ProblemEntity problem = problemDAO.getProblemById(solution.getProblemId());
            user.setSolvedCount(user.getSolvedCount() + 1);
            problem.setSolvedCount(problem.getSolvedCount() + 1);
            userDAO.updateUser(user);
            problemDAO.updateProblem(problem);
        }

        return result("success");
    }

    private JudgeTaskDTO createJudgeTask(SolutionEntity solution) {
        ProblemEntity problem = problemDAO.getProblemById(solution.getProblemId());
        if (problem == null) {
            return null;
        }

        String username = userDAO.getUsernameById(solution.getUserId());
        String languageName = languageManager.getLangNameById(solution.getLanguage());
        boolean isDbJudge = languageManager.isDbLang(solution.getLanguage());

        return JudgeTaskDTO.from(solution, problem, username, languageName, isDbJudge);
    }
}
