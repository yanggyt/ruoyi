package com.ruoyi.content.utils;

import com.ruoyi.content.exception.BusinessException;
import com.ruoyi.content.redis.RedisManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户信息请求检查
 *
 * @author qbh
 */
@Component
public class CheckUtil {

    @Autowired
    private RedisManager redisManager;

    /**
     * 检查30s内同一用户是否存在重复请求
     *
     * @param id
     * @param data
     * @return
     */
    public boolean isRepeat(String id, String method, String data) {

        String lastValue = null;
        try {
            lastValue = redisManager.query("contentMKTCms-repeatcheck" + "_" + id + "_" + method);
        } catch (Exception e) {
            throw new BusinessException("获取redis里的repeatcheck值异常!");
        }
        String md5data = EncoderUtil.Md5_32(data).toUpperCase();
        if (StringUtils.isBlank(lastValue)) {
            redisManager.save("contentMKTCms-repeatcheck" + "_" + id + "_" + method, md5data, 30);
            return false;
        } else if (lastValue.equals(md5data)) {
            return true;
        } else {
            redisManager.save("contentMKTCms-repeatcheck" + "_" + id + "_" + method, md5data);
            return false;
        }
    }
}
