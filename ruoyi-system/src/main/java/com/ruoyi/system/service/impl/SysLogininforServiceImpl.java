package com.ruoyi.system.service.impl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.QSysLogininfor;
import com.ruoyi.system.domain.SysLogininfor;
import com.ruoyi.system.repository.SysLogininfoRepository;
import com.ruoyi.system.service.ISysLogininforService;
import com.ruoyi.system.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 系统访问日志情况信息 服务层处理
 *
 * @author ruoyi
 */
@Service
public class SysLogininforServiceImpl extends BaseService implements ISysLogininforService {

    @Autowired
    private SysLogininfoRepository sysLogininfoRepository;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private EntityManager entityManager;

    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    @Override
    public void insertLogininfor(SysLogininfor logininfor) {
        sysLogininfoRepository.save(logininfor);
    }

    /**
     * 查询系统登录日志集合
     *
     * @param logininfor 访问日志对象
     * @return 登录记录集合
     */
    @Override
    public Page<SysLogininfor> selectLogininforList(SysLogininfor logininfor, Pageable pageable) {
        return sysLogininfoRepository.findAll(getPredicate(logininfor), pageable);
    }

    private Predicate getPredicate(SysLogininfor logininfor){
        QSysLogininfor qSysLogininfor = QSysLogininfor.sysLogininfor;
        List<Predicate> predicates = new ArrayList<>();
        if(StringUtils.isNotEmpty(logininfor.getIpaddr())){
            predicates.add(buildLike(qSysLogininfor.ipaddr, logininfor.getIpaddr()));
        }
        if(StringUtils.isNotEmpty(logininfor.getStatus())){
            predicates.add(buildEqual(qSysLogininfor.status, logininfor.getStatus()));
        }
        if(StringUtils.isNotEmpty(logininfor.getLoginName())){
            predicates.add(buildLike(qSysLogininfor.loginName, logininfor.getLoginName()));
        }
        if(logininfor.getStartTime() != null){
            predicates.add(buildGreaterThanOrEqualTo(qSysLogininfor.loginTime, logininfor.getStartTime()));
        }
        if(logininfor.getEndTime() != null){
            predicates.add(buildLessThanOrEqualTo(qSysLogininfor.loginTime, logininfor.getEndTime()));
        }
        return ExpressionUtils.allOf(predicates);
    }

    /**
     * 批量删除系统登录日志
     *
     * @param ids 需要删除的数据
     * @return
     */
    @Override
    public int deleteLogininforByIds(String ids) {
        sysLogininfoRepository.deleteByInfoIdIn(Arrays.asList(Convert.toLongArray(ids)));
        return 1;
    }

    /**
     * 清空系统登录日志
     */
    @Transactional
    @Override
    public void cleanLogininfor() {
        jdbcTemplate.update("truncate table " + SysLogininfor.TABLE_NAME);
    }
}
