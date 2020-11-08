package com.ruoyi.front.mapper;

import java.util.List;
import com.ruoyi.front.domain.CommonProblem;

/**
 * 常见问题Mapper接口
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
public interface CommonProblemMapper 
{
    /**
     * 查询常见问题
     * 
     * @param id 常见问题ID
     * @return 常见问题
     */
    public CommonProblem selectCommonProblemById(Long id);

    /**
     * 查询常见问题列表
     * 
     * @param commonProblem 常见问题
     * @return 常见问题集合
     */
    public List<CommonProblem> selectCommonProblemList(CommonProblem commonProblem);

    /**
     * 新增常见问题
     * 
     * @param commonProblem 常见问题
     * @return 结果
     */
    public int insertCommonProblem(CommonProblem commonProblem);

    /**
     * 修改常见问题
     * 
     * @param commonProblem 常见问题
     * @return 结果
     */
    public int updateCommonProblem(CommonProblem commonProblem);

    /**
     * 删除常见问题
     * 
     * @param id 常见问题ID
     * @return 结果
     */
    public int deleteCommonProblemById(Long id);

    /**
     * 批量删除常见问题
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCommonProblemByIds(String[] ids);
}
