package com.ruoyi.busi.mapper;

import java.util.List;
import com.ruoyi.busi.domain.BusiPrisonLine;

/**
 * 监区产线Mapper接口
 * 
 * @author WangCL
 * @date 2021-12-17
 */
public interface BusiPrisonLineMapper 
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
     * 删除监区产线
     * 
     * @param id 监区产线主键
     * @return 结果
     */
    public int deleteBusiPrisonLineById(Long id);

    /**
     * 批量删除监区产线
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusiPrisonLineByIds(String[] ids);
}
