package com.ruoyi.his.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.func.Func;
import com.ruoyi.common.func.RandomType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.domain.HisOutpatientExpensesBill;
import com.ruoyi.his.domain.HisOutpatientExpensesBillDetail;
import com.ruoyi.his.mapper.HisOutpatientExpensesBillDetailMapper;
import com.ruoyi.his.mapper.HisOutpatientExpensesBillMapper;
import com.ruoyi.his.service.IHisOutpatientExpensesBillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 待缴费单Service业务层处理
 *
 * @author bend
 * @date 2020-07-09
 */
@Service
public class HisOutpatientExpensesBillServiceImpl implements IHisOutpatientExpensesBillService {
    @Resource
    private HisOutpatientExpensesBillMapper hisOutpatientExpensesBillMapper;
    @Resource
    private HisOutpatientExpensesBillDetailMapper hisOutpatientExpensesBillDetailMapper;

    /**
     * 查询待缴费单
     *
     * @param id 待缴费单ID
     * @return 待缴费单
     */
    @Override
    public HisOutpatientExpensesBill selectHisOutpatientExpensesBillById(Long id) {
        return hisOutpatientExpensesBillMapper.selectHisOutpatientExpensesBillById(id);
    }

    /**
     * 查询待缴费单
     *
     * @param hisOutpatientExpensesBill 待缴费单ID
     * @return 待缴费单
     */
    @Override
    public HisOutpatientExpensesBill selectHisOutpatientExpensesBill(HisOutpatientExpensesBill hisOutpatientExpensesBill) {
        return hisOutpatientExpensesBillMapper.selectOne(hisOutpatientExpensesBill);
    }

    /**
     * 查询待缴费单列表
     *
     * @param hisOutpatientExpensesBill 待缴费单
     * @return 待缴费单
     */
    @Override
    public List<HisOutpatientExpensesBill> selectHisOutpatientExpensesBillList(HisOutpatientExpensesBill hisOutpatientExpensesBill) {
        return hisOutpatientExpensesBillMapper.selectHisOutpatientExpensesBillList(hisOutpatientExpensesBill);
    }

    /**
     * 新增待缴费单
     *
     * @param hisOutpatientExpensesBill 待缴费单
     * @return 结果
     */
    @Transactional
    @Override
    public int insertHisOutpatientExpensesBill(HisOutpatientExpensesBill hisOutpatientExpensesBill) {
        int rows = hisOutpatientExpensesBillMapper.insertSelective(hisOutpatientExpensesBill);
        insertHisOutpatientExpensesBillDetail(hisOutpatientExpensesBill);
        return rows;
    }

    /**
     * 批量新增待缴费单
     *
     * @param hisOutpatientExpensesBillList 待缴费单列表
     * @return 结果
     */
    @Transactional
    @Override
    public int insertHisOutpatientExpensesBillBatch(List<HisOutpatientExpensesBill> hisOutpatientExpensesBillList) {
        AtomicInteger rows = new AtomicInteger();
        if (Func.isNotEmpty(hisOutpatientExpensesBillList)) {
            hisOutpatientExpensesBillList.forEach(hisOutpatientExpensesBill -> {
                rows.addAndGet(hisOutpatientExpensesBillMapper.insertSelective(hisOutpatientExpensesBill));
                insertHisOutpatientExpensesBillDetail(hisOutpatientExpensesBill);
            });
            return rows.get();
        } else return -1;
    }

    /**
     * 修改待缴费单
     *
     * @param hisOutpatientExpensesBill 待缴费单
     * @return 结果
     */
    @Transactional
    @Override
    public int updateHisOutpatientExpensesBill(HisOutpatientExpensesBill hisOutpatientExpensesBill) {
        hisOutpatientExpensesBillMapper.deleteHisOutpatientExpensesBillDetailByBillId(hisOutpatientExpensesBill.getId());
        insertHisOutpatientExpensesBillDetail(hisOutpatientExpensesBill);
        return hisOutpatientExpensesBillMapper.updateByPrimaryKeySelective(hisOutpatientExpensesBill);
    }

