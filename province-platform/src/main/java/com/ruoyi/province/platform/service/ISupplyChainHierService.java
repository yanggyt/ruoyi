package com.ruoyi.province.platform.service;

import java.util.List;
import com.ruoyi.province.platform.domain.SupplyChainHier;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 供应链层级Service接口
 * 
 * @author dalin
 * @date 2021-01-14
 */
public interface ISupplyChainHierService 
{
    /**
     * 查询供应链层级
     * 
     * @param supplyChainId 供应链层级ID
     * @return 供应链层级
     */
    public SupplyChainHier selectSupplyChainHierById(Long supplyChainId);

            /**
         * 查询供应链层级
         *
         * @param supplyChainHier 供应链层级ID
         * @return 供应链层级
         */
        public String checkSupplyChainHierUnique(SupplyChainHier supplyChainHier);
    
    /**
     * 查询供应链层级列表
     * 
     * @param supplyChainHier 供应链层级
     * @return 供应链层级集合
     */
    public List<SupplyChainHier> selectSupplyChainHierList(SupplyChainHier supplyChainHier);

    /**
     * 新增供应链层级
     * 
     * @param supplyChainHier 供应链层级
     * @return 结果
     */
    public int insertSupplyChainHier(SupplyChainHier supplyChainHier);

    /**
     * 修改供应链层级
     * 
     * @param supplyChainHier 供应链层级
     * @return 结果
     */
    public int updateSupplyChainHier(SupplyChainHier supplyChainHier);

    /**
     * 批量删除供应链层级
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSupplyChainHierByIds(String ids);

    /**
     * 删除供应链层级信息
     * 
     * @param supplyChainId 供应链层级ID
     * @return 结果
     */
    public int deleteSupplyChainHierById(Long supplyChainId);

    /**
     * 查询部门管理树（排除下级）
     *
     * @param dept 部门信息
     * @return 所有部门信息
     */
    public List<Ztree> selectSupplyChainHierTreeExcludeChild(SupplyChainHier supplyChainHier);

    /**
     * 根据ID查询所有子供应链层级（正常状态）
     *
     * @param supplyChainId ID
     * @return 子供应链层级数
     */
    public int selectNormalChildrenSupplyChainHierById(Long supplyChainId);


    /**
     * 查询供应链层级树列表
     * 
     * @return 所有供应链层级信息
     */
    public List<Ztree> selectSupplyChainHierTree();
}
