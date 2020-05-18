package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.BusinessFirm;

/**
 * 商家信息Service接口
 * 
 * @author ruoyi
 * @date 2020-05-19
 */
public interface IBusinessFirmService 
{
    /**
     * 查询商家信息
     * 
     * @param id 商家信息ID
     * @return 商家信息
     */
    public BusinessFirm selectBusinessFirmById(Long id);

    /**
     * 查询商家信息列表
     * 
     * @param businessFirm 商家信息
     * @return 商家信息集合
     */
    public List<BusinessFirm> selectBusinessFirmList(BusinessFirm businessFirm);

    /**
     * 新增商家信息
     * 
     * @param businessFirm 商家信息
     * @return 结果
     */
    public int insertBusinessFirm(BusinessFirm businessFirm);

    /**
     * 修改商家信息
     * 
     * @param businessFirm 商家信息
     * @return 结果
     */
    public int updateBusinessFirm(BusinessFirm businessFirm);

    /**
     * 批量删除商家信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusinessFirmByIds(String ids);

    /**
     * 删除商家信息信息
     * 
     * @param id 商家信息ID
     * @return 结果
     */
    public int deleteBusinessFirmById(Long id);
}
