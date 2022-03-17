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




//    /**
//     * 查询所有岗位
//     *
//     * @return 岗位列表
//     */
//    public List<SysPost> selectPostAll();
//
//    /**
//     * 根据用户ID查询岗位
//     *
//     * @param userId 用户ID
//     * @return 岗位列表
//     */
//    public List<SysPost> selectPostsByUserId(Long userId);
//
//    /**
//     * 通过岗位ID查询岗位信息
//     *
//     * @param postId 岗位ID
//     * @return 角色对象信息
//     */
//    public SysPost selectPostById(Long postId);
//
//    /**
//     * 批量删除岗位信息
//     *
//     * @param ids 需要删除的数据ID
//     * @return 结果
//     */
//    public int deletePostByIds(Long[] ids);
//
//    /**
//     * 修改岗位信息
//     *
//     * @param post 岗位信息
//     * @return 结果
//     */
//    public int updatePost(SysPost post);
//
//    /**
//     * 新增岗位信息
//     *
//     * @param post 岗位信息
//     * @return 结果
//     */
//    public int insertPost(SysPost post);
//
//    /**
//     * 校验岗位名称
//     *
//     * @param postName 岗位名称
//     * @return 结果
//     */
//    public SysPost checkPostNameUnique(String postName);
//
//    /**
//     * 校验岗位编码
//     *
//     * @param postCode 岗位编码
//     * @return 结果
//     */
//    public SysPost checkPostCodeUnique(String postCode);
}
