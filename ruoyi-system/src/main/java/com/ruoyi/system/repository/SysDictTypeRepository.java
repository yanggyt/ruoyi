package com.ruoyi.system.repository;

import com.ruoyi.common.base.BaseRepository;
import com.ruoyi.system.domain.SysDictType;
import org.springframework.stereotype.Repository;

@Repository
public interface SysDictTypeRepository extends BaseRepository<SysDictType, Long> {

    SysDictType findFirstByDictType(String dictType);
}
