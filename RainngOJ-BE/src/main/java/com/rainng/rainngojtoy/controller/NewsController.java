package com.rainng.rainngojtoy.controller;

import com.rainng.rainngojtoy.models.dto.ResultDTO;
import com.rainng.rainngojtoy.service.NewsService;
import org.springframework.stereotype.Controller;

@Controller
public class NewsController extends BaseController {
    private final NewsService newsService = NewsService.getInstance();

    /**
     * 获取最新新闻列表
     */
    public ResultDTO getLatestNews() {
        return newsService.listLatestNews();
    }

    /**
     * 获取新闻
     *
     * @param id 新闻Id
     */
    public ResultDTO getNews(Integer id) {
        return newsService.getNewsById(id);
    }
}
