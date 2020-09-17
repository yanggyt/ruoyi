package com.ruoyi.business.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.business.domain.BizMember;

/**
 * 会员Service接口
 * 
 * @author ruoyi
 * @date 2020-09-11
 */
public interface IBizMemberService 
{
    /**
     * 查询会员
     * 
     * @param id 会员ID
     * @return 会员
     */
    public BizMember selectBizMemberById(Long id);

    /**
     * 查询会员
     *
     * @param id 会员ID
     * @return 会员
     */
    public BizMember selectBizMemberSimple(Long id);

    /**
     * 查询会员列表
     * 
     * @param bizMember 会员
     * @return 会员集合
     */
    public List<BizMember> selectBizMemberList(BizMember bizMember);

    /**
     * 会员福豆余额
     *
     * @param memberID type
     * @return 结果
     */
    public Long selectBizMemberDou(Long memberID, int type);

    /**
     * 新增会员
     * 
     * @param bizMember 会员
     * @return 结果
     */
    public int insertBizMember(BizMember bizMember);

    /**
     * 修改会员
     * 
     * @param bizMember 会员
     * @return 结果
     */
    public int updateBizMember(BizMember bizMember);

    /**
     * 修改会员
     *
     * @param bizMember 会员
     * @return 结果
     */
    public int updateBizMemberAndDou(BizMember bizMember);

    /**
     * 批量删除会员
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizMemberByIds(String ids);

    /**
     * 删除会员信息
     * 
     * @param id 会员ID
     * @return 结果
     */
    public int deleteBizMemberById(Long id);

}
