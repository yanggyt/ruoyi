package com.ruoyi.front.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.front.mapper.CommonProblemMapper;
import com.ruoyi.front.domain.CommonProblem;
import com.ruoyi.front.service.ICommonProblemService;
import com.ruoyi.common.core.text.Convert;

/**
 * 常见问题Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Service
public class CommonProblemServiceImpl implements ICommonProblemService 
{
    @Autowired
    private CommonProblemMapper commonProblemMapper;

    /**
     * 查询常见问题
     * 
     * @param id 常见问题ID
     * @return 常见问题
     */
    @Override
    public CommonProblem selectCommonProblemById(Long id)
    {
        return commonProblemMapper.selectCommonProblemById(id);
    }

    /**
     * 查询常见问题列表
     * 
     * @param commonProblem 常见问题
     * @return 常见问题
     */
    @Override
    public List<CommonProblem> selectCommonProblemList(CommonProblem commonProblem)
    {
        return commonProblemMapper.selectCommonProblemList(commonProblem);
    }

    /**
     * 新增常见问题
     * 
     * @param commonProblem 常见问题
     * @return 结果
     */
    @Override
    public int insertCommonProblem(CommonProblem commonProblem)
    {
        commonProblem.setCreateTime(DateUtils.getNowDate());
        return commonProblemMapper.insertCommonProblem(commonProblem);
    }

    /**
     * 修改常见问题
     * 
     * @param commonProblem 常见问题
     * @return 结果
     */
    @Override
    public int updateCommonProblem(CommonProblem commonProblem)
    {
        commonProblem.setUpdateTime(DateUtils.getNowDate());
        return commonProblemMapper.updateCommonProblem(commonProblem);
    }

    /**
     * 删除常见问题对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCommonProblemByIds(String ids)
    {
        return commonProblemMapper.deleteCommonProblemByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除常见问题信息
     * 
     * @param id 常见问题ID
     * @return 结果
     */
    @Override
    public int deleteCommonProblemById(Long id)
    {
        return commonProblemMapper.deleteCommonProblemById(id);
    }
}
