package com.ruoyi.content.service;

import com.ruoyi.content.message.Message;

import java.util.List;

public interface CmsUserRoleService {

    /**
     * 添加对应关系
     */
    public Message insertUserRole(String names, String email);

    /**
     * 根据主键删除对应关系
     */
    public Message delUserRole(String id);

    /**
     * 查询对应关系，添加或者删除对应关系之前先确定此关系是否存在
     */
    public List<?> queryUserRole(String name, String email);
}
