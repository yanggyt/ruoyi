package com.ruoyi.test.service.impl;

import com.ruoyi.test.mapper.OracleDdlMapper;
import com.ruoyi.test.service.OracleDdlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
//@DataSource(value = DataSourceType.SLAVE)
public class OracleDdlServiceImpl implements OracleDdlService {
    @Autowired
    private OracleDdlMapper oracleDdlMapper;

    //修改表名
    public  int alterTableName(String originalTableName, String newTableName)
    {
        return oracleDdlMapper.alterTableName(originalTableName,newTableName);
    }

    // truncate指定数据库表的数据
    public int truncateTable(String tableName){
        return oracleDdlMapper.truncateTable(tableName);
    }

    //drop 指定定数据库表
    public int dropTable(String tableName){
        return oracleDdlMapper.dropTable(tableName);
    }


    //根据传入的表明，创建新的表并且将原表的数据插入到新的Occur表中
    public void copyTable(String newTableName,String originalTableName){
        return ;
    }

    //统计某张表中的总数据条数
    public int getRecordCount(String tableName){
        return oracleDdlMapper.getRecordCount(tableName);
    }

    //从指定数据库中，查询是否存在某张表
    public int isTableInDb(String dataBaseName, String tableName){
        return oracleDdlMapper.isTableInDb(dataBaseName,tableName);
    }
}
