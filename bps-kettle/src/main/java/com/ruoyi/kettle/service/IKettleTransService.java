package com.ruoyi.kettle.service;

import java.util.List;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.kettle.domain.KettleTrans;

/**
 * 转换Service接口
 * 
 * @author kone
 * @date 2021-07-14
 */
public interface IKettleTransService 
{
    /**
     * 查询转换
     * 
     * @param id 转换ID
     * @return 转换
     */
    public KettleTrans selectKettleTransById(Long id);

    /**
     * 查询转换列表
     * 
     * @param kettleTrans 转换
     * @return 转换集合
     */
    public List<KettleTrans> selectKettleTransList(KettleTrans kettleTrans);

    /**
     * 新增转换
     * 
     * @param kettleTrans 转换
     * @return 结果
     */
    public AjaxResult insertKettleTrans(KettleTrans kettleTrans);

    /**
     * 修改转换
     * 
     * @param kettleTrans 转换
     * @return 结果
     */
    public int updateKettleTrans(KettleTrans kettleTrans);

    /**
     * 批量删除转换
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKettleTransByIds(String ids);

    /**
     * 删除转换信息
     * 
     * @param id 转换ID
     * @return 结果
     */
    public int deleteKettleTransById(Long id);
    /**
     * @Description:立即执行一次转换
     * @Author: Kone.wang
     * @Date: 2021/7/15 14:31
     * @param trans :
     * @return: void
     **/
    AjaxResult run(KettleTrans trans);

    List<String> queryTransLog(KettleTrans trans)   ;
    /**
     * @Description:设置定时执行转换
     * @Author: Kone.wang
     * @Date: 2021/7/21 14:59
     * @param id:
     * @param transName:
     * @return: com.ruoyi.common.core.domain.AjaxResult
     **/
    public  AjaxResult runTransQuartz(String id,String transName);

    Long checkQuartzExist(String checkStr);
}
