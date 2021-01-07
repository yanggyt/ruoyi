package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.BusResourceApply;
import java.util.List;	

/**
 * 资源使用申请 数据层
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public interface BusResourceApplyMapper 
{
	/**
     * 查询资源使用申请信息
     * 
     * @param applyId 资源使用申请ID
     * @return 资源使用申请信息
     */
	public BusResourceApply selectBusResourceApplyById(Long applyId);
	
	/**
     * 查询资源使用申请列表
     * 
     * @param busResourceApply 资源使用申请信息
     * @return 资源使用申请集合
     */
	public List<BusResourceApply> selectBusResourceApplyList(BusResourceApply busResourceApply);
	
	/**
     * 新增资源使用申请
     * 
     * @param busResourceApply 资源使用申请信息
     * @return 结果
     */
	public int insertBusResourceApply(BusResourceApply busResourceApply);
	
	/**
     * 修改资源使用申请
     * 
     * @param busResourceApply 资源使用申请信息
     * @return 结果
     */
	public int updateBusResourceApply(BusResourceApply busResourceApply);
	
	/**
     * 删除资源使用申请
     * 
     * @param applyId 资源使用申请ID
     * @return 结果
     */
	public int deleteBusResourceApplyById(Long applyId);
	
	/**
     * 批量删除资源使用申请
     * 
     * @param applyIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteBusResourceApplyByIds(String[] applyIds);
	
}