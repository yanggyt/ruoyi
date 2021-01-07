package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BusDeptApplyMapper;
import com.ruoyi.system.domain.BusDeptApply;
import com.ruoyi.system.service.IBusDeptApplyService;
import com.ruoyi.common.core.text.Convert;

/**
 * 企业入驻申请 服务层实现
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Service
public class BusDeptApplyServiceImpl implements IBusDeptApplyService 
{
	@Autowired
	private BusDeptApplyMapper busDeptApplyMapper;

	/**
     * 查询企业入驻申请信息
     * 
     * @param applyId 企业入驻申请ID
     * @return 企业入驻申请信息
     */
    @Override
	public BusDeptApply selectBusDeptApplyById(Long applyId)
	{
	    return busDeptApplyMapper.selectBusDeptApplyById(applyId);
	}
	
	/**
     * 查询企业入驻申请列表
     * 
     * @param busDeptApply 企业入驻申请信息
     * @return 企业入驻申请集合
     */
	@Override
	public List<BusDeptApply> selectBusDeptApplyList(BusDeptApply busDeptApply)
	{
	    return busDeptApplyMapper.selectBusDeptApplyList(busDeptApply);
	}
	
    /**
     * 新增企业入驻申请
     * 
     * @param busDeptApply 企业入驻申请信息
     * @return 结果
     */
	@Override
	public int insertBusDeptApply(BusDeptApply busDeptApply)
	{
	    return busDeptApplyMapper.insertBusDeptApply(busDeptApply);
	}
	
	/**
     * 修改企业入驻申请
     * 
     * @param busDeptApply 企业入驻申请信息
     * @return 结果
     */
	@Override
	public int updateBusDeptApply(BusDeptApply busDeptApply)
	{
	    return busDeptApplyMapper.updateBusDeptApply(busDeptApply);
	}

	/**
     * 删除企业入驻申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBusDeptApplyByIds(String ids)
	{
		return busDeptApplyMapper.deleteBusDeptApplyByIds(Convert.toStrArray(ids));
	}
	
}
