package com.ruoyi.fq.service.impl;

import java.util.List;
                                                                                                                                                                                                import com.ruoyi.common.utils.DateUtils;
            import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
    import com.ruoyi.fq.mapper.FqTableMapper;
import com.ruoyi.fq.domain.FqTable;
import com.ruoyi.fq.service.IFqTableService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import org.springframework.util.ObjectUtils;

/**
 * 封铅登记Service业务层处理
 *
 * @author mario
 * @date 2020-07-09
 */
@Service
public class FqTableServiceImpl implements IFqTableService
{
    @Autowired
    private FqTableMapper fqTableMapper;

    /**
     * 查询封铅登记
     *
     * @param id 封铅登记ID
     * @return 封铅登记
     */
    @Override
    public FqTable selectFqTableById(Long id)
    {
        return fqTableMapper.selectFqTableById(id);
    }

    /**
     * 查询封铅登记列表
     *
     * @param fqTable 封铅登记
     * @return 封铅登记
     */
    @Override
    public List<FqTable> selectFqTableList(FqTable fqTable)
    {
        return fqTableMapper.selectFqTableList(fqTable);
    }

    /**
     * 新增封铅登记
     *
     * @param fqTable 封铅登记
     * @return 结果
     */
            @Override
    public int insertFqTable(FqTable fqTable)
    {
                                                                                                                                                                                                                                                                                                                            fqTable.setCreateTime(DateUtils.getNowDate());
                                                                                                                        return fqTableMapper.insertFqTable(fqTable);
            }

    /**
     * 批量新增封铅登记
     *
     * @param fqTable 封铅登记
     * @return 结果
     */
    @Override
    public int batchInsertFqTable(List<FqTable> list)
    {
        return fqTableMapper.batchInsertFqTable(list);
    }

    /**
     * 修改封铅登记
     *
     * @param fqTable 封铅登记
     * @return 结果
     */
            @Override
    public int updateFqTable(FqTable fqTable)
    {
                                                                                                                                                                                                                                                                                                                                                                    fqTable.setUpdateTime(DateUtils.getNowDate());
                                                                            return fqTableMapper.updateFqTable(fqTable);
    }

    /**
     * 删除封铅登记对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
            @Override
    public int deleteFqTableByIds(String ids)
    {
                return fqTableMapper.deleteFqTableByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除封铅登记信息
     *
     * @param id 封铅登记ID
     * @return 结果
     */
    @Override
    public int deleteFqTableById(Long id)
    {
                return fqTableMapper.deleteFqTableById(id);
    }
        
    @Override
    public String importData(List<FqTable> list, Boolean isUpdateSupport, String operName) {
        /*if (ObjectUtils.isEmpty(list) || list.size() == 0)
        {
            throw new BusinessException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (FqTable data : list)
        {
            try
            {
                // 验证是否存在
                FqTable parms = new FqTable();
                parms.setStartNo(data.getStartNo());
                parms.setEndNo(data.getEndNo());
                parms.setColor(data.getColor());
                parms.setbName(data.getbName());
                List<FqTable> u = fqTableMapper.selectFqPackageList(parms);
                if (u == null || u.size() == 0)
                {
                    data.setCreateBy(operName);
                    this.insertFqTable(data);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、起始编号: " + data.getStartNo()+ " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    data.setUpdateBy(operName);
                    this.updateFqTable(data);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、起始编号: " + data.getStartNo() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、起始编号: " + data.getStartNo() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、起始编号: " + data.getStartNo() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();*/
        return null;
    }
}
