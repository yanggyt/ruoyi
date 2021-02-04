package com.ruoyi.province.platform.mapper;

import java.util.List;
import com.ruoyi.province.platform.domain.SupplyChainHier;
import org.apache.ibatis.annotations.Param;

/**
 * 供应链层级Mapper接口
 * 
 * @author dalin
 * @date 2021-01-14
 */
public interface SupplyChainHierMapper 
{
    /**
     * 查询供应链层级
     * 
     * @param supplyChainId 供应链层级ID
     * @return 供应链层级
     */
    public SupplyChainHier selectSupplyChainHierById(Long supplyChainId);

            /**
         * 校验 供应链层级 名称是否重复
         *
         * @param SupplyChainName
         * @return 供应链层级
         */
        public SupplyChainHier checkSupplyChainHierUnique(String SupplyChainName);
    
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
     * 删除供应链层级
     * 
     * @param supplyChainId 供应链层级ID
     * @return 结果
     */
    public int deleteSupplyChainHierById(Long supplyChainId);

    /**
     * 批量删除供应链层级
     * 
     * @param supplyChainIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteSupplyChainHierByIds(String[] supplyChainIds);


    /**
     * 修改子元素关系
     *
     * @param supplyChainHiers 子元素
     * @return 结果
     */
    public int updateSupplyChainHierChildren(@Param("supplyChainHiers") List<SupplyChainHier> supplyChainHier);


    /**
     * 根据ID查询所有子部门
     *
     * @param supplyChainHierId 部门ID
     * @return 部门列表
     */
    public List<SupplyChainHier> selectChildrenSupplyChainHierById(Long supplyChainId);

    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param supplyChainId 部门ID
     * @return 子部门数
     */
    public int selectNormalChildrenSupplyChainHierById(Long supplyChainId);

    /**
     * 修改所在部门的父级部门状态
     *
     * @param supplyChainHier 部门
     */
    public void updateSupplyChainHierStatus(SupplyChainHier supplyChainHier);


}
