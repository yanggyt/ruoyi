package com.ruoyi.system.service;

import com.ruoyi.system.domain.BusReqComment;
import java.util.List;

/**
 * 资源需求评论 服务层
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public interface IBusReqCommentService 
{
	/**
     * 查询资源需求评论信息
     * 
     * @param commentId 资源需求评论ID
     * @return 资源需求评论信息
     */
	public BusReqComment selectBusReqCommentById(Long commentId);
	
	/**
     * 查询资源需求评论列表
     * 
     * @param busReqComment 资源需求评论信息
     * @return 资源需求评论集合
     */
	public List<BusReqComment> selectBusReqCommentList(BusReqComment busReqComment);
	
	/**
     * 新增资源需求评论
     * 
     * @param busReqComment 资源需求评论信息
     * @return 结果
     */
	public int insertBusReqComment(BusReqComment busReqComment);
	
	/**
     * 修改资源需求评论
     * 
     * @param busReqComment 资源需求评论信息
     * @return 结果
     */
	public int updateBusReqComment(BusReqComment busReqComment);
		
	/**
     * 删除资源需求评论信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBusReqCommentByIds(String ids);
	
}
