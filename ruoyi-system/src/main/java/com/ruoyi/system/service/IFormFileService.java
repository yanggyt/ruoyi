package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.FormFile;

/**
 * 表单文件Service接口
 *
 * @author SKaiL
 * @date 2022-09-29
 */
public interface IFormFileService
{
    /**
     * 查询表单文件
     *
     * @param id 表单文件主键
     * @return 表单文件
     */
    public FormFile selectFormFileById(Long id);

    /**
     * 查询表单文件列表
     *
     * @param formFile 表单文件
     * @return 表单文件集合
     */
    public List<FormFile> selectFormFileList(FormFile formFile);

    /**
     * 新增表单文件
     *
     * @param formFile 表单文件
     * @return 结果
     */
    public int insertFormFile(FormFile formFile);

    /**
     * 修改表单文件
     *
     * @param formFile 表单文件
     * @return 结果
     */
    public int updateFormFile(FormFile formFile);

    /**
     * 批量删除表单文件
     *
     * @param ids 需要删除的表单文件主键集合
     * @return 结果
     */
    public int deleteFormFileByIds(String ids);

    /**
     * 删除表单文件信息
     *
     * @param id 表单文件主键
     * @return 结果
     */
    public int deleteFormFileById(Long id);

    /**
     * 批量新增表单文件
     * @param fileList 表单文件
     * @return 结果
     */
    public int batchFormFile(List<FormFile> fileList);
}
