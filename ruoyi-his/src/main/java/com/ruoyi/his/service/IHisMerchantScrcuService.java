package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisMerchantScrcu;

import java.util.List;

/**
 * 农信商户Service接口
 * 
 * @author bend
 * @date 2020-07-27
 */
public interface IHisMerchantScrcuService
{
    /**
     * 查询农信商户
     * 
     * @param id 农信商户ID
     * @return 农信商户
     */
    public HisMerchantScrcu selectHisMerchantScrcuById(Long id);

    /**
     * 查询农信商户
     *
     * @param hisMerchantScrcu 农信商户
     * @return 农信商户
     */
    public HisMerchantScrcu selectHisMerchantScrcu(HisMerchantScrcu hisMerchantScrcu);

    /**
     * 查询农信商户列表
     * 
     * @param hisMerchantScrcu 农信商户
     * @return 农信商户集合
     */
    public List<HisMerchantScrcu> selectHisMerchantScrcuList(HisMerchantScrcu hisMerchantScrcu);

    /**
     * 新增农信商户
     * 
     * @param hisMerchantScrcu 农信商户
     * @return 结果
     */
    public int insertHisMerchantScrcu(HisMerchantScrcu hisMerchantScrcu);

    /**
     * 批量新增农信商户
     *
     * @param hisMerchantScrcuList 农信商户列表
     * @return 结果
     */
    public int insertHisMerchantScrcuBatch(List<HisMerchantScrcu> hisMerchantScrcuList);

    /**
     * 修改农信商户
     * 
     * @param hisMerchantScrcu 农信商户
     * @return 结果
     */
    public int updateHisMerchantScrcu(HisMerchantScrcu hisMerchantScrcu);

    /**
     * 批量删除农信商户
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisMerchantScrcuByIds(String ids);

    /**
     * 删除农信商户信息
     * 
     * @param id 农信商户ID
     * @return 结果
     */
    public int deleteHisMerchantScrcuById(Long id);
}
