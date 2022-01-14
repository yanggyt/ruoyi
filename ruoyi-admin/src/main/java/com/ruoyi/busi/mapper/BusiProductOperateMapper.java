package com.ruoyi.busi.mapper;

import java.util.List;
import java.util.Map;

import com.ruoyi.busi.domain.BusiProductOperate;

/**
 * 成品操作流水Mapper接口
 * 
 * @author WangCL
 * @date 2022-01-08
 */
public interface BusiProductOperateMapper 
{
    /**
     * 查询成品操作流水
     * 
     * @param id 成品操作流水主键
     * @return 成品操作流水
     */
    public BusiProductOperate selectBusiProductOperateById(String id);

    /**
     * 查询成品操作流水列表
     * 
     * @param busiProductOperate 成品操作流水
     * @return 成品操作流水集合
     */
    public List<BusiProductOperate> selectBusiProductOperateList(BusiProductOperate busiProductOperate);

    /**
     * 新增成品操作流水
     * 
     * @param busiProductOperate 成品操作流水
     * @return 结果
     */
    public int insertBusiProductOperate(BusiProductOperate busiProductOperate);

    /**
     * 修改成品操作流水
     * 
     * @param busiProductOperate 成品操作流水
     * @return 结果
     */
    public int updateBusiProductOperate(BusiProductOperate busiProductOperate);

    /**
     * 删除成品操作流水
     * 
     * @param id 成品操作流水主键
     * @return 结果
     */
    public int deleteBusiProductOperateById(String id);

    /**
     * 批量删除成品操作流水
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteBusiProductOperateByIds(String[] ids);

    public List<Map<String, String>> selProductSizeByLineId(String lineId);

    public List<Map<String, String>> selProductColorByLineIdAndSize(Map<String, String> map);

    public List<Map<String, String>> selProductSizeByOrderId(String lineId);

    public List<Map<String, String>> selProductColorByOrderIdAndSize(Map<String, String> map);


}
