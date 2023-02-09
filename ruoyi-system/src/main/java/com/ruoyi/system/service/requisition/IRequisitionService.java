package com.ruoyi.system.service.requisition;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.FormFile;
import com.ruoyi.system.domain.requisition.Requisition;
import com.ruoyi.system.domain.requisition.RequisitionAuditResults;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 请购单Service接口
 *
 * @author SKaiL
 * @date 2022-09-26
 */
public interface IRequisitionService
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
     * 我的草稿
     *
     * @param requisition 请购单
     * @return 请购单集合
     */
    public List<Requisition> selectRequisitionListDraft(Requisition requisition);

    /**
     * 新增请购单
     *
     * @param requisition 请购单
     * @param user        操作用户
     * @return 结果
     */
    public Long insertRequisition(Requisition requisition, SysUser user);

    /**
     * 修改请购单
     *
     * @param requisition 请购单
     * @param user        操作用户
     * @return 结果
     */
    public Long updateRequisition(Requisition requisition, SysUser user);

    /**
     * 批量删除请购单
     *
     * @param ids 需要删除的请购单主键集合
     * @return 结果
     */
    public int deleteRequisitionByIds(String ids);

    /**
     * 删除请购单信息
     *
     * @param id 请购单主键
     * @return 结果
     */
    public int deleteRequisitionById(Long id);

    /**
     * 请购单提交
     *
     * @param requisitionId 请购单
     * @param sysUser       操作用户
     * @return 结果
     */
    public Long submit(Long requisitionId, SysUser sysUser);

    /**
     * 查询文件列表
     *
     * @param parentId 父id
     * @param type     类型
     * @return 文件列表
     */
    public List<FormFile> selectFormFile(Long parentId, Integer type);

    /**
     * 通用审核提交
     *
     * @param auditResults 审核结果信息
     * @return
     */
    public int auditSubmit(RequisitionAuditResults auditResults, SysUser sysUser);

    /**
     * 请购单撤回
     *
     * @param id 请购单id
     * @return 结果
     */
    public int withdraw(Long id);

    /**
     * 上传审批截图
     *
     * @param file    审批截图
     * @param id      单子ID
     * @param sysUser 操作用户
     * @return
     */
    public int closeRequisitionFile(MultipartFile file, Long id, SysUser sysUser);

    /**
     * 批量审核
     * @param auditResults 审核结果
     * @param sysUser 操作人
     * @return
     */
    public String requisitionBatchReview(RequisitionAuditResults auditResults, SysUser sysUser);

    /**
     * 使用说明相关文档下载
     *
     * @param type 文档类型(1.使用说明 2.文档模版)
     */
    public void downloadFileDescription(Integer type, HttpServletResponse response);
}
