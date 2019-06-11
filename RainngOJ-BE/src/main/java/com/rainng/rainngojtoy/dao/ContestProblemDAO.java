package com.rainng.rainngojtoy.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rainng.rainngojtoy.dao.mapper.ContestProblemMapper;
import com.rainng.rainngojtoy.models.entity.ContestProblemEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContestProblemDAO {
    @Autowired
    private ContestProblemMapper contestProblemMapper;

    public List<ContestProblemEntity> listContestProblemsByContestId(Integer contestId) {
        LambdaQueryWrapper<ContestProblemEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ContestProblemEntity::getContestId, contestId);

        return contestProblemMapper.selectList(wrapper);
    }
}
