package com.ruoyi.bend.service;

import java.util.List;
import com.ruoyi.bend.domain.WechatMember;

/**
 * 微信用户Service接口
 * 
 * @author bend
 * @date 2020-08-30
 */
public interface IWechatMemberService 
{
    /**
     * 查询微信用户
     * 
     * @param id 微信用户ID
     * @return 微信用户
     */
    public WechatMember selectWechatMemberById(Long id);

    /**
     * 查询微信用户列表
     * 
     * @param wechatMember 微信用户
     * @return 微信用户集合
     */
    public List<WechatMember> selectWechatMemberList(WechatMember wechatMember);

    /**
     * 新增微信用户
     * 
     * @param wechatMember 微信用户
     * @return 结果
     */
    public int insertWechatMember(WechatMember wechatMember);

    /**
     * 修改微信用户
     * 
     * @param wechatMember 微信用户
     * @return 结果
     */
    public int updateWechatMember(WechatMember wechatMember);

    /**
     * 批量删除微信用户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWechatMemberByIds(String ids);

    /**
     * 删除微信用户信息
     * 
     * @param id 微信用户ID
     * @return 结果
     */
    public int deleteWechatMemberById(Long id);
}
