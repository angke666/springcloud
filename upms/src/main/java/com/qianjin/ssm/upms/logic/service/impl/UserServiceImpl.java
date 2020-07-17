package com.qianjin.ssm.upms.logic.service.impl;

import com.qianjin.ssm.sccommon.entity.User;
import com.qianjin.ssm.upms.logic.dao.UserDao;
import com.qianjin.ssm.upms.logic.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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

    @Override
    public User findByName(String name) {
        return dao.findByName(name);
    }
}
