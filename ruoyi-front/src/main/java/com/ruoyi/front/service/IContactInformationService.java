package com.ruoyi.front.service;

import java.util.List;
import com.ruoyi.front.domain.ContactInformation;

/**
 * 联系方式Service接口
 * 
 * @author ruoyi
 * @date 2020-10-22
 */
public interface IContactInformationService 
{
    /**
     * 查询联系方式
     * 
     * @param id 联系方式ID
     * @return 联系方式
     */
    public ContactInformation selectContactInformationById(Long id);

    /**
     * 查询联系方式列表
     * 
     * @param contactInformation 联系方式
     * @return 联系方式集合
     */
    public List<ContactInformation> selectContactInformationList(ContactInformation contactInformation);

    /**
     * 新增联系方式
     * 
     * @param contactInformation 联系方式
     * @return 结果
     */
    public int insertContactInformation(ContactInformation contactInformation);

    /**
     * 修改联系方式
     * 
     * @param contactInformation 联系方式
     * @return 结果
     */
    public int updateContactInformation(ContactInformation contactInformation);

    /**
     * 批量删除联系方式
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteContactInformationByIds(String ids);

    /**
     * 删除联系方式信息
     * 
     * @param id 联系方式ID
     * @return 结果
     */
    public int deleteContactInformationById(Long id);
}
