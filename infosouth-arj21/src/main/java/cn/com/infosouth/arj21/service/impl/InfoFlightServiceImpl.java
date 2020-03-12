package cn.com.infosouth.arj21.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.infosouth.arj21.mapper.InfoFlightMapper;
import cn.com.infosouth.arj21.domain.InfoFlight;
import cn.com.infosouth.arj21.service.IInfoFlightService;
import cn.com.infosouth.common.core.text.Convert;

/**
 * 航班信息Service业务层处理
 * 
 * @author kxnf
 * @date 2020-03-04
 */
@Service
public class InfoFlightServiceImpl implements IInfoFlightService 
{
    @Autowired
    private InfoFlightMapper infoFlightMapper;

    /**
     * 查询航班信息
     * 
     * @param id 航班信息ID
     * @return 航班信息
     */
    @Override
    public InfoFlight selectInfoFlightById(String id)
    {
        return infoFlightMapper.selectInfoFlightById(id);
    }

    /**
     * 查询航班信息列表
     * 
     * @param infoFlight 航班信息
     * @return 航班信息
     */
    @Override
    public List<InfoFlight> selectInfoFlightList(InfoFlight infoFlight)
    {
        return infoFlightMapper.selectInfoFlightList(infoFlight);
    }

    /**
     * 新增航班信息
     * 
     * @param infoFlight 航班信息
     * @return 结果
     */
    @Override
    public int insertInfoFlight(InfoFlight infoFlight)
    {
        return infoFlightMapper.insertInfoFlight(infoFlight);
    }

    /**
     * 修改航班信息
     * 
     * @param infoFlight 航班信息
     * @return 结果
     */
    @Override
    public int updateInfoFlight(InfoFlight infoFlight)
    {
        return infoFlightMapper.updateInfoFlight(infoFlight);
    }

    /**
     * 删除航班信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteInfoFlightByIds(String ids)
    {
        return infoFlightMapper.deleteInfoFlightByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除航班信息信息
     * 
     * @param id 航班信息ID
     * @return 结果
     */
    @Override
    public int deleteInfoFlightById(String id)
    {
        return infoFlightMapper.deleteInfoFlightById(id);
    }

	@Override
	public String findFlightCount() {
		return infoFlightMapper.findFlightCount();
	}

	@Override
	public String getAcTypeByArn(String arn) {
		return infoFlightMapper.getAcTypeByArn(arn);
	}

	//csv文件是否已存在
	@Override
	public boolean csvExist(String csvName) {
		int count = infoFlightMapper.csvExist(csvName);
		return count > 0 ? true: false;
	}

	/**   
	 * @Title: getIdByCsvName   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: String      
	 * @throws   
	 */
	public String getIdByCsvName(String csvName){
		return infoFlightMapper.getIdByCsvName(csvName);
	}
}
