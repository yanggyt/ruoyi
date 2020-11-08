package com.ruoyi.front.service.impl;

import java.util.List;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.enums.UserStatus;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.front.mapper.ContractTypeMapper;
import com.ruoyi.front.domain.ContractType;
import com.ruoyi.front.service.IContractTypeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 合同分类Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Service
public class ContractTypeServiceImpl implements IContractTypeService 
{
    @Autowired
    private ContractTypeMapper contractTypeMapper;

    /**
     * 查询 正常的合同分类列表
     *
     * @return 合同分类
     */
    @Override
    public List<ContractType> getNormalContractTypeList() {
        ContractType contractType = new ContractType();
        contractType.setDelFlag(Constants.NO_DELETE);
        return this.selectContractTypeList(contractType);
    }

    /**
     * 查询合同分类
     * 
     * @param id 合同分类ID
     * @return 合同分类
     */
    @Override
    public ContractType selectContractTypeById(Long id)
    {
        return contractTypeMapper.selectContractTypeById(id);
    }

    /**
     * 查询合同分类列表
     * 
     * @param contractType 合同分类
     * @return 合同分类
     */
    @Override
    public List<ContractType> selectContractTypeList(ContractType contractType)
    {
        return contractTypeMapper.selectContractTypeList(contractType);
    }

    /**
     * 新增合同分类
     * 
     * @param contractType 合同分类
     * @return 结果
     */
    @Override
    public int insertContractType(ContractType contractType)
    {
        contractType.setCreateTime(DateUtils.getNowDate());
        return contractTypeMapper.insertContractType(contractType);
    }

    /**
     * 修改合同分类
     * 
     * @param contractType 合同分类
     * @return 结果
     */
    @Override
    public int updateContractType(ContractType contractType)
    {
        contractType.setUpdateTime(DateUtils.getNowDate());
        return contractTypeMapper.updateContractType(contractType);
    }

    /**
     * 删除合同分类对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteContractTypeByIds(String ids)
    {
        return contractTypeMapper.deleteContractTypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除合同分类信息
     * 
     * @param id 合同分类ID
     * @return 结果
     */
    @Override
    public int deleteContractTypeById(Long id)
    {
        return contractTypeMapper.deleteContractTypeById(id);
    }
}
