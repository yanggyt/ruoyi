package com.ruoyi.business.mapper;


import com.ruoyi.business.domain.BizMemberAddress;

import java.util.List;

/**
 * 会员收货地址Mapper接口
 *
 * @author ruoyi
 * @date 2020-09-17
 */
public interface BizMemberAddressMapper
{
    /**
     * 查询会员收货地址
     *
     * @param id 会员收货地址ID
     * @return 会员收货地址
     */
    public BizMemberAddress selectBizMemberAddressById(Long id);

    /**
     * 查询会员默认收货地址
     *
     * @param memberID 会员ID
     * @return 会员收货地址
     */
    public BizMemberAddress selectDefaultAddressByMemberId(Long memberID);

    /**
     * 取消默认收货地址
     *
     * @param memberID 会员ID
     * @return int
     */
    public int cancelDefaultAddress(Long memberID);

    /**
     * 查询会员收货地址列表
     *
     * @param memberID 会员ID
     * @return 会员收货地址集合
     */
    public List<BizMemberAddress> selectBizMemberAddressList(Long memberID);

    /**
     * 新增会员收货地址
     *
     * @param bizMemberAddress 会员收货地址
     * @return 结果
     */
    public int insertBizMemberAddress(BizMemberAddress bizMemberAddress);

    /**
     * 修改会员收货地址
     *
     * @param bizMemberAddress 会员收货地址
     * @return 结果
     */
    public int updateBizMemberAddress(BizMemberAddress bizMemberAddress);

    /**
     * 删除会员收货地址
     *
     * @param id 会员收货地址ID
     * @return 结果
     */
    public int deleteBizMemberAddressById(Long id);

    /**
     * 批量删除会员收货地址
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizMemberAddressByIds(String[] ids);
}