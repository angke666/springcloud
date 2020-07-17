package com.qianjin.ssm.userservice.logic.service.impl;

import com.qianjin.ssm.sccommon.entity.User;
import com.qianjin.ssm.userservice.logic.dao.UserDao;
import com.qianjin.ssm.userservice.logic.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao dao;

    @Override
    public int save(User user) {
        return dao.save(user);
    }

    @Override
    public int update(User user) {
        return dao.update(user);
    }

    @Override
    public User getEntity(Integer id) {
        return dao.getEntity(id);
    }
}
