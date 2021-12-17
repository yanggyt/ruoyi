package com.ruoyi.busi.service;

import java.util.List;
import com.ruoyi.busi.domain.BusiPrisonLine;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 监区产线Service接口
 * 
 * @author WangCL
 * @date 2021-12-17
 */
public interface IBusiPrisonLineService 
{
    /**
     * 查询监区产线
     * 
     * @param id 监区产线主键
     * @return 监区产线
     */
    public BusiPrisonLine selectBusiPrisonLineById(Long id);

    /**
     * 查询监区产线列表
     * 
     * @param busiPrisonLine 监区产线
     * @return 监区产线集合
     */
    public List<BusiPrisonLine> selectBusiPrisonLineList(BusiPrisonLine busiPrisonLine);

    /**
     * 新增监区产线
     * 
     * @param busiPrisonLine 监区产线
     * @return 结果
     */
    public int insertBusiPrisonLine(BusiPrisonLine busiPrisonLine);

    /**
     * 修改监区产线
     * 
     * @param busiPrisonLine 监区产线
     * @return 结果
     */
    public int updateBusiPrisonLine(BusiPrisonLine busiPrisonLine);

    /**
     * 批量删除监区产线
     * 
     * @param ids 需要删除的监区产线主键集合
     * @return 结果
     */
    public int deleteBusiPrisonLineByIds(String ids);

    /**
     * 删除监区产线信息
     * 
     * @param id 监区产线主键
     * @return 结果
     */
    public int deleteBusiPrisonLineById(Long id);

    /**
     * 查询监区产线树列表
     *
     * JCOnly 是否只要监区
     *
     * @return 所有监区产线信息
     */
    public List<Ztree> selectBusiPrisonLineTree(String JCOnly);
}
