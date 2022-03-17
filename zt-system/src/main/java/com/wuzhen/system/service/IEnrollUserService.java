package com.wuzhen.system.service;

import com.wuzhen.system.domain.EnrollUser;
import com.wuzhen.system.domain.SysPost;

import java.util.List;

/**
 * 报名用户服务 服务层
 * 
 * @author zhengzheng
 */
public interface IEnrollUserService
{
    /**
     * 查询岗位信息集合
     * 
     * @param enrollUser 报名信息表
     * @return 报名信息集合
     */
    public List<EnrollUser> selectEnrollUserList(EnrollUser enrollUser);



//    /**
//     * 根据用户ID查询报名信息
//     *
//     * @param enrollUserNo 用户ID
//     * @return 岗位列表
//     */
//    public List<SysPost> selectPostsByUserId(Long enrollUserNo);


}
