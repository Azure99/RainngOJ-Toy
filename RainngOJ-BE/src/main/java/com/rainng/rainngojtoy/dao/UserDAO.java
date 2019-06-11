package com.rainng.rainngojtoy.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainng.rainngojtoy.dao.mapper.UserMapper;
import com.rainng.rainngojtoy.models.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAO {
    private static final int TOP_USERS_COUNT = 10;
    private static final int RANK_PAGE_SIZE = 30;

    @Autowired
    private UserMapper userMapper;

    public UserEntity getUserById(Integer id) {
        return userMapper.selectById(id);
    }

    public UserEntity getUserByUsername(String username) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserEntity::getUsername, username);

        return userMapper.selectOne(wrapper);
    }

    public List<UserEntity> listTopUsers() {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(UserEntity::getUsername, UserEntity::getSolvedCount);

        wrapper.orderByDesc(UserEntity::getSolvedCount)
                .orderByAsc(UserEntity::getSubmitCount)
                .orderByAsc(UserEntity::getUsername);

        wrapper.last("LIMIT " + TOP_USERS_COUNT);

        return userMapper.selectList(wrapper);
    }

    public int insertUser(UserEntity userEntity) {
        return userMapper.insert(userEntity);
    }

    public int updateUser(UserEntity userEntity) {
        return userMapper.updateById(userEntity);
    }

    public int countUsers() {
        return userMapper.selectCount(new QueryWrapper<>());
    }

    public int getRankPageSize() {
        return RANK_PAGE_SIZE;
    }

    public List<UserEntity> getRankPage(Integer pageIndex) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(
                UserEntity::getId, UserEntity::getUsername, UserEntity::getNickname,
                UserEntity::getSolvedCount, UserEntity::getSubmitCount, UserEntity::getLocked);

        wrapper.orderByDesc(UserEntity::getSolvedCount)
                .orderByAsc(UserEntity::getSubmitCount);

        Page<UserEntity> page = new Page<>(pageIndex, RANK_PAGE_SIZE);

        return userMapper.selectPage(page, wrapper).getRecords();
    }

    public String getUsernameById(Integer id) {
        LambdaQueryWrapper<UserEntity> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(UserEntity::getId, UserEntity::getUsername)
                .eq(UserEntity::getId, id);

        UserEntity user = userMapper.selectOne(wrapper);
        if (user == null) {
            return null;
        }

        return user.getUsername();
    }
}
