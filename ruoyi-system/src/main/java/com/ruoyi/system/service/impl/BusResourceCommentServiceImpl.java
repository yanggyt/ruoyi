package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BusResourceCommentMapper;
import com.ruoyi.system.domain.BusResourceComment;
import com.ruoyi.system.service.IBusResourceCommentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 资源评论 服务层实现
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Service
public class BusResourceCommentServiceImpl implements IBusResourceCommentService 
{
	@Autowired
	private BusResourceCommentMapper busResourceCommentMapper;

	/**
     * 查询资源评论信息
     * 
     * @param commentId 资源评论ID
     * @return 资源评论信息
     */
    @Override
	public BusResourceComment selectBusResourceCommentById(Long commentId)
	{
	    return busResourceCommentMapper.selectBusResourceCommentById(commentId);
	}
	
	/**
     * 查询资源评论列表
     * 
     * @param busResourceComment 资源评论信息
     * @return 资源评论集合
     */
	@Override
	public List<BusResourceComment> selectBusResourceCommentList(BusResourceComment busResourceComment)
	{
	    return busResourceCommentMapper.selectBusResourceCommentList(busResourceComment);
	}
	
    /**
     * 新增资源评论
     * 
     * @param busResourceComment 资源评论信息
     * @return 结果
     */
	@Override
	public int insertBusResourceComment(BusResourceComment busResourceComment)
	{
	    return busResourceCommentMapper.insertBusResourceComment(busResourceComment);
	}
	
	/**
     * 修改资源评论
     * 
     * @param busResourceComment 资源评论信息
     * @return 结果
     */
	@Override
	public int updateBusResourceComment(BusResourceComment busResourceComment)
	{
	    return busResourceCommentMapper.updateBusResourceComment(busResourceComment);
	}

	/**
     * 删除资源评论对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBusResourceCommentByIds(String ids)
	{
		return busResourceCommentMapper.deleteBusResourceCommentByIds(Convert.toStrArray(ids));
	}
	
}
