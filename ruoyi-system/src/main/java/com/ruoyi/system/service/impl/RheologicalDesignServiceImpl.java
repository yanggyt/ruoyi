package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.RheologicalDesignMapper;
import com.ruoyi.system.domain.RheologicalDesign;
import com.ruoyi.system.service.IRheologicalDesignService;
import com.ruoyi.common.core.text.Convert;

/**
 * 流变学设计数据Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
@Service
public class RheologicalDesignServiceImpl implements IRheologicalDesignService 
{
    @Autowired
    private RheologicalDesignMapper rheologicalDesignMapper;

    /**
     * 查询流变学设计数据
     * 
     * @param rheologicalDesignNumber 流变学设计数据ID
     * @return 流变学设计数据
     */
    @Override
    public RheologicalDesign selectRheologicalDesignById(Long rheologicalDesignNumber)
    {
        return rheologicalDesignMapper.selectRheologicalDesignById(rheologicalDesignNumber);
    }

    /**
     * 查询流变学设计数据列表
     * 
     * @param rheologicalDesign 流变学设计数据
     * @return 流变学设计数据
     */
    @Override
    public List<RheologicalDesign> selectRheologicalDesignList(RheologicalDesign rheologicalDesign)
    {
        return rheologicalDesignMapper.selectRheologicalDesignList(rheologicalDesign);
    }

    /**
     * 新增流变学设计数据
     * 
     * @param rheologicalDesign 流变学设计数据
     * @return 结果
     */
    @Override
    public int insertRheologicalDesign(RheologicalDesign rheologicalDesign)
    {
        return rheologicalDesignMapper.insertRheologicalDesign(rheologicalDesign);
    }

    /**
     * 修改流变学设计数据
     * 
     * @param rheologicalDesign 流变学设计数据
     * @return 结果
     */
    @Override
    public int updateRheologicalDesign(RheologicalDesign rheologicalDesign)
    {
        return rheologicalDesignMapper.updateRheologicalDesign(rheologicalDesign);
    }

    /**
     * 删除流变学设计数据对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteRheologicalDesignByIds(String ids)
    {
        return rheologicalDesignMapper.deleteRheologicalDesignByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除流变学设计数据信息
     * 
     * @param rheologicalDesignNumber 流变学设计数据ID
     * @return 结果
     */
    @Override
    public int deleteRheologicalDesignById(Long rheologicalDesignNumber)
    {
        return rheologicalDesignMapper.deleteRheologicalDesignById(rheologicalDesignNumber);
    }
}
