package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.RheologicalDesign;

/**
 * 流变学设计数据Service接口
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
public interface IRheologicalDesignService 
{
    /**
     * 查询流变学设计数据
     * 
     * @param rheologicalDesignNumber 流变学设计数据ID
     * @return 流变学设计数据
     */
    public RheologicalDesign selectRheologicalDesignById(Long rheologicalDesignNumber);

    /**
     * 查询流变学设计数据列表
     * 
     * @param rheologicalDesign 流变学设计数据
     * @return 流变学设计数据集合
     */
    public List<RheologicalDesign> selectRheologicalDesignList(RheologicalDesign rheologicalDesign);

    /**
     * 新增流变学设计数据
     * 
     * @param rheologicalDesign 流变学设计数据
     * @return 结果
     */
    public int insertRheologicalDesign(RheologicalDesign rheologicalDesign);

    /**
     * 修改流变学设计数据
     * 
     * @param rheologicalDesign 流变学设计数据
     * @return 结果
     */
    public int updateRheologicalDesign(RheologicalDesign rheologicalDesign);

    /**
     * 批量删除流变学设计数据
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRheologicalDesignByIds(String ids);

    /**
     * 删除流变学设计数据信息
     * 
     * @param rheologicalDesignNumber 流变学设计数据ID
     * @return 结果
     */
    public int deleteRheologicalDesignById(Long rheologicalDesignNumber);
}
