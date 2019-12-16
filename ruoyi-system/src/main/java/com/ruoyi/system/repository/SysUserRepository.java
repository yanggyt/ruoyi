package com.ruoyi.system.repository;

import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.EntityGraph;
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
}
