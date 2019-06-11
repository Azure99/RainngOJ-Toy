package com.rainng.rainngojtoy.models.dto;

public class JudgeResultDTO {
    private Integer submitId;
    private Integer resultCode;
    private String judgeDetail;
    private Double passRate;
    private Integer timeCost;
    private Integer memoryCost;

    public Integer getSubmitId() {
        return submitId;
    }

    public void setSubmitId(Integer submitId) {
        this.submitId = submitId;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getJudgeDetail() {
        return judgeDetail;
    }

    public void setJudgeDetail(String judgeDetail) {
        this.judgeDetail = judgeDetail;
    }

    public Double getPassRate() {
        return passRate;
    }

    public void setPassRate(Double passRate) {
        this.passRate = passRate;
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
}
