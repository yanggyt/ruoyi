package com.ruoyi.bend.service;

import java.util.List;
import com.ruoyi.bend.domain.Member;

/**
 * 会员列表Service接口
 * 
 * @author bend
 * @date 2020-08-30
 */
public interface IMemberService 
{
    /**
     * 查询会员列表
     * 
     * @param id 会员列表ID
     * @return 会员列表
     */
    public Member selectMemberById(Long id);

    /**
     * 查询会员列表列表
     * 
     * @param member 会员列表
     * @return 会员列表集合
     */
    public List<Member> selectMemberList(Member member);

    /**
     * 新增会员列表
     * 
     * @param member 会员列表
     * @return 结果
     */
    public int insertMember(Member member);

    /**
     * 修改会员列表
     * 
     * @param member 会员列表
     * @return 结果
     */
    public int updateMember(Member member);

    /**
     * 批量删除会员列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMemberByIds(String ids);

    /**
     * 删除会员列表信息
     * 
     * @param id 会员列表ID
     * @return 结果
     */
    public int deleteMemberById(Long id);
}
