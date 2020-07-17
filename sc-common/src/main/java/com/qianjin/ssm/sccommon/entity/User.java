package com.qianjin.ssm.sccommon.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {
    private Integer id;
    private Integer roleId;
    private String name;
    private String password;
    private Integer age;
    private String roleName;
    private Role role;
}
