package com.rainng.rainngojtoy.models.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.rainng.rainngojtoy.models.entity.SolutionEntity;

import java.util.Date;

public class StatusSolutionDTO {
    private Integer id;
    private Integer problemId;
    private String username;
    private Integer result;
    private Integer timeCost;
    private Integer memoryCost;
    private Integer codeLength;
    private Integer language;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date submitTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getTimeCost() {
        return timeCost;
    }

    public void setTimeCost(Integer timeCost) {
        this.timeCost = timeCost;
    }

    public Integer getMemoryCost() {
        return memoryCost;
    }

    public void setMemoryCost(Integer memoryCost) {
        this.memoryCost = memoryCost;
    }

    public Integer getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(Integer codeLength) {
        this.codeLength = codeLength;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public static StatusSolutionDTO fromSolutionEntity(SolutionEntity solution, String username) {
        StatusSolutionDTO dto = new StatusSolutionDTO();
        dto.id = solution.getId();
        dto.problemId = solution.getProblemId();
        dto.username = username;
        dto.result = solution.getResult();
        dto.timeCost = solution.getTimeCost();
        dto.memoryCost = solution.getMemoryCost();
        dto.codeLength = solution.getCodeLength();
        dto.language = solution.getLanguage();
        dto.submitTime = solution.getSubmitTime();

        return dto;
    }
}
