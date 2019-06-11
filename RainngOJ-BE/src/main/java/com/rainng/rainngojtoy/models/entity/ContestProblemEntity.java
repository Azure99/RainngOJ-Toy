package com.rainng.rainngojtoy.models.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("roj_contest_problem")
public class ContestProblemEntity {
    private static final String CONTEST_ID = "cprob_cont_id";
    private static final String CONTEST_PROBLEM_ID = "cprob_id";
    private static final String PROBLEM_ID = "cprob_prob_id";

    @TableField(CONTEST_ID)
    private Integer contestId;

    @TableId(CONTEST_PROBLEM_ID)
    private Integer contestProblemId;

    @TableField(PROBLEM_ID)
    private Integer problemId;

    public Integer getContestId() {
        return contestId;
    }

    public void setContestId(Integer contestId) {
        this.contestId = contestId;
    }

    public Integer getContestProblemId() {
        return contestProblemId;
    }

    public void setContestProblemId(Integer contestProblemId) {
        this.contestProblemId = contestProblemId;
    }

    public Integer getProblemId() {
        return problemId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }
}
