package com.ruoyi.busi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.busi.mapper.BusiMaterialOperateMapper;
import com.ruoyi.busi.domain.BusiMaterialOperate;
import com.ruoyi.busi.service.IBusiMaterialOperateService;
import com.ruoyi.common.core.text.Convert;

/**
 * 物料操作流水Service业务层处理
 * 
 * @author WangCL
 * @date 2021-12-24
 */
@Service
public class BusiMaterialOperateServiceImpl implements IBusiMaterialOperateService 
{
    @Autowired
    private BusiMaterialOperateMapper busiMaterialOperateMapper;

    /**
     * 查询物料操作流水
     * 
     * @param id 物料操作流水主键
     * @return 物料操作流水
     */
    @Override
    public BusiMaterialOperate selectBusiMaterialOperateById(Long id)
    {
        return busiMaterialOperateMapper.selectBusiMaterialOperateById(id);
    }

    /**
     * 查询物料操作流水列表
     * 
     * @param busiMaterialOperate 物料操作流水
     * @return 物料操作流水
     */
    @Override
    public List<BusiMaterialOperate> selectBusiMaterialOperateList(BusiMaterialOperate busiMaterialOperate)
    {
        return busiMaterialOperateMapper.selectBusiMaterialOperateList(busiMaterialOperate);
    }

    /**
     * 新增物料操作流水
     * 
     * @param busiMaterialOperate 物料操作流水
     * @return 结果
     */
    @Override
    public int insertBusiMaterialOperate(BusiMaterialOperate busiMaterialOperate)
    {
        busiMaterialOperate.setCreateTime(DateUtils.getNowDate());
        return busiMaterialOperateMapper.insertBusiMaterialOperate(busiMaterialOperate);
    }

    /**
     * 修改物料操作流水
     * 
     * @param busiMaterialOperate 物料操作流水
     * @return 结果
     */
    @Override
    public int updateBusiMaterialOperate(BusiMaterialOperate busiMaterialOperate)
    {
        return busiMaterialOperateMapper.updateBusiMaterialOperate(busiMaterialOperate);
    }

    /**
     * 批量删除物料操作流水
     * 
     * @param ids 需要删除的物料操作流水主键
     * @return 结果
     */
    @Override
    public int deleteBusiMaterialOperateByIds(String ids)
    {
        return busiMaterialOperateMapper.deleteBusiMaterialOperateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除物料操作流水信息
     * 
     * @param id 物料操作流水主键
     * @return 结果
     */
    @Override
    public int deleteBusiMaterialOperateById(Long id)
    {
        return busiMaterialOperateMapper.deleteBusiMaterialOperateById(id);
    }
}
