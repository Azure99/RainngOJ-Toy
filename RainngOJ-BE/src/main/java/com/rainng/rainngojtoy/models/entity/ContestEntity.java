package com.rainng.rainngojtoy.models.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("roj_contest")
public class ContestEntity {
    private static final String ID = "cont_id";
    private static final String TITLE = "cont_title";
    private static final String DESCRIPTION = "cont_description";
    private static final String START_TIME = "cont_starttime";
    private static final String END_TIME = "cont_endtime";

    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @TableField(TITLE)
    private String title;

    @TableField(DESCRIPTION)
    private String description;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(START_TIME)
    private Date startTime;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(END_TIME)
    private Date endTime;

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
}
