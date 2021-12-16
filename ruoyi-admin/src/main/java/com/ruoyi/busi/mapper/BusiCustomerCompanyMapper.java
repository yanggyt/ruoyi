package com.ruoyi.busi.mapper;

import java.util.List;
import com.ruoyi.busi.domain.BusiCustomerCompany;
import com.ruoyi.busi.domain.BusiCustomerPerson;

/**
 * 客户公司Mapper接口
 * 
 * @author WangCL
 * @date 2021-12-16
 */
public interface BusiCustomerCompanyMapper 
{
    /**
     * 查询客户公司
     * 
     * @param id 客户公司主键
     * @return 客户公司
     */
    public BusiCustomerCompany selectBusiCustomerCompanyById(String id);

    /**
     * 查询客户公司列表
     * 
     * @param busiCustomerCompany 客户公司
     * @return 客户公司集合
     */
    public List<BusiCustomerCompany> selectBusiCustomerCompanyList(BusiCustomerCompany busiCustomerCompany);

    /**
     * 新增客户公司
     * 
     * @param busiCustomerCompany 客户公司
     * @return 结果
     */
    public int insertBusiCustomerCompany(BusiCustomerCompany busiCustomerCompany);

    /**
     * 修改客户公司
     * 
     * @param busiCustomerCompany 客户公司
     * @return 结果
     */
    public int updateBusiCustomerCompany(BusiCustomerCompany busiCustomerCompany);

    /**
     * 删除客户公司
     * 
     * @param id 客户公司主键
     * @return 结果
     */
    public int deleteBusiCustomerCompanyById(String id);

    /**
     * 批量删除客户公司
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusiCustomerCompanyByIds(String[] ids);

    /**
     * 批量删除客户公司人员
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusiCustomerPersonByCompanyIds(String[] ids);
    
    /**
     * 批量新增客户公司人员
     * 
     * @param busiCustomerPersonList 客户公司人员列表
     * @return 结果
     */
    public int batchBusiCustomerPerson(List<BusiCustomerPerson> busiCustomerPersonList);
    

    /**
     * 通过客户公司主键删除客户公司人员信息
     * 
     * @param id 客户公司ID
     * @return 结果
     */
    public int deleteBusiCustomerPersonByCompanyId(String id);
}
