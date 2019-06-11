package com.rainng.rainngojtoy.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainng.rainngojtoy.dao.mapper.ProblemMapper;
import com.rainng.rainngojtoy.models.entity.ProblemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProblemDAO {
    private static final int PAGE_SIZE = 30;

    @Autowired
    private ProblemMapper problemMapper;

    public Integer countProblems() {
        return problemMapper.selectCount(new QueryWrapper<>());
    }

    public Integer getPageSize() {
        return PAGE_SIZE;
    }

    public int updateProblem(ProblemEntity problemEntity) {
        return problemMapper.updateById(problemEntity);
    }

    public List<ProblemEntity> getProblemPage(Integer pageIndex) {
        LambdaQueryWrapper<ProblemEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(
                ProblemEntity::getId, ProblemEntity::getTitle,
                ProblemEntity::getSolvedCount, ProblemEntity::getSubmitCount);

        wrapper.orderByAsc(ProblemEntity::getId);

        Page<ProblemEntity> page = new Page<>(pageIndex, PAGE_SIZE);

        return problemMapper.selectPage(page, wrapper).getRecords();
    }

    public ProblemEntity getProblemById(Integer id) {
        return problemMapper.selectById(id);
    }

    public List<ProblemEntity> listProblemsByIds(Integer[] ids) {
        LambdaQueryWrapper<ProblemEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(
                ProblemEntity::getId, ProblemEntity::getTitle,
                ProblemEntity::getSolvedCount, ProblemEntity::getSubmitCount);

        wrapper.in(ProblemEntity::getId, ids);
        wrapper.orderByAsc(ProblemEntity::getId);

        return problemMapper.selectList(wrapper);
    }
}
