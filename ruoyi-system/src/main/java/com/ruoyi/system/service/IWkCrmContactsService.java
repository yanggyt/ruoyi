package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.WkCrmContacts;

/**
 * 联系人Service接口
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
public interface IWkCrmContactsService 
{
    /**
     * 查询联系人
     * 
     * @param contactsId 联系人ID
     * @return 联系人
     */
    public WkCrmContacts selectWkCrmContactsById(Long contactsId);

    /**
     * 查询联系人列表
     * 
     * @param wkCrmContacts 联系人
     * @return 联系人集合
     */
    public List<WkCrmContacts> selectWkCrmContactsList(WkCrmContacts wkCrmContacts);

    /**
     * 新增联系人
     * 
     * @param wkCrmContacts 联系人
     * @return 结果
     */
    public int insertWkCrmContacts(WkCrmContacts wkCrmContacts);

    /**
     * 修改联系人
     * 
     * @param wkCrmContacts 联系人
     * @return 结果
     */
    public int updateWkCrmContacts(WkCrmContacts wkCrmContacts);

    /**
     * 批量删除联系人
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWkCrmContactsByIds(String ids);

    /**
     * 删除联系人信息
     * 
     * @param contactsId 联系人ID
     * @return 结果
     */
    public int deleteWkCrmContactsById(Long contactsId);
}
