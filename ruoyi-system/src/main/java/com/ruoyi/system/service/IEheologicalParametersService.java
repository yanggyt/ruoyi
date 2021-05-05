package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.EheologicalParameters;

/**
 * 流变参数Service接口
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
public interface IEheologicalParametersService 
{
    /**
     * 查询流变参数
     * 
     * @param rheologicalParameternumber 流变参数ID
     * @return 流变参数
     */
    public EheologicalParameters selectEheologicalParametersById(Long rheologicalParameternumber);

    /**
     * 查询流变参数列表
     * 
     * @param eheologicalParameters 流变参数
     * @return 流变参数集合
     */
    public List<EheologicalParameters> selectEheologicalParametersList(EheologicalParameters eheologicalParameters);

    /**
     * 新增流变参数
     * 
     * @param eheologicalParameters 流变参数
     * @return 结果
     */
    public int insertEheologicalParameters(EheologicalParameters eheologicalParameters);

    /**
     * 修改流变参数
     * 
     * @param eheologicalParameters 流变参数
     * @return 结果
     */
    public int updateEheologicalParameters(EheologicalParameters eheologicalParameters);

    /**
     * 批量删除流变参数
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEheologicalParametersByIds(String ids);

    /**
     * 删除流变参数信息
     * 
     * @param rheologicalParameternumber 流变参数ID
     * @return 结果
     */
    public int deleteEheologicalParametersById(Long rheologicalParameternumber);
}
