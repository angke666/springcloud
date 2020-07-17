package com.qianjin.ssm.userservice.logic.service.impl;

import com.qianjin.ssm.sccommon.entity.Role;
import com.qianjin.ssm.userservice.logic.dao.RoleDao;
import com.qianjin.ssm.userservice.logic.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private RoleDao dao;

    @Override
    public int save(Role role) {
        return dao.save(role);
    }

    @Override
    public int update(Role role) {
        return dao.update(role);
    }

    @Override
    public Role getEntity(Integer id) {
        return dao.getEntity(id);
    }
}
