package com.ruoyi.business.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.business.domain.BizMember;

/**
 * 会员Mapper接口
 * 
 * @author ruoyi
 * @date 2020-09-11
 */
public interface BizMemberMapper 
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
     * 查询会员
     *
     * @param mobile 手机号
     * @return 会员
     */
    public BizMember selectBizMemberByMobile(String mobile);

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
     * @param map
     * @return 结果
     */
    public Long selectBizMemberDou(Map map);

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
     * 删除会员
     * 
     * @param id 会员ID
     * @return 结果
     */
    public int deleteBizMemberById(Long id);

    /**
     * 批量删除会员
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizMemberByIds(String[] ids);

    /**
     * 取出有效下级数量(有订单)
     *
     * @param memberID 上级用户ID
     * @return 结果
     */
    public int getValidChildCount(Long memberID);

    /**
     * 取出会员等级明细及商品购买数
     *
     * @param paramMap
     * @return 结果
     */
    public List<Map> selectTeamData(Map paramMap);
}
