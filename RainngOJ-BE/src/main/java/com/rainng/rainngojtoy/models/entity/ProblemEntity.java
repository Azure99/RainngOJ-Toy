package com.rainng.rainngojtoy.models.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("roj_problem")
public class ProblemEntity {
    private static final String ID = "prob_id";
    private static final String TITLE = "prob_title";
    private static final String DESCRIPTION = "prob_description";
    private static final String INPUT = "prob_input";
    private static final String OUTPUT = "prob_output";
    private static final String SAMPLE_INPUT = "prob_sample_input";
    private static final String SAMPLE_OUTPUT = "prob_sample_output";
    private static final String HINT = "prob_hint";
    private static final String TIME_LIMIT = "prob_time_limit";
    private static final String MEMORY_LIMIT = "prob_memory_limit";
    private static final String SUBMIT_COUNT = "prob_submit_count";
    private static final String SOLVED_COUNT = "prob_solved_count";
    private static final String IS_DB_JUDGE = "prob_is_dbjudge";
    private static final String DATA_VERSION = "prob_data_version";

    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @TableField(TITLE)
    private String title;

    @TableField(DESCRIPTION)
    private String description;

    @TableField(INPUT)
    private String input;

    @TableField(OUTPUT)
    private String output;

    @TableField(SAMPLE_INPUT)
    private String sampleInput;

    @TableField(SAMPLE_OUTPUT)
    private String sampleOutput;

    @TableField(HINT)
    private String hint;

    @TableField(TIME_LIMIT)
    private Integer timeLimit;

    @TableField(MEMORY_LIMIT)
    private Integer memoryLimit;

    @TableField(SUBMIT_COUNT)
    private Integer submitCount;

    @TableField(SOLVED_COUNT)
    private Integer solvedCount;

    @TableField(IS_DB_JUDGE)
    private Boolean dbJudge;

    @TableField(DATA_VERSION)
    private String dataVersion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getSampleInput() {
        return sampleInput;
    }

    public void setSampleInput(String sampleInput) {
        this.sampleInput = sampleInput;
    }

    public String getSampleOutput() {
        return sampleOutput;
    }

    public void setSampleOutput(String sampleOutput) {
        this.sampleOutput = sampleOutput;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
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

    public Integer getSubmitCount() {
        return submitCount;
    }

    public void setSubmitCount(Integer submitCount) {
        this.submitCount = submitCount;
    }

    public Integer getSolvedCount() {
        return solvedCount;
    }

    public void setSolvedCount(Integer solvedCount) {
        this.solvedCount = solvedCount;
    }

    public Boolean getDbJudge() {
        return dbJudge;
    }

    public void setDbJudge(Boolean dbJudge) {
        this.dbJudge = dbJudge;
    }

    public String getDataVersion() {
        return dataVersion;
    }

    public void setDataVersion(String dataVersion) {
        this.dataVersion = dataVersion;
    }
}
