package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Address;

/**
 * 地区信息Service接口
 * 
 * @author ruoyi
 * @date 2020-05-23
 */
public interface IAddressService 
{
    /**
     * 查询地区信息
     * 
     * @param id 地区信息ID
     * @return 地区信息
     */
    public Address selectAddressById(Long id);

    /**
     * 查询地区信息
     * @param parentCode
     * @return 地区信息
     */
    List<Address> selectAddressByParentCode(String parentCode);

    /**
     * 查询地区信息列表
     * 
     * @param address 地区信息
     * @return 地区信息集合
     */
    public List<Address> selectAddressList(Address address);

    /**
     * 新增地区信息
     * 
     * @param address 地区信息
     * @return 结果
     */
    public int insertAddress(Address address);

    /**
     * 修改地区信息
     * 
     * @param address 地区信息
     * @return 结果
     */
    public int updateAddress(Address address);

    /**
     * 批量删除地区信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAddressByIds(String ids);

    /**
     * 删除地区信息信息
     * 
     * @param id 地区信息ID
     * @return 结果
     */
    public int deleteAddressById(Long id);
}
