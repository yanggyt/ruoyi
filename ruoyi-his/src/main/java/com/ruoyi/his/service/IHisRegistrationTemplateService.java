package com.ruoyi.his.service;

import java.util.List;
import com.ruoyi.his.domain.HisRegistrationTemplate;

/**
 * 挂号模板Service接口
 * 
 * @author bend
 * @date 2020-07-01
 */
public interface IHisRegistrationTemplateService 
{
    /**
     * 查询挂号模板
     * 
     * @param id 挂号模板ID
     * @return 挂号模板
     */
    public HisRegistrationTemplate selectHisRegistrationTemplateById(Long id);

    /**
     * 查询挂号模板
     *
     * @param hisRegistrationTemplate 挂号模板ID
     * @return 挂号模板
     */
    public HisRegistrationTemplate selectHisRegistrationTemplate(HisRegistrationTemplate hisRegistrationTemplate);

    /**
     * 查询挂号模板列表
     * 
     * @param hisRegistrationTemplate 挂号模板
     * @return 挂号模板集合
     */
    public List<HisRegistrationTemplate> selectHisRegistrationTemplateList(HisRegistrationTemplate hisRegistrationTemplate);

    /**
     * 新增挂号模板
     * 
     * @param hisRegistrationTemplate 挂号模板
     * @return 结果
     */
    public int insertHisRegistrationTemplate(HisRegistrationTemplate hisRegistrationTemplate);

    /**
     * 修改挂号模板
     * 
     * @param hisRegistrationTemplate 挂号模板
     * @return 结果
     */
    public int updateHisRegistrationTemplate(HisRegistrationTemplate hisRegistrationTemplate);

    /**
     * 批量删除挂号模板
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisRegistrationTemplateByIds(String ids);

    /**
     * 删除挂号模板信息
     * 
     * @param id 挂号模板ID
     * @return 结果
     */
    public int deleteHisRegistrationTemplateById(Long id);

    /**
     * 修改模板信息
     *
     * @param hisRegistrationTemplate 模板信息
     * @return 结果
     */
    public int changeStatus(HisRegistrationTemplate hisRegistrationTemplate);
}
