package com.ruoyi.front.service;

import java.util.List;
import com.ruoyi.front.domain.ContractType;

/**
 * 合同分类Service接口
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
public interface IContractTypeService 
{
    /**
     * 查询合同分类
     * 
     * @param id 合同分类ID
     * @return 合同分类
     */
    public ContractType selectContractTypeById(Long id);

    /**
     * 查询合同分类列表
     * 
     * @param contractType 合同分类
     * @return 合同分类集合
     */
    public List<ContractType> selectContractTypeList(ContractType contractType);

    /**
     * 新增合同分类
     * 
     * @param contractType 合同分类
     * @return 结果
     */
    public int insertContractType(ContractType contractType);

    /**
     * 修改合同分类
     * 
     * @param contractType 合同分类
     * @return 结果
     */
    public int updateContractType(ContractType contractType);

    /**
     * 批量删除合同分类
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteContractTypeByIds(String ids);

    /**
     * 删除合同分类信息
     * 
     * @param id 合同分类ID
     * @return 结果
     */
    public int deleteContractTypeById(Long id);
}
