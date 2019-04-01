package com.ruoyi.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.json.JSON;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.common.utils.http.HttpUtils;

/**
 * 获取地址类
 *
 * @author ruoyi
 */
public class AddressUtils {
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    public static final String IP_URL = "https://api.map.baidu.com/location/ip";
    public static final String ak = "hNGEz4KKVtRLkM1zrUKDjGRv";

    public static String getRealAddressByIP(String ip) {
        String address = "XX XX";

        // 内网不查询
        if (IpUtils.internalIp(ip)) {
            return "内网IP";
        }
        if (Global.isAddressEnabled()) {

            if (ip.contains("," )) {
                ip = ip.split("," )[0];
            }
            String rspStr = HttpUtils.sendPost(IP_URL, "ip=" + ip + "&ak=" + ak);
            if (StringUtils.isEmpty(rspStr)) {
                log.error("获取地理位置异常 {}", ip);
                return address;
            }
            JSONObject obj;
            try {
                obj = JSON.unmarshal(rspStr, JSONObject.class);
                log.info("地址位置接口返回信息:{}", rspStr);
//                JSONObject data = obj.getObj("data");
                address = obj.getStr("address" );
//                String region = data.getStr("region");
//                String city = data.getStr("city");
//                address = region + " " + city;
            } catch (Exception e) {
                log.error("获取地理位置异常 {}", ip);
            }
        }
        return address;
    }
}
