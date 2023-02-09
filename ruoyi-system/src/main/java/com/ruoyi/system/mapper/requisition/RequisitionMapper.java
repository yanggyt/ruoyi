package com.ruoyi.system.mapper.requisition;

import com.ruoyi.system.domain.requisition.Requisition;
import com.ruoyi.system.domain.requisition.RequisitionDt1;
import com.ruoyi.system.domain.requisition.RequisitionDt2;

import java.util.List;

/**
 * 请购单Mapper接口
 * 
 * @author SKaiL
 * @date 2022-09-26
 */
public interface RequisitionMapper 
{
    /**
     * 查询请购单
     * 
     * @param id 请购单主键
     * @return 请购单
     */
    public Requisition selectRequisitionById(Long id);

    /**
     * 查询请购单列表
     * 
     * @param requisition 请购单
     * @return 请购单集合
     */
    public List<Requisition> selectRequisitionList(Requisition requisition);

    /**
     * 批量查询请购单列表
     * @param requisition 请购单
     * @return 结果
     */
    public List<Requisition> selectRequisitionListByStatus(Requisition requisition);

    /**
     * 查询请购单产品明细dt1列表
     *
     * @param requisitionDt1 请购单产品明细dt1
     * @return 请购单产品明细dt1集合
     */
    public List<RequisitionDt1> selectRequisitionDt1List(RequisitionDt1 requisitionDt1);

    /**
     * 查询请购单供应商明显dt2信息
     *
     * @param requisitionDt2 请购单供应商明显dt2信息
     * @return 请购单供应商明显dt2信息集合
     */
    public List<RequisitionDt2> selectRequisitionDt2List(RequisitionDt2 requisitionDt2);

    /**
     * 新增请购单
     * 
     * @param requisition 请购单
     * @return 结果
     */
    public int insertRequisition(Requisition requisition);

    /**
     * 修改请购单
     * 
     * @param requisition 请购单
     * @return 结果
     */
    public int updateRequisition(Requisition requisition);

    /**
     * 删除请购单
     * 
     * @param id 请购单主键
     * @return 结果
     */
    public int deleteRequisitionById(Long id);

    /**
     * 批量删除请购单
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRequisitionByIds(String[] ids);

    /**
     * 批量删除请购单产品明细dt1
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRequisitionDt1ByRequisitionIds(String[] ids);

    /**
     * 批量删除请购单供应商明显dt2
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRequisitionDt2ByRequisitionIds(String[] ids);

    /**
     * 通过请购单主键删除请购单产品明细dt1信息
     *
     * @param id 请购单ID
     * @return 结果
     */
    public int deleteRequisitionDt1ByRequisitionId(Long id);


    /**
     * 通过请购单主键删除请购单供应商明显dt2信息
     *
     * @param id 请购单ID
     * @return 结果
     */
    public int deleteRequisitionDt2ByRequisitionId(Long id);

    /**
     * 批量新增请购单产品明细dt1
     *
     * @param requisitionDt1List 请购单产品明细dt1列表
     * @return 结果
     */
    public int batchRequisitionDt1(List<RequisitionDt1> requisitionDt1List);

    /**
     * 批量新增请购单供应商明显dt2信息
     *
     * @param requisitionDt2List 请购单供应商明显dt2信息
     * @return 结果
     */
    public Integer batchRequisitionDt2(List<RequisitionDt2> requisitionDt2List);

    /**
     * 查询当前编号的最后一天记录
     * @param args 编号
     * @return 结果
     */
    public List<Requisition> selectTheLastLine(String args);
}
