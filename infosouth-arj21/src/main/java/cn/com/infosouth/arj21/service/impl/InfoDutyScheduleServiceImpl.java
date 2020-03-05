package cn.com.infosouth.arj21.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.infosouth.arj21.service.IInfoDutyScheduleService;


/**
 * 任务调度管理Service
 * @author zy
 * @version 2018-01-05
 */
@Service
@Transactional(readOnly = true)
public class IInfoDutyScheduleServiceImpl implements IInfoDutyScheduleService{

	
	@Override
	public List<Map<String, String>> findInfoDutyScheduleMapList(String static_acType_B737) {
 		return null;
	}

	@Override
	public String findJobCount() {
		return null;
	}

}