    /**
     * 删除待缴费单对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteHisOutpatientExpensesBillByIds(String ids) {
        hisOutpatientExpensesBillMapper.deleteHisOutpatientExpensesBillDetailByBillIds(Convert.toStrArray(ids));
        return hisOutpatientExpensesBillMapper.deleteByIds(ids);
    }

    /**
     * 删除待缴费单信息
     *
     * @param id 待缴费单ID
     * @return 结果
     */
    @Override
    public int deleteHisOutpatientExpensesBillById(Long id) {
        hisOutpatientExpensesBillMapper.deleteHisOutpatientExpensesBillDetailByBillId(id);
        return hisOutpatientExpensesBillMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增清单详情信息
     *
     * @param hisOutpatientExpensesBill 待缴费单对象
     */
    public void insertHisOutpatientExpensesBillDetail(HisOutpatientExpensesBill hisOutpatientExpensesBill) {
        List<HisOutpatientExpensesBillDetail> hisOutpatientExpensesBillDetailList = hisOutpatientExpensesBill.getHisOutpatientExpensesBillDetailList();
        Long billId = hisOutpatientExpensesBill.getId();
        if (StringUtils.isNotNull(hisOutpatientExpensesBillDetailList)) {
            List<HisOutpatientExpensesBillDetail> list = new ArrayList<HisOutpatientExpensesBillDetail>();
            for (HisOutpatientExpensesBillDetail hisOutpatientExpensesBillDetail : hisOutpatientExpensesBillDetailList) {
                //自定义ID规则
                hisOutpatientExpensesBillDetail.setId(Func.random(32, RandomType.INT));
                hisOutpatientExpensesBillDetail.setBillId(billId);
                list.add(hisOutpatientExpensesBillDetail);
            }
            if (list.size() > 0) {
                hisOutpatientExpensesBillMapper.batchHisOutpatientExpensesBillDetail(list);
            }
        }
    }

    @Override
    public List<HisOutpatientExpensesBillDetail> selectHisOutpatientExpensesBillDetailList(HisOutpatientExpensesBillDetail hisOutpatientExpensesBillDetail) {
        return hisOutpatientExpensesBillDetailMapper.selectHisOutpatientExpensesBillDetailList(hisOutpatientExpensesBillDetail);
    }

    /**
     * @param hisOutpatientExpensesBillUpdateList 待缴费单详情
     */
    @Transactional
    @Override
    public void updateHisOutpatientExpensesBillBatch(List<HisOutpatientExpensesBill> hisOutpatientExpensesBillUpdateList) {
        if (Func.isNotEmpty(hisOutpatientExpensesBillUpdateList)) {
            //更新详情
            hisOutpatientExpensesBillUpdateList.forEach(hisOutpatientExpensesBill -> {
                hisOutpatientExpensesBillMapper.updateByPrimaryKeySelective(hisOutpatientExpensesBill);
                hisOutpatientExpensesBillMapper.deleteHisOutpatientExpensesBillDetailByBillId(hisOutpatientExpensesBill.getId());
                insertHisOutpatientExpensesBillDetail(hisOutpatientExpensesBill);
            });
        }
    }

    /**
     * @param hisOutpatientExpensesBill 待缴费单
     */
    @Override
    public void updateHisOutpatientExpensesBillStatus(HisOutpatientExpensesBill hisOutpatientExpensesBill) {
        hisOutpatientExpensesBillMapper.updateByPrimaryKeySelective(hisOutpatientExpensesBill);
    }

    @Transactional
    @Override
    public void deleteHisOutpatientExpensesBillBatch(List<HisOutpatientExpensesBill> hisOutpatientExpensesBillList) {
        if (Func.isNotEmpty(hisOutpatientExpensesBillList)) {
            hisOutpatientExpensesBillList.forEach(hisOutpatientExpensesBill -> {
                hisOutpatientExpensesBillMapper.deleteHisOutpatientExpensesBillDetailByBillId(hisOutpatientExpensesBill.getId());
                hisOutpatientExpensesBillMapper.deleteByPrimaryKey(hisOutpatientExpensesBill.getId());
            });
        }
    }
}
