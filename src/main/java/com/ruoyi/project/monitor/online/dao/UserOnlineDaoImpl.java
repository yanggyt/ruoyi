package com.ruoyi.project.monitor.online.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.ruoyi.framework.web.dao.DynamicObjectBaseDao;
import com.ruoyi.framework.web.page.PageUtilEntity;
import com.ruoyi.project.monitor.online.domain.UserOnline;

/**
 * 在线用户数据层
 * 
 * @author yangzz
 */
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
    @Override
    public int deleteByOnlineId(String sessionId)
    {
        return this.delete("SystemOnlineMapper.deleteByOnlineId", sessionId);
    }

    /**
     * 保存会话信息
     * 
     * @param online 会话信息
     */
    @Override
    public int saveByOnline(UserOnline online)
    {
        return this.save("SystemOnlineMapper.saveByOnline", online);
    }

    /**
     * 查询会话集合
     * 
     * @param pageUtilEntity 分页参数
     */
    @Override
    public List<UserOnline> pageInfoQuery(PageUtilEntity pageUtilEntity)
    {
        List<UserOnline> userOnlineList = null;
        try
        {
            userOnlineList = this.findForList("SystemOnlineMapper.pageInfoQueryUserOnline", pageUtilEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return userOnlineList;
    }

    /**
     * 查询过期会话集合
     * 
     * @param lastAccessTime 过期时间
     */
    @Override
    public List<UserOnline> selectByOnlineExpired(String lastAccessTime)
    {
        List<UserOnline> userOnlineList = null;
        try
        {
            userOnlineList = this.findForList("SystemOnlineMapper.selectByOnlineExpired", lastAccessTime);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return userOnlineList;
    }
}
