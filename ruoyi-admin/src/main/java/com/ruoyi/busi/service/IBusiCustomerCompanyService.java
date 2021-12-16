package com.ruoyi.busi.service;

import java.util.List;
import com.ruoyi.busi.domain.BusiCustomerCompany;

/**
 * 客户公司Service接口
 * 
 * @author WangCL
 * @date 2021-12-16
 */
public interface IBusiCustomerCompanyService 
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
     * 批量删除客户公司
     * 
     * @param ids 需要删除的客户公司主键集合
     * @return 结果
     */
    public int deleteBusiCustomerCompanyByIds(String ids);

    /**
     * 删除客户公司信息
     * 
     * @param id 客户公司主键
     * @return 结果
     */
    public int deleteBusiCustomerCompanyById(String id);
}
