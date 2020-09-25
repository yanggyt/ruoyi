package com.ruoyi.system.service.impl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import com.ruoyi.common.base.BaseService;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.QSysDictType;
import com.ruoyi.system.domain.SysDictType;
import com.ruoyi.system.repository.SysDictDataRepository;
import com.ruoyi.system.repository.SysDictTypeRepository;
import com.ruoyi.system.service.ISysDictTypeService;
import org.hibernate.query.criteria.internal.predicate.BooleanExpressionPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
@CacheConfig(cacheNames = "sys_dict_type")
public class SysDictTypeServiceImpl extends BaseService implements ISysDictTypeService {
    @Autowired
    private SysDictTypeRepository sysDictTypeRepository;
    @Autowired
    private SysDictDataRepository sysDictDataRepository;

    /**
     * 根据条件分页查询字典类型
     *
     * @param dictType 字典类型信息
     * @return 字典类型集合信息
     */
    @Cacheable
    @Override
    public Page<SysDictType> selectDictTypeList(SysDictType dictType, Pageable pageable) {
        return sysDictTypeRepository.findAll(getPredicate(dictType), pageable);
    }

    private Predicate getPredicate(SysDictType sysDictType){
        QSysDictType qSysDictType = QSysDictType.sysDictType;
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(alwaysTrue());
        if(StringUtils.isNotEmpty(sysDictType.getDictName())){
            predicates.add(buildLike(qSysDictType.dictName, sysDictType.getDictName()));
        }
        if(StringUtils.isNotEmpty(sysDictType.getStatus())){
            predicates.add(buildEqual(qSysDictType.status, sysDictType.getStatus()));
        }
        if(StringUtils.isNotEmpty(sysDictType.getDictType())){
            predicates.add(buildLike(qSysDictType.dictType, sysDictType.getDictType()));
        }
        if(sysDictType.getStartTime() != null){
            predicates.add(buildGreaterThanOrEqualTo(qSysDictType.createTime, sysDictType.getStartTime()));
        }
        if(sysDictType.getEndTime() != null){
            predicates.add(buildLessThanOrEqualTo(qSysDictType.createTime, sysDictType.getEndTime()));
        }
        return ExpressionUtils.allOf(predicates);
    }

    /**
     * 根据所有字典类型
     *
     * @return 字典类型集合信息
     */
    @Override
    public List<SysDictType> selectDictTypeAll() {
        return sysDictTypeRepository.findAll();
    }

    /**
     * 根据字典类型ID查询信息
     *
     * @param dictId 字典类型ID
     * @return 字典类型
     */
    @Override
    public SysDictType selectDictTypeById(Long dictId) {
        return sysDictTypeRepository.findById(dictId).get();
    }

    /**
     * 根据字典类型查询信息
     *
     * @param dictType 字典类型
     * @return 字典类型
     */
    public SysDictType selectDictTypeByType(String dictType) {
        return sysDictTypeRepository.findFirstByDictType(dictType);
    }

    /**
     * 通过字典ID删除字典信息
     *
     * @param dictId 字典ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDictTypeById(Long dictId) {
        sysDictTypeRepository.deleteById(dictId);
        return 1;
    }

    /**
     * 批量删除字典类型
     *
     * @param ids 需要删除的数据
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteDictTypeByIds(String ids) throws BusinessException {
        Long[] dictIds = Convert.toLongArray(ids);
        for (Long dictId : dictIds) {
            SysDictType dictType = selectDictTypeById(dictId);
            if (sysDictDataRepository.countByDictType(dictType.getDictType()) > 0) {
                throw new BusinessException(String.format("%1$s已分配,不能删除", dictType.getDictName()));
            }
            deleteDictTypeById(dictId);
        }
        return dictIds.length;
    }

    /**
     * 新增保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    public int insertDictType(SysDictType dictType) {
        sysDictTypeRepository.save(dictType);
        return 1;
    }

    /**
     * 修改保存字典类型信息
     *
     * @param dictType 字典类型信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateDictType(SysDictType dictType) {
        SysDictType oldDict = sysDictTypeRepository.findById(dictType.getDictId()).get();
        sysDictDataRepository.updateDictType(dictType.getDictType(), oldDict.getDictType());
        sysDictTypeRepository.save(dictType);
        return 1;
    }

    /**
     * 校验字典类型称是否唯一
     *
     * @param dict 字典类型
     * @return 结果
     */
    @Override
    public String checkDictTypeUnique(SysDictType dict) {
        Long dictId = StringUtils.isNull(dict.getDictId()) ? -1L : dict.getDictId();
        SysDictType dictType = sysDictTypeRepository.findFirstByDictType(dict.getDictType());
        if (StringUtils.isNotNull(dictType) && dictType.getDictId().longValue() != dictId.longValue()) {
            return UserConstants.DICT_TYPE_NOT_UNIQUE;
        }
        return UserConstants.DICT_TYPE_UNIQUE;
    }

    /**
     * 查询字典类型树
     *
     * @param dictType 字典类型
     * @return 所有字典类型
     */
    public List<Ztree> selectDictTree(SysDictType dictType) {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<SysDictType> dictList = sysDictTypeRepository.findAll();
        for (SysDictType dict : dictList) {
            if (UserConstants.DICT_NORMAL.equals(dict.getStatus())) {
                Ztree ztree = new Ztree();
                ztree.setId(dict.getDictId());
                ztree.setName(transDictName(dict));
                ztree.setTitle(dict.getDictType());
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

    @CacheEvict(allEntries = true)
    @Override
    public void clearCache() {

    }

    public String transDictName(SysDictType dictType) {
        StringBuffer sb = new StringBuffer();
        sb.append("(" + dictType.getDictName() + ")");
        sb.append("&nbsp;&nbsp;&nbsp;" + dictType.getDictType());
        return sb.toString();
    }
}
