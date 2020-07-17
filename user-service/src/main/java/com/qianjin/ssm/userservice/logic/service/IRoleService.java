package com.qianjin.ssm.userservice.logic.service;


import com.qianjin.ssm.sccommon.entity.Role;

public interface IRoleService {

    int save(Role role);

    int update(Role role);

    Role getEntity(Integer id);

}
