package com.ruoyi.busi.service.impl;

import java.util.Date;
import java.util.List;

import com.ruoyi.busi.domain.BusiMaterialStock;
import com.ruoyi.busi.mapper.BusiMaterialStockMapper;
import com.ruoyi.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.busi.mapper.BusiMaterialOperateMapper;
import com.ruoyi.busi.domain.BusiMaterialOperate;
import com.ruoyi.busi.service.IBusiMaterialOperateService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.annotation.Transactional;

/**
 * 物料操作流水Service业务层处理
 *
 * @author WangCL
 * @date 2021-12-24
 */
@Service
public class BusiMaterialOperateServiceImpl implements IBusiMaterialOperateService {
    @Autowired
    private BusiMaterialOperateMapper busiMaterialOperateMapper;

    @Autowired
    private BusiMaterialStockMapper busiMaterialStockMapper;

    /**
     * 查询物料操作流水
     *
     * @param id 物料操作流水主键
     * @return 物料操作流水
     */
    @Override
    public BusiMaterialOperate selectBusiMaterialOperateById(Long id) {
        return busiMaterialOperateMapper.selectBusiMaterialOperateById(id);
    }

    /**
     * 查询物料操作流水列表
     *
     * @param busiMaterialOperate 物料操作流水
     * @return 物料操作流水
     */
    @Override
    public List<BusiMaterialOperate> selectBusiMaterialOperateList(BusiMaterialOperate busiMaterialOperate) {
        return busiMaterialOperateMapper.selectBusiMaterialOperateList(busiMaterialOperate);
    }

    /**
     * 新增物料操作流水
     *
     * @param busiMaterialOperate 物料操作流水
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertBusiMaterialOperate(BusiMaterialOperate busiMaterialOperate) throws ServiceException {
        // 先查询库存
        List<BusiMaterialStock> busiMaterialStocks = queryBusiMaterialStocks(busiMaterialOperate);
        BusiMaterialStock busiMaterialStock;

        if (busiMaterialStocks.size() != 0) { // 已有库存，则更新
            busiMaterialStock = busiMaterialStocks.get(0);
            if ("1".equals(busiMaterialOperate.getOprateType())) { // 1为入库
                busiMaterialStock.setAmountIn(busiMaterialOperate.getAmount() + busiMaterialStock.getAmountIn());
            } else {// 2为入库
                long stockAmount = busiMaterialStock.getAmountIn() - busiMaterialStock.getAmountOut();
                if(busiMaterialOperate.getAmount() > stockAmount){
                    throw new ServiceException("出库超过库存");
                }
                busiMaterialStock.setAmountOut(busiMaterialOperate.getAmount() + busiMaterialStock.getAmountOut());
            }
            busiMaterialStockMapper.updateBusiMaterialStock(busiMaterialStock);
        } else {// 没有库存则新建
            busiMaterialStock = new BusiMaterialStock();
            busiMaterialStock.setColor(busiMaterialOperate.getColor());
            busiMaterialStock.setClassify(busiMaterialOperate.getClassify());
            busiMaterialStock.setOrderId(busiMaterialOperate.getOrderId());
            busiMaterialStock.setUnit(busiMaterialOperate.getUnit());
            busiMaterialStock.setCreateTime(new Date());
            busiMaterialStock.setCreateBy("system");

            if ("1".equals(busiMaterialOperate.getOprateType())) {
                busiMaterialStock.setAmountIn(busiMaterialOperate.getAmount());
            } else {
                throw new ServiceException("尚未建立库存，请先入库再出库");
            }
            busiMaterialStockMapper.insertBusiMaterialStock(busiMaterialStock);
        }

        busiMaterialOperate.setMaterialStockId(busiMaterialStock.getId());
        // 插入操作流水
        return busiMaterialOperateMapper.insertBusiMaterialOperate(busiMaterialOperate);
    }


    private List<BusiMaterialStock> queryBusiMaterialStocks(BusiMaterialOperate busiMaterialOperate) {
        BusiMaterialStock stockQuery = new BusiMaterialStock();
        stockQuery.setOrderId(busiMaterialOperate.getOrderId());
        stockQuery.setColor(busiMaterialOperate.getColor());
        stockQuery.setClassify(busiMaterialOperate.getClassify());
        return busiMaterialStockMapper.selectBusiMaterialStockList(stockQuery);
    }

    /**
     * 修改物料操作流水
     *
     * @param busiMaterialOperate 物料操作流水
     * @return 结果
     */
    @Override
    public int updateBusiMaterialOperate(BusiMaterialOperate busiMaterialOperate) {
        return busiMaterialOperateMapper.updateBusiMaterialOperate(busiMaterialOperate);
    }

    /**
     * 批量删除物料操作流水
     *
     * @param ids 需要删除的物料操作流水主键
     * @return 结果
     */
    @Override
    public int deleteBusiMaterialOperateByIds(String ids) {
        return busiMaterialOperateMapper.deleteBusiMaterialOperateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除物料操作流水信息
     *
     * @param id 物料操作流水主键
     * @return 结果
     */
    @Override
    public int deleteBusiMaterialOperateById(Long id) {
        return busiMaterialOperateMapper.deleteBusiMaterialOperateById(id);
    }
}
