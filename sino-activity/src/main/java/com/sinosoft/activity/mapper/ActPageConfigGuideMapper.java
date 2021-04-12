package com.sinosoft.activity.mapper;

import java.util.List;
import com.sinosoft.activity.domain.ActPageConfigGuide;

/**
 * 活动展示内容配置Mapper接口
 * 
 * @author dy
 * @date 2021-04-08
 */
public interface ActPageConfigGuideMapper 
{
    /**
     * 查询活动展示内容配置
     * 
     * @param id 活动展示内容配置ID
     * @return 活动展示内容配置
     */
    public ActPageConfigGuide selectActPageConfigGuideById(Integer id);

    /**
     * 查询活动展示内容配置列表
     * 
     * @param actPageConfigGuide 活动展示内容配置
     * @return 活动展示内容配置集合
     */
    public List<ActPageConfigGuide> selectActPageConfigGuideList(ActPageConfigGuide actPageConfigGuide);

    /**
     * 新增活动展示内容配置
     * 
     * @param actPageConfigGuide 活动展示内容配置
     * @return 结果
     */
    public int insertActPageConfigGuide(ActPageConfigGuide actPageConfigGuide);

    /**
     * 修改活动展示内容配置
     * 
     * @param actPageConfigGuide 活动展示内容配置
     * @return 结果
     */
    public int updateActPageConfigGuide(ActPageConfigGuide actPageConfigGuide);

    /**
     * 删除活动展示内容配置
     * 
     * @param id 活动展示内容配置ID
     * @return 结果
     */
    public int deleteActPageConfigGuideById(Integer id);

    /**
     * 批量删除活动展示内容配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteActPageConfigGuideByIds(String[] ids);
    /**
     * 根据活动编码查询
     * @param drawCode
     * @return
     */
   public ActPageConfigGuide selectActPageConfigGuideByCode(String drawCode);
}
