package com.ruoyi.province.platform.mapper;

import java.util.List;
import com.ruoyi.province.platform.domain.PurchUserType;
import org.apache.ibatis.annotations.Param;

/**
 * 购货用户类型Mapper接口
 * 
 * @author dalin
 * @date 2021-01-14
 */
public interface PurchUserTypeMapper 
{
    /**
     * 查询购货用户类型
     * 
     * @param purchTypeId 购货用户类型ID
     * @return 购货用户类型
     */
    public PurchUserType selectPurchUserTypeById(Long purchTypeId);

            /**
         * 校验 购货用户类型 名称是否重复
         *
         * @param PurchTypeName
         * @return 购货用户类型
         */
        public PurchUserType checkPurchUserTypeUnique(String PurchTypeName);
    
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
     * 删除购货用户类型
     * 
     * @param purchTypeId 购货用户类型ID
     * @return 结果
     */
    public int deletePurchUserTypeById(Long purchTypeId);

    /**
     * 批量删除购货用户类型
     * 
     * @param purchTypeIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePurchUserTypeByIds(String[] purchTypeIds);


    /**
     * 修改子元素关系
     *
     * @param purchUserTypes 子元素
     * @return 结果
     */
    public int updatePurchUserTypeChildren(@Param("purchUserTypes") List<PurchUserType> purchUserType);


    /**
     * 根据ID查询所有子部门
     *
     * @param purchUserTypeId 部门ID
     * @return 部门列表
     */
    public List<PurchUserType> selectChildrenPurchUserTypeById(Long purchTypeId);

    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param purchTypeId 部门ID
     * @return 子部门数
     */
    public int selectNormalChildrenPurchUserTypeById(Long purchTypeId);

    /**
     * 修改所在部门的父级部门状态
     *
     * @param purchUserType 部门
     */
    public void updatePurchUserTypeStatus(PurchUserType purchUserType);


}
