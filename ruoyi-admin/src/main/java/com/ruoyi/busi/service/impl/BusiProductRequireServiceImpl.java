package com.ruoyi.busi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Map;

import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.busi.domain.BusiMaterialRequire;
import com.ruoyi.busi.mapper.BusiProductRequireMapper;
import com.ruoyi.busi.domain.BusiProductRequire;
import com.ruoyi.busi.service.IBusiProductRequireService;
import com.ruoyi.common.core.text.Convert;

/**
 * 产品需求Service业务层处理
 * 
 * @author WangCL
 * @date 2021-12-22
 */
@Service
public class BusiProductRequireServiceImpl implements IBusiProductRequireService 
{
    @Autowired
    private BusiProductRequireMapper busiProductRequireMapper;

    /**
     * 查询产品需求
     * 
     * @param id 产品需求主键
     * @return 产品需求
     */
    @Override
    public BusiProductRequire selectBusiProductRequireById(String id)
    {
        return busiProductRequireMapper.selectBusiProductRequireById(id);
    }

    /**
     * 查询产品需求列表
     * 
     * @param busiProductRequire 产品需求
     * @return 产品需求
     */
    @Override
    public List<BusiProductRequire> selectBusiProductRequireList(BusiProductRequire busiProductRequire)
    {
        return busiProductRequireMapper.selectBusiProductRequireList(busiProductRequire);
    }

    /**
     * 新增产品需求
     * 
     * @param busiProductRequire 产品需求
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBusiProductRequire(BusiProductRequire busiProductRequire)
    {
        busiProductRequire.setCreateTime(DateUtils.getNowDate());
        int rows = busiProductRequireMapper.insertBusiProductRequire(busiProductRequire);
        insertBusiMaterialRequire(busiProductRequire);
        return rows;
    }

    /**
     * 修改产品需求
     * 
     * @param busiProductRequire 产品需求
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBusiProductRequire(BusiProductRequire busiProductRequire)
    {
        busiProductRequire.setUpdateTime(DateUtils.getNowDate());
        busiProductRequireMapper.deleteBusiMaterialRequireByProductRequireId(busiProductRequire.getId());
        insertBusiMaterialRequire(busiProductRequire);
        return busiProductRequireMapper.updateBusiProductRequire(busiProductRequire);
    }

    /**
     * 批量删除产品需求
     * 
     * @param ids 需要删除的产品需求主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBusiProductRequireByIds(String ids)
    {
        busiProductRequireMapper.deleteBusiMaterialRequireByProductRequireIds(Convert.toStrArray(ids));
        return busiProductRequireMapper.deleteBusiProductRequireByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除产品需求信息
     * 
     * @param id 产品需求主键
     * @return 结果
     */
    @Override
    public int deleteBusiProductRequireById(String id)
    {
        busiProductRequireMapper.deleteBusiMaterialRequireByProductRequireId(id);
        return busiProductRequireMapper.deleteBusiProductRequireById(id);
    }

    @Override
    public List<Map<String, String>> selectMaterialRequireByOrderId(String orderId) {
        return busiProductRequireMapper.selectMaterialRequireByOrderId(orderId);
    }

    /**
     * 新增物料需求信息
     * 
     * @param busiProductRequire 产品需求对象
     */
    public void insertBusiMaterialRequire(BusiProductRequire busiProductRequire)
    {
        List<BusiMaterialRequire> busiMaterialRequireList = busiProductRequire.getBusiMaterialRequireList();
        String id = busiProductRequire.getId();
        if (StringUtils.isNotNull(busiMaterialRequireList))
        {
            List<BusiMaterialRequire> list = new ArrayList<BusiMaterialRequire>();
            for (BusiMaterialRequire busiMaterialRequire : busiMaterialRequireList)
            {
                busiMaterialRequire.setProductRequireId(id);
                list.add(busiMaterialRequire);
            }
            if (list.size() > 0)
            {
                busiProductRequireMapper.batchBusiMaterialRequire(list);
            }
        }
    }
}
