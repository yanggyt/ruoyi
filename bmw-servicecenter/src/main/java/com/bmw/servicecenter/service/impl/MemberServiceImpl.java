package com.bmw.servicecenter.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bmw.servicecenter.mapper.MemberMapper;
import com.bmw.servicecenter.domain.Member;
import com.bmw.servicecenter.service.IMemberService;
import com.bmw.common.core.text.Convert;

/**
 * 客户 服务层实现
 * 
 * @author bmw
 * @date 2019-07-26
 */
@Service
public class MemberServiceImpl implements IMemberService 
{
	@Autowired
	private MemberMapper memberMapper;

	/**
     * 查询客户信息
     * 
     * @param memberId 客户ID
     * @return 客户信息
     */
    @Override
	public Member selectMemberById(Long memberId)
	{
	    return memberMapper.selectMemberById(memberId);
	}
	
	/**
     * 查询客户列表
     * 
     * @param member 客户信息
     * @return 客户集合
     */
	@Override
	public List<Member> selectMemberList(Member member)
	{
	    return memberMapper.selectMemberList(member);
	}
	
    /**
     * 新增客户
     * 
     * @param member 客户信息
     * @return 结果
     */
	@Override
	public int insertMember(Member member)
	{
	    return memberMapper.insertMember(member);
	}
	
	/**
     * 修改客户
     * 
     * @param member 客户信息
     * @return 结果
     */
	@Override
	public int updateMember(Member member)
	{
	    return memberMapper.updateMember(member);
	}

	/**
     * 删除客户对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteMemberByIds(String ids)
	{
		return memberMapper.deleteMemberByIds(Convert.toStrArray(ids));
	}
	
}
