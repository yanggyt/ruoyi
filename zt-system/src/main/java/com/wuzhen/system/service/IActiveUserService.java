package com.wuzhen.system.service;

import com.wuzhen.system.domain.EnrollActiveUser;
import com.wuzhen.system.domain.EnrollUser;

import java.util.List;

/**
 * 报名用户服务 服务层
 * 
 * @author zhengzheng
 */
public interface IActiveUserService
{
    /**
     * 查询报名用户活动
     * 
     * @param enrollActiveUser 报名信息表
     * @return 报名信息集合
     */
    public List<EnrollActiveUser> selectActiveUserList(EnrollActiveUser enrollActiveUser);

    

}
