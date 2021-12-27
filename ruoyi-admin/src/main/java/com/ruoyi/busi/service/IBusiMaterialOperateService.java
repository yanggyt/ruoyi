package com.ruoyi.busi.service;

import java.util.List;
import com.ruoyi.busi.domain.BusiMaterialOperate;
import com.ruoyi.common.exception.ServiceException;

/**
 * 物料操作流水Service接口
 * 
 * @author WangCL
 * @date 2021-12-24
 */
public interface IBusiMaterialOperateService 
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
    public int insertBusiMaterialOperate(BusiMaterialOperate busiMaterialOperate) throws ServiceException;

    /**
     * 修改物料操作流水
     * 
     * @param busiMaterialOperate 物料操作流水
     * @return 结果
     */
    public int updateBusiMaterialOperate(BusiMaterialOperate busiMaterialOperate);

    /**
     * 批量删除物料操作流水
     * 
     * @param ids 需要删除的物料操作流水主键集合
     * @return 结果
     */
    public int deleteBusiMaterialOperateByIds(String ids);

    /**
     * 删除物料操作流水信息
     * 
     * @param id 物料操作流水主键
     * @return 结果
     */
    public int deleteBusiMaterialOperateById(Long id);
}
