package com.qianjin.ssm.sccommon.entity;

import java.io.Serializable;
import java.util.List;

//@Data
public class Role implements Serializable {
    private Integer id;
    private String name;
    private List<User> userList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
