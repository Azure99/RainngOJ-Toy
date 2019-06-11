package com.rainng.rainngojtoy.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rainng.rainngojtoy.dao.mapper.NewsMapper;
import com.rainng.rainngojtoy.models.entity.NewsEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDAO {
    private static final int LATEST_SIZE = 5;

    @Autowired
    private NewsMapper newsMapper;

    public List<NewsEntity> listLatestNews() {
        LambdaQueryWrapper<NewsEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(NewsEntity::getId);
        wrapper.last("LIMIT " + LATEST_SIZE);

        return newsMapper.selectList(wrapper);
    }

    public NewsEntity getNewsById(Integer id) {
        return newsMapper.selectById(id);
    }
}
