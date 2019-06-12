package com.ruoyi.template.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.template.mapper.TmplSwitchMapper;
import com.ruoyi.template.domain.TmplSwitch;
import com.ruoyi.template.service.ITmplSwitchService;
import com.ruoyi.common.core.text.Convert;

/**
 * 交换机模板 服务层实现
 * 
 * @author TP
 * @date 2019-06-12
 */
@Service
public class TmplSwitchServiceImpl implements ITmplSwitchService 
{
	@Autowired
	private TmplSwitchMapper tmplSwitchMapper;

	/**
     * 查询交换机模板信息
     * 
     * @param switchId 交换机模板ID
     * @return 交换机模板信息
     */
    @Override
	public TmplSwitch selectTmplSwitchById(Integer switchId)
	{
	    return tmplSwitchMapper.selectTmplSwitchById(switchId);
	}
	
	/**
     * 查询交换机模板列表
     * 
     * @param tmplSwitch 交换机模板信息
     * @return 交换机模板集合
     */
	@Override
	public List<TmplSwitch> selectTmplSwitchList(TmplSwitch tmplSwitch)
	{
	    return tmplSwitchMapper.selectTmplSwitchList(tmplSwitch);
	}
	
    /**
     * 新增交换机模板
     * 
     * @param tmplSwitch 交换机模板信息
     * @return 结果
     */
	@Override
	public int insertTmplSwitch(TmplSwitch tmplSwitch)
	{
	    return tmplSwitchMapper.insertTmplSwitch(tmplSwitch);
	}
	
	/**
     * 修改交换机模板
     * 
     * @param tmplSwitch 交换机模板信息
     * @return 结果
     */
	@Override
	public int updateTmplSwitch(TmplSwitch tmplSwitch)
	{
	    return tmplSwitchMapper.updateTmplSwitch(tmplSwitch);
	}

	/**
     * 删除交换机模板对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteTmplSwitchByIds(String ids)
	{
		return tmplSwitchMapper.deleteTmplSwitchByIds(Convert.toStrArray(ids));
	}
	
}
