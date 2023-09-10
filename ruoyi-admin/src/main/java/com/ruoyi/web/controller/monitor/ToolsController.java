package com.ruoyi.web.controller.monitor;

import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * 工具
 * 
 * @author ruoyi
 */
@RestController
public class ToolsController extends BaseController
{
    private String prefix = "tool";


    @GetMapping("/tool/getJsonString")
    public AjaxResult getJsonString(@RequestParam @NotBlank String jsonString,String filed) {
        JSONArray jsonArray = JSONArray.parseArray(jsonString);
        List<String> response = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            com.alibaba.fastjson.JSONObject item = jsonArray.getJSONObject(0);
            String itemStr = item.getString(filed);
            response.add(itemStr);
        }
        String join = String.join(",", response);
        return AjaxResult.success("",join);
    }


}
