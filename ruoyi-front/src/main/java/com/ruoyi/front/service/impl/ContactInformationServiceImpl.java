package com.ruoyi.front.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.front.mapper.ContactInformationMapper;
import com.ruoyi.front.domain.ContactInformation;
import com.ruoyi.front.service.IContactInformationService;
import com.ruoyi.common.core.text.Convert;

/**
 * 联系方式Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-22
 */
@Service
public class ContactInformationServiceImpl implements IContactInformationService 
{
    @Autowired
    private ContactInformationMapper contactInformationMapper;

    /**
     * 查询联系方式
     * 
     * @param id 联系方式ID
     * @return 联系方式
     */
    @Override
    public ContactInformation selectContactInformationById(Long id)
    {
        return contactInformationMapper.selectContactInformationById(id);
    }

    /**
     * 查询联系方式列表
     * 
     * @param contactInformation 联系方式
     * @return 联系方式
     */
    @Override
    public List<ContactInformation> selectContactInformationList(ContactInformation contactInformation)
    {
        return contactInformationMapper.selectContactInformationList(contactInformation);
    }

    /**
     * 新增联系方式
     * 
     * @param contactInformation 联系方式
     * @return 结果
     */
    @Override
    public int insertContactInformation(ContactInformation contactInformation)
    {
        contactInformation.setCreateTime(DateUtils.getNowDate());
        return contactInformationMapper.insertContactInformation(contactInformation);
    }

    /**
     * 修改联系方式
     * 
     * @param contactInformation 联系方式
     * @return 结果
     */
    @Override
    public int updateContactInformation(ContactInformation contactInformation)
    {
        contactInformation.setUpdateTime(DateUtils.getNowDate());
        return contactInformationMapper.updateContactInformation(contactInformation);
    }

    /**
     * 删除联系方式对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteContactInformationByIds(String ids)
    {
        return contactInformationMapper.deleteContactInformationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除联系方式信息
     * 
     * @param id 联系方式ID
     * @return 结果
     */
    @Override
    public int deleteContactInformationById(Long id)
    {
        return contactInformationMapper.deleteContactInformationById(id);
    }
}
