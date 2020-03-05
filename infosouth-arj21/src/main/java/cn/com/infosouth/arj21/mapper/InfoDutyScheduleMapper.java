package cn.com.infosouth.arj21.mapper;

import java.util.List;
import java.util.Map;

import cn.com.infosouth.arj21.domain.InfoDutySchedule;

/**
 * 任务调度Mapper接口
 * 
 * @author kxnf
 * @date 2020-03-05
 */
public interface InfoDutyScheduleMapper 
{
    /**
     * 查询任务调度
     * 
     * @param id 任务调度ID
     * @return 任务调度
     */
    public InfoDutySchedule selectInfoDutyScheduleById(String id);

    /**
     * 查询任务调度列表
     * 
     * @param infoDutySchedule 任务调度
     * @return 任务调度集合
     */
    public List<InfoDutySchedule> selectInfoDutyScheduleList(InfoDutySchedule infoDutySchedule);

    /**
     * 新增任务调度
     * 
     * @param infoDutySchedule 任务调度
     * @return 结果
     */
    public int insertInfoDutySchedule(InfoDutySchedule infoDutySchedule);

    /**
     * 修改任务调度
     * 
     * @param infoDutySchedule 任务调度
     * @return 结果
     */
    public int updateInfoDutySchedule(InfoDutySchedule infoDutySchedule);

    /**
     * 删除任务调度
     * 
     * @param id 任务调度ID
     * @return 结果
     */
    public int deleteInfoDutyScheduleById(String id);

    /**
     * 批量删除任务调度
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteInfoDutyScheduleByIds(String[] ids);

    /**
     * 查找任务列表
     * @param static_acType_B737
     * @return
     */
	public List<Map<String, String>> findInfoDutyScheduleMapList(String static_acType_B737);

	/**
	 * 查找job数量
	 * @return
	 */
	public String findJobCount();
}
