package com.rainng.rainngojtoy.models.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

@TableName("roj_news")
public class NewsEntity {
    private static final String ID = "news_id";
    private static final String TITLE = "news_title";
    private static final String CONTENT = "news_content";
    private static final String DATE = "news_date";

    @TableId(value = ID, type = IdType.AUTO)
    private Integer id;

    @TableField(TITLE)
    private String title;

    @TableField(CONTENT)
    private String content;

    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @TableField(DATE)
    private Date date;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
