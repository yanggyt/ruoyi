package cn.com.infosouth.arj21.service;

import java.util.List;
import java.util.Map;

public interface IInfoDutyScheduleService {

	List<Map<String, String>> findInfoDutyScheduleMapList(String static_acType_B737);

	/**
	 * 任务数量
	 * @return
	 */
	String findJobCount();

}
