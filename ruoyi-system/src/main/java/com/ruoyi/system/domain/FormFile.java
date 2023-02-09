package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 表单文件对象
 *
 * @author SKaiL
 * @date 2022/9/29
 */
@Data
public class FormFile extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 相关联主键id */
    @Excel(name = "相关联主键id")
    private Long parentId;

    /** 文件类型 */
    @Excel(name = "文件类型")
    private Integer fileType;

    /** 文件路径 */
    @Excel(name = "文件路径")
    private String filePath;

    /** 文件名 */
    @Excel(name = "文件名")
    private String fileName;

    /** APP接收的文件路径 */
    private String file;

    public FormFile()
    {
    }

    public FormFile(Long parentId, Integer fileType)
    {
        this.parentId = parentId;
        this.fileType = fileType;
    }
}
