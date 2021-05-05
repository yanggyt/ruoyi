package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ShuiniMapper;
import com.ruoyi.system.domain.Shuini;
import com.ruoyi.system.service.IShuiniService;
import com.ruoyi.common.core.text.Convert;

/**
 * 水泥浆Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
@Service
public class ShuiniServiceImpl implements IShuiniService 
{
    @Autowired
    private ShuiniMapper shuiniMapper;

    /**
     * 查询水泥浆
     * 
     * @param liquidNumber 水泥浆ID
     * @return 水泥浆
     */
    @Override
    public Shuini selectShuiniById(Long liquidNumber)
    {
        return shuiniMapper.selectShuiniById(liquidNumber);
    }

    /**
     * 查询水泥浆列表
     * 
     * @param shuini 水泥浆
     * @return 水泥浆
     */
    @Override
    public List<Shuini> selectShuiniList(Shuini shuini)
    {
        return shuiniMapper.selectShuiniList(shuini);
    }

    /**
     * 新增水泥浆
     * 
     * @param shuini 水泥浆
     * @return 结果
     */
    @Override
    public int insertShuini(Shuini shuini)
    {
        return shuiniMapper.insertShuini(shuini);
    }

    /**
     * 修改水泥浆
     * 
     * @param shuini 水泥浆
     * @return 结果
     */
    @Override
    public int updateShuini(Shuini shuini)
    {
        return shuiniMapper.updateShuini(shuini);
    }

    /**
     * 删除水泥浆对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteShuiniByIds(String ids)
    {
        return shuiniMapper.deleteShuiniByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除水泥浆信息
     * 
     * @param liquidNumber 水泥浆ID
     * @return 结果
     */
    @Override
    public int deleteShuiniById(Long liquidNumber)
    {
        return shuiniMapper.deleteShuiniById(liquidNumber);
    }
}
