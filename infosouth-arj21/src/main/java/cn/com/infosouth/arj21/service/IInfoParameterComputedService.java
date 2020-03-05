package cn.com.infosouth.arj21.service;

import java.util.List;
import cn.com.infosouth.arj21.domain.InfoParameterComputed;

/**
 * 计算参数Service接口
 * 
 * @author kxnf
 * @date 2020-03-05
 */
public interface IInfoParameterComputedService 
{
    /**
     * 查询计算参数
     * 
     * @param id 计算参数ID
     * @return 计算参数
     */
    public InfoParameterComputed selectInfoParameterComputedById(String id);

    /**
     * 查询计算参数列表
     * 
     * @param infoParameterComputed 计算参数
     * @return 计算参数集合
     */
    public List<InfoParameterComputed> selectInfoParameterComputedList(InfoParameterComputed infoParameterComputed);

    /**
     * 新增计算参数
     * 
     * @param infoParameterComputed 计算参数
     * @return 结果
     */
    public int insertInfoParameterComputed(InfoParameterComputed infoParameterComputed);

    /**
     * 修改计算参数
     * 
     * @param infoParameterComputed 计算参数
     * @return 结果
     */
    public int updateInfoParameterComputed(InfoParameterComputed infoParameterComputed);

    /**
     * 批量删除计算参数
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteInfoParameterComputedByIds(String ids);

    /**
     * 删除计算参数信息
     * 
     * @param id 计算参数ID
     * @return 结果
     */
    public int deleteInfoParameterComputedById(String id);
}
