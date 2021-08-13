package com.ruoyi.test.conrtroller;

import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.test.service.OracleDdlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@DataSource(value = DataSourceType.SLAVE)
public class OracleDdlController {
    @Autowired
    private OracleDdlService oracleDdlService;

    //查询表录数
    @RequestMapping("getRecordCount")

    public String getRecordCount() {
        String tableName = "tc_user";
        int result = oracleDdlService.getRecordCount(tableName);
        return result + "";
    }

    //检查表是否存在
    @RequestMapping("isTableInDb")
    public String isTableInDb() {
        String dbName = "ds7";
        String tableName = "tc_user";
        return (oracleDdlService.isTableInDb(dbName, tableName)>0?"实例ds7中存在表tc_user":"实例ds7中不存在表tc_user");
    }

    //复制表
    @RequestMapping("copyTable")
    public String copyTable() {
        String originalTalble = "tc_user";
        String newTable = "new_tc_user";

        if (oracleDdlService.isTableInDb("ds7", originalTalble) > 0) {
            try {
                oracleDdlService.copyTable(newTable, originalTalble);
                return "复制" + originalTalble + "成功！新表名：" + newTable;
            } catch (Exception e) {
                System.out.println(e);
                return "复制" + originalTalble + "失败！";
            }
        } else {
            return "表"+originalTalble+"不存在";

        }

    }

}
