package com.ruoyi.his.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.his.domain.HisRegistrationTemplate;
import com.ruoyi.his.mapper.HisRegistrationTemplateMapper;
import com.ruoyi.his.service.IHisRegistrationTemplateService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 挂号模板Service业务层处理
 * 
 * @author bend
 * @date 2020-07-01
 */
@Service
public class HisRegistrationTemplateServiceImpl implements IHisRegistrationTemplateService 
{
    @Resource
    private HisRegistrationTemplateMapper hisRegistrationTemplateMapper;

    /**
     * 查询挂号模板
     * 
     * @param id 挂号模板ID
     * @return 挂号模板
     */
    @Override
    public HisRegistrationTemplate selectHisRegistrationTemplateById(Long id)
    {
        return hisRegistrationTemplateMapper.selectHisRegistrationTemplateById(id);
    }

    @Override
    public HisRegistrationTemplate selectHisRegistrationTemplate(HisRegistrationTemplate hisRegistrationTemplate) {
        return hisRegistrationTemplateMapper.selectHisRegistrationTemplate(hisRegistrationTemplate);
    }

    /**
     * 查询挂号模板列表
     * 
     * @param hisRegistrationTemplate 挂号模板
     * @return 挂号模板
     */
    @Override
    public List<HisRegistrationTemplate> selectHisRegistrationTemplateList(HisRegistrationTemplate hisRegistrationTemplate)
    {
        return hisRegistrationTemplateMapper.selectHisRegistrationTemplateList(hisRegistrationTemplate);
    }

    /**
     * 新增挂号模板
     * 
     * @param hisRegistrationTemplate 挂号模板
     * @return 结果
     */
    @Override
    public int insertHisRegistrationTemplate(HisRegistrationTemplate hisRegistrationTemplate)
    {
        return hisRegistrationTemplateMapper.insertHisRegistrationTemplate(hisRegistrationTemplate);
    }

    /**
     * 修改挂号模板
     * 
     * @param hisRegistrationTemplate 挂号模板
     * @return 结果
     */
    @Override
    public int updateHisRegistrationTemplate(HisRegistrationTemplate hisRegistrationTemplate)
    {
        return hisRegistrationTemplateMapper.updateByPrimaryKeySelective(hisRegistrationTemplate);
    }

    /**
     * 删除挂号模板对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHisRegistrationTemplateByIds(String ids)
    {
        return hisRegistrationTemplateMapper.deleteHisRegistrationTemplateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除挂号模板信息
     * 
     * @param id 挂号模板ID
     * @return 结果
     */
    @Override
    public int deleteHisRegistrationTemplateById(Long id)
    {
        return hisRegistrationTemplateMapper.deleteHisRegistrationTemplateById(id);
    }

    /**
     *
     * @param hisRegistrationTemplate 模板信息
     */
    @Override
    public int changeStatus(HisRegistrationTemplate hisRegistrationTemplate)
    {
        return hisRegistrationTemplateMapper.updateByPrimaryKeySelective(hisRegistrationTemplate);
    }
}
