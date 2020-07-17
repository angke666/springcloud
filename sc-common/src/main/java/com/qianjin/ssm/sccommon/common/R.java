package com.qianjin.ssm.sccommon.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 封装返回类
 */
@Data
public class R<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;
    private String info;
    private T data;

    public R() {
    }

    public R(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public R(int code, String info, T data) {
        this.code = code;
        this.info = info;
        this.data = data;
    }
}
