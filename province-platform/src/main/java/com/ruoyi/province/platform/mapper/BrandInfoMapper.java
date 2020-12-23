package com.ruoyi.province.platform.mapper;

import com.ruoyi.province.platform.domain.BrandInfo;

import java.util.List;

/**
 * 品牌资料Mapper接口
 * 
 * @author dalin
 * @date 2020-12-23
 */
public interface BrandInfoMapper 
{
    /**
     * 查询品牌资料
     * 
     * @param brandId 品牌资料ID
     * @return 品牌资料
     */
    public BrandInfo selectBrandInfoById(Long brandId);

            /**
         * 校验 品牌资料 名称是否重复
         *
         * @param BrandName
         * @return 品牌资料
         */
        public BrandInfo checkBrandInfoUnique(String BrandName);
    
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
     * 删除品牌资料
     * 
     * @param brandId 品牌资料ID
     * @return 结果
     */
    public int deleteBrandInfoById(Long brandId);

    /**
     * 批量删除品牌资料
     * 
     * @param brandIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBrandInfoByIds(String[] brandIds);
}
