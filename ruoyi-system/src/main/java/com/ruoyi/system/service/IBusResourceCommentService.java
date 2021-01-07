package com.ruoyi.system.service;

import com.ruoyi.system.domain.BusResourceComment;
import java.util.List;

/**
 * 资源评论 服务层
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public interface IBusResourceCommentService 
{
	/**
     * 查询资源评论信息
     * 
     * @param commentId 资源评论ID
     * @return 资源评论信息
     */
	public BusResourceComment selectBusResourceCommentById(Long commentId);
	
	/**
     * 查询资源评论列表
     * 
     * @param busResourceComment 资源评论信息
     * @return 资源评论集合
     */
	public List<BusResourceComment> selectBusResourceCommentList(BusResourceComment busResourceComment);
	
	/**
     * 新增资源评论
     * 
     * @param busResourceComment 资源评论信息
     * @return 结果
     */
	public int insertBusResourceComment(BusResourceComment busResourceComment);
	
	/**
     * 修改资源评论
     * 
     * @param busResourceComment 资源评论信息
     * @return 结果
     */
	public int updateBusResourceComment(BusResourceComment busResourceComment);
		
	/**
     * 删除资源评论信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteBusResourceCommentByIds(String ids);
	
}
