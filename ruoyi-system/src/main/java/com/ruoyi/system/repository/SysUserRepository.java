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

    @EntityGraph(attributePaths = {"roles", "posts"})
    SysUser findFirstByDelFlagAndLoginName(String delFlag, String loginName);

    @EntityGraph(attributePaths = {"roles", "posts"})
    SysUser findFirstByDelFlagAndAndPhonenumber(String delFlag, String phone);

    @EntityGraph(attributePaths = {"roles", "posts"})
    SysUser findFirstByDelFlagAndEmail(String delFlag, String email);

    int countByDelFlagAndDept(String delFlag, SysDept dept);

    int countByDelFlagAndPostsContaining(String delFlag, SysPost sysPost);

    @EntityGraph(attributePaths = {"roles", "posts"})
    SysUser findSysUserByDelFlagAndUserId(String delFlag, Long userId);

    int countByLoginName(String loginName);

    @EntityGraph(attributePaths = {"roles", "posts"})
    SysUser findFirstByPhonenumber(String phone);

    @EntityGraph(attributePaths = {"roles", "posts"})
    SysUser findFirstByEmail(String email);

    @EntityGraph(attributePaths = {"roles", "posts"})
    SysUser findFirstByLoginName(String loginName);

    @Modifying
    @Query("update SysUser u set u.status = ?1 where u.userId = ?2")
    void changeStatus(String status, Long userId);

    int countByRolesContaining(SysRole sysRole);
}
