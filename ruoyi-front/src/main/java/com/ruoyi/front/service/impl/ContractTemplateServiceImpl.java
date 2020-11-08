package com.ruoyi.front.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.front.mapper.ContractTemplateMapper;
import com.ruoyi.front.domain.ContractTemplate;
import com.ruoyi.front.service.IContractTemplateService;
import com.ruoyi.common.core.text.Convert;

/**
 * 合同模板Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Service
public class ContractTemplateServiceImpl implements IContractTemplateService 
{
    @Autowired
    private ContractTemplateMapper contractTemplateMapper;

    /**
     * 查询合同模板
     * 
     * @param id 合同模板ID
     * @return 合同模板
     */
    @Override
    public ContractTemplate selectContractTemplateById(Long id)
    {
        return contractTemplateMapper.selectContractTemplateById(id);
    }

    /**
     * 查询合同模板列表
     * 
     * @param contractTemplate 合同模板
     * @return 合同模板
     */
    @Override
    public List<ContractTemplate> selectContractTemplateList(ContractTemplate contractTemplate)
    {
        return contractTemplateMapper.selectContractTemplateList(contractTemplate);
    }

    /**
     * 新增合同模板
     * 
     * @param contractTemplate 合同模板
     * @return 结果
     */
    @Override
    public int insertContractTemplate(ContractTemplate contractTemplate)
    {
        contractTemplate.setCreateTime(DateUtils.getNowDate());
        return contractTemplateMapper.insertContractTemplate(contractTemplate);
    }

    /**
     * 修改合同模板
     * 
     * @param contractTemplate 合同模板
     * @return 结果
     */
    @Override
    public int updateContractTemplate(ContractTemplate contractTemplate)
    {
        contractTemplate.setUpdateTime(DateUtils.getNowDate());
        return contractTemplateMapper.updateContractTemplate(contractTemplate);
    }

    /**
     * 删除合同模板对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteContractTemplateByIds(String ids)
    {
        return contractTemplateMapper.deleteContractTemplateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除合同模板信息
     * 
     * @param id 合同模板ID
     * @return 结果
     */
    @Override
    public int deleteContractTemplateById(Long id)
    {
        return contractTemplateMapper.deleteContractTemplateById(id);
    }
}
