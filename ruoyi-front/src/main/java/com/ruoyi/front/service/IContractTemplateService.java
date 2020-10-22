package com.ruoyi.front.service;

import java.util.List;
import com.ruoyi.front.domain.ContractTemplate;

/**
 * 合同模板Service接口
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
public interface IContractTemplateService 
{
    /**
     * 查询合同模板
     * 
     * @param id 合同模板ID
     * @return 合同模板
     */
    public ContractTemplate selectContractTemplateById(Long id);

    /**
     * 查询合同模板列表
     * 
     * @param contractTemplate 合同模板
     * @return 合同模板集合
     */
    public List<ContractTemplate> selectContractTemplateList(ContractTemplate contractTemplate);

    /**
     * 新增合同模板
     * 
     * @param contractTemplate 合同模板
     * @return 结果
     */
    public int insertContractTemplate(ContractTemplate contractTemplate);

    /**
     * 修改合同模板
     * 
     * @param contractTemplate 合同模板
     * @return 结果
     */
    public int updateContractTemplate(ContractTemplate contractTemplate);

    /**
     * 批量删除合同模板
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteContractTemplateByIds(String ids);

    /**
     * 删除合同模板信息
     * 
     * @param id 合同模板ID
     * @return 结果
     */
    public int deleteContractTemplateById(Long id);
}
