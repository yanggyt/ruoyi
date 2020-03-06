package cn.com.infosouth.arj21.service;

import java.util.List;
import cn.com.infosouth.arj21.domain.InfoAirlineAccessData;

/**
 * 航空公司数据接入Service接口
 * 
 * @author kxnf
 * @date 2020-03-06
 */
public interface IInfoAirlineAccessDataService 
{
    /**
     * 查询航空公司数据接入
     * 
     * @param id 航空公司数据接入ID
     * @return 航空公司数据接入
     */
    public InfoAirlineAccessData selectInfoAirlineAccessDataById(String id);

    /**
     * 查询航空公司数据接入列表
     * 
     * @param infoAirlineAccessData 航空公司数据接入
     * @return 航空公司数据接入集合
     */
    public List<InfoAirlineAccessData> selectInfoAirlineAccessDataList(InfoAirlineAccessData infoAirlineAccessData);

    /**
     * 新增航空公司数据接入
     * 
     * @param infoAirlineAccessData 航空公司数据接入
     * @return 结果
     */
    public int insertInfoAirlineAccessData(InfoAirlineAccessData infoAirlineAccessData);

    /**
     * 修改航空公司数据接入
     * 
     * @param infoAirlineAccessData 航空公司数据接入
     * @return 结果
     */
    public int updateInfoAirlineAccessData(InfoAirlineAccessData infoAirlineAccessData);

    /**
     * 批量删除航空公司数据接入
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteInfoAirlineAccessDataByIds(String ids);

    /**
     * 删除航空公司数据接入信息
     * 
     * @param id 航空公司数据接入ID
     * @return 结果
     */
    public int deleteInfoAirlineAccessDataById(String id);
}
