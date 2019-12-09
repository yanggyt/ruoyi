package com.ruoyi.system.repository;

import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysUserRepository extends BaseRepository<SysUser, Long> {

    SysUser findFirstByDelFlagAndLoginName(String delFlag, String loginName);

    SysUser findFirstByDelFlagAndAndPhonenumber(String delFlag, String phone);

    SysUser findFirstByDelFlagAndEmail(String delFlag, String email);

}
