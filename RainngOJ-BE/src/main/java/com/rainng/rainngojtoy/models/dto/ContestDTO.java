package com.rainng.rainngojtoy.models.dto;

import com.rainng.rainngojtoy.models.entity.ContestEntity;
import com.rainng.rainngojtoy.models.entity.ProblemEntity;

import java.util.Date;
import java.util.List;

public class ContestDTO {
    private Integer id;
    private String title;
    private String description;
    private Date startTime;
    private Date endTime;
    private List<ProblemEntity> problems;

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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public List<ProblemEntity> getProblems() {
        return problems;
    }

    public void setProblems(List<ProblemEntity> problems) {
        this.problems = problems;
    }

    public static ContestDTO from(ContestEntity contest, List<ProblemEntity> problems) {
        ContestDTO dto = new ContestDTO();
        dto.setId(contest.getId());
        dto.setTitle(contest.getTitle());
        dto.setDescription(contest.getDescription());
        dto.setStartTime(contest.getStartTime());
        dto.setEndTime(contest.getEndTime());
        dto.setProblems(problems);

        return dto;
    }
}
