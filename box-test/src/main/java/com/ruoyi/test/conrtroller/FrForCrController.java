package com.ruoyi.test.conrtroller;

import com.ruoyi.test.service.OracleDdlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.Map;

@RestController
public class FrForCrController {
    @Autowired
    private OracleDdlService oracleDdlService;

    @CrossOrigin
    @RequestMapping("/test/frforcr")
    public void frforcr(@RequestBody Map<String,Object> map){
        for(Object value:map.values()){
            String dbName="ds7";
            String tableName=value.toString();
            String tableNameWithDb=dbName+"."+tableName;
            int isTableInDb=oracleDdlService.isTableInDb(dbName,tableName);
            if(isTableInDb>0){
                try{
                    oracleDdlService.dropTable(tableNameWithDb);
                    System.out.println(LocalTime.now()+"【"+tableNameWithDb+"】已被删除！");
                }catch (Exception e){
                    System.out.println(LocalTime.now()+"【"+tableNameWithDb+"】删除异常！");
                }
            }
            else{
                System.out.println(LocalTime.now()+"【"+tableNameWithDb+"】不存在！");
            }
        }

    }



}


