package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AddressMapper;
import com.ruoyi.system.domain.Address;
import com.ruoyi.system.service.IAddressService;
import com.ruoyi.common.core.text.Convert;

/**
 * 地区信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-23
 */
@Service("iAddressService")
public class AddressServiceImpl implements IAddressService 
{
    @Autowired
    private AddressMapper addressMapper;

    /**
     * 查询地区信息
     * 
     * @param id 地区信息ID
     * @return 地区信息
     */
    @Override
    public Address selectAddressById(Long id)
    {
        return addressMapper.selectAddressById(id);
    }


    /**
     * 查询地区信息
     *
     * @param parentCode
     * @return 地区信息
     */
    @Override
    public List<Address> selectAddressByParentCode(String parentCode)
    {
        return addressMapper.selectAddressByParentCode(parentCode);
    }

    /**
     * 查询地区信息列表
     * 
     * @param address 地区信息
     * @return 地区信息
     */
    @Override
    public List<Address> selectAddressList(Address address)
    {
        return addressMapper.selectAddressList(address);
    }

    /**
     * 新增地区信息
     * 
     * @param address 地区信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertAddress(Address address)
    {
        return addressMapper.insertAddress(address);
    }

    /**
     * 修改地区信息
     * 
     * @param address 地区信息
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateAddress(Address address)
    {
        return addressMapper.updateAddress(address);
    }

    /**
     * 删除地区信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAddressByIds(String ids)
    {
        return addressMapper.deleteAddressByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除地区信息信息
     * 
     * @param id 地区信息ID
     * @return 结果
     */
    @Override
    public int deleteAddressById(Long id)
    {
        return addressMapper.deleteAddressById(id);
    }
}
