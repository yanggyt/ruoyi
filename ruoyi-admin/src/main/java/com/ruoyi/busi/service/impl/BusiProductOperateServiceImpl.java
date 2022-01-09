package com.ruoyi.busi.service.impl;

import java.util.List;
import java.util.Map;

import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.busi.mapper.BusiProductOperateMapper;
import com.ruoyi.busi.domain.BusiProductOperate;
import com.ruoyi.busi.service.IBusiProductOperateService;
import com.ruoyi.common.core.text.Convert;

/**
 * 成品操作流水Service业务层处理
 * 
 * @author WangCL
 * @date 2022-01-08
 */
@Service
public class BusiProductOperateServiceImpl implements IBusiProductOperateService 
{
    @Autowired
    private BusiProductOperateMapper busiProductOperateMapper;

    /**
     * 查询成品操作流水
     * 
     * @param id 成品操作流水主键
     * @return 成品操作流水
     */
    @Override
    public BusiProductOperate selectBusiProductOperateById(String id)
    {
        return busiProductOperateMapper.selectBusiProductOperateById(id);
    }

    /**
     * 查询成品操作流水列表
     * 
     * @param busiProductOperate 成品操作流水
     * @return 成品操作流水
     */
    @Override
    public List<BusiProductOperate> selectBusiProductOperateList(BusiProductOperate busiProductOperate)
    {
        return busiProductOperateMapper.selectBusiProductOperateList(busiProductOperate);
    }

    /**
     * 新增成品操作流水
     * 
     * @param busiProductOperate 成品操作流水
     * @return 结果
     */
    @Override
    public int insertBusiProductOperate(BusiProductOperate busiProductOperate)
    {
        busiProductOperate.setCreateTime(DateUtils.getNowDate());


        return busiProductOperateMapper.insertBusiProductOperate(busiProductOperate);
    }

    /**
     * 修改成品操作流水
     * 
     * @param busiProductOperate 成品操作流水
     * @return 结果
     */
    @Override
    public int updateBusiProductOperate(BusiProductOperate busiProductOperate)
    {
        return busiProductOperateMapper.updateBusiProductOperate(busiProductOperate);
    }

    /**
     * 批量删除成品操作流水
     * 
     * @param ids 需要删除的成品操作流水主键
     * @return 结果
     */
    @Override
    public int deleteBusiProductOperateByIds(String ids)
    {
        return busiProductOperateMapper.deleteBusiProductOperateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除成品操作流水信息
     * 
     * @param id 成品操作流水主键
     * @return 结果
     */
    @Override
    public int deleteBusiProductOperateById(String id)
    {
        return busiProductOperateMapper.deleteBusiProductOperateById(id);
    }

    @Override
    public List<Map<String, String>> selProductSizeByLineId(String lineId) {
        return busiProductOperateMapper.selProductSizeByLineId(lineId);
    }

    @Override
    public List<Map<String, String>> selProductColorByLineIdAndSize(Map<String, String> map) {
        return busiProductOperateMapper.selProductColorByLineIdAndSize(map);
    }
}
