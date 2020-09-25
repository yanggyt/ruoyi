package com.ruoyi.system.repository;

import com.ruoyi.common.base.BaseRepository;
import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.domain.SysRole;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleRepository extends BaseRepository<SysRole, Long> {

    int countByMenusContaining(SysMenu menu);

    SysRole findFirstByRoleName(String roleName);

    SysRole findFirstByRoleKey(String roleKey);

    @Query("update SysRole r set r.status = ?1 where r.roleId = ?2")
    @Modifying
    void updateStatus(String status, Long roleId);

    @EntityGraph(attributePaths = {"depts", "menus"}, type = EntityGraph.EntityGraphType.LOAD )
    SysRole findByRoleId(Long roleId);
}
