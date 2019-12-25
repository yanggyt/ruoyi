package com.ruoyi.system.repository;

import com.ruoyi.system.domain.SysLogininfor;
import com.ruoyi.system.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SysLogininfoRepository extends BaseRepository<SysLogininfor, Long> {

    @Modifying
    void deleteByInfoIdIn(Collection<Long> ids);
}
