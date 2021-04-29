package com.ruoyi.product.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.product.mapper.AduitMessageMapper;
import com.ruoyi.product.domain.AduitMessage;
import com.ruoyi.product.service.IAduitMessageService;
import com.ruoyi.common.core.text.Convert;

/**
 * productService业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-29
 */
@Service
public class AduitMessageServiceImpl implements IAduitMessageService 
{
    @Autowired
    private AduitMessageMapper aduitMessageMapper;

    /**
     * 查询product
     * 
     * @param id productID
     * @return product
     */
    @Override
    public AduitMessage selectAduitMessageById(Long id)
    {
        return aduitMessageMapper.selectAduitMessageById(id);
    }

    /**
     * 查询product列表
     * 
     * @param aduitMessage product
     * @return product
     */
    @Override
    public List<AduitMessage> selectAduitMessageList(AduitMessage aduitMessage)
    {
        return aduitMessageMapper.selectAduitMessageList(aduitMessage);
    }

    /**
     * 新增product
     * 
     * @param aduitMessage product
     * @return 结果
     */
    @Override
    public int insertAduitMessage(AduitMessage aduitMessage)
    {
        return aduitMessageMapper.insertAduitMessage(aduitMessage);
    }

    /**
     * 修改product
     * 
     * @param aduitMessage product
     * @return 结果
     */
    @Override
    public int updateAduitMessage(AduitMessage aduitMessage)
    {
        return aduitMessageMapper.updateAduitMessage(aduitMessage);
    }

    /**
     * 删除product对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAduitMessageByIds(String ids)
    {
        return aduitMessageMapper.deleteAduitMessageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除product信息
     * 
     * @param id productID
     * @return 结果
     */
    @Override
    public int deleteAduitMessageById(Long id)
    {
        return aduitMessageMapper.deleteAduitMessageById(id);
    }
}
