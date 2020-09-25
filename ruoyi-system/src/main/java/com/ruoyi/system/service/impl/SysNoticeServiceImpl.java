package com.ruoyi.system.service.impl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.ruoyi.common.base.BaseService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.QSysNotice;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.repository.SysNoticeRepository;
import com.ruoyi.system.service.ISysNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 公告 服务层实现
 *
 * @author ruoyi
 * @date 2018-06-25
 */
@Service
public class SysNoticeServiceImpl extends BaseService implements ISysNoticeService {
    @Autowired
    private SysNoticeRepository sysNoticeRepository;

    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public SysNotice selectNoticeById(Long noticeId) {
        return sysNoticeRepository.findById(noticeId).get();
    }

    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    @Override
    public Page<SysNotice> selectNoticeList(SysNotice notice, Pageable pageable) {
        return sysNoticeRepository.findAll(getPredicate(notice), pageable);
    }

    private Predicate getPredicate(SysNotice sysNotice){
        QSysNotice qSysNotice = QSysNotice.sysNotice;
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(alwaysTrue());
        if(StringUtils.isNotEmpty(sysNotice.getNoticeTitle())){
            predicates.add(buildLike(qSysNotice.noticeTitle, sysNotice.getNoticeTitle()));
        }
        if(StringUtils.isNotEmpty(sysNotice.getNoticeType())){
            predicates.add(buildEqual(qSysNotice.noticeType, sysNotice.getNoticeType()));
        }
        if(StringUtils.isNotEmpty(sysNotice.getCreateBy())){
            predicates.add(buildLike(qSysNotice.createBy, sysNotice.getCreateBy()));
        }
        return ExpressionUtils.allOf(predicates);
    }

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Transactional
    @Override
    public int insertNotice(SysNotice notice) {
        sysNoticeRepository.save(notice);
        return 1;
    }

    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Transactional
    @Override
    public int updateNotice(SysNotice notice) {
        sysNoticeRepository.save(notice);
        return 1;
    }

    /**
     * 删除公告对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(String ids) {
        sysNoticeRepository.deleteByNoticeIdIn(Arrays.asList(Convert.toLongArray(ids)));
        return 1;
    }
}
