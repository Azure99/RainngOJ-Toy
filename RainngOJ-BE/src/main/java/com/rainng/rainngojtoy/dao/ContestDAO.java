package com.rainng.rainngojtoy.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainng.rainngojtoy.dao.mapper.ContestMapper;
import com.rainng.rainngojtoy.models.entity.ContestEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContestDAO {
    private static final int PAGE_SIZE = 20;

    @Autowired
    private ContestMapper contestMapper;

    public Integer getPageSize() {
        return PAGE_SIZE;
    }

    public Integer countContests() {
        return contestMapper.selectCount(new QueryWrapper<>());
    }

    public List<ContestEntity> getContestPage(Integer pageIndex) {
        LambdaQueryWrapper<ContestEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(ContestEntity::getId, ContestEntity::getTitle, ContestEntity::getStartTime, ContestEntity::getEndTime);
        wrapper.orderByDesc(ContestEntity::getId);

        Page<ContestEntity> page = new Page<>(pageIndex, PAGE_SIZE);

        return contestMapper.selectPage(page, wrapper).getRecords();
    }

    public ContestEntity getContestById(Integer id) {
        return contestMapper.selectById(id);
    }
}
