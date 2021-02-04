package com.ruoyi.province.platform.service;

import java.util.List;
import com.ruoyi.province.platform.domain.Regional;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 地区资料Service接口
 * 
 * @author dalin
 * @date 2021-01-11
 */
public interface IRegionalService 
{
    /**
     * 查询地区资料
     * 
     * @param regionalId 地区资料ID
     * @return 地区资料
     */
    public Regional selectRegionalById(Long regionalId);

            /**
         * 查询地区资料
         *
         * @param regional 地区资料ID
         * @return 地区资料
         */
        public String checkRegionalUnique(Regional regional);
    
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
     * 批量删除地区资料
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRegionalByIds(String ids);

    /**
     * 删除地区资料信息
     * 
     * @param regionalId 地区资料ID
     * @return 结果
     */
    public int deleteRegionalById(Long regionalId);

    /**
     * 查询地区资料树列表
     * 
     * @return 所有地区资料信息
     */
    public List<Ztree> selectRegionalTree();
}
