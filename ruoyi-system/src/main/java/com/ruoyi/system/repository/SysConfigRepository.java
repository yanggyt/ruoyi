package com.ruoyi.system.repository;

import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface SysConfigRepository extends BaseRepository<SysConfig, Long> {

    SysConfig findFirstByConfigKey(String configKey);

    @Modifying
    void deleteByConfigIdIn(Collection<Long> ids);
}
