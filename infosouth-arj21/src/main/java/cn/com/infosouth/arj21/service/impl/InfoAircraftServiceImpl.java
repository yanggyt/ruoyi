package cn.com.infosouth.arj21.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.infosouth.arj21.mapper.InfoAircraftMapper;
import cn.com.infosouth.arj21.domain.InfoAircraft;
import cn.com.infosouth.arj21.service.IInfoAircraftService;
import cn.com.infosouth.common.core.text.Convert;

/**
 * 飞机Service业务层处理
 * 
 * @author kxnf
 * @date 2020-03-05
 */
@Service
public class InfoAircraftServiceImpl implements IInfoAircraftService 
{
    @Autowired
    private InfoAircraftMapper infoAircraftMapper;

    /**
     * 查询飞机
     * 
     * @param id 飞机ID
     * @return 飞机
     */
    @Override
    public InfoAircraft selectInfoAircraftById(String id)
    {
        return infoAircraftMapper.selectInfoAircraftById(id);
    }

    /**
     * 查询飞机列表
     * 
     * @param infoAircraft 飞机
     * @return 飞机
     */
    @Override
    public List<InfoAircraft> selectInfoAircraftList(InfoAircraft infoAircraft)
    {
        return infoAircraftMapper.selectInfoAircraftList(infoAircraft);
    }

    /**
     * 新增飞机
     * 
     * @param infoAircraft 飞机
     * @return 结果
     */
    @Override
    public int insertInfoAircraft(InfoAircraft infoAircraft)
    {
        return infoAircraftMapper.insertInfoAircraft(infoAircraft);
    }

    /**
     * 修改飞机
     * 
     * @param infoAircraft 飞机
     * @return 结果
     */
    @Override
    public int updateInfoAircraft(InfoAircraft infoAircraft)
    {
        return infoAircraftMapper.updateInfoAircraft(infoAircraft);
    }

    /**
     * 删除飞机对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteInfoAircraftByIds(String ids)
    {
        return infoAircraftMapper.deleteInfoAircraftByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除飞机信息
     * 
     * @param id 飞机ID
     * @return 结果
     */
    @Override
    public int deleteInfoAircraftById(String id)
    {
        return infoAircraftMapper.deleteInfoAircraftById(id);
    }
}
