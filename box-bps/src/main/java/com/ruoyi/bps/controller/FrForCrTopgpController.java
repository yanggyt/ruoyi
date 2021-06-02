package com.ruoyi.bps.controller;

import com.ruoyi.bps.service.TopgpDdlService;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class FrForCrTopgpController {
    @Autowired
    private TopgpDdlService topgpDdlService;

    //访问 ../anon/bps/frforcr/topprod时，使用topprod
    @CrossOrigin
    @Log(title = "CSFR412_CR报表_TOPPROD", businessType = BusinessType.DROP)
    @RequestMapping("/anon/bps/frforcr/topprod")
    @DataSource(value = DataSourceType.TOPPRODDSREPORT)
    public AjaxResult frforcrtopprod(@RequestBody Map<String,Object> map){
        return frforcrtoppgp(map);
    }

    //访问../anon/bps/frforcr/topprod时，使用toptest实例
    @CrossOrigin
    @Log(title = "CSFR412_CR报表_TOPTEST", businessType = BusinessType.DROP)
    @RequestMapping("/anon/bps/frforcr/toptest")
    @DataSource(value = DataSourceType.TOPTESTDSREPORT)
    public AjaxResult frforcrtoptest(@RequestBody Map<String,Object> map){
        return frforcrtoppgp(map);
    }

    private AjaxResult frforcrtoppgp(Map<String,Object> map){
      /*  多此一举，在@RequestBody时取不到值是，已经会报500错误了，并不会执行到这一段。
        if(map.isEmpty()){  //如果传过来的值为空，返回未取到表名
            // System.out.println(LocalTime.now()+"未获取到表名！");
            return AjaxResult.error("未取到表名传参！");
        }
     */
        StringBuilder droppedTable = new StringBuilder();
        StringBuilder errorDroppedTable = new StringBuilder();
        StringBuilder notExistsTable = new StringBuilder();

        for (String tableName : getTableName(map)) {
            if(topgpDdlService.isTableInDb("ds_report",tableName) <=0){
                //表名在数据库中不存在
                //System.out.println(LocalTime.now() + "【" + tableName + "】不存在！");
                notExistsTable.append(tableName+",");
                continue;
            }

            try {
                topgpDdlService.dropTable("ds_report." + tableName);
                //System.out.println(LocalTime.now() + "【" + tableName + "】已被删除！");
                droppedTable.append(tableName+",");
            } catch (Exception e) {
                //System.out.println(LocalTime.now() + "【" + tableName + "】删除异常！");
                errorDroppedTable.append(tableName+",");
            }
        }

        //删除异常写入日志
        if (StringUtils.isNotEmpty(errorDroppedTable)){
            return AjaxResult.error(errorDroppedTable+"删除异常！");
        }
        //表不存在写入日志
        if(StringUtils.isNotEmpty(notExistsTable)){
            return AjaxResult.warn(notExistsTable+"表不存在！");
        }
        //被删除的表写入日志
        if(StringUtils.isNotEmpty(droppedTable)){
            return AjaxResult.success(droppedTable + "删除成功！");
        }
        //以上三种情况都不存在，说明发生了不明异常，写入日志
        return AjaxResult.warn("发生不明异常！");
    }

    //防止意手抖把SID也当成表传过来
    //排除sid的key值
    private List<String> getTableName(Map<String,Object> map){
        List<String> list=new ArrayList<String>();
        for(String key:map.keySet()){
            if(key !="sid")
            {
                list.add(map.get(key).toString());
            }
        }
        return list;
    }
}


