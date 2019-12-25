package com.ruoyi.system.service.impl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.ruoyi.common.base.BaseService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.QSysDictData;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.repository.SysDictDataRepository;
import com.ruoyi.system.service.ISysDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 字典 业务层处理
 *
 * @author ruoyi
 */
@Service
public class SysDictDataServiceImpl extends BaseService implements ISysDictDataService {
    @Autowired
    private SysDictDataRepository sysDictDataRepository;

    /**
     * 根据条件分页查询字典数据
     *
     * @param dictData 字典数据信息
     * @return 字典数据集合信息
     */
    @Override
    public Page<SysDictData> selectDictDataList(SysDictData dictData, Pageable pageable) {
        return sysDictDataRepository.findAll(getPredicate(dictData), pageable);
    }

    private Predicate getPredicate(SysDictData dictData){
        QSysDictData qSysDictData = QSysDictData.sysDictData;
        List<Predicate> predicates = new ArrayList<>();
        if(StringUtils.isNotEmpty(dictData.getDictType())){
            predicates.add(buildEqual(qSysDictData.dictType, dictData.getDictType()));
        }
        if(StringUtils.isNotEmpty(dictData.getDictLabel())){
            predicates.add(buildLike(qSysDictData.status, dictData.getDictLabel()));
        }
        if(StringUtils.isNotEmpty(dictData.getStatus())){
            predicates.add(buildEqual(qSysDictData.status, dictData.getStatus()));
        }
        return ExpressionUtils.allOf(predicates);
    }

    /**
     * 根据字典类型查询字典数据
     *
     * @param dictType 字典类型
     * @return 字典数据集合信息
     */
    @Override
    public List<SysDictData> selectDictDataByType(String dictType) {
        return sysDictDataRepository.findByDictType(dictType);
    }

    /**
     * 根据字典类型和字典键值查询字典数据信息
     *
     * @param dictType  字典类型
     * @param dictValue 字典键值
     * @return 字典标签
     */
    @Override
    public String selectDictLabel(String dictType, String dictValue) {
        SysDictData dictData = sysDictDataRepository.findFirstByDictTypeAndDictLabel(dictType, dictType);
        return  dictData == null ? dictData.getDictValue() : "";
    }

    /**
     * 根据字典数据ID查询信息
     *
     * @param dictCode 字典数据ID
     * @return 字典数据
     */
    @Override
    public SysDictData selectDictDataById(Long dictCode) {
        return sysDictDataRepository.findById(dictCode).get();
    }

    /**
     * 通过字典ID删除字典数据信息
     *
     * @param dictCode 字典数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDictDataById(Long dictCode) {
        sysDictDataRepository.deleteById(dictCode);
        return 1;
    }

    /**
     * 批量删除字典数据
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDictDataByIds(String ids) {
        for(Long id : Convert.toLongArray(ids)){
            deleteDictDataById(id);
        }
        return 1;
    }

    /**
     * 新增保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int insertDictData(SysDictData dictData) {
        sysDictDataRepository.save(dictData);
        return 1;
    }

    /**
     * 修改保存字典数据信息
     *
     * @param dictData 字典数据信息
     * @return 结果
     */
    @Override
    public int updateDictData(SysDictData dictData) {
        sysDictDataRepository.save(dictData);
        return 1;
    }
}
