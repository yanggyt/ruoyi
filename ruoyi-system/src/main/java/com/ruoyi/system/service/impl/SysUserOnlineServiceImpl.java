package com.ruoyi.system.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysUserOnline;
import com.ruoyi.system.repository.SysUserOnlineRepository;
import com.ruoyi.system.service.ISysUserOnlineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 在线用户 服务层处理
 *
 * @author ruoyi
 */
@Service
public class SysUserOnlineServiceImpl implements ISysUserOnlineService {

    @Autowired
    private SysUserOnlineRepository sysUserOnlineRepository;

    /**
     * 通过会话序号查询信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    @Override
    public SysUserOnline selectOnlineById(String sessionId) {
        return sysUserOnlineRepository.findById(sessionId).orElse(null);
    }

    /**
     * 通过会话序号删除信息
     *
     * @param sessionId 会话ID
     * @return 在线用户信息
     */
    @Transactional
    @Override
    public void deleteOnlineById(String sessionId) {
        sysUserOnlineRepository.deleteById(sessionId);
    }

    /**
     * 通过会话序号删除信息
     *
     * @param sessions 会话ID集合
     * @return 在线用户信息
     */
    @Transactional
    @Override
    public void batchDeleteOnline(List<String> sessions) {
        for (String sessionId : sessions) {
            deleteOnlineById(sessionId);
        }
    }

    /**
     * 保存会话信息
     *
     * @param online 会话信息
     */
    @Override
    public SysUserOnline saveOnline(SysUserOnline online) {
        return sysUserOnlineRepository.save(online);
    }

    /**
     * 查询会话集合
     *
     * @param userOnline 在线用户
     */
    @Override
    public Page<SysUserOnline> selectUserOnlineList(SysUserOnline userOnline, Pageable pageable) {
        return sysUserOnlineRepository.findAll(getSpecification(userOnline), pageable);
    }

    private Specification<SysUserOnline> getSpecification(SysUserOnline userOnline){
        return new Specification<SysUserOnline>() {
            @Override
            public Predicate toPredicate(Root<SysUserOnline> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(StringUtils.isNotEmpty(userOnline.getIpaddr())){
                    predicates.add(criteriaBuilder.like(root.get("ipaddr").as(String.class), "%" + userOnline.getIpaddr() + "%"));
                }
                if(StringUtils.isNotEmpty(userOnline.getLoginName())){
                    predicates.add(criteriaBuilder.like(root.get("loginName").as(String.class), "%" + userOnline.getLoginName() + "%"));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    /**
     * 强退用户
     *
     * @param sessionId 会话ID
     */
    @Transactional
    @Override
    public void forceLogout(String sessionId) {
        deleteOnlineById(sessionId);
    }

    /**
     * 查询会话集合
     *
     * @param expiredDate 失效日期
     */
    @Override
    public List<SysUserOnline> selectOnlineByExpired(Date expiredDate) {
        return sysUserOnlineRepository.findByLastAccessTimeLessThanEqual(expiredDate);
    }
}
