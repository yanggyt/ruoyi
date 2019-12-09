package com.ruoyi.system.repository;

import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysDeptRepository extends BaseRepository<SysDept, Long> {

    int countByDelFlagAndParent(String delFlag, SysDept parent);
}
