package com.ruoyi.business.model;

import com.alibaba.fastjson.JSONObject;

/**
 * 用户实体
 * @author bei.wu
 */
public class Member {

    /**
     * 用户主键
     */
    private Long id;
    /**
     * 用户名称
     */
    private String name;
    /**
     * 用户手机号
     */
    private String mobile;

    public Member() {

    }

    public Member(Long id, String name, String mobile) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String toJsonString() {
        return JSONObject.toJSONString(this);
    }
}
