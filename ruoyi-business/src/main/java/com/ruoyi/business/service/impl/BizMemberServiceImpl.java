package com.ruoyi.business.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BizMemberMapper;
import com.ruoyi.business.domain.BizMember;
import com.ruoyi.business.service.IBizMemberService;
import com.ruoyi.common.core.text.Convert;

/**
 * 会员Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-09-11
 */
@Service
public class BizMemberServiceImpl implements IBizMemberService 
{
    @Autowired
    private BizMemberMapper bizMemberMapper;

    /**
     * 查询会员
     * 
     * @param id 会员ID
     * @return 会员
     */
    @Override
    public BizMember selectBizMemberById(Long id)
    {
        return bizMemberMapper.selectBizMemberById(id);
    }

    /**
     * 查询会员
     *
     * @param id 会员ID
     * @return 会员
     */
    @Override
    public BizMember selectBizMemberSimple(Long id)
    {
        return bizMemberMapper.selectBizMemberSimple(id);
    }

    /**
     * 查询会员列表
     * 
     * @param bizMember 会员
     * @return 会员
     */
    @Override
    public List<BizMember> selectBizMemberList(BizMember bizMember)
    {
        return bizMemberMapper.selectBizMemberList(bizMember);
    }

    /**
     * 会员福豆余额
     *
     * @param memberID type
     * @return 结果
     */
    public Long selectBizMemberDou(Long memberID, int type)
    {
        Map map = new HashMap<>();
        map.put("memberID", memberID);
        map.put("type", type);
        return bizMemberMapper.selectBizMemberDou(map);
    }

    /**
     * 新增会员
     * 
     * @param bizMember 会员
     * @return 结果
     */
    @Override
    public int insertBizMember(BizMember bizMember)
    {
        bizMember.setCreateTime(DateUtils.getNowDate());
        return bizMemberMapper.insertBizMember(bizMember);
    }

    /**
     * 修改会员
     * 
     * @param bizMember 会员
     * @return 结果
     */
    @Override
    public int updateBizMember(BizMember bizMember)
    {
        bizMember.setUpdateTime(DateUtils.getNowDate());
        return bizMemberMapper.updateBizMember(bizMember);
    }

    /**
     * 删除会员对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBizMemberByIds(String ids)
    {
        return bizMemberMapper.deleteBizMemberByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除会员信息
     * 
     * @param id 会员ID
     * @return 结果
     */
    @Override
    public int deleteBizMemberById(Long id)
    {
        return bizMemberMapper.deleteBizMemberById(id);
    }
}
