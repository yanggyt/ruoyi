package com.ruoyi.province.platform.service;

import java.util.List;
import com.ruoyi.province.platform.domain.BrandInfo;

/**
 * 品牌资料Service接口
 * 
 * @author dalin
 * @date 2020-12-23
 */
public interface IBrandInfoService 
{
    /**
     * 查询品牌资料
     * 
     * @param brandId 品牌资料ID
     * @return 品牌资料
     */
    public BrandInfo selectBrandInfoById(Long brandId);

            /**
         * 查询品牌资料
         *
         * @param brandId 品牌资料ID
         * @return 品牌资料
         */
        public String checkBrandInfoUnique(BrandInfo brandInfo);
    
    /**
     * 查询品牌资料列表
     * 
     * @param brandInfo 品牌资料
     * @return 品牌资料集合
     */
    public List<BrandInfo> selectBrandInfoList(BrandInfo brandInfo);

    /**
     * 新增品牌资料
     * 
     * @param brandInfo 品牌资料
     * @return 结果
     */
    public int insertBrandInfo(BrandInfo brandInfo);

    /**
     * 修改品牌资料
     * 
     * @param brandInfo 品牌资料
     * @return 结果
     */
    public int updateBrandInfo(BrandInfo brandInfo);

    /**
     * 批量删除品牌资料
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBrandInfoByIds(String ids);

    /**
     * 删除品牌资料信息
     * 
     * @param brandId 品牌资料ID
     * @return 结果
     */
    public int deleteBrandInfoById(Long brandId);
}
