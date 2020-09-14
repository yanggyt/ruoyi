package com.ruoyi.quartz.service.impl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.ruoyi.common.base.BaseService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.quartz.domain.QSysJobLog;
import com.ruoyi.quartz.domain.SysJobLog;
import com.ruoyi.quartz.repository.SysJobLogRepository;
import com.ruoyi.quartz.service.ISysJobLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时任务调度日志信息 服务层
 *
 * @author ruoyi
 */
@Service
public class SysJobLogServiceImpl extends BaseService implements ISysJobLogService {
    @Autowired
    private SysJobLogRepository sysJobLogRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 获取quartz调度器日志的计划任务
     *
     * @param jobLog 调度日志信息
     * @return 调度任务日志集合
     */
    @Override
    public Page<SysJobLog> selectJobLogList(SysJobLog jobLog, Pageable pageable) {
        return sysJobLogRepository.findAll(getPredicate(jobLog), pageable);
    }

    private Predicate getPredicate(SysJobLog jobLog){
        QSysJobLog qSysJobLog = QSysJobLog.sysJobLog;
        List<Predicate> predicates = new ArrayList<>();
        if(StringUtils.isNotEmpty(jobLog.getJobName())){
            predicates.add(buildLike(qSysJobLog.jobName, jobLog.getJobName()));
        }
        if(StringUtils.isNotEmpty(jobLog.getJobGroup())){
            predicates.add(buildEqual(qSysJobLog.jobGroup, jobLog.getJobGroup()));
        }
        if(StringUtils.isNotEmpty(jobLog.getStatus())){
            predicates.add(buildEqual(qSysJobLog.status, jobLog.getStatus()));
        }
        if(StringUtils.isNotEmpty(jobLog.getInvokeTarget())){
            predicates.add(buildLike(qSysJobLog.invokeTarget, jobLog.getInvokeTarget()));
        }
        if(jobLog.getStartTime() != null){
            predicates.add(buildGreaterThanOrEqualTo(qSysJobLog.startTime, jobLog.getStartTime()));
        }
        if(jobLog.getEndTime() != null){
            predicates.add(buildLessThanOrEqualTo(qSysJobLog.endTime, jobLog.getEndTime()));
        }
        return ExpressionUtils.allOf(predicates);
    }

    /**
     * 通过调度任务日志ID查询调度信息
     *
     * @param jobLogId 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    @Override
    public SysJobLog selectJobLogById(Long jobLogId) {
        return sysJobLogRepository.findById(jobLogId).get();
    }

    /**
     * 新增任务日志
     *
     * @param jobLog 调度日志信息
     */
    @Transactional
    @Override
    public void addJobLog(SysJobLog jobLog) {
        sysJobLogRepository.save(jobLog);
    }

    /**
     * 批量删除调度日志信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteJobLogByIds(String ids) {
        for(Long id : Convert.toLongArray(ids)){
            deleteJobLogById(id);
        }
        return 1;
    }

    /**
     * 删除任务日志
     *
     * @param jobId 调度日志ID
     */
    @Transactional
    @Override
    public int deleteJobLogById(Long jobId) {
        sysJobLogRepository.deleteById(jobId);
        return 1;
    }

    /**
     * 清空任务日志
     */
    @Transactional
    @Override
    public void cleanJobLog() {
        jdbcTemplate.update("truncate table " + SysJobLog.TABLE_NAME);
    }
}
