package com.ruoyi.province.platform.service;

import java.util.List;
import com.ruoyi.province.platform.domain.PurchUserType;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 购货用户类型Service接口
 * 
 * @author dalin
 * @date 2021-01-14
 */
public interface IPurchUserTypeService 
{
    /**
     * 查询购货用户类型
     * 
     * @param purchTypeId 购货用户类型ID
     * @return 购货用户类型
     */
    public PurchUserType selectPurchUserTypeById(Long purchTypeId);

            /**
         * 查询购货用户类型
         *
         * @param purchUserType 购货用户类型ID
         * @return 购货用户类型
         */
        public String checkPurchUserTypeUnique(PurchUserType purchUserType);
    
    /**
     * 查询购货用户类型列表
     * 
     * @param purchUserType 购货用户类型
     * @return 购货用户类型集合
     */
    public List<PurchUserType> selectPurchUserTypeList(PurchUserType purchUserType);

    /**
     * 新增购货用户类型
     * 
     * @param purchUserType 购货用户类型
     * @return 结果
     */
    public int insertPurchUserType(PurchUserType purchUserType);

    /**
     * 修改购货用户类型
     * 
     * @param purchUserType 购货用户类型
     * @return 结果
     */
    public int updatePurchUserType(PurchUserType purchUserType);

    /**
     * 批量删除购货用户类型
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchUserTypeByIds(String ids);

    /**
     * 删除购货用户类型信息
     * 
     * @param purchTypeId 购货用户类型ID
     * @return 结果
     */
    public int deletePurchUserTypeById(Long purchTypeId);

    /**
     * 查询部门管理树（排除下级）
     *
     * @param dept 部门信息
     * @return 所有部门信息
     */
    public List<Ztree> selectPurchUserTypeTreeExcludeChild(PurchUserType purchUserType);

    /**
     * 根据ID查询所有子购货用户类型（正常状态）
     *
     * @param purchTypeId ID
     * @return 子购货用户类型数
     */
    public int selectNormalChildrenPurchUserTypeById(Long purchTypeId);


    /**
     * 查询购货用户类型树列表
     * 
     * @return 所有购货用户类型信息
     */
    public List<Ztree> selectPurchUserTypeTree();
}
