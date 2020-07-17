package com.qianjin.ssm.userservice.logic.dao;

import com.qianjin.ssm.sccommon.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {

    int save(User user);

    int update(User user);

    User getEntity(@Param("id") Integer id);
}
