package com.ruoyi.generator.repository;

import com.ruoyi.common.base.BaseRepository;
import com.ruoyi.generator.domain.GenTable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenTableRepository extends BaseRepository<GenTable, Long> {

    GenTable findFirstByTableName(String tableName);

    @Override
    Optional<GenTable> findById(Long id);
}
