package com.ruoyi.busi.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.busi.mapper.BusiCustomerPersonMapper;
import com.ruoyi.busi.domain.BusiCustomerPerson;
import com.ruoyi.busi.service.IBusiCustomerPersonService;
import com.ruoyi.common.core.text.Convert;

/**
 * 客户公司人员Service业务层处理
 * 
 * @author WangCL
 * @date 2021-12-16
 */
@Service
public class BusiCustomerPersonServiceImpl implements IBusiCustomerPersonService 
{
    @Autowired
    private BusiCustomerPersonMapper busiCustomerPersonMapper;

    /**
     * 查询客户公司人员
     * 
     * @param id 客户公司人员主键
     * @return 客户公司人员
     */
    @Override
    public BusiCustomerPerson selectBusiCustomerPersonById(String id)
    {
        return busiCustomerPersonMapper.selectBusiCustomerPersonById(id);
    }

    /**
     * 查询客户公司人员列表
     * 
     * @param busiCustomerPerson 客户公司人员
     * @return 客户公司人员
     */
    @Override
    public List<BusiCustomerPerson> selectBusiCustomerPersonList(BusiCustomerPerson busiCustomerPerson)
    {
        return busiCustomerPersonMapper.selectBusiCustomerPersonList(busiCustomerPerson);
    }

    /**
     * 新增客户公司人员
     * 
     * @param busiCustomerPerson 客户公司人员
     * @return 结果
     */
    @Override
    public int insertBusiCustomerPerson(BusiCustomerPerson busiCustomerPerson)
    {
        busiCustomerPerson.setCreateTime(DateUtils.getNowDate());
        return busiCustomerPersonMapper.insertBusiCustomerPerson(busiCustomerPerson);
    }

    /**
     * 修改客户公司人员
     * 
     * @param busiCustomerPerson 客户公司人员
     * @return 结果
     */
    @Override
    public int updateBusiCustomerPerson(BusiCustomerPerson busiCustomerPerson)
    {
        busiCustomerPerson.setUpdateTime(DateUtils.getNowDate());
        return busiCustomerPersonMapper.updateBusiCustomerPerson(busiCustomerPerson);
    }

    /**
     * 批量删除客户公司人员
     * 
     * @param ids 需要删除的客户公司人员主键
     * @return 结果
     */
    @Override
    public int deleteBusiCustomerPersonByIds(String ids)
    {
        return busiCustomerPersonMapper.deleteBusiCustomerPersonByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除客户公司人员信息
     * 
     * @param id 客户公司人员主键
     * @return 结果
     */
    @Override
    public int deleteBusiCustomerPersonById(String id)
    {
        return busiCustomerPersonMapper.deleteBusiCustomerPersonById(id);
    }
}
