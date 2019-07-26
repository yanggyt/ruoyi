package com.bmw.servicecenter.service;

import com.bmw.servicecenter.domain.Member;
import java.util.List;

/**
 * 客户 服务层
 * 
 * @author bmw
 * @date 2019-07-26
 */
public interface IMemberService 
{
	/**
     * 查询客户信息
     * 
     * @param memberId 客户ID
     * @return 客户信息
     */
	public Member selectMemberById(Long memberId);
	
	/**
     * 查询客户列表
     * 
     * @param member 客户信息
     * @return 客户集合
     */
	public List<Member> selectMemberList(Member member);
	
	/**
     * 新增客户
     * 
     * @param member 客户信息
     * @return 结果
     */
	public int insertMember(Member member);
	
	/**
     * 修改客户
     * 
     * @param member 客户信息
     * @return 结果
     */
	public int updateMember(Member member);
		
	/**
     * 删除客户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteMemberByIds(String ids);
	
}
