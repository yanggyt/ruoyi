package com.ruoyi.system.service;

import com.ruoyi.system.domain.BusDeptApply;
import java.util.List;

/**
 * 企业入驻申请 服务层
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public interface IBusDeptApplyService 
{
	/**
     * 查询企业入驻申请信息
     * 
     * @param applyId 企业入驻申请ID
     * @return 企业入驻申请信息
     */
	public BusDeptApply selectBusDeptApplyById(Long applyId);
	
	/**
     * 查询企业入驻申请列表
     * 
     * @param busDeptApply 企业入驻申请信息
     * @return 企业入驻申请集合
     */
	public List<BusDeptApply> selectBusDeptApplyList(BusDeptApply busDeptApply);
	
	/**
     * 新增企业入驻申请
     * 
     * @param busDeptApply 企业入驻申请信息
     * @return 结果
     */
	public int insertBusDeptApply(BusDeptApply busDeptApply);
	
	/**
     * 修改企业入驻申请
     * 
     * @param busDeptApply 企业入驻申请信息
     * @return 结果
     */
	public int updateBusDeptApply(BusDeptApply busDeptApply);
		
	/**
     * 删除企业入驻申请信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBusDeptApplyByIds(String ids);
	
}
