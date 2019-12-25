package com.ruoyi.system.repository;

import com.ruoyi.common.base.BaseRepository;
import com.ruoyi.system.domain.SysDept;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SysDeptRepository extends BaseRepository<SysDept, Long> {

    int countByDelFlagAndParent(String delFlag, SysDept parent);

    int countByDelFlagAndDeptId(String delFlag, Long deptId);

    SysDept findFirstByDelFlagAndDeptNameAndParent(String delFlag, String deptName, SysDept parent);

    @Modifying
    @Query("update SysDept d set d.delFlag = ?1 where d.deptId = ?2")
    void updateDelFlagByDeptId(String delFlag, Long deptId);

    @Modifying
    @Query("update SysDept d set d.status = ?1 where d.deptId = ?2")
    void updateStatusByDeptId(String status, Long deptId);

    int countByParent(SysDept parent);

    SysDept findFirstByParentOrderByCodeDesc(SysDept parent);
}
