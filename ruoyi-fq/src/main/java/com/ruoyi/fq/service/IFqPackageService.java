package com.ruoyi.fq.service;

import com.ruoyi.fq.domain.FqPackage;

import java.util.List;

/**
 * 封铅袋Service接口
 * 
 * @author mario
 * @date 2020-07-02
 */
public interface IFqPackageService 
{
    /**
     * 查询封铅袋
     * 
     * @param id 封铅袋ID
     * @return 封铅袋
     */
    public FqPackage selectFqPackageById(Long id);

    /**
     * 查询封铅袋列表
     * 
     * @param fqPackage 封铅袋
     * @return 封铅袋集合
     */
    public List<FqPackage> selectFqPackageList(FqPackage fqPackage);

    /**
     * 新增封铅袋
     * 
     * @param fqPackage 封铅袋
     * @return 结果
     */
    public int insertFqPackage(FqPackage fqPackage);

    /**
     * 修改封铅袋
     * 
     * @param fqPackage 封铅袋
     * @return 结果
     */
    public int updateFqPackage(FqPackage fqPackage);

    public int updateFqPackageByIds(FqPackage fqPackage,String ids);

    /**
     * 批量删除封铅袋
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFqPackageByIds(String ids);

    /**
     * 删除封铅袋信息
     * 
     * @param id 封铅袋ID
     * @return 结果
     */
    public int deleteFqPackageById(Long id);

    /**
     * 导入数据
     *
     * @param list 数据列表列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    public String importData(List<FqPackage> list, Boolean isUpdateSupport, String operName);

    /**
     * 出库操作
     * 记录日志
     * @param fqPackage
     * @param ids
     * @return
     */
    public int updateCkFqPackageByIds(FqPackage fqPackage,String ids);

    /**
     * 归还操作
     * 记录日志
     * @param fqPackage
     * @param ids
     * @return
     */
    public int updateGhFqPackageByIds(FqPackage fqPackage,String ids);
}
