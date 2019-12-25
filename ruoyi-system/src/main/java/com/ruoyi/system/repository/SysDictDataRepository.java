package com.ruoyi.system.repository;

import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysDictDataRepository extends BaseRepository<SysDictData, Long> {

    @Modifying
    @Query("update SysDictData d set d.dictType = ?1 where d.dictType = ?2")
    void updateDictType(String newType, String oldType);

    int countByDictType(String dictType);

    List<SysDictData> findByDictType(String dictType);

    SysDictData findFirstByDictTypeAndDictLabel(String type, String label);

}
