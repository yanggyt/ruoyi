package cn.com.infosouth.arj21.service.impl;

import java.util.List;
import cn.com.infosouth.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.infosouth.arj21.mapper.InfoAirlineAccessDataMapper;
import cn.com.infosouth.arj21.domain.InfoAirlineAccessData;
import cn.com.infosouth.arj21.service.IInfoAirlineAccessDataService;
import cn.com.infosouth.common.core.text.Convert;

/**
 * 航空公司数据接入Service业务层处理
 * 
 * @author kxnf
 * @date 2020-03-06
 */
@Service
public class InfoAirlineAccessDataServiceImpl implements IInfoAirlineAccessDataService 
{
    @Autowired
    private InfoAirlineAccessDataMapper infoAirlineAccessDataMapper;

    /**
     * 查询航空公司数据接入
     * 
     * @param id 航空公司数据接入ID
     * @return 航空公司数据接入
     */
    @Override
    public InfoAirlineAccessData selectInfoAirlineAccessDataById(String id)
    {
        return infoAirlineAccessDataMapper.selectInfoAirlineAccessDataById(id);
    }

    /**
     * 查询航空公司数据接入列表
     * 
     * @param infoAirlineAccessData 航空公司数据接入
     * @return 航空公司数据接入
     */
    @Override
    public List<InfoAirlineAccessData> selectInfoAirlineAccessDataList(InfoAirlineAccessData infoAirlineAccessData)
    {
        return infoAirlineAccessDataMapper.selectInfoAirlineAccessDataList(infoAirlineAccessData);
    }

    /**
     * 新增航空公司数据接入
     * 
     * @param infoAirlineAccessData 航空公司数据接入
     * @return 结果
     */
    @Override
    public int insertInfoAirlineAccessData(InfoAirlineAccessData infoAirlineAccessData)
    {
        infoAirlineAccessData.setCreateTime(DateUtils.getNowDate());
        return infoAirlineAccessDataMapper.insertInfoAirlineAccessData(infoAirlineAccessData);
    }

    /**
     * 修改航空公司数据接入
     * 
     * @param infoAirlineAccessData 航空公司数据接入
     * @return 结果
     */
    @Override
    public int updateInfoAirlineAccessData(InfoAirlineAccessData infoAirlineAccessData)
    {
        return infoAirlineAccessDataMapper.updateInfoAirlineAccessData(infoAirlineAccessData);
    }

    /**
     * 删除航空公司数据接入对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteInfoAirlineAccessDataByIds(String ids)
    {
        return infoAirlineAccessDataMapper.deleteInfoAirlineAccessDataByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除航空公司数据接入信息
     * 
     * @param id 航空公司数据接入ID
     * @return 结果
     */
    @Override
    public int deleteInfoAirlineAccessDataById(String id)
    {
        return infoAirlineAccessDataMapper.deleteInfoAirlineAccessDataById(id);
    }
}
