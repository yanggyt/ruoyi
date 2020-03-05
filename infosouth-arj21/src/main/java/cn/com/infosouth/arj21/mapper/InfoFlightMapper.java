package cn.com.infosouth.arj21.mapper;

import java.util.List;
import cn.com.infosouth.arj21.domain.InfoFlight;

/**
 * 航班信息Mapper接口
 * 
 * @author kxnf
 * @date 2020-03-04
 */
public interface InfoFlightMapper
{
    /**
     * 查询航班信息
     * 
     * @param id 航班信息ID
     * @return 航班信息
     */
    public InfoFlight selectInfoFlightById(String id);

    /**
     * 查询航班信息列表
     * 
     * @param infoFlight 航班信息
     * @return 航班信息集合
     */
    public List<InfoFlight> selectInfoFlightList(InfoFlight infoFlight);

    /**
     * 新增航班信息
     * 
     * @param infoFlight 航班信息
     * @return 结果
     */
    public int insertInfoFlight(InfoFlight infoFlight);

    /**
     * 修改航班信息
     * 
     * @param infoFlight 航班信息
     * @return 结果
     */
    public int updateInfoFlight(InfoFlight infoFlight);

    /**
     * 删除航班信息
     * 
     * @param id 航班信息ID
     * @return 结果
     */
    public int deleteInfoFlightById(String id);

    /**
     * 批量删除航班信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteInfoFlightByIds(String[] ids);

	public String findFlightCount();
}
