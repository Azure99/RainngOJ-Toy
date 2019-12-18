package com.rainng.rainngojtoy.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainng.rainngojtoy.dao.mapper.SolutionMapper;
import com.rainng.rainngojtoy.models.constant.JudgeResultCode;
import com.rainng.rainngojtoy.models.entity.SolutionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class SolutionDAO {
    private static final int PAGE_SIZE = 30;
    private static final int MAX_PENDING_TASK_PRE_REQUEST = 1;

    @Autowired
    private SolutionMapper solutionMapper;

    public int getPageSize() {
        return PAGE_SIZE;
    }

    public int countSolutions() {
        return solutionMapper.selectCount(new QueryWrapper<>());
    }

    public int insertSolution(SolutionEntity solutionEntity) {
        return solutionMapper.insert(solutionEntity);
    }

    public SolutionEntity getSolutionById(Integer id) {
        return solutionMapper.selectById(id);
    }

    public int updateSolution(SolutionEntity solutionEntity) {
        return solutionMapper.updateById(solutionEntity);
    }

    public List<SolutionEntity> getSolutionPage(Integer pageIndex, Integer problemId, Integer userId, Integer result, Integer language) {
        LambdaQueryWrapper<SolutionEntity> wrapper = Wrappers.lambdaQuery();
        wrapper.select(
                SolutionEntity::getId, SolutionEntity::getProblemId, SolutionEntity::getUserId,
                SolutionEntity::getResult, SolutionEntity::getTimeCost, SolutionEntity::getMemoryCost,
                SolutionEntity::getCodeLength, SolutionEntity::getLanguage, SolutionEntity::getSubmitTime);

        wrapper.orderByDesc(SolutionEntity::getId);

        if (problemId > -1) {
            wrapper.eq(SolutionEntity::getProblemId, problemId);
        }
        if (userId > -1) {
            wrapper.eq(SolutionEntity::getUserId, userId);
        }
        if (result > -1) {
            wrapper.eq(SolutionEntity::getResult, result);
        }
        if (language > -1) {
            wrapper.eq(SolutionEntity::getLanguage, language);
        }

        Page<SolutionEntity> page = new Page<>(pageIndex, PAGE_SIZE);

        return solutionMapper.selectPage(page, wrapper).getRecords();
    }

    public List<SolutionEntity> listSolutionsByIds(Integer[] ids) {
        LambdaQueryWrapper<SolutionEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(
                SolutionEntity::getId, SolutionEntity::getResult,
                SolutionEntity::getTimeCost, SolutionEntity::getMemoryCost);

        wrapper.in(SolutionEntity::getId, ids);

        return solutionMapper.selectList(wrapper);
    }

    public List<SolutionEntity> listPendingSolutions() {
        LambdaQueryWrapper<SolutionEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(SolutionEntity::getId)
                .eq(SolutionEntity::getResult, JudgeResultCode.PENDING)
                .last("LIMIT " + MAX_PENDING_TASK_PRE_REQUEST);

        return solutionMapper.selectList(wrapper);
    }

    public List<Map<String, Object>> getStatusPieCount() {
        QueryWrapper<SolutionEntity> wrapper = new QueryWrapper<>();
        wrapper.select("count(*) AS count, solu_result AS result");
        wrapper.groupBy("solu_result");

        return solutionMapper.selectMaps(wrapper);
    }
}
