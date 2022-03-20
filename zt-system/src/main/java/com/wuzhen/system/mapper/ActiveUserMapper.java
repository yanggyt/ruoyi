package com.wuzhen.system.mapper;

import com.wuzhen.system.domain.EnrollActiveUser;
import com.wuzhen.system.domain.EnrollUser;

import java.util.List;

/**
 * 报名用户信息 数据层
 * 
 * @author zhengzheng
 */
public interface ActiveUserMapper
{
    /**
     * 报名用户数据集合
     * 
     * @param enrollActiveUser 报名用户信息
     * @return 报名用户集合
     */
    public List<EnrollActiveUser> selectActiveUserList(EnrollActiveUser enrollActiveUser);


}
