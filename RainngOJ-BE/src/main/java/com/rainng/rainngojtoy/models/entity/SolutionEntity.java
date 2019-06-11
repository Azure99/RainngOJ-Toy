package com.rainng.rainngojtoy.models.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("roj_solution")
public class SolutionEntity {
    private static final String ID = "solu_id";
    private static final String USER_ID = "solu_user_id";
    private static final String PROB_ID = "solu_prob_id";
    private static final String LANGUAGE = "solu_language";
    private static final String SOURCE_CODE = "solu_sourcecode";
    private static final String CODE_LENGTH = "solu_code_length";
    private static final String RESULT = "solu_result";
    private static final String TIME_COST = "solu_time_cost";
    private static final String MEMORY_COST = "solu_memory_cost";
    private static final String SUBMIT_TIME = "solu_submit_time";
    private static final String CONT_ID = "solu_cont_id";

    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @TableField(USER_ID)
    private Integer userId;

    @TableField(PROB_ID)
    private Integer problemId;

    @TableField(LANGUAGE)
    private Integer language;

    @TableField(SOURCE_CODE)
    private String sourceCode;

    @TableField(CODE_LENGTH)
    private Integer codeLength;

    @TableField(RESULT)
    private Integer result;

    @TableField(TIME_COST)
    private Integer timeCost;

    @TableField(MEMORY_COST)
    private Integer memoryCost;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(SUBMIT_TIME)
    private Date submitTime;

    @TableField(CONT_ID)
    private Integer contestId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public Integer getLanguage() {
        return language;
    }

    public void setLanguage(Integer language) {
        this.language = language;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(String sourceCode) {
        this.sourceCode = sourceCode;
    }

    public Integer getCodeLength() {
        return codeLength;
    }

    public void setCodeLength(Integer codeLength) {
        this.codeLength = codeLength;
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

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }
}
