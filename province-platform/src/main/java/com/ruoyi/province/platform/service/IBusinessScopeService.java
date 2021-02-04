package com.ruoyi.province.platform.service;

import java.util.List;
import com.ruoyi.province.platform.domain.BusinessScope;

/**
 * 业务规模Service接口
 * 
 * @author dalin
 * @date 2020-12-24
 */
public interface IBusinessScopeService 
{
    /**
     * 查询业务规模
     * 
     * @param businessScaleId 业务规模ID
     * @return 业务规模
     */
    public BusinessScope selectBusinessScopeById(Long businessScaleId);

            /**
         * 查询业务规模
         *
         * @param businessScope 业务规模ID
         * @return 业务规模
         */
        public String checkBusinessScopeUnique(BusinessScope businessScope);
    
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
     * 批量删除业务规模
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusinessScopeByIds(String ids);

    /**
     * 删除业务规模信息
     * 
     * @param businessScaleId 业务规模ID
     * @return 结果
     */
    public int deleteBusinessScopeById(Long businessScaleId);
}
