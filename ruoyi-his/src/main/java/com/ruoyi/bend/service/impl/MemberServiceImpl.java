package com.ruoyi.bend.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bend.mapper.MemberMapper;
import com.ruoyi.bend.domain.Member;
import com.ruoyi.bend.service.IMemberService;
import com.ruoyi.common.core.text.Convert;

/**
 * 会员列表Service业务层处理
 * 
 * @author bend
 * @date 2020-08-30
 */
@Service
public class MemberServiceImpl implements IMemberService 
{
    @Autowired
    private MemberMapper memberMapper;

    /**
     * 查询会员列表
     * 
     * @param id 会员列表ID
     * @return 会员列表
     */
    @Override
    public Member selectMemberById(Long id)
    {
        return memberMapper.selectMemberById(id);
    }

    /**
     * 查询会员列表列表
     * 
     * @param member 会员列表
     * @return 会员列表
     */
    @Override
    public List<Member> selectMemberList(Member member)
    {
        return memberMapper.selectMemberList(member);
    }

    /**
     * 新增会员列表
     * 
     * @param member 会员列表
     * @return 结果
     */
    @Override
    public int insertMember(Member member)
    {
        member.setCreateTime(DateUtils.getNowDate());
        return memberMapper.insertMember(member);
    }

    /**
     * 修改会员列表
     * 
     * @param member 会员列表
     * @return 结果
     */
    @Override
    public int updateMember(Member member)
    {
        member.setUpdateTime(DateUtils.getNowDate());
        return memberMapper.updateMember(member);
    }

    /**
     * 删除会员列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMemberByIds(String ids)
    {
        return memberMapper.deleteMemberByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除会员列表信息
     * 
     * @param id 会员列表ID
     * @return 结果
     */
    @Override
    public int deleteMemberById(Long id)
    {
        return memberMapper.deleteMemberById(id);
    }
}
