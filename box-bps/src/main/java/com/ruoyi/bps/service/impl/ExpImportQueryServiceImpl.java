package com.ruoyi.bps.service.impl;

import com.ruoyi.bps.domain.ExpImportQuery;
import com.ruoyi.bps.domain.ExpressInfo;
import com.ruoyi.bps.mapper.ExpImportQueryMapper;
import com.ruoyi.bps.mapper.ExpressInfoMapper;
import com.ruoyi.bps.service.IExpImportQueryService;
import com.ruoyi.bps.service.IExpressInfoService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel批量快递查询Service业务层处理
 * 
 * @author Bo
 * @date 2021-07-21
 */
@Service
public class ExpImportQueryServiceImpl implements IExpImportQueryService 
{
    @Autowired
    private ExpImportQueryMapper expImportQueryMapper;

    @Autowired
    private IExpressInfoService expressInfoService;

    @Autowired
    private ExpressInfoMapper expressInfoMapper;

    /**
     * 查询Excel批量快递查询
     * 
     * @param sid Excel批量快递查询ID
     * @return Excel批量快递查询
     */
    @Override
    public ExpImportQuery selectExpImportQueryById(Long sid)
    {
        return expImportQueryMapper.selectExpImportQueryById(sid);
    }

    /**
     * 查询Excel批量快递查询列表
     * 
     * @param expImportQuery Excel批量快递查询
     * @return Excel批量快递查询
     */
    @Override
    public List<ExpImportQuery> selectExpImportQueryList(ExpImportQuery expImportQuery)
    {
        return expImportQueryMapper.selectExpImportQueryList(expImportQuery);
    }

    /**
     * 新增Excel批量快递查询
     * 
     * @param expImportQuery Excel批量快递查询
     * @return 结果
     */
    @Override
    public int insertExpImportQuery(ExpImportQuery expImportQuery)
    {
        return expImportQueryMapper.insertExpImportQuery(expImportQuery);
    }

    /**
     * 修改Excel批量快递查询
     * 
     * @param expImportQuery Excel批量快递查询
     * @return 结果
     */
    @Override
    public int updateExpImportQuery(ExpImportQuery expImportQuery)
    {
        return expImportQueryMapper.updateExpImportQuery(expImportQuery);
    }

    /**
     * 删除Excel批量快递查询对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteExpImportQueryByIds(String ids)
    {
        return expImportQueryMapper.deleteExpImportQueryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除Excel批量快递查询信息
     * 
     * @param sid Excel批量快递查询ID
     * @return 结果
     */
    @Override
    public int deleteExpImportQueryById(Long sid)
    {
        return expImportQueryMapper.deleteExpImportQueryById(sid);
    }


    /**
     * 删除Excel批量快递查询信息
     *
     * @param expressInfoList Excel导入的快递列表
     * @return 结果
     */
    @Override
    public AjaxResult importData(List<ExpressInfo> expressInfoList) {
        String queryTime= DateUtils.dateTimeNow("yyyy-MM-dd HH:mm:ss");
        String queryId= LocalDateTime.now().toString();
        ExpImportQuery expImportQuery=new ExpImportQuery();
        List<ExpressInfo> expressInfoListForInsert=new ArrayList<>();
        try{
            //将查询到的快递结果放到expressInfoListForInsert，并插入到数据库表expressInfo
            for( ExpressInfo expressInfo:expressInfoList){
                ExpressInfo ei= expressInfoService.SelectExpressInfo(expressInfo);
                ei.setQueryId(queryId);
                ei.setQueryUserName(ShiroUtils.getSysUser().getUserName());
                ei.setQueryType("excel");
                ei.setQueryTime(queryTime);
                //expressInfoService.insertExpressInfo(ei);
                expressInfoListForInsert.add(ei);
            }
            expressInfoMapper.batchInsertExpressInfo(expressInfoListForInsert);
            //将本次excel导入查询记录到数据表exp_import_query
            expImportQuery.setQueryTime(queryTime);
            expImportQuery.setQueryLoginName(ShiroUtils.getLoginName());
            expImportQuery.setQueryUserName(ShiroUtils.getSysUser().getUserName());
            expImportQuery.setFinishTime(DateUtils.dateTimeNow("yyyy-MM-dd HH:mm:ss"));
            expImportQuery.setQueryIp(ShiroUtils.getIp());
            expImportQuery.setStatus("success");
            expImportQuery.setQueryQty(String.valueOf(expressInfoList.size()));
            expImportQuery.setQueryId(queryId);
            expImportQueryMapper.insertExpImportQuery(expImportQuery);

            return AjaxResult.success("导入查询成功!");
        }catch (Exception e){
            expImportQuery.setStatus("fail");
            return AjaxResult.error(e.getMessage());
        }
    }
}
