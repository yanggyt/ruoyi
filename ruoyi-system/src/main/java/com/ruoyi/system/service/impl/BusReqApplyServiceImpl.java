package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BusReqApplyMapper;
import com.ruoyi.system.domain.BusReqApply;
import com.ruoyi.system.service.IBusReqApplyService;
import com.ruoyi.common.core.text.Convert;

/**
 * 资源需求接包申请 服务层实现
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Service
public class BusReqApplyServiceImpl implements IBusReqApplyService 
{
	@Autowired
	private BusReqApplyMapper busReqApplyMapper;

	/**
     * 查询资源需求接包申请信息
     * 
     * @param applyId 资源需求接包申请ID
     * @return 资源需求接包申请信息
     */
    @Override
	public BusReqApply selectBusReqApplyById(Long applyId)
	{
	    return busReqApplyMapper.selectBusReqApplyById(applyId);
	}
	
	/**
     * 查询资源需求接包申请列表
     * 
     * @param busReqApply 资源需求接包申请信息
     * @return 资源需求接包申请集合
     */
	@Override
	public List<BusReqApply> selectBusReqApplyList(BusReqApply busReqApply)
	{
	    return busReqApplyMapper.selectBusReqApplyList(busReqApply);
	}
	
    /**
     * 新增资源需求接包申请
     * 
     * @param busReqApply 资源需求接包申请信息
     * @return 结果
     */
	@Override
	public int insertBusReqApply(BusReqApply busReqApply)
	{
	    return busReqApplyMapper.insertBusReqApply(busReqApply);
	}
	
	/**
     * 修改资源需求接包申请
     * 
     * @param busReqApply 资源需求接包申请信息
     * @return 结果
     */
	@Override
	public int updateBusReqApply(BusReqApply busReqApply)
	{
	    return busReqApplyMapper.updateBusReqApply(busReqApply);
	}

	/**
     * 删除资源需求接包申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBusReqApplyByIds(String ids)
	{
		return busReqApplyMapper.deleteBusReqApplyByIds(Convert.toStrArray(ids));
	}
	
}
