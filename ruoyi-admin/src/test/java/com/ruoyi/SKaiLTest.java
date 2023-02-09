package com.ruoyi;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.CacheUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.framework.web.domain.server.Sys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test
 *
 * @author SKaiL
 * @date 2022/10/20
 */
@SpringBootTest
public class SKaiLTest
{

    @Test
    public void postTest()
    {
//        String url = "http://192.168.5.8:8084/index.php?m=api&f=getSessionID&t=json";
//        String s = HttpUtils.sendPost(url, null).replaceAll("&", "");
//        Map<String, Object> map = JSONObject.parseObject(s);
//        Map<String, Object> data = JSONObject.parseObject(map.get("data").toString());
//        String sessionID = (String) (data.get("sessionID"));
//        String url1 = "http://192.168.5.8:8084/index.php?sid=" + sessionID + "&m=user&f=login&account=silergyapi&password=silergyq1w2e3&t=json";
//        String s1 = HttpUtils.sendPost(url1, null).replaceAll("&", "");
//        Map mmap = JSONObject.parseObject(s1, HashMap.class);
//        if (mmap.containsKey("status")) {
//            if (map.get("status").equals("success")) {
//                String url2 = "http://192.168.5.8:8084/index.php?&sid=" + sessionID + "&m=api&f=getModel&module=api&methodName=getalluserapp&t=json";
//                String s2 = HttpUtils.sendPost(url2, null);
//                Map m = JSONObject.parseObject(s2, HashMap.class);
//                Map mm = JSONObject.parseObject(m.get("data").toString(), HashMap.class);
//                List<SysUser> userList = new ArrayList<>();
//            }
//        }
    }

    @Test
    public void CacheTest(){
        Object o = CacheUtils.get(Constants.SYS_SUPPLIER_CACHE, Constants.SYS_SUPPLIER_KEY);
        List<Ztree> ztrees = JSONObject.parseArray((String) o, Ztree.class);

    }
}
