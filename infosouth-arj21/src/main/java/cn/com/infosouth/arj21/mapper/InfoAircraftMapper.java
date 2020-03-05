package cn.com.infosouth.arj21.mapper;

import java.util.List;
import cn.com.infosouth.arj21.domain.InfoAircraft;

/**
 * 飞机Mapper接口
 * 
 * @author kxnf
 * @date 2020-03-05
 */
public interface InfoAircraftMapper 
{
    /**
     * 查询飞机
     * 
     * @param id 飞机ID
     * @return 飞机
     */
    public InfoAircraft selectInfoAircraftById(String id);

    /**
     * 查询飞机列表
     * 
     * @param infoAircraft 飞机
     * @return 飞机集合
     */
    public List<InfoAircraft> selectInfoAircraftList(InfoAircraft infoAircraft);

    /**
     * 新增飞机
     * 
     * @param infoAircraft 飞机
     * @return 结果
     */
    public int insertInfoAircraft(InfoAircraft infoAircraft);

    /**
     * 修改飞机
     * 
     * @param infoAircraft 飞机
     * @return 结果
     */
    public int updateInfoAircraft(InfoAircraft infoAircraft);

    /**
     * 删除飞机
     * 
     * @param id 飞机ID
     * @return 结果
     */
    public int deleteInfoAircraftById(String id);

    /**
     * 批量删除飞机
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteInfoAircraftByIds(String[] ids);
}
