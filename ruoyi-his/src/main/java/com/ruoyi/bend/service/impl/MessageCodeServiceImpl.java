package com.ruoyi.bend.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bend.mapper.MessageCodeMapper;
import com.ruoyi.bend.domain.MessageCode;
import com.ruoyi.bend.service.IMessageCodeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 短信管理Service业务层处理
 * 
 * @author bend
 * @date 2020-08-30
 */
@Service
public class MessageCodeServiceImpl implements IMessageCodeService 
{
    @Autowired
    private MessageCodeMapper messageCodeMapper;

    /**
     * 查询短信管理
     * 
     * @param id 短信管理ID
     * @return 短信管理
     */
    @Override
    public MessageCode selectMessageCodeById(Long id)
    {
        return messageCodeMapper.selectMessageCodeById(id);
    }

    /**
     * 查询短信管理列表
     * 
     * @param messageCode 短信管理
     * @return 短信管理
     */
    @Override
    public List<MessageCode> selectMessageCodeList(MessageCode messageCode)
    {
        return messageCodeMapper.selectMessageCodeList(messageCode);
    }

    /**
     * 新增短信管理
     * 
     * @param messageCode 短信管理
     * @return 结果
     */
    @Override
    public int insertMessageCode(MessageCode messageCode)
    {
        messageCode.setCreateTime(DateUtils.getNowDate());
        return messageCodeMapper.insertMessageCode(messageCode);
    }

    /**
     * 修改短信管理
     * 
     * @param messageCode 短信管理
     * @return 结果
     */
    @Override
    public int updateMessageCode(MessageCode messageCode)
    {
        messageCode.setUpdateTime(DateUtils.getNowDate());
        return messageCodeMapper.updateMessageCode(messageCode);
    }

    /**
     * 删除短信管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteMessageCodeByIds(String ids)
    {
        return messageCodeMapper.deleteMessageCodeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除短信管理信息
     * 
     * @param id 短信管理ID
     * @return 结果
     */
    @Override
    public int deleteMessageCodeById(Long id)
    {
        return messageCodeMapper.deleteMessageCodeById(id);
    }
}
