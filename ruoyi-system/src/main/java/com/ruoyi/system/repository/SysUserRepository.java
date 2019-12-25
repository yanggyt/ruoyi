package com.ruoyi.system.repository;

import com.ruoyi.common.base.BaseRepository;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.domain.SysUser;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRepository extends BaseRepository<SysUser, Long> {

    SysUser findFirstByDelFlagAndLoginName(String delFlag, String loginName);

    SysUser findFirstByDelFlagAndAndPhonenumber(String delFlag, String phone);

    SysUser findFirstByDelFlagAndEmail(String delFlag, String email);

    int countByDelFlagAndDept(String delFlag, SysDept dept);

    int countByDelFlagAndPostsContaining(String delFlag, SysPost sysPost);

    @EntityGraph(attributePaths = {"roles", "posts"})
    SysUser findSysUserByDelFlagAndUserId(String delFlag, Long userId);

    int countByLoginName(String loginName);

    SysUser findFirstByPhonenumber(String phone);

    SysUser findFirstByEmail(String email);

    SysUser findFirstByLoginName(String loginName);

    @Modifying
    @Query("update SysUser u set u.status = ?1 where u.userId = ?2")
    void changeStatus(String status, Long userId);

    int countByRolesContaining(SysRole sysRole);
}
