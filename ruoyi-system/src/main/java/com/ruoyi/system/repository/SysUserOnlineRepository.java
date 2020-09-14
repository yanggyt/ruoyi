package com.ruoyi.system.repository;

import com.ruoyi.common.base.BaseRepository;
import com.ruoyi.system.domain.SysUserOnline;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface SysUserOnlineRepository extends BaseRepository<SysUserOnline, String> {

    List<SysUserOnline> findByLastAccessTimeLessThanEqual(Date date);
}
