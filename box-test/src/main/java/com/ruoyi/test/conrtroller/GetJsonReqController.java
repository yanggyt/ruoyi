package com.ruoyi.test.conrtroller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.test.domain.Beauty;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/test")
public class GetJsonReqController {

    @CrossOrigin
    @RequestMapping(value = "simple")
    // json的结构和内部字段名称可以与POJO/DTO/javabean完全对应
    public Map<String, String> getJsonBean(@RequestBody Beauty beauty) {
        Map<String, String> result = null;

        if (beauty != null) {
            System.out.println("姓名：" + beauty.getName());
            System.out.println("年龄：" + beauty.getAge());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("出生日期：" + sdf.format(beauty.getDate()));

            System.out.println("年收入：" + beauty.getSalary());
            result = new HashMap<>();
            result.put("code", "1");
            result.put("msg", "ok");
        }

        return result;
    }

    @CrossOrigin
    @RequestMapping(value = "complex")
    //json的结构较为复杂，不直接与POJO/DTO/javabean对应。
    public Map<String, String> getJsonComplex(@RequestBody JSONObject param) {
        Map<String, String> result = null;

        if (param != null) {
            JSONObject master = param.getJSONObject("master");
            Beauty beauty = (Beauty) JSONObject.toJavaObject(master, Beauty.class);
            System.out.println(beauty);

            JSONArray mm = param.getJSONArray("MM");
            for (int i = 0; i < mm.size(); i++) {
                // 这里不能使用get(i)，因为get(i)只会得到键值对。
                JSONObject json = mm.getJSONObject(i);
                Beauty bt = (Beauty) JSONObject.toJavaObject(json, Beauty.class);
                System.out.println(bt);
            }

            result = new HashMap<>();
            result.put("code", "1");
            result.put("msg", "ok");
        }

        return result;
    }
}
