package com.rainng.rainngojtoy.service;

import com.rainng.rainngojtoy.dao.UserDAO;
import com.rainng.rainngojtoy.models.dto.PageInfoDTO;
import com.rainng.rainngojtoy.models.dto.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class RankService extends BaseService {
    private static RankService instance;

    public static RankService getInstance() {
        return instance;
    }

    @PostConstruct
    public void init() {
        instance = this;
    }

    @Autowired
    private UserDAO userDAO;

    public ResultDTO getPageInfo() {
        return result(new PageInfoDTO(userDAO.countUsers(), userDAO.getRankPageSize()));
    }

    public ResultDTO getRankPage(Integer pageIndex) {
        return result(userDAO.getRankPage(pageIndex));
    }
}
