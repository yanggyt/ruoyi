package com.ruoyi.generator.repository;

import com.ruoyi.common.base.BaseRepository;
import com.ruoyi.generator.domain.GenTable;
import com.ruoyi.generator.domain.GenTableColumn;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface GenTableColumnRepository extends BaseRepository<GenTableColumn, Long> {

    List<GenTableColumn> findByTable(GenTable genTable);

    @Modifying
    void deleteByTableIn(Collection<GenTable> tables);

}
