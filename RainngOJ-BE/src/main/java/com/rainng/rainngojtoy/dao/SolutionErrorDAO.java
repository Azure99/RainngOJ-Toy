package com.rainng.rainngojtoy.dao;

import com.rainng.rainngojtoy.dao.mapper.SolutionErrorMapper;
import com.rainng.rainngojtoy.models.entity.SolutionErrorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SolutionErrorDAO {
    @Autowired
    private SolutionErrorMapper solutionErrorMapper;

    public int insertSolutionError(SolutionErrorEntity solutionError) {
        return solutionErrorMapper.insert(solutionError);
    }

    public SolutionErrorEntity getSolutionErrorById(Integer id) {
        return solutionErrorMapper.selectById(id);
    }

    public int updateSolutionError(SolutionErrorEntity solutionError) {
        return solutionErrorMapper.updateById(solutionError);
    }

}
