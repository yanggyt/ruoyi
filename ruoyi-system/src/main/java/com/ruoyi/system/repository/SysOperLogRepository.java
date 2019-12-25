package com.ruoyi.system.repository;

import com.ruoyi.common.base.BaseRepository;
import com.ruoyi.system.domain.SysOperLog;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SysOperLogRepository extends BaseRepository<SysOperLog, Long> {

    @Modifying
    void deleteByOperIdIn(Collection<Long> ids);
}
