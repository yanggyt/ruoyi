package com.ruoyi.project.monitor.online.service;

import java.util.Date;
import java.util.List;

import com.ruoyi.framework.web.page.PageUtilEntity;
import com.ruoyi.project.monitor.online.domain.UserOnline;

/**
 * 在线用户 服务层
 * 
 * @author ruoyi
 */
public interface IUserOnlineService
{
    /**
     * 通过会话序号查询信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public UserOnline selectByOnlineId(String sessionId);

    /**
     * 通过会话序号删除信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public void deleteByOnlineId(String sessionId);

    /**
     * 通过会话序号删除信息
     * 
     * @param sessions 会话ID集合
     * @return 在线用户信息
     */
    public void batchDeleteByOnline(List<String> sessions);

    /**
     * 保存会话信息
     * 
     * @param online 会话信息
     */
    public void saveByOnline(UserOnline online);

    /**
     * 查询会话集合
     * 
     * @param pageUtilEntity 分页参数
     * @return 会话集合
     */
    public List<UserOnline> pageInfoQueryUserOnline(PageUtilEntity pageUtilEntity);

    /**
     * 读取Session信息
     * 
     * @param sessionId 会话ID
     */
    public void forceLogout(String sessionId);

    /**
     * 查询会话集合
     * 
     * @param expiredDate 有效期
     * @return 会话集合
     */
    public List<UserOnline> selectByOnlineExpired(Date expiredDate);
}
