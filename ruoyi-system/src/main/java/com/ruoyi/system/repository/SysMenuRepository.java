package com.ruoyi.system.repository;

import com.ruoyi.system.domain.SysMenu;
import com.ruoyi.system.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SysMenuRepository extends BaseRepository<SysMenu, Long> {

    List<SysMenu> findAllByMenuTypeInAndVisibleOrderByOrderNum(Collection<String> types, String visiable);
}
