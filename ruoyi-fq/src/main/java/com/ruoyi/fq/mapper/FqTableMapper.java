package com.ruoyi.fq.mapper;

import java.util.List;
import com.ruoyi.fq.domain.FqTable;

/**
 * 封铅登记Mapper接口
 * 
 * @author mario
 * @date 2020-07-09
 */
public interface FqTableMapper 
{
    /**
     * 查询封铅登记
     * 
     * @param id 封铅登记ID
     * @return 封铅登记
     */
    public FqTable selectFqTableById(Long id);

    /**
     * 查询封铅登记列表
     * 
     * @param fqTable 封铅登记
     * @return 封铅登记集合
     */
    public List<FqTable> selectFqTableList(FqTable fqTable);

    /**
     * 新增封铅登记
     * 
     * @param fqTable 封铅登记
     * @return 结果
     */
    public int insertFqTable(FqTable fqTable);

    /**
     * 批量新增封铅登记
     *
     * @param list 封铅登记
     * @return 结果
     */
    public int batchInsertFqTable(List<FqTable> list);

    /**
     * 修改封铅登记
     * 
     * @param fqTable 封铅登记
     * @return 结果
     */
    public int updateFqTable(FqTable fqTable);

    /**
     * 删除封铅登记
     * 
     * @param id 封铅登记ID
     * @return 结果
     */
    public int deleteFqTableById(Long id);

    /**
     * 批量删除封铅登记
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFqTableByIds(String[] ids);
}
