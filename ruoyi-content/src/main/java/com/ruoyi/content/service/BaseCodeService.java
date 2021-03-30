package com.ruoyi.content.service;

import com.ruoyi.content.domain.BaseCode;
import com.ruoyi.content.domain.BaseCodeTree;
import com.ruoyi.content.message.Message;

import java.util.List;

/**
 * 说明：基础数据查询
 *
 * @author wang.q
 * @date 2017年8月25日
 */
public interface BaseCodeService {
    /**
     * 根据代码类型查找数据字典项
     *
     * @param codeType 代码类型
     * @return
     */
    public List<BaseCode> queryBaseCodeByType(String codeType);

    /**
     * 添加基础数据
     *
     * @param baseCode
     * @return
     */
    public Message saveBaseCode(BaseCode baseCode);

    /**
     * 更新基础数据
     *
     * @param baseCode
     * @return
     */
    public Message updateBaseCode(BaseCode baseCode);

    /**
     * 根据id查询基本数据
     *
     * @return
     */
    public Message queryBaseCode(String ids);

    /**
     * 条件查询基础数据
     *
     * @param codeType
     * @param codeCname
     * @param codeCode
     * @return
     */
    public List<BaseCode> queryBaseCode(String codeType, String codeCname, String codeCode);

    /**
     * 统计条件下有多少数据
     *
     * @param codeType
     * @param codeCname
     * @param codeCode
     * @return
     */
    public int countBaseCode(String codeType, String codeCname, String codeCode);

    /**
     * 查询栏目信息
     *
     * @param
     * @return
     */
    public List<BaseCode> queryColumn();

    /**
     * 删除栏目
     *
     * @param ids
     * @return
     */
    Integer delBaseCode(String ids);

    /**
     * 查询栏目树
     *
     * @param codeCode
     * @param codeType
     * @return
     */
    public List<BaseCodeTree> columnTree(String codeCode, String codeType);

    /**
     * 操作栏目排序
     *
     * @param columnId
     * @param operateType
     * @return
     */
    public Message operateColumn(String columnId, String operateType);

    /**
     * 查询栏目树
     *
     * @param codeCode
     * @return
     */
    List<BaseCodeTree> baseColumnTree(String codeCode);

}
