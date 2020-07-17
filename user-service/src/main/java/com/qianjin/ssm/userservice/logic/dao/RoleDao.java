package com.qianjin.ssm.userservice.logic.dao;

import com.qianjin.ssm.sccommon.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface RoleDao {

    int save(Role role);

    int update(Role role);

    Role getEntity(@Param("id") Integer id);

}
