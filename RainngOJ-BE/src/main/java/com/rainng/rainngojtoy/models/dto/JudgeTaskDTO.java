package com.rainng.rainngojtoy.models.dto;

import com.rainng.rainngojtoy.models.entity.ProblemEntity;
import com.rainng.rainngojtoy.models.entity.SolutionEntity;

public class JudgeTaskDTO {
    private Integer submitId;
    private Integer problemId;
    private String dataVersion;
    private String author;
    private String language;
    private Integer timeLimit;
    private Integer memoryLimit;
    private Boolean judgeAllCases = false;
    private String sourceCode;
    private Integer judgeType;

    public Integer getSubmitId() {
        return submitId;
    }

    public void setSubmitId(Integer submitId) {
        this.submitId = submitId;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public String getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(String dataVersion) {
        this.dataVersion = dataVersion;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public Integer getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(Integer memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public Boolean getJudgeAllCases() {
        return judgeAllCases;
    }

    public void setJudgeAllCases(Boolean judgeAllCases) {
        this.judgeAllCases = judgeAllCases;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public Integer getJudgeType() {
        return judgeType;
    }

    public void setJudgeType(Integer judgeType) {
        this.judgeType = judgeType;
    }

    public static JudgeTaskDTO from(SolutionEntity solution, ProblemEntity problem, String username,
                                    String languageName, boolean isDbJudge) {
        JudgeTaskDTO task = new JudgeTaskDTO();
        task.setSubmitId(solution.getId());
        task.setAuthor(username);
        task.setDataVersion("1.0");
        task.setJudgeAllCases(false);
        task.setLanguage(languageName);
        task.setProblemId(solution.getProblemId());
        task.setSourceCode(solution.getSourceCode());
        task.setTimeLimit(problem.getTimeLimit());
        task.setMemoryLimit(problem.getMemoryLimit());
        task.setDataVersion(problem.getDataVersion());

        if (isDbJudge) {
            task.setJudgeType(2);
        } else {
            task.setJudgeType(0);
        }

        return task;
    }
}
