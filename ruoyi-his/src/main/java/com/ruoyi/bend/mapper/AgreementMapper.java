package com.ruoyi.bend.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.bend.domain.Agreement;
import java.util.List;

/**
 * 协议管理Mapper接口
 * 
 * @author bend
 * @date 2020-08-30
 */
public interface AgreementMapper extends RuoYiBaseMapper<Agreement>
{
    /**
     * 查询协议管理
     * 
     * @param id 协议管理ID
     * @return 协议管理
     */
    public Agreement selectAgreementById(Long id);

    /**
     * 查询协议管理列表
     * 
     * @param agreement 协议管理
     * @return 协议管理集合
     */
    public List<Agreement> selectAgreementList(Agreement agreement);

    /**
     * 新增协议管理
     * 
     * @param agreement 协议管理
     * @return 结果
     */
    public int insertAgreement(Agreement agreement);

    /**
     * 修改协议管理
     * 
     * @param agreement 协议管理
     * @return 结果
     */
    public int updateAgreement(Agreement agreement);

    /**
     * 删除协议管理
     * 
     * @param id 协议管理ID
     * @return 结果
     */
    public int deleteAgreementById(Long id);

    /**
     * 批量删除协议管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAgreementByIds(String[] ids);
}
