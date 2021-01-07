package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.BusResourceComment;
import java.util.List;	

/**
 * 资源评论 数据层
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public interface BusResourceCommentMapper 
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
     * 删除资源评论
     * 
     * @param commentId 资源评论ID
     * @return 结果
     */
	public int deleteBusResourceCommentById(Long commentId);
	
	/**
     * 批量删除资源评论
     * 
     * @param commentIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteBusResourceCommentByIds(String[] commentIds);
	
}