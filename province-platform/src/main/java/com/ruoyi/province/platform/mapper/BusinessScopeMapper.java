package com.ruoyi.province.platform.mapper;

import com.ruoyi.province.platform.domain.BusinessScope;

import java.util.List;

/**
 * 业务规模Mapper接口
 * 
 * @author dalin
 * @date 2020-12-24
 */
public interface BusinessScopeMapper 
{
    /**
     * 查询业务规模
     * 
     * @param businessScaleId 业务规模ID
     * @return 业务规模
     */
    public BusinessScope selectBusinessScopeById(Long businessScaleId);

            /**
         * 校验 业务规模 名称是否重复
         *
         * @param BusinessScaleName
         * @return 业务规模
         */
        public BusinessScope checkBusinessScopeUnique(String BusinessScaleName);
    
    /**
     * 查询业务规模列表
     * 
     * @param businessScope 业务规模
     * @return 业务规模集合
     */
    public List<BusinessScope> selectBusinessScopeList(BusinessScope businessScope);

    /**
     * 新增业务规模
     * 
     * @param businessScope 业务规模
     * @return 结果
     */
    public int insertBusinessScope(BusinessScope businessScope);

    /**
     * 修改业务规模
     * 
     * @param businessScope 业务规模
     * @return 结果
     */
    public int updateBusinessScope(BusinessScope businessScope);

    /**
     * 删除业务规模
     * 
     * @param businessScaleId 业务规模ID
     * @return 结果
     */
    public int deleteBusinessScopeById(Long businessScaleId);

    /**
     * 批量删除业务规模
     * 
     * @param businessScaleIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusinessScopeByIds(String[] businessScaleIds);
}
