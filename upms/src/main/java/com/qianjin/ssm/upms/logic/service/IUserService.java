package com.qianjin.ssm.upms.logic.service;

import com.qianjin.ssm.sccommon.entity.User;

public interface IUserService {

    int save(User user);

    int update(User user);

    User getEntity(Integer id);

    User findByName(String name);
}
