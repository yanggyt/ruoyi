package com.ruoyi.busi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.busi.domain.BusiCustomerPerson;
import com.ruoyi.busi.mapper.BusiCustomerCompanyMapper;
import com.ruoyi.busi.domain.BusiCustomerCompany;
import com.ruoyi.busi.service.IBusiCustomerCompanyService;
import com.ruoyi.common.core.text.Convert;

/**
 * 客户公司Service业务层处理
 * 
 * @author WangCL
 * @date 2021-12-16
 */
@Service
public class BusiCustomerCompanyServiceImpl implements IBusiCustomerCompanyService 
{
    @Autowired
    private BusiCustomerCompanyMapper busiCustomerCompanyMapper;

    /**
     * 查询客户公司
     * 
     * @param id 客户公司主键
     * @return 客户公司
     */
    @Override
    public BusiCustomerCompany selectBusiCustomerCompanyById(String id)
    {
        return busiCustomerCompanyMapper.selectBusiCustomerCompanyById(id);
    }

    /**
     * 查询客户公司列表
     * 
     * @param busiCustomerCompany 客户公司
     * @return 客户公司
     */
    @Override
    public List<BusiCustomerCompany> selectBusiCustomerCompanyList(BusiCustomerCompany busiCustomerCompany)
    {
        return busiCustomerCompanyMapper.selectBusiCustomerCompanyList(busiCustomerCompany);
    }

    /**
     * 新增客户公司
     * 
     * @param busiCustomerCompany 客户公司
     * @return 结果
     */
    @Transactional
    @Override
    public int insertBusiCustomerCompany(BusiCustomerCompany busiCustomerCompany)
    {
        busiCustomerCompany.setCreateTime(DateUtils.getNowDate());
        int rows = busiCustomerCompanyMapper.insertBusiCustomerCompany(busiCustomerCompany);
        insertBusiCustomerPerson(busiCustomerCompany);
        return rows;
    }

    /**
     * 修改客户公司
     * 
     * @param busiCustomerCompany 客户公司
     * @return 结果
     */
    @Transactional
    @Override
    public int updateBusiCustomerCompany(BusiCustomerCompany busiCustomerCompany)
    {
        busiCustomerCompany.setUpdateTime(DateUtils.getNowDate());
        busiCustomerCompanyMapper.deleteBusiCustomerPersonByCompanyId(busiCustomerCompany.getId());
        insertBusiCustomerPerson(busiCustomerCompany);
        return busiCustomerCompanyMapper.updateBusiCustomerCompany(busiCustomerCompany);
    }

    /**
     * 批量删除客户公司
     * 
     * @param ids 需要删除的客户公司主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteBusiCustomerCompanyByIds(String ids)
    {
        busiCustomerCompanyMapper.deleteBusiCustomerPersonByCompanyIds(Convert.toStrArray(ids));
        return busiCustomerCompanyMapper.deleteBusiCustomerCompanyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户公司信息
     * 
     * @param id 客户公司主键
     * @return 结果
     */
    @Override
    public int deleteBusiCustomerCompanyById(String id)
    {
        busiCustomerCompanyMapper.deleteBusiCustomerPersonByCompanyId(id);
        return busiCustomerCompanyMapper.deleteBusiCustomerCompanyById(id);
    }

    /**
     * 新增客户公司人员信息
     * 
     * @param busiCustomerCompany 客户公司对象
     */
    public void insertBusiCustomerPerson(BusiCustomerCompany busiCustomerCompany)
    {
        List<BusiCustomerPerson> busiCustomerPersonList = busiCustomerCompany.getBusiCustomerPersonList();
        String id = busiCustomerCompany.getId();
        if (StringUtils.isNotNull(busiCustomerPersonList))
        {
            List<BusiCustomerPerson> list = new ArrayList<BusiCustomerPerson>();
            for (BusiCustomerPerson busiCustomerPerson : busiCustomerPersonList)
            {
                busiCustomerPerson.setCreateTime(DateUtils.getNowDate());
                busiCustomerPerson.setCompanyId(id);
                list.add(busiCustomerPerson);
            }
            if (list.size() > 0)
            {
                busiCustomerCompanyMapper.batchBusiCustomerPerson(list);
            }
        }
    }
}
