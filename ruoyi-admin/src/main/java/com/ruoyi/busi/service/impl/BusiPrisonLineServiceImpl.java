package com.ruoyi.busi.service.impl;

import java.util.List;
import java.util.ArrayList;

import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.busi.mapper.BusiPrisonLineMapper;
import com.ruoyi.busi.domain.BusiPrisonLine;
import com.ruoyi.busi.service.IBusiPrisonLineService;
import com.ruoyi.common.core.text.Convert;

/**
 * 监区产线Service业务层处理
 *
 * @author WangCL
 * @date 2021-12-17
 */
@Service
public class BusiPrisonLineServiceImpl implements IBusiPrisonLineService {
    @Autowired
    private BusiPrisonLineMapper busiPrisonLineMapper;

    /**
     * 查询监区产线
     *
     * @param id 监区产线主键
     * @return 监区产线
     */
    @Override
    public BusiPrisonLine selectBusiPrisonLineById(Long id) {
        return busiPrisonLineMapper.selectBusiPrisonLineById(id);
    }

    /**
     * 查询监区产线列表
     *
     * @param busiPrisonLine 监区产线
     * @return 监区产线
     */
    @Override
    public List<BusiPrisonLine> selectBusiPrisonLineList(BusiPrisonLine busiPrisonLine) {
        return busiPrisonLineMapper.selectBusiPrisonLineList(busiPrisonLine);
    }

    /**
     * 新增监区产线
     *
     * @param busiPrisonLine 监区产线
     * @return 结果
     */
    @Override
    public int insertBusiPrisonLine(BusiPrisonLine busiPrisonLine) {
        busiPrisonLine.setCreateTime(DateUtils.getNowDate());
        return busiPrisonLineMapper.insertBusiPrisonLine(busiPrisonLine);
    }

    /**
     * 修改监区产线
     *
     * @param busiPrisonLine 监区产线
     * @return 结果
     */
    @Override
    public int updateBusiPrisonLine(BusiPrisonLine busiPrisonLine) {
        busiPrisonLine.setUpdateTime(DateUtils.getNowDate());
        return busiPrisonLineMapper.updateBusiPrisonLine(busiPrisonLine);
    }

    /**
     * 批量删除监区产线
     *
     * @param ids 需要删除的监区产线主键
     * @return 结果
     */
    @Override
    public int deleteBusiPrisonLineByIds(String ids) {
        return busiPrisonLineMapper.deleteBusiPrisonLineByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除监区产线信息
     *
     * @param id 监区产线主键
     * @return 结果
     */
    @Override
    public int deleteBusiPrisonLineById(Long id) {
        return busiPrisonLineMapper.deleteBusiPrisonLineById(id);
    }

    /**
     * 查询监区产线树列表
     *
     * @return 所有监区产线信息
     */
    @Override
    public List<Ztree> selectBusiPrisonLineTree(String JCOnly, String status) {
        BusiPrisonLine query = new BusiPrisonLine();
        query.setStatus(status);
        List<BusiPrisonLine> busiPrisonLineList = busiPrisonLineMapper.selectBusiPrisonLineList(query);
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (BusiPrisonLine busiPrisonLine : busiPrisonLineList) {
            // 只查监区，只查查监区时过滤掉产线
            if ("yes".equals(JCOnly) && "C".equals(busiPrisonLine.getClassify())) {
                continue;
            }
            Ztree ztree = new Ztree();
            ztree.setId(busiPrisonLine.getId());
            ztree.setpId(busiPrisonLine.getPid());
            ztree.setName(busiPrisonLine.getDisname());
            ztree.setTitle(busiPrisonLine.getDisname());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
