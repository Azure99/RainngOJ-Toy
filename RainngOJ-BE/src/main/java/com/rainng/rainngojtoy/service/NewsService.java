package com.rainng.rainngojtoy.service;

import com.rainng.rainngojtoy.dao.NewsDAO;
import com.rainng.rainngojtoy.models.dto.ResultDTO;
import com.rainng.rainngojtoy.models.entity.NewsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class NewsService extends BaseService {
    private static NewsService instance;

    public static NewsService getInstance() {
        return instance;
    }

    @PostConstruct
    public void init() {
        instance = this;
    }

    @Autowired
    private NewsDAO newsDAO;

    public ResultDTO listLatestNews() {
        return result(newsDAO.listLatestNews());
    }

    public ResultDTO getNewsById(Integer id) {
        NewsEntity news = newsDAO.getNewsById(id);
        if (news == null) {
            return failedResult("新闻" + id + "不存在");
        }
        return result(news);
    }
}
