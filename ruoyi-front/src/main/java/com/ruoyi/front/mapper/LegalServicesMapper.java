package com.ruoyi.front.mapper;

import java.util.List;
import com.ruoyi.front.domain.LegalServices;

/**
 * 法律服务Mapper接口
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
public interface LegalServicesMapper 
{
    /**
     * 查询法律服务
     * 
     * @param id 法律服务ID
     * @return 法律服务
     */
    public LegalServices selectLegalServicesById(Long id);

    /**
     * 查询法律服务列表
     * 
     * @param legalServices 法律服务
     * @return 法律服务集合
     */
    public List<LegalServices> selectLegalServicesList(LegalServices legalServices);

    /**
     * 新增法律服务
     * 
     * @param legalServices 法律服务
     * @return 结果
     */
    public int insertLegalServices(LegalServices legalServices);

    /**
     * 修改法律服务
     * 
     * @param legalServices 法律服务
     * @return 结果
     */
    public int updateLegalServices(LegalServices legalServices);

    /**
     * 删除法律服务
     * 
     * @param id 法律服务ID
     * @return 结果
     */
    public int deleteLegalServicesById(Long id);

    /**
     * 批量删除法律服务
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLegalServicesByIds(String[] ids);
}
