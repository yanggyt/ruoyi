package com.ruoyi.project.system.online.dao;

import org.springframework.stereotype.Repository;
import com.ruoyi.framework.core.dao.DynamicObjectBaseDao;
import com.ruoyi.project.system.online.domain.UserOnline;

@Repository("userOnlineDao")
public class UserOnlineDaoImpl extends DynamicObjectBaseDao implements IUserOnlineDao
{
    /**
     * 通过会话序号查询信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    @Override
    public UserOnline selectByOnlineId(String sessionId)
    {
        return this.findForObject("SystemOnlineMapper.selectByOnlineId", sessionId);
    }

    /**
     * 通过会话序号删除信息
     * 
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    public int deleteByOnlineId(String sessionId)
    {
        return this.delete("SystemOnlineMapper.deleteByOnlineId", sessionId);
    }

    /**
     * 保存会话信息
     * 
     * @param online 会话信息
     */
    public int saveByOnline(UserOnline online)
    {
        return this.save("SystemOnlineMapper.saveByOnline", online);
    }
}
