package com.ruoyi.common.base;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @author solo
 * @date 2019/8/23
 */
public class BaseBean implements Serializable {
    public BaseBean() {
    }

    public String toString() {
        return JSON.toJSONString(this);
    }
}
