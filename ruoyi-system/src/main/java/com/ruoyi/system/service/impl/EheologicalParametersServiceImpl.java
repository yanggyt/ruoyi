package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.EheologicalParametersMapper;
import com.ruoyi.system.domain.EheologicalParameters;
import com.ruoyi.system.service.IEheologicalParametersService;
import com.ruoyi.common.core.text.Convert;

/**
 * 流变参数Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
@Service
public class EheologicalParametersServiceImpl implements IEheologicalParametersService 
{
    @Autowired
    private EheologicalParametersMapper eheologicalParametersMapper;

    /**
     * 查询流变参数
     * 
     * @param rheologicalParameternumber 流变参数ID
     * @return 流变参数
     */
    @Override
    public EheologicalParameters selectEheologicalParametersById(Long rheologicalParameternumber)
    {
        return eheologicalParametersMapper.selectEheologicalParametersById(rheologicalParameternumber);
    }

    /**
     * 查询流变参数列表
     * 
     * @param eheologicalParameters 流变参数
     * @return 流变参数
     */
    @Override
    public List<EheologicalParameters> selectEheologicalParametersList(EheologicalParameters eheologicalParameters)
    {
        return eheologicalParametersMapper.selectEheologicalParametersList(eheologicalParameters);
    }

    /**
     * 新增流变参数
     * 
     * @param eheologicalParameters 流变参数
     * @return 结果
     */
    @Override
    public int insertEheologicalParameters(EheologicalParameters eheologicalParameters)
    {
        return eheologicalParametersMapper.insertEheologicalParameters(eheologicalParameters);
    }

    /**
     * 修改流变参数
     * 
     * @param eheologicalParameters 流变参数
     * @return 结果
     */
    @Override
    public int updateEheologicalParameters(EheologicalParameters eheologicalParameters)
    {
        return eheologicalParametersMapper.updateEheologicalParameters(eheologicalParameters);
    }

    /**
     * 删除流变参数对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEheologicalParametersByIds(String ids)
    {
        return eheologicalParametersMapper.deleteEheologicalParametersByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除流变参数信息
     * 
     * @param rheologicalParameternumber 流变参数ID
     * @return 结果
     */
    @Override
    public int deleteEheologicalParametersById(Long rheologicalParameternumber)
    {
        return eheologicalParametersMapper.deleteEheologicalParametersById(rheologicalParameternumber);
    }
}
