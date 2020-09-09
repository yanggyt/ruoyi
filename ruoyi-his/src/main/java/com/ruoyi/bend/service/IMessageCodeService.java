package com.ruoyi.bend.service;

import java.util.List;
import com.ruoyi.bend.domain.MessageCode;

/**
 * 短信管理Service接口
 * 
 * @author bend
 * @date 2020-08-30
 */
public interface IMessageCodeService 
{
    /**
     * 查询短信管理
     * 
     * @param id 短信管理ID
     * @return 短信管理
     */
    public MessageCode selectMessageCodeById(Long id);

    /**
     * 查询短信管理列表
     * 
     * @param messageCode 短信管理
     * @return 短信管理集合
     */
    public List<MessageCode> selectMessageCodeList(MessageCode messageCode);

    /**
     * 新增短信管理
     * 
     * @param messageCode 短信管理
     * @return 结果
     */
    public int insertMessageCode(MessageCode messageCode);

    /**
     * 修改短信管理
     * 
     * @param messageCode 短信管理
     * @return 结果
     */
    public int updateMessageCode(MessageCode messageCode);

    /**
     * 批量删除短信管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteMessageCodeByIds(String ids);

    /**
     * 删除短信管理信息
     * 
     * @param id 短信管理ID
     * @return 结果
     */
    public int deleteMessageCodeById(Long id);
}
