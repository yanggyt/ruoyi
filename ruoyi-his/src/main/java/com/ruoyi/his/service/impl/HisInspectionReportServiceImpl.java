package com.ruoyi.his.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.func.Func;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.his.mapper.HisInspectionReportItemMapper;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import com.ruoyi.his.domain.HisInspectionReportItem;
import com.ruoyi.his.mapper.HisInspectionReportMapper;
import com.ruoyi.his.domain.HisInspectionReport;
import com.ruoyi.his.service.IHisInspectionReportService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * 检查检验Service业务层处理
 * 
 * @author bend
 * @date 2020-07-10
 */
@Service
public class HisInspectionReportServiceImpl implements IHisInspectionReportService
{
    @Resource
    private HisInspectionReportMapper hisInspectionReportMapper;
    @Resource
    private HisInspectionReportItemMapper hisInspectionReportItemMapper;

    /**
     * 查询检查检验
     * 
     * @param id 检查检验ID
     * @return 检查检验
     */
    @Override
    public HisInspectionReport selectHisInspectionReportById(Long id)
    {
        return hisInspectionReportMapper.selectHisInspectionReportById(id);
    }

    /**
     * 查询检查检验
     *
     * @param hisInspectionReport 检查检验ID
     * @return 检查检验
     */
    @Override
    public HisInspectionReport selectHisInspectionReport(HisInspectionReport hisInspectionReport)
    {
        return hisInspectionReportMapper.selectOne(hisInspectionReport);
    }

    /**
     * 查询检查检验列表
     * 
     * @param hisInspectionReport 检查检验
     * @return 检查检验
     */
    @Override
    public List<HisInspectionReport> selectHisInspectionReportList(HisInspectionReport hisInspectionReport)
    {
        return hisInspectionReportMapper.selectHisInspectionReportList(hisInspectionReport);
    }

    /**
     * 新增检查检验
     * 
     * @param hisInspectionReport 检查检验
     * @return 结果
     */
    @Transactional
    @Override
    public int insertHisInspectionReport(HisInspectionReport hisInspectionReport)
    {
        int rows = hisInspectionReportMapper.insertSelective(hisInspectionReport);
        insertHisInspectionReportItem(hisInspectionReport);
        return rows;
    }

    /**
     * 批量新增检查检验
     *
     * @param hisInspectionReportList 检查检验列表
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insertHisInspectionReportBatch(List<HisInspectionReport> hisInspectionReportList)
    {
        //return hisInspectionReportMapper.insertList(hisInspectionReportList);
        AtomicInteger rows= new AtomicInteger();
        if (Func.isNotEmpty(hisInspectionReportList)){
            hisInspectionReportList.forEach(hisInspectionReport -> {
                rows.addAndGet(hisInspectionReportMapper.insertSelective(hisInspectionReport));
                insertHisInspectionReportItem(hisInspectionReport);
            });
            return rows.get();
        } else return -1;

    }

    /**
     * 修改检查检验
     * 
     * @param hisInspectionReport 检查检验
     * @return 结果
     */
    @Transactional
    @Override
    public int updateHisInspectionReport(HisInspectionReport hisInspectionReport)
    {
        hisInspectionReportMapper.deleteHisInspectionReportItemByReportId(hisInspectionReport.getId());
        insertHisInspectionReportItem(hisInspectionReport);
        return hisInspectionReportMapper.updateByPrimaryKeySelective(hisInspectionReport);
    }

    /**
     * 删除检查检验对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteHisInspectionReportByIds(String ids)
    {
        hisInspectionReportMapper.deleteHisInspectionReportItemByReportIds(Convert.toStrArray(ids));
        return hisInspectionReportMapper.deleteByIds(ids);
    }

    /**
     * 删除检查检验信息
     * 
     * @param id 检查检验ID
     * @return 结果
     */
    @Override
    public int deleteHisInspectionReportById(Long id)
    {
        hisInspectionReportMapper.deleteHisInspectionReportItemByReportId(id);
        return hisInspectionReportMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增检查检验明细信息
     * 
     * @param hisInspectionReport 检查检验对象
     */
    public void insertHisInspectionReportItem(HisInspectionReport hisInspectionReport)
    {
        List<HisInspectionReportItem> hisInspectionReportItemList = hisInspectionReport.getHisInspectionReportItemList();
        Long id = hisInspectionReport.getId();
        if (StringUtils.isNotNull(hisInspectionReportItemList))
        {
            List<HisInspectionReportItem> list = new ArrayList<HisInspectionReportItem>();
            for (HisInspectionReportItem hisInspectionReportItem : hisInspectionReportItemList)
            {
                hisInspectionReportItem.setReportId(id);
                list.add(hisInspectionReportItem);
            }
            if (list.size() > 0)
            {
                hisInspectionReportMapper.batchHisInspectionReportItem(list);
            }
        }
    }

    /**
     *
     * @param hisInspectionReportItem 检查检验项目详情
     * @return 检查检验项目详情列表
     */
    @Override
    public List<HisInspectionReportItem> selectHisInspectionReportItemList(HisInspectionReportItem hisInspectionReportItem)
    {
        return hisInspectionReportItemMapper.selectHisInspectionReportItemList(hisInspectionReportItem);
    }
}
