package com.ruoyi.common.utils;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysDictData;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * proCode工具类
 * 
 * @author ruoyi
 */
@Component
public class ProCodeUtils
{
    /**
     * 分隔符
     */
    public static final String SEPARATOR = ",";

    /**
     * 设置缓存
     * 
     * @param key 参数键
     * @param proCodeList 数据列表
     */
//    public static void setProCodeCache(String key, List<String> proCodeList)
//    {
//        CacheUtils.put(getCacheName(), getCacheKey(key), proCodeList);
//    }

    /**
     * 获取字典缓存
     * 
     * @param key 参数键
     * @return dictDatas 字典数据列表
     */
    public static List<SysDictData> getDictCache(String key)
    {
        Object cacheObj = CacheUtils.get(getCacheName(), getCacheKey(key));
        if (StringUtils.isNotNull(cacheObj))
        {
            return StringUtils.cast(cacheObj);
        }
        return null;
    }







    /**
     * 删除指定字典缓存
     * 
     * @param key 字典键
     */
    public static void removeProCodeCache(String key)
    {
        CacheUtils.remove(getCacheName(), getCacheKey(key));
    }

    /**
     * 清空字典缓存
     */
    public static void clearProCodeCache()
    {
        CacheUtils.removeAll(getCacheName());
    }

    /**
     * 获取cache name
     * 
     * @return 缓存名
     */
    public static String getCacheName()
    {
        return Constants.ZT_WGCPRORELEASE_CACHE;
    }

    /**
     * 设置cache key
     * 
     * @param configKey 参数键
     * @return 缓存键key
     */
    public static String getCacheKey(String configKey)
    {
        return Constants.ZT_WGCPRORELEASE_KEY + configKey;
    }
}
