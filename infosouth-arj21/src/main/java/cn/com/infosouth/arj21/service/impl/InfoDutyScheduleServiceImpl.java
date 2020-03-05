package cn.com.infosouth.arj21.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.infosouth.arj21.mapper.InfoDutyScheduleMapper;
import cn.com.infosouth.arj21.domain.InfoDutySchedule;
import cn.com.infosouth.arj21.service.IInfoDutyScheduleService;
import cn.com.infosouth.common.core.text.Convert;

/**
 * 任务调度Service业务层处理
 * 
 * @author kxnf
 * @date 2020-03-05
 */
@Service
public class InfoDutyScheduleServiceImpl implements IInfoDutyScheduleService 
{
    @Autowired
    private InfoDutyScheduleMapper infoDutyScheduleMapper;

    /**
     * 查询任务调度
     * 
     * @param id 任务调度ID
     * @return 任务调度
     */
    @Override
    public InfoDutySchedule selectInfoDutyScheduleById(String id)
    {
        return infoDutyScheduleMapper.selectInfoDutyScheduleById(id);
    }

    /**
     * 查询任务调度列表
     * 
     * @param infoDutySchedule 任务调度
     * @return 任务调度
     */
    @Override
    public List<InfoDutySchedule> selectInfoDutyScheduleList(InfoDutySchedule infoDutySchedule)
    {
        return infoDutyScheduleMapper.selectInfoDutyScheduleList(infoDutySchedule);
    }

    /**
     * 新增任务调度
     * 
     * @param infoDutySchedule 任务调度
     * @return 结果
     */
    @Override
    public int insertInfoDutySchedule(InfoDutySchedule infoDutySchedule)
    {
        return infoDutyScheduleMapper.insertInfoDutySchedule(infoDutySchedule);
    }

    /**
     * 修改任务调度
     * 
     * @param infoDutySchedule 任务调度
     * @return 结果
     */
    @Override
    public int updateInfoDutySchedule(InfoDutySchedule infoDutySchedule)
    {
        return infoDutyScheduleMapper.updateInfoDutySchedule(infoDutySchedule);
    }

    /**
     * 删除任务调度对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteInfoDutyScheduleByIds(String ids)
    {
        return infoDutyScheduleMapper.deleteInfoDutyScheduleByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除任务调度信息
     * 
     * @param id 任务调度ID
     * @return 结果
     */
    @Override
    public int deleteInfoDutyScheduleById(String id)
    {
        return infoDutyScheduleMapper.deleteInfoDutyScheduleById(id);
    }

	@Override
	public List<Map<String, String>> findInfoDutyScheduleMapList(String static_acType_B737) {
		return infoDutyScheduleMapper.findInfoDutyScheduleMapList(static_acType_B737);
	}

	@Override
	public String findJobCount() {
		return infoDutyScheduleMapper.findJobCount();
	}
}
