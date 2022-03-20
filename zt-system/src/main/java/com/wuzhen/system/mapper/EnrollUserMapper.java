package com.wuzhen.system.mapper;

import com.wuzhen.system.domain.EnrollUser;
import com.wuzhen.system.domain.SysPost;

import java.util.List;

/**
 * 报名用户信息 数据层
 * 
 * @author zhengzheng
 */
public interface EnrollUserMapper
{
    /**
     * 报名用户数据集合
     * 
     * @param enrollUser 报名用户信息
     * @return 报名用户集合
     */
    public List<EnrollUser> selectEnrollUserList(EnrollUser enrollUser);


}
