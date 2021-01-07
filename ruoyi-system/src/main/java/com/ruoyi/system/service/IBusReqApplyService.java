package com.ruoyi.system.service;

import com.ruoyi.system.domain.BusReqApply;
import java.util.List;

/**
 * 资源需求接包申请 服务层
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public interface IBusReqApplyService 
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
     * 删除资源需求接包申请信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBusReqApplyByIds(String ids);
	
}
