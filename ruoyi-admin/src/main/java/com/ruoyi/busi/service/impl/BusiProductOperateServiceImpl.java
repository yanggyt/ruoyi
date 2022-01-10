package com.ruoyi.busi.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ruoyi.busi.domain.*;
import com.ruoyi.busi.mapper.BusiOrderMapper;
import com.ruoyi.busi.mapper.BusiProductStockMapper;
import com.ruoyi.busi.mapper.BusiSubTaskMapper;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.busi.mapper.BusiProductOperateMapper;
import com.ruoyi.busi.service.IBusiProductOperateService;
import com.ruoyi.common.core.text.Convert;

/**
 * 成品操作流水Service业务层处理
 *
 * @author WangCL
 * @date 2022-01-08
 */
@Service
public class BusiProductOperateServiceImpl implements IBusiProductOperateService {
    @Autowired
    private BusiProductOperateMapper busiProductOperateMapper;
    @Autowired
    private BusiProductStockMapper busiProductStockMapper;

    @Autowired
    private BusiOrderMapper busiOrderMapper;

    @Autowired
    private BusiSubTaskMapper subTaskMapper;

    /**
     * 查询成品操作流水
     *
     * @param id 成品操作流水主键
     * @return 成品操作流水
     */
    @Override
    public BusiProductOperate selectBusiProductOperateById(String id) {
        return busiProductOperateMapper.selectBusiProductOperateById(id);
    }

    /**
     * 查询成品操作流水列表
     *
     * @param busiProductOperate 成品操作流水
     * @return 成品操作流水
     */
    @Override
    public List<BusiProductOperate> selectBusiProductOperateList(BusiProductOperate busiProductOperate) {
        return busiProductOperateMapper.selectBusiProductOperateList(busiProductOperate);
    }

    /**
     * 新增成品操作流水
     *
     * @param busiProductOperate 成品操作流水
     * @return 结果
     */
    @Override
    public int insertBusiProductOperate(BusiProductOperate busiProductOperate) {
        List<BusiProductStock> busiProductStocks = queryBusiProductStocks(busiProductOperate);
        busiProductOperate.setProductValue(cacuValue(busiProductOperate));
        busiProductOperate.setCreateTime(DateUtils.getNowDate());

        BusiProductStock busiProductStock;
        if (busiProductStocks.size() != 0) {
            busiProductStock = busiProductStocks.get(0);
            if ("1".equals(busiProductOperate.getOprateType())) { //1为入库
                busiProductStock.setAmount(busiProductStock.getAmount() + busiProductOperate.getAmount());
                updateCompletedAmount(busiProductOperate.getSubTaskId(), busiProductOperate.getAmount()); // 更新子任务完成量
            } else {// 出库或退回
                if (busiProductOperate.getAmount() > busiProductStock.getAmount()) {
                    throw new ServiceException("操作值超过库存，当前库存值：" + busiProductStock.getAmount());
                }
                busiProductStock.setAmount(busiProductStock.getAmount() - busiProductOperate.getAmount());
                if ("3".equals(busiProductOperate.getOprateType())) { // 退回时，任务完成值还要减掉退回值，响应的操作值更新为负值，以方便后面统计
                    busiProductOperate.setAmount(-busiProductOperate.getAmount());
                    busiProductOperate.setProductValue(-busiProductOperate.getProductValue());
                    updateCompletedAmount(busiProductOperate.getSubTaskId(), -busiProductOperate.getAmount()); // 更新子任务完成量
                }
            }
            busiProductStock.setUpdateTime(new Date());
            busiProductStockMapper.updateBusiProductStock(busiProductStock);
        } else {
            busiProductStock = createBusiProductStock(busiProductOperate);
            if ("1".equals(busiProductOperate.getOprateType())) { //1为入库
                busiProductStock.setAmount(busiProductOperate.getAmount());
                long completedAmount = busiProductOperate.getAmount();
                updateCompletedAmount(busiProductOperate.getSubTaskId(), completedAmount);
                busiProductStockMapper.insertBusiProductStock(busiProductStock); //插入库存
            } else { // 出库或退回
                throw new ServiceException("尚未建立库存，请先进行入库操作");
            }
        }

        busiProductOperate.setProductStockId(busiProductStock.getId());
        return busiProductOperateMapper.insertBusiProductOperate(busiProductOperate);
    }

    private void updateCompletedAmount(String subTaskId, long operateAmount) {
        BusiSubTask busiSubTask = subTaskMapper.selectBusiSubTaskById(subTaskId);
        long completedAmount = busiSubTask.getCompletedAmount();
        busiSubTask.setCompletedAmount(completedAmount + operateAmount);
        subTaskMapper.updateBusiSubTask(busiSubTask); // 更新子任务完成量
    }

    /**
     * 计算产值
     *
     * @param busiProductOperate
     * @return
     */
    private double cacuValue(BusiProductOperate busiProductOperate) {
        BusiOrder order = busiOrderMapper.selectBusiOrderById(busiProductOperate.getOrderId());
        double price = order.getPrice();
        double value = busiProductOperate.getAmount() * price;
        return value;
    }

    private BusiProductStock createBusiProductStock(BusiProductOperate busiProductOperate) {
        BusiProductStock busiProductStock;
        busiProductStock = new BusiProductStock();
        busiProductStock.setOrderId(busiProductOperate.getOrderId());
        busiProductStock.setColor(busiProductOperate.getColor());
        busiProductStock.setSize(busiProductOperate.getSize());
        busiProductStock.setCreateBy("system");
        busiProductStock.setCreateTime(new Date());
        return busiProductStock;
    }

    private List<BusiProductStock> queryBusiProductStocks(BusiProductOperate busiProductOperate) {
        BusiProductStock stockQuery = new BusiProductStock();
        stockQuery.setOrderId(busiProductOperate.getOrderId());
        stockQuery.setColor(busiProductOperate.getColor());
        stockQuery.setSize(busiProductOperate.getSize());
        return busiProductStockMapper.selectBusiProductStockList(stockQuery);
    }

    /**
     * 修改成品操作流水
     *
     * @param busiProductOperate 成品操作流水
     * @return 结果
     */
    @Override
    public int updateBusiProductOperate(BusiProductOperate busiProductOperate) {
        return busiProductOperateMapper.updateBusiProductOperate(busiProductOperate);
    }

    /**
     * 批量删除成品操作流水
     *
     * @param ids 需要删除的成品操作流水主键
     * @return 结果
     */
    @Override
    public int deleteBusiProductOperateByIds(String ids) {
        return busiProductOperateMapper.deleteBusiProductOperateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除成品操作流水信息
     *
     * @param id 成品操作流水主键
     * @return 结果
     */
    @Override
    public int deleteBusiProductOperateById(String id) {
        return busiProductOperateMapper.deleteBusiProductOperateById(id);
    }

    @Override
    public List<Map<String, String>> selProductSizeByLineId(String lineId) {
        return busiProductOperateMapper.selProductSizeByLineId(lineId);
    }

    @Override
    public List<Map<String, String>> selProductColorByLineIdAndSize(Map<String, String> map) {
        return busiProductOperateMapper.selProductColorByLineIdAndSize(map);
    }
}
