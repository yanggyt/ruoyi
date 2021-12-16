package com.ruoyi.busi.mapper;

import java.util.List;
import com.ruoyi.busi.domain.BusiCustomerPerson;

/**
 * 客户公司人员Mapper接口
 * 
 * @author WangCL
 * @date 2021-12-16
 */
public interface BusiCustomerPersonMapper 
{
    /**
     * 查询客户公司人员
     * 
     * @param id 客户公司人员主键
     * @return 客户公司人员
     */
    public BusiCustomerPerson selectBusiCustomerPersonById(String id);

    /**
     * 查询客户公司人员列表
     * 
     * @param busiCustomerPerson 客户公司人员
     * @return 客户公司人员集合
     */
    public List<BusiCustomerPerson> selectBusiCustomerPersonList(BusiCustomerPerson busiCustomerPerson);

    /**
     * 新增客户公司人员
     * 
     * @param busiCustomerPerson 客户公司人员
     * @return 结果
     */
    public int insertBusiCustomerPerson(BusiCustomerPerson busiCustomerPerson);

    /**
     * 修改客户公司人员
     * 
     * @param busiCustomerPerson 客户公司人员
     * @return 结果
     */
    public int updateBusiCustomerPerson(BusiCustomerPerson busiCustomerPerson);

    /**
     * 删除客户公司人员
     * 
     * @param id 客户公司人员主键
     * @return 结果
     */
    public int deleteBusiCustomerPersonById(String id);

    /**
     * 批量删除客户公司人员
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusiCustomerPersonByIds(String[] ids);
}
