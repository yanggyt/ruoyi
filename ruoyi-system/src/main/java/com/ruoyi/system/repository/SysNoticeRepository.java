package com.ruoyi.system.repository;

import com.ruoyi.common.base.BaseRepository;
import com.ruoyi.system.domain.SysNotice;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SysNoticeRepository extends BaseRepository<SysNotice, Long> {

    @Modifying
    void deleteByNoticeIdIn(Collection<Long> ids);
}
