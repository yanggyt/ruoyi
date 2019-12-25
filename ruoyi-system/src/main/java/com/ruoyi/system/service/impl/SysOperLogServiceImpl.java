package com.ruoyi.system.service.impl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.QSysOperLog;
import com.ruoyi.system.domain.SysOperLog;
import com.ruoyi.system.repository.SysOperLogRepository;
import com.ruoyi.system.service.ISysOperLogService;
import com.ruoyi.system.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 操作日志 服务层处理
 *
 * @author ruoyi
 */
@Service
public class SysOperLogServiceImpl extends BaseService implements ISysOperLogService {
    @Autowired
    private SysOperLogRepository sysOperLogRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 新增操作日志
     *
     * @param operLog 操作日志对象
     */
    @Override
    public void insertOperlog(SysOperLog operLog) {
        sysOperLogRepository.save(operLog);
    }

    /**
     * 查询系统操作日志集合
     *
     * @param operLog 操作日志对象
     * @return 操作日志集合
     */
    @Override
    public Page<SysOperLog> selectOperLogList(SysOperLog operLog, Pageable pageable) {
        return sysOperLogRepository.findAll(getPredicate(operLog), pageable);
    }

    private Predicate getPredicate(SysOperLog operLog){
        QSysOperLog qSysOperLog = QSysOperLog.sysOperLog;
        List<Predicate> predicates = new ArrayList<>();
        if(StringUtils.isNotEmpty(operLog.getTitle())){
            predicates.add(buildLike(qSysOperLog.title, operLog.getTitle()));
        }
        if(operLog.getBusinessType() != null){
            predicates.add(buildEqual(qSysOperLog.businessType, operLog.getBusinessType()));
        }
        if(operLog.getBusinessTypes() != null && operLog.getBusinessTypes().length > 0){
            predicates.add(qSysOperLog.businessType.in(operLog.getBusinessTypes()));
        }
        if(operLog.getStatus() != null){
            predicates.add(buildEqual(qSysOperLog.status, operLog.getStatus()));
        }
        if(StringUtils.isNotEmpty(operLog.getOperName())){
            predicates.add(buildLike(qSysOperLog.operName, operLog.getOperName()));
        }
        if(operLog.getStartTime() != null){
            predicates.add(buildGreaterThanOrEqualTo(qSysOperLog.operTime, operLog.getStartTime()));
        }
        if(operLog.getEndTime() != null){
            predicates.add(buildLessThanOrEqualTo(qSysOperLog.operTime, operLog.getEndTime()));
        }
        return ExpressionUtils.allOf(predicates);
    }

    /**
     * 批量删除系统操作日志
     *
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public int deleteOperLogByIds(String ids) {
        sysOperLogRepository.deleteByOperIdIn(Arrays.asList(Convert.toLongArray(ids)));
        return 1;
    }

    /**
     * 查询操作日志详细
     *
     * @param operId 操作ID
     * @return 操作日志对象
     */
    @Override
    public SysOperLog selectOperLogById(Long operId) {
        return sysOperLogRepository.findById(operId).get();
    }

    /**
     * 清空操作日志
     */
    @Transactional
    @Override
    public void cleanOperLog() {
        jdbcTemplate.update("truncate table " + SysOperLog.TABLE_NAME);
    }
}
