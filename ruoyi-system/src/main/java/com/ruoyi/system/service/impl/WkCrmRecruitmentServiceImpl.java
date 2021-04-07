package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WkCrmRecruitmentMapper;
import com.ruoyi.system.domain.WkCrmRecruitment;
import com.ruoyi.system.service.IWkCrmRecruitmentService;
import com.ruoyi.common.core.text.Convert;

/**
 * 招聘职位Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
@Service
public class WkCrmRecruitmentServiceImpl implements IWkCrmRecruitmentService 
{
    @Autowired
    private WkCrmRecruitmentMapper wkCrmRecruitmentMapper;

    /**
     * 查询招聘职位
     * 
     * @param id 招聘职位ID
     * @return 招聘职位
     */
    @Override
    public WkCrmRecruitment selectWkCrmRecruitmentById(Long id)
    {
        return wkCrmRecruitmentMapper.selectWkCrmRecruitmentById(id);
    }

    /**
     * 查询招聘职位列表
     * 
     * @param wkCrmRecruitment 招聘职位
     * @return 招聘职位
     */
    @Override
    public List<WkCrmRecruitment> selectWkCrmRecruitmentList(WkCrmRecruitment wkCrmRecruitment)
    {
        return wkCrmRecruitmentMapper.selectWkCrmRecruitmentList(wkCrmRecruitment);
    }

    /**
     * 新增招聘职位
     * 
     * @param wkCrmRecruitment 招聘职位
     * @return 结果
     */
    @Override
    public int insertWkCrmRecruitment(WkCrmRecruitment wkCrmRecruitment)
    {
        return wkCrmRecruitmentMapper.insertWkCrmRecruitment(wkCrmRecruitment);
    }

    /**
     * 修改招聘职位
     * 
     * @param wkCrmRecruitment 招聘职位
     * @return 结果
     */
    @Override
    public int updateWkCrmRecruitment(WkCrmRecruitment wkCrmRecruitment)
    {
        return wkCrmRecruitmentMapper.updateWkCrmRecruitment(wkCrmRecruitment);
    }

    /**
     * 删除招聘职位对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWkCrmRecruitmentByIds(String ids)
    {
        return wkCrmRecruitmentMapper.deleteWkCrmRecruitmentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除招聘职位信息
     * 
     * @param id 招聘职位ID
     * @return 结果
     */
    @Override
    public int deleteWkCrmRecruitmentById(Long id)
    {
        return wkCrmRecruitmentMapper.deleteWkCrmRecruitmentById(id);
    }
}
