package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Shuini;

/**
 * 水泥浆Mapper接口
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
public interface ShuiniMapper 
{
    /**
     * 查询水泥浆
     * 
     * @param liquidNumber 水泥浆ID
     * @return 水泥浆
     */
    public Shuini selectShuiniById(Long liquidNumber);

    /**
     * 查询水泥浆列表
     * 
     * @param shuini 水泥浆
     * @return 水泥浆集合
     */
    public List<Shuini> selectShuiniList(Shuini shuini);

    /**
     * 新增水泥浆
     * 
     * @param shuini 水泥浆
     * @return 结果
     */
    public int insertShuini(Shuini shuini);

    /**
     * 修改水泥浆
     * 
     * @param shuini 水泥浆
     * @return 结果
     */
    public int updateShuini(Shuini shuini);

    /**
     * 删除水泥浆
     * 
     * @param liquidNumber 水泥浆ID
     * @return 结果
     */
    public int deleteShuiniById(Long liquidNumber);

    /**
     * 批量删除水泥浆
     * 
     * @param liquidNumbers 需要删除的数据ID
     * @return 结果
     */
    public int deleteShuiniByIds(String[] liquidNumbers);
}
