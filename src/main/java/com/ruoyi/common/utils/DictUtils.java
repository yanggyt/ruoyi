/**
 * Copyright &copy; 2018 All rights reserved.
 */
package com.ruoyi.common.utils;

import com.ruoyi.project.system.dict.domain.DictData;
import com.ruoyi.project.system.dict.mapper.DictDataMapper;

import java.util.List;

/**
 * 字典工具类
 *
 * @author yawu_bear
 * @date 2018-08-10
 */

public class DictUtils {

    private static DictDataMapper dictDataMapper = SpringUtil.getBean(DictDataMapper.class);

    public static final String DICT_CACHE = "dictCache";
    public static final String DICT_CACHE_TYPE = "type_";

    /**
     * 根据类型和键值获取标签
     *
     * @param type
     * @param value
     * @return
     */
    public static String getDictLabel(String type, String value) {
        String label = "";
        if (StringUtils.isNotBlank(type) && StringUtils.isNotBlank(value)) {
            for (DictData dictData : getDataByType(type)) {
                if (label.equals(dictData.getDictValue())) {
                    label = dictData.getDictLabel();
                }
            }
        }
        return label;
    }

    /**
     * 根据type获取data列表
     *
     * @param dictType
     * @return
     */
    public static List<DictData> getDataByType(String dictType) {
        List<DictData> dictList = (List<DictData>) CacheUtils.get(DICT_CACHE, DICT_CACHE_TYPE + dictType);
        if (StringUtils.isEmpty(dictList)) {
            dictList = dictDataMapper.selectDictDataByType(dictType);
            CacheUtils.put(DICT_CACHE, DICT_CACHE_TYPE + dictType, dictList);
        }
        return dictList;
    }

    /**
     * 增删改字典缓存刷新
     *
     * @param dictType
     * @param row
     */
    public static void flushDictList(String dictType, int row) {
        if (row > 0) {
            // 增删改成功修改缓存信息
            List<DictData> dictList = dictDataMapper.selectDictDataByType(dictType);
            CacheUtils.remove(DictUtils.DICT_CACHE, DictUtils.DICT_CACHE_TYPE + dictType);
            CacheUtils.put(DictUtils.DICT_CACHE, DictUtils.DICT_CACHE_TYPE + dictType, dictList);
        }
    }

    /**
     * 字典类型修改缓存操作
     * @param oldType
     * @param newType
     * @param row
     */
    public static void flushDictList(String oldType, String newType, int row) {
        if (row > 0) {
            // 增删改成功修改缓存信息
            List<DictData> dictList = dictDataMapper.selectDictDataByType(oldType);
            CacheUtils.remove(DictUtils.DICT_CACHE, DictUtils.DICT_CACHE_TYPE + oldType);
            CacheUtils.put(DictUtils.DICT_CACHE, DictUtils.DICT_CACHE_TYPE + newType, dictList);
        }
    }

    /**
     * 缓存重置
     *
     * @param row
     */
    public static void restAllDictList(int row) {
        if (row > 0) {
            // 增删改成功修改缓存信息
            List<DictData> dictList = dictDataMapper.selectDictDataList(new DictData());
            if (StringUtils.isNotEmpty(dictList)) {
                for (DictData dictData : dictList) {
                    CacheUtils.removeAll(DictUtils.DICT_CACHE);
                    CacheUtils.put(DictUtils.DICT_CACHE, DictUtils.DICT_CACHE_TYPE + dictData.getDictType(), dictList);
                }
            }
        }
    }
}
