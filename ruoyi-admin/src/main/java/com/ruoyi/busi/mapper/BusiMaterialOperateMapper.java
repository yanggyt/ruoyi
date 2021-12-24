package com.ruoyi.busi.mapper;

import java.util.List;
import com.ruoyi.busi.domain.BusiMaterialOperate;

/**
 * 物料操作流水Mapper接口
 * 
 * @author WangCL
 * @date 2021-12-24
 */
public interface BusiMaterialOperateMapper 
{
    /**
     * 查询物料操作流水
     * 
     * @param id 物料操作流水主键
     * @return 物料操作流水
     */
    public BusiMaterialOperate selectBusiMaterialOperateById(Long id);

    /**
     * 查询物料操作流水列表
     * 
     * @param busiMaterialOperate 物料操作流水
     * @return 物料操作流水集合
     */
    public List<BusiMaterialOperate> selectBusiMaterialOperateList(BusiMaterialOperate busiMaterialOperate);

    /**
     * 新增物料操作流水
     * 
     * @param busiMaterialOperate 物料操作流水
     * @return 结果
     */
    public int insertBusiMaterialOperate(BusiMaterialOperate busiMaterialOperate);

    /**
     * 修改物料操作流水
     * 
     * @param busiMaterialOperate 物料操作流水
     * @return 结果
     */
    public int updateBusiMaterialOperate(BusiMaterialOperate busiMaterialOperate);

    /**
     * 删除物料操作流水
     * 
     * @param id 物料操作流水主键
     * @return 结果
     */
    public int deleteBusiMaterialOperateById(Long id);

    /**
     * 批量删除物料操作流水
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusiMaterialOperateByIds(String[] ids);
}
