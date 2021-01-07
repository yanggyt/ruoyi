package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.BusReqApply;
import java.util.List;	

/**
 * 资源需求接包申请 数据层
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public interface BusReqApplyMapper 
{
	/**
     * 查询资源需求接包申请信息
     * 
     * @param applyId 资源需求接包申请ID
     * @return 资源需求接包申请信息
     */
	public BusReqApply selectBusReqApplyById(Long applyId);
	
	/**
     * 查询资源需求接包申请列表
     * 
     * @param busReqApply 资源需求接包申请信息
     * @return 资源需求接包申请集合
     */
	public List<BusReqApply> selectBusReqApplyList(BusReqApply busReqApply);
	
	/**
     * 新增资源需求接包申请
     * 
     * @param busReqApply 资源需求接包申请信息
     * @return 结果
     */
	public int insertBusReqApply(BusReqApply busReqApply);
	
	/**
     * 修改资源需求接包申请
     * 
     * @param busReqApply 资源需求接包申请信息
     * @return 结果
     */
	public int updateBusReqApply(BusReqApply busReqApply);
	
	/**
     * 删除资源需求接包申请
     * 
     * @param applyId 资源需求接包申请ID
     * @return 结果
     */
	public int deleteBusReqApplyById(Long applyId);
	
	/**
     * 批量删除资源需求接包申请
     * 
     * @param applyIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteBusReqApplyByIds(String[] applyIds);
	
}