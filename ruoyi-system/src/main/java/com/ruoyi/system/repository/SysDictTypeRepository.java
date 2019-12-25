package com.ruoyi.system.repository;

import com.ruoyi.system.domain.SysDictType;
import com.ruoyi.system.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysDictTypeRepository extends BaseRepository<SysDictType, Long> {

    SysDictType findFirstByDictType(String dictType);
}
