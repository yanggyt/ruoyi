package cn.com.infosouth.arj21.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.infosouth.arj21.mapper.InfoParameterComputedMapper;
import cn.com.infosouth.arj21.domain.InfoParameterComputed;
import cn.com.infosouth.arj21.service.IInfoParameterComputedService;
import cn.com.infosouth.common.core.text.Convert;

/**
 * 计算参数Service业务层处理
 * 
 * @author kxnf
 * @date 2020-03-05
 */
@Service
public class InfoParameterComputedServiceImpl implements IInfoParameterComputedService 
{
    @Autowired
    private InfoParameterComputedMapper infoParameterComputedMapper;

    /**
     * 查询计算参数
     * 
     * @param id 计算参数ID
     * @return 计算参数
     */
    @Override
    public InfoParameterComputed selectInfoParameterComputedById(String id)
    {
        return infoParameterComputedMapper.selectInfoParameterComputedById(id);
    }

    /**
     * 查询计算参数列表
     * 
     * @param infoParameterComputed 计算参数
     * @return 计算参数
     */
    @Override
    public List<InfoParameterComputed> selectInfoParameterComputedList(InfoParameterComputed infoParameterComputed)
    {
        return infoParameterComputedMapper.selectInfoParameterComputedList(infoParameterComputed);
    }

    /**
     * 新增计算参数
     * 
     * @param infoParameterComputed 计算参数
     * @return 结果
     */
    @Override
    public int insertInfoParameterComputed(InfoParameterComputed infoParameterComputed)
    {
        return infoParameterComputedMapper.insertInfoParameterComputed(infoParameterComputed);
    }

    /**
     * 修改计算参数
     * 
     * @param infoParameterComputed 计算参数
     * @return 结果
     */
    @Override
    public int updateInfoParameterComputed(InfoParameterComputed infoParameterComputed)
    {
        return infoParameterComputedMapper.updateInfoParameterComputed(infoParameterComputed);
    }

    /**
     * 删除计算参数对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteInfoParameterComputedByIds(String ids)
    {
        return infoParameterComputedMapper.deleteInfoParameterComputedByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除计算参数信息
     * 
     * @param id 计算参数ID
     * @return 结果
     */
    @Override
    public int deleteInfoParameterComputedById(String id)
    {
        return infoParameterComputedMapper.deleteInfoParameterComputedById(id);
    }

	@Override
	public List<InfoParameterComputed> findList(InfoParameterComputed infoParameterComputed) {
		return infoParameterComputedMapper.selectInfoParameterComputedList(infoParameterComputed);
	}
}
