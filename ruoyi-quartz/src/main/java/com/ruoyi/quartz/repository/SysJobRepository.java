package com.ruoyi.quartz.repository;

import com.ruoyi.common.base.BaseRepository;
import com.ruoyi.quartz.domain.SysJob;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SysJobRepository extends BaseRepository<SysJob, Long> {

    @Modifying
    @Query("update SysJob j set j.status = ?1 where j.jobId = ?2")
    void updateStatus(String status, Long id);
}
