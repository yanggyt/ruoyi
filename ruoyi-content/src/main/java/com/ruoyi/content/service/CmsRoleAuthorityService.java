package com.ruoyi.content.service;

import com.ruoyi.content.message.Message;

import java.util.List;

/**
 * 说明：系统用户权限
 *
 * @author wang.q
 * @date 2017年8月15日
 */
public interface CmsRoleAuthorityService {
    /**
     * 查询当前用户的
     *
     * @return
     */
    public List<?> queryUserRole();


    /**
     * 添加系统用户权限
     */
    public Message insertRoleAuthority(String selfChild, String parentChild);

    /**
     * 删除或者添加之前先查询当前对应关系是否存在
     */
    public List<?> queryRoleAuthority(String selfChild, String parentChild);

    /**
     * 删除对应关系
     */
    public Message delRoleAuthority(String selfChild, String parentChild);

}
