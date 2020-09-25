package com.ruoyi.business.service.impl;

import com.ruoyi.business.domain.BizMemberAddress;
import com.ruoyi.business.mapper.BizMemberAddressMapper;
import com.ruoyi.business.service.IBizMemberAddressService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 * 会员收货地址Service业务层处理
 *
 * @author ruoyi
 * @date 2020-09-17
 */
@Service
public class BizMemberAddressServiceImpl implements IBizMemberAddressService
{
    @Resource
    private BizMemberAddressMapper bizMemberAddressMapper;

    /**
     * 查询会员收货地址
     *
     * @param id 会员收货地址ID
     * @return 会员收货地址
     */
    @Override
    public BizMemberAddress selectBizMemberAddressById(Long id)
    {
        return bizMemberAddressMapper.selectBizMemberAddressById(id);
    }

    /**
     * 查询会员默认收货地址
     *
     * @param memberID 会员ID
     * @return 会员收货地址
     */
    @Override
    public BizMemberAddress selectDefaultAddressByMemberId(Long memberID)
    {
        return bizMemberAddressMapper.selectDefaultAddressByMemberId(memberID);
    }

    /**
     * 取消默认收货地址
     *
     * @param memberID 会员ID
     * @return int
     */
    @Override
    public int cancelDefaultAddress(Long memberID)
    {
        return bizMemberAddressMapper.cancelDefaultAddress(memberID);
    }

    /**
     * 查询会员收货地址列表
     *
     * @param memberID 会员ID
     * @return 会员收货地址
     */
    @Override
    public List<BizMemberAddress> selectBizMemberAddressList(Long memberID)
    {
        return bizMemberAddressMapper.selectBizMemberAddressList(memberID);
    }

    /**
     * 新增会员收货地址
     *
     * @param bizMemberAddress 会员收货地址
     * @return 结果
     */
    @Override
    public int insertBizMemberAddress(BizMemberAddress bizMemberAddress)
    {
        bizMemberAddress.setCreateTime(DateUtils.getNowDate());
        if (bizMemberAddress.getIsDelete() == 1) {  //默认收货地址
            bizMemberAddressMapper.cancelDefaultAddress(bizMemberAddress.getMemberID());
        }
        return bizMemberAddressMapper.insertBizMemberAddress(bizMemberAddress);
    }

    /**
     * 修改会员收货地址
     *
     * @param bizMemberAddress 会员收货地址
     * @return 结果
     */
    @Override
    public int updateBizMemberAddress(BizMemberAddress bizMemberAddress)
    {
        bizMemberAddress.setUpdateTime(DateUtils.getNowDate());
        if (bizMemberAddress.getIsDelete() == 1) {  //默认收货地址
            bizMemberAddressMapper.cancelDefaultAddress(bizMemberAddress.getMemberID());
        }
        return bizMemberAddressMapper.updateBizMemberAddress(bizMemberAddress);
    }

    /**
     * 删除会员收货地址信息
     *
     * @param id 会员收货地址ID
     * @return 结果
     */
    @Override
    public int deleteBizMemberAddressById(Long id)
    {
        return bizMemberAddressMapper.deleteBizMemberAddressById(id);
    }
}