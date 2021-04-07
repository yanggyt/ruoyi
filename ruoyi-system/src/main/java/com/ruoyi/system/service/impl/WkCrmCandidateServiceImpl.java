package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WkCrmCandidateMapper;
import com.ruoyi.system.domain.WkCrmCandidate;
import com.ruoyi.system.service.IWkCrmCandidateService;
import com.ruoyi.common.core.text.Convert;

/**
 * 候选人Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
@Service
public class WkCrmCandidateServiceImpl implements IWkCrmCandidateService 
{
    @Autowired
    private WkCrmCandidateMapper wkCrmCandidateMapper;

    /**
     * 查询候选人
     * 
     * @param id 候选人ID
     * @return 候选人
     */
    @Override
    public WkCrmCandidate selectWkCrmCandidateById(Integer id)
    {
        return wkCrmCandidateMapper.selectWkCrmCandidateById(id);
    }

    /**
     * 查询候选人列表
     * 
     * @param wkCrmCandidate 候选人
     * @return 候选人
     */
    @Override
    public List<WkCrmCandidate> selectWkCrmCandidateList(WkCrmCandidate wkCrmCandidate)
    {
        return wkCrmCandidateMapper.selectWkCrmCandidateList(wkCrmCandidate);
    }

    /**
     * 新增候选人
     * 
     * @param wkCrmCandidate 候选人
     * @return 结果
     */
    @Override
    public int insertWkCrmCandidate(WkCrmCandidate wkCrmCandidate)
    {
        return wkCrmCandidateMapper.insertWkCrmCandidate(wkCrmCandidate);
    }

    /**
     * 修改候选人
     * 
     * @param wkCrmCandidate 候选人
     * @return 结果
     */
    @Override
    public int updateWkCrmCandidate(WkCrmCandidate wkCrmCandidate)
    {
        return wkCrmCandidateMapper.updateWkCrmCandidate(wkCrmCandidate);
    }

    /**
     * 删除候选人对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWkCrmCandidateByIds(String ids)
    {
        return wkCrmCandidateMapper.deleteWkCrmCandidateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除候选人信息
     * 
     * @param id 候选人ID
     * @return 结果
     */
    @Override
    public int deleteWkCrmCandidateById(Integer id)
    {
        return wkCrmCandidateMapper.deleteWkCrmCandidateById(id);
    }
}
