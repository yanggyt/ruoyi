package com.ruoyi.bps.service;

public interface TopgpDdlService {
    //修改表名
    int alterTableName(String originalTableName, String newTableName);

    // truncate指定数据库表的数据
    int truncateTable(String tableName);

    //drop 指定定数据库表
    int dropTable(String tableName);


    //根据传入的表明，创建新的表并且将原表的数据插入到新的Occur表中
    void copyTable(String newTableName,String originalTableName);

    //统计某张表中的总数据条数
    int getRecordCount(String tableName);

    //从指定数据库中，查询是否存在某张表
    int isTableInDb(String dataBaseName, String tableName);
}
