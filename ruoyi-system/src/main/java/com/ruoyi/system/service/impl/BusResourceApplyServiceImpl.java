package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BusResourceApplyMapper;
import com.ruoyi.system.domain.BusResourceApply;
import com.ruoyi.system.service.IBusResourceApplyService;
import com.ruoyi.common.core.text.Convert;

/**
 * 资源使用申请 服务层实现
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Service
public class BusResourceApplyServiceImpl implements IBusResourceApplyService 
{
	@Autowired
	private BusResourceApplyMapper busResourceApplyMapper;

	/**
     * 查询资源使用申请信息
     * 
     * @param applyId 资源使用申请ID
     * @return 资源使用申请信息
     */
    @Override
	public BusResourceApply selectBusResourceApplyById(Long applyId)
	{
	    return busResourceApplyMapper.selectBusResourceApplyById(applyId);
	}
	
	/**
     * 查询资源使用申请列表
     * 
     * @param busResourceApply 资源使用申请信息
     * @return 资源使用申请集合
     */
	@Override
	public List<BusResourceApply> selectBusResourceApplyList(BusResourceApply busResourceApply)
	{
	    return busResourceApplyMapper.selectBusResourceApplyList(busResourceApply);
	}
	
    /**
     * 新增资源使用申请
     * 
     * @param busResourceApply 资源使用申请信息
     * @return 结果
     */
	@Override
	public int insertBusResourceApply(BusResourceApply busResourceApply)
	{
	    return busResourceApplyMapper.insertBusResourceApply(busResourceApply);
	}
	
	/**
     * 修改资源使用申请
     * 
     * @param busResourceApply 资源使用申请信息
     * @return 结果
     */
	@Override
	public int updateBusResourceApply(BusResourceApply busResourceApply)
	{
	    return busResourceApplyMapper.updateBusResourceApply(busResourceApply);
	}

	/**
     * 删除资源使用申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBusResourceApplyByIds(String ids)
	{
		return busResourceApplyMapper.deleteBusResourceApplyByIds(Convert.toStrArray(ids));
	}
	
}
