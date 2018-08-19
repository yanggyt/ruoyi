package com.ruoyi.project.system.dict.service;

import java.util.List;

import com.ruoyi.common.utils.CacheUtils;
import com.ruoyi.common.utils.DictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.security.ShiroUtils;
import com.ruoyi.project.system.dict.domain.DictData;
import com.ruoyi.project.system.dict.mapper.DictDataMapper;

/**
 * 字典 业务层处理
 *
 * @author ruoyi
 */
@Service
public class DictDataServiceImpl implements IDictDataService
{
    @Autowired
    private DictDataMapper dictDataMapper;

    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public List<DictData> selectDictDataList(DictData dictData)
    {
        return dictDataMapper.selectDictDataList(dictData);
    }

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<DictData> selectDictDataByType(String dictType)
    {
//        return dictDataMapper.selectDictDataByType(dictType);
        return DictUtils.getDataByType(dictType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType 字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue)
    {
//        return dictDataMapper.selectDictLabel(dictType, dictValue);
        return DictUtils.getDictLabel(dictType, dictValue);
    }

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Override
    public DictData selectDictDataById(Long dictCode)
    {
        return dictDataMapper.selectDictDataById(dictCode);
    }

    /**
     * 通过字典ID删除字典数据信息
     *
     * @param dictCode 字典数据ID
     * @return 结果
     */
    @Override
    public int deleteDictDataById(Long dictCode)
    {
        DictData dictData = dictDataMapper.selectDictDataById(dictCode);
        String dictType = dictData.getDictType();
        int row = dictDataMapper.deleteDictDataById(dictCode);
        DictUtils.flushDictList(dictType, row);
        return row;
    }

    /**
     * 批量删除字典数据
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    @Override
    public int deleteDictDataByIds(String ids)
    {
        int row = dictDataMapper.deleteDictDataByIds(Convert.toStrArray(ids));
        DictUtils.restAllDictList(row);
        return row;
    }

    /**
     * 新增保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int insertDictData(DictData dictData)
    {
        dictData.setCreateBy(ShiroUtils.getLoginName());
        String dictType = dictData.getDictType();
        int row = dictDataMapper.insertDictData(dictData);
        DictUtils.flushDictList(dictType, row);
        return row;
    }

    /**
     * 修改保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int updateDictData(DictData dictData)
    {
        dictData.setUpdateBy(ShiroUtils.getLoginName());
        String dictType = dictData.getDictType();
        int row = dictDataMapper.updateDictData(dictData);
        DictUtils.flushDictList(dictType, row);
        return row;
    }

}
