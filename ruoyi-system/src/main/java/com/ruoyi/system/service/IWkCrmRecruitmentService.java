package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.WkCrmRecruitment;

/**
 * 招聘职位Service接口
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
public interface IWkCrmRecruitmentService 
{
    /**
     * 查询招聘职位
     * 
     * @param id 招聘职位ID
     * @return 招聘职位
     */
    public WkCrmRecruitment selectWkCrmRecruitmentById(Long id);

    /**
     * 查询招聘职位列表
     * 
     * @param wkCrmRecruitment 招聘职位
     * @return 招聘职位集合
     */
    public List<WkCrmRecruitment> selectWkCrmRecruitmentList(WkCrmRecruitment wkCrmRecruitment);

    /**
     * 新增招聘职位
     * 
     * @param wkCrmRecruitment 招聘职位
     * @return 结果
     */
    public int insertWkCrmRecruitment(WkCrmRecruitment wkCrmRecruitment);

    /**
     * 修改招聘职位
     * 
     * @param wkCrmRecruitment 招聘职位
     * @return 结果
     */
    public int updateWkCrmRecruitment(WkCrmRecruitment wkCrmRecruitment);

    /**
     * 批量删除招聘职位
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWkCrmRecruitmentByIds(String ids);

    /**
     * 删除招聘职位信息
     * 
     * @param id 招聘职位ID
     * @return 结果
     */
    public int deleteWkCrmRecruitmentById(Long id);
}
