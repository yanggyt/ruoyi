package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BusReqCommentMapper;
import com.ruoyi.system.domain.BusReqComment;
import com.ruoyi.system.service.IBusReqCommentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 资源需求评论 服务层实现
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Service
public class BusReqCommentServiceImpl implements IBusReqCommentService 
{
	@Autowired
	private BusReqCommentMapper busReqCommentMapper;

	/**
     * 查询资源需求评论信息
     * 
     * @param commentId 资源需求评论ID
     * @return 资源需求评论信息
     */
    @Override
	public BusReqComment selectBusReqCommentById(Long commentId)
	{
	    return busReqCommentMapper.selectBusReqCommentById(commentId);
	}
	
	/**
     * 查询资源需求评论列表
     * 
     * @param busReqComment 资源需求评论信息
     * @return 资源需求评论集合
     */
	@Override
	public List<BusReqComment> selectBusReqCommentList(BusReqComment busReqComment)
	{
	    return busReqCommentMapper.selectBusReqCommentList(busReqComment);
	}
	
    /**
     * 新增资源需求评论
     * 
     * @param busReqComment 资源需求评论信息
     * @return 结果
     */
	@Override
	public int insertBusReqComment(BusReqComment busReqComment)
	{
	    return busReqCommentMapper.insertBusReqComment(busReqComment);
	}
	
	/**
     * 修改资源需求评论
     * 
     * @param busReqComment 资源需求评论信息
     * @return 结果
     */
	@Override
	public int updateBusReqComment(BusReqComment busReqComment)
	{
	    return busReqCommentMapper.updateBusReqComment(busReqComment);
	}

	/**
     * 删除资源需求评论对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBusReqCommentByIds(String ids)
	{
		return busReqCommentMapper.deleteBusReqCommentByIds(Convert.toStrArray(ids));
	}
	
}
