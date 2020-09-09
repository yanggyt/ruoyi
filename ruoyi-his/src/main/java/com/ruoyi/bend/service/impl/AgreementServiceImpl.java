package com.ruoyi.bend.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bend.mapper.AgreementMapper;
import com.ruoyi.bend.domain.Agreement;
import com.ruoyi.bend.service.IAgreementService;
import com.ruoyi.common.core.text.Convert;

/**
 * 协议管理Service业务层处理
 * 
 * @author bend
 * @date 2020-08-30
 */
@Service
public class AgreementServiceImpl implements IAgreementService 
{
    @Autowired
    private AgreementMapper agreementMapper;

    /**
     * 查询协议管理
     * 
     * @param id 协议管理ID
     * @return 协议管理
     */
    @Override
    public Agreement selectAgreementById(Long id)
    {
        return agreementMapper.selectAgreementById(id);
    }

    /**
     * 查询协议管理列表
     * 
     * @param agreement 协议管理
     * @return 协议管理
     */
    @Override
    public List<Agreement> selectAgreementList(Agreement agreement)
    {
        return agreementMapper.selectAgreementList(agreement);
    }

    /**
     * 新增协议管理
     * 
     * @param agreement 协议管理
     * @return 结果
     */
    @Override
    public int insertAgreement(Agreement agreement)
    {
        agreement.setCreateTime(DateUtils.getNowDate());
        return agreementMapper.insertAgreement(agreement);
    }

    /**
     * 修改协议管理
     * 
     * @param agreement 协议管理
     * @return 结果
     */
    @Override
    public int updateAgreement(Agreement agreement)
    {
        agreement.setUpdateTime(DateUtils.getNowDate());
        return agreementMapper.updateAgreement(agreement);
    }

    /**
     * 删除协议管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAgreementByIds(String ids)
    {
        return agreementMapper.deleteAgreementByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除协议管理信息
     * 
     * @param id 协议管理ID
     * @return 结果
     */
    @Override
    public int deleteAgreementById(Long id)
    {
        return agreementMapper.deleteAgreementById(id);
    }
}
