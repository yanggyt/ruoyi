package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WkCrmContactsMapper;
import com.ruoyi.system.domain.WkCrmContacts;
import com.ruoyi.system.service.IWkCrmContactsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 联系人Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
@Service
public class WkCrmContactsServiceImpl implements IWkCrmContactsService 
{
    @Autowired
    private WkCrmContactsMapper wkCrmContactsMapper;

    /**
     * 查询联系人
     * 
     * @param contactsId 联系人ID
     * @return 联系人
     */
    @Override
    public WkCrmContacts selectWkCrmContactsById(Long contactsId)
    {
        return wkCrmContactsMapper.selectWkCrmContactsById(contactsId);
    }

    /**
     * 查询联系人列表
     * 
     * @param wkCrmContacts 联系人
     * @return 联系人
     */
    @Override
    public List<WkCrmContacts> selectWkCrmContactsList(WkCrmContacts wkCrmContacts)
    {
        return wkCrmContactsMapper.selectWkCrmContactsList(wkCrmContacts);
    }

    /**
     * 新增联系人
     * 
     * @param wkCrmContacts 联系人
     * @return 结果
     */
    @Override
    public int insertWkCrmContacts(WkCrmContacts wkCrmContacts)
    {
        wkCrmContacts.setCreateTime(DateUtils.getNowDate());
        return wkCrmContactsMapper.insertWkCrmContacts(wkCrmContacts);
    }

    /**
     * 修改联系人
     * 
     * @param wkCrmContacts 联系人
     * @return 结果
     */
    @Override
    public int updateWkCrmContacts(WkCrmContacts wkCrmContacts)
    {
        wkCrmContacts.setUpdateTime(DateUtils.getNowDate());
        return wkCrmContactsMapper.updateWkCrmContacts(wkCrmContacts);
    }

    /**
     * 删除联系人对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWkCrmContactsByIds(String ids)
    {
        return wkCrmContactsMapper.deleteWkCrmContactsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除联系人信息
     * 
     * @param contactsId 联系人ID
     * @return 结果
     */
    @Override
    public int deleteWkCrmContactsById(Long contactsId)
    {
        return wkCrmContactsMapper.deleteWkCrmContactsById(contactsId);
    }
}
