package com.ruoyi.his.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.func.Func;
import com.ruoyi.common.func.RandomType;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.domain.HisPatientExpenses;
import com.ruoyi.his.domain.HisPatientExpensesDetail;
import com.ruoyi.his.mapper.HisPatientExpensesDetailMapper;
import com.ruoyi.his.mapper.HisPatientExpensesMapper;
import com.ruoyi.his.service.IHisPatientExpensesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 费用记录Service业务层处理
 *
 * @author bend
 * @date 2020-07-09
 */
@Service
public class HisPatientExpensesServiceImpl implements IHisPatientExpensesService
{
    @Resource
    private HisPatientExpensesMapper hisPatientExpensesMapper;
    @Resource
    private HisPatientExpensesDetailMapper hisPatientExpensesDetailMapper;

    /**
     * 查询费用记录
     *
     * @param id 费用记录ID
     * @return 费用记录
     */
    @Override
    public HisPatientExpenses selectHisPatientExpensesById(Long id)
    {
        return hisPatientExpensesMapper.selectHisPatientExpensesById(id);
    }

    /**
     * 查询费用记录
     *
     * @param hisPatientExpenses 费用记录ID
     * @return 费用记录
     */
    @Override
    public HisPatientExpenses selectHisPatientExpenses(HisPatientExpenses hisPatientExpenses)
    {
        return hisPatientExpensesMapper.selectOne(hisPatientExpenses);
    }

    /**
     * 查询费用记录列表
     *
     * @param hisPatientExpenses 费用记录
     * @return 费用记录
     */
    @Override
    public List<HisPatientExpenses> selectHisPatientExpensesList(HisPatientExpenses hisPatientExpenses)
    {
        return hisPatientExpensesMapper.selectHisPatientExpensesList(hisPatientExpenses);
    }

    /**
     * 新增费用记录
     *
     * @param hisPatientExpenses 费用记录
     * @return 结果
     */
    @Transactional
    @Override
    public int insertHisPatientExpenses(HisPatientExpenses hisPatientExpenses)
    {
        int rows = hisPatientExpensesMapper.insertSelective(hisPatientExpenses);
        insertHisPatientExpensesDetail(hisPatientExpenses);
        return rows;
    }

    /**
     * 批量新增费用记录
     *
     * @param hisPatientExpensesList 费用记录列表
     */
    @Transactional
    @Override
    public void insertHisPatientExpensesBatch(List<HisPatientExpenses> hisPatientExpensesList)
    {
        AtomicInteger rows= new AtomicInteger();
        if (Func.isNotEmpty(hisPatientExpensesList)){
            hisPatientExpensesList.forEach(hisPatientExpenses -> {
                rows.addAndGet(hisPatientExpensesMapper.insertSelective(hisPatientExpenses));
                insertHisPatientExpensesDetail(hisPatientExpenses);
            });
            rows.get();
        }
    }

    /**
     * 修改费用记录
     *
     * @param hisPatientExpenses 费用记录
     * @return 结果
     */
    @Transactional
    @Override
    public int updateHisPatientExpenses(HisPatientExpenses hisPatientExpenses)
    {
        hisPatientExpensesMapper.deleteHisPatientExpensesDetailByExpensesId(hisPatientExpenses.getId());
        insertHisPatientExpensesDetail(hisPatientExpenses);
        return hisPatientExpensesMapper.updateByPrimaryKeySelective(hisPatientExpenses);
    }

    /**
     * 删除费用记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteHisPatientExpensesByIds(String ids)
    {
        hisPatientExpensesMapper.deleteHisPatientExpensesDetailByExpensesIds(Convert.toStrArray(ids));
        return hisPatientExpensesMapper.deleteByIds(ids);
    }

    /**
     * 删除费用记录信息
     *
     * @param id 费用记录ID
     * @return 结果
     */
    @Override
    public int deleteHisPatientExpensesById(Long id)
    {
        hisPatientExpensesMapper.deleteHisPatientExpensesDetailByExpensesId(id);
        return hisPatientExpensesMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增费用详情信息
     *
     * @param hisPatientExpenses 费用记录对象
     */
    public void insertHisPatientExpensesDetail(HisPatientExpenses hisPatientExpenses)
    {
        List<HisPatientExpensesDetail> hisPatientExpensesDetailList = hisPatientExpenses.getHisPatientExpensesDetailList();
        Long expensesId = hisPatientExpenses.getId();
        if (StringUtils.isNotNull(hisPatientExpensesDetailList))
        {
            List<HisPatientExpensesDetail> list = new ArrayList<>();
            for (HisPatientExpensesDetail hisPatientExpensesDetail : hisPatientExpensesDetailList)
            {
                //自定义ID规则
                hisPatientExpensesDetail.setId(Func.random(32, RandomType.INT));
                hisPatientExpensesDetail.setExpensesId(expensesId);
                list.add(hisPatientExpensesDetail);
            }
            if (list.size() > 0)
            {
                hisPatientExpensesMapper.batchHisPatientExpensesDetail(list);
            }
        }
    }

    /**
     *
     * @param hisPatientExpensesList 费用列表
     */
    @Override
    public void updateHisPatientExpensesBatch(List<HisPatientExpenses> hisPatientExpensesList)
    {
        if (Func.isNotEmpty(hisPatientExpensesList)) {
            //更新详情
            hisPatientExpensesList.forEach(hisPatientExpenses -> {
                hisPatientExpensesMapper.updateByPrimaryKeySelective(hisPatientExpenses);
                hisPatientExpensesMapper.deleteHisPatientExpensesDetailByExpensesId(hisPatientExpenses.getId());
                insertHisPatientExpensesDetail(hisPatientExpenses);
                //属性 isChargeFee是否收费 不是必须的！
            });
        }
    }

    /**
     *
     * @param hisPatientExpensesDetail 费用详情
     */
    @Override
    public List<HisPatientExpensesDetail> selectHisPatientExpensesDetailList(HisPatientExpensesDetail hisPatientExpensesDetail)
    {
        return hisPatientExpensesDetailMapper.selectHisPatientExpensesDetailList(hisPatientExpensesDetail);
    }

    /**
     * 批量删除
     * @param hisPatientExpensesList
     */
    @Transactional
    @Override
    public void deleteHisPatientExpensesBatch(List<HisPatientExpenses> hisPatientExpensesList)
    {
        if (Func.isNotEmpty(hisPatientExpensesList)) {
            hisPatientExpensesList.forEach(hisPatientExpenses -> {
                hisPatientExpensesMapper.deleteHisPatientExpensesDetailByExpensesId(hisPatientExpenses.getId());
                hisPatientExpensesMapper.deleteByPrimaryKey(hisPatientExpenses.getId());
            });
        }
    }
}
