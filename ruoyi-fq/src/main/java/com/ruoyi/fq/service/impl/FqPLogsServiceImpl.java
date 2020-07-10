package com.ruoyi.fq.service.impl;

import java.util.List;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.fq.mapper.FqPLogsMapper;
import com.ruoyi.fq.domain.FqPLogs;
import com.ruoyi.fq.service.IFqPLogsService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.util.ObjectUtils;

/**
 * 封铅袋出入库记录Service业务层处理
 *
 * @author mario
 * @date 2020-07-09
 */
@Service
public class FqPLogsServiceImpl implements IFqPLogsService
{
    @Autowired
    private FqPLogsMapper fqPLogsMapper;

    /**
     * 查询封铅袋出入库记录
     *
     * @param id 封铅袋出入库记录ID
     * @return 封铅袋出入库记录
     */
    @Override
    public FqPLogs selectFqPLogsById(String id)
    {
        return fqPLogsMapper.selectFqPLogsById(id);
    }

    /**
     * 查询封铅袋出入库记录列表
     *
     * @param fqPLogs 封铅袋出入库记录
     * @return 封铅袋出入库记录
     */
    @Override
    public List<FqPLogs> selectFqPLogsList(FqPLogs fqPLogs)
    {
        return fqPLogsMapper.selectFqPLogsList(fqPLogs);
    }

    /**
     * 新增封铅袋出入库记录
     *
     * @param fqPLogs 封铅袋出入库记录
     * @return 结果
     */
            @Override
    public int insertFqPLogs(FqPLogs fqPLogs)
    {
                                                                                                                                                                                                                                            fqPLogs.setCreateTime(DateUtils.getNowDate());
        return fqPLogsMapper.insertFqPLogs(fqPLogs);
    }

    /**
     * 修改封铅袋出入库记录
     *
     * @param fqPLogs 封铅袋出入库记录
     * @return 结果
     */
            @Override
    public int updateFqPLogs(FqPLogs fqPLogs)
    {
                                                                                                                                                                                                                                                                                    fqPLogs.setUpdateTime(DateUtils.getNowDate());
        return fqPLogsMapper.updateFqPLogs(fqPLogs);
    }

    /**
     * 删除封铅袋出入库记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
            @Override
    public int deleteFqPLogsByIds(String ids)
    {
                return fqPLogsMapper.deleteFqPLogsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除封铅袋出入库记录信息
     *
     * @param id 封铅袋出入库记录ID
     * @return 结果
     */
    @Override
    public int deleteFqPLogsById(String id)
    {
                return fqPLogsMapper.deleteFqPLogsById(id);
    }
        
    @Override
    public String importData(List<FqPLogs> list, Boolean isUpdateSupport, String operName) {
        /*if (ObjectUtils.isEmpty(list) || list.size() == 0)
        {
            throw new BusinessException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (FqPLogs data : list)
        {
            try
            {
                // 验证是否存在
                FqPLogs parms = new FqPLogs();
//                parms.setStartNo(data.getStartNo());
//                parms.setEndNo(data.getEndNo());
//                parms.setColor(data.getColor());
//                parms.setbName(data.getbName());
                //List<FqPLogs> u = fqPLogsMapper.selectFqPackageList(parms);
                if (u == null || u.size() == 0)
                {
                    data.setCreateBy(operName);
                    this.insertFqPLogs(data);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、起始编号: " + data.getStartNo()+ " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    data.setUpdateBy(operName);
                    this.updateFqPLogs(data);
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
