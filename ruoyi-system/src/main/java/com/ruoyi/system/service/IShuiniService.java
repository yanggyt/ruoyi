package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Shuini;

/**
 * 水泥浆Service接口
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
public interface IShuiniService 
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
     * 批量删除水泥浆
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteShuiniByIds(String ids);

    /**
     * 删除水泥浆信息
     * 
     * @param liquidNumber 水泥浆ID
     * @return 结果
     */
    public int deleteShuiniById(Long liquidNumber);
}
