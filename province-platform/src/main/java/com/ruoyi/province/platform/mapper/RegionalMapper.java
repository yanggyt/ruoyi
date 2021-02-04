package com.ruoyi.province.platform.mapper;

import java.util.List;
import com.ruoyi.province.platform.domain.Regional;

/**
 * 地区资料Mapper接口
 * 
 * @author dalin
 * @date 2021-01-11
 */
public interface RegionalMapper 
{
    /**
     * 查询地区资料
     * 
     * @param regionalId 地区资料ID
     * @return 地区资料
     */
    public Regional selectRegionalById(Long regionalId);

            /**
         * 校验 地区资料 名称是否重复
         *
         * @param RegionalName
         * @return 地区资料
         */
        public Regional checkRegionalUnique(String RegionalName);
    
    /**
     * 查询地区资料列表
     * 
     * @param regional 地区资料
     * @return 地区资料集合
     */
    public List<Regional> selectRegionalList(Regional regional);

    /**
     * 新增地区资料
     * 
     * @param regional 地区资料
     * @return 结果
     */
    public int insertRegional(Regional regional);

    /**
     * 修改地区资料
     * 
     * @param regional 地区资料
     * @return 结果
     */
    public int updateRegional(Regional regional);

    /**
     * 删除地区资料
     * 
     * @param regionalId 地区资料ID
     * @return 结果
     */
    public int deleteRegionalById(Long regionalId);

    /**
     * 批量删除地区资料
     * 
     * @param regionalIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteRegionalByIds(String[] regionalIds);
}
