package com.ruoyi.test.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component("OracleDdlMapper")
public interface OracleDdlMapper {
    // alter table
    int alterTableName(@Param("originalTableName") String originalTableName,
                       @Param("newTableName") String newTableName);

    //truncate table
    int truncateTable(@Param("tableName") String tableName);

    //drop table
    int dropTable(@Param("tableName") String tableName);

    //copy table
    void copyTable(@Param("newTableName") String newTableName,
                                     @Param("originalTableName") String originalTableName);

    //获取表记录数
    int getRecordCount(@Param("tableName") String tableName);



    //查询数据库中表是否存在
    int isTableInDb(@Param("dataBaseName") String dataBaseName,
                                  @Param("tableName") String tableName);
}
