package com.rainng.rainngojtoy.models.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@TableName("roj_solution_error")
public class SolutionErrorEntity {
    private static final String ID = "serr_solu_id";
    private static final String INFO = "serr_info";

    @TableId(value = ID)
    private Integer id;

    @TableField(INFO)
    private String info;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
