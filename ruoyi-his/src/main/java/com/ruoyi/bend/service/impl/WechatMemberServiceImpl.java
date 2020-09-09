package com.ruoyi.bend.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bend.mapper.WechatMemberMapper;
import com.ruoyi.bend.domain.WechatMember;
import com.ruoyi.bend.service.IWechatMemberService;
import com.ruoyi.common.core.text.Convert;

/**
 * 微信用户Service业务层处理
 * 
 * @author bend
 * @date 2020-08-30
 */
@Service
public class WechatMemberServiceImpl implements IWechatMemberService 
{
    @Autowired
    private WechatMemberMapper wechatMemberMapper;

    /**
     * 查询微信用户
     * 
     * @param id 微信用户ID
     * @return 微信用户
     */
    @Override
    public WechatMember selectWechatMemberById(Long id)
    {
        return wechatMemberMapper.selectWechatMemberById(id);
    }

    /**
     * 查询微信用户列表
     * 
     * @param wechatMember 微信用户
     * @return 微信用户
     */
    @Override
    public List<WechatMember> selectWechatMemberList(WechatMember wechatMember)
    {
        return wechatMemberMapper.selectWechatMemberList(wechatMember);
    }

    /**
     * 新增微信用户
     * 
     * @param wechatMember 微信用户
     * @return 结果
     */
    @Override
    public int insertWechatMember(WechatMember wechatMember)
    {
        wechatMember.setCreateTime(DateUtils.getNowDate());
        return wechatMemberMapper.insertWechatMember(wechatMember);
    }

    /**
     * 修改微信用户
     * 
     * @param wechatMember 微信用户
     * @return 结果
     */
    @Override
    public int updateWechatMember(WechatMember wechatMember)
    {
        wechatMember.setUpdateTime(DateUtils.getNowDate());
        return wechatMemberMapper.updateWechatMember(wechatMember);
    }

    /**
     * 删除微信用户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWechatMemberByIds(String ids)
    {
        return wechatMemberMapper.deleteWechatMemberByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除微信用户信息
     * 
     * @param id 微信用户ID
     * @return 结果
     */
    @Override
    public int deleteWechatMemberById(Long id)
    {
        return wechatMemberMapper.deleteWechatMemberById(id);
    }
}
