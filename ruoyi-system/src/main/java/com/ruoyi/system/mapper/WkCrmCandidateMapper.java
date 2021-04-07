package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.WkCrmCandidate;

/**
 * 候选人Mapper接口
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
public interface WkCrmCandidateMapper 
{
    /**
     * 查询候选人
     * 
     * @param id 候选人ID
     * @return 候选人
     */
    public WkCrmCandidate selectWkCrmCandidateById(Integer id);

    /**
     * 查询候选人列表
     * 
     * @param wkCrmCandidate 候选人
     * @return 候选人集合
     */
    public List<WkCrmCandidate> selectWkCrmCandidateList(WkCrmCandidate wkCrmCandidate);

    /**
     * 新增候选人
     * 
     * @param wkCrmCandidate 候选人
     * @return 结果
     */
    public int insertWkCrmCandidate(WkCrmCandidate wkCrmCandidate);

    /**
     * 修改候选人
     * 
     * @param wkCrmCandidate 候选人
     * @return 结果
     */
    public int updateWkCrmCandidate(WkCrmCandidate wkCrmCandidate);

    /**
     * 删除候选人
     * 
     * @param id 候选人ID
     * @return 结果
     */
    public int deleteWkCrmCandidateById(Integer id);

    /**
     * 批量删除候选人
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWkCrmCandidateByIds(String[] ids);
}
