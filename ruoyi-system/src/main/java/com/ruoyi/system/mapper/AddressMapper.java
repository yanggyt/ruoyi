package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Address;

/**
 * 地区信息Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-23
 */
public interface AddressMapper 
{
    /**
     * 查询地区信息
     * 
     * @param id 地区信息ID
     * @return 地区信息
     */
    Address selectAddressById(Long id);

    /**
     * 查询地区信息列表
     * 
     * @param address 地区信息
     * @return 地区信息集合
     */
    List<Address> selectAddressList(Address address);

    /**
     * 新增地区信息
     * 
     * @param address 地区信息
     * @return 结果
     */
    int insertAddress(Address address);

    /**
     * 修改地区信息
     * 
     * @param address 地区信息
     * @return 结果
     */
    int updateAddress(Address address);

    /**
     * 删除地区信息
     * 
     * @param id 地区信息ID
     * @return 结果
     */
    int deleteAddressById(Long id);

    /**
     * 批量删除地区信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    int deleteAddressByIds(String[] ids);

    /**
     * 查询地区信息列表
     * @param parentCode
     * @return
     */
    List<Address> selectAddressByParentCode(String parentCode);
}
