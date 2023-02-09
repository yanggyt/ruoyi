package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.FormFileMapper;
import com.ruoyi.system.domain.FormFile;
import com.ruoyi.system.service.IFormFileService;
import com.ruoyi.common.core.text.Convert;

/**
 * 表单文件Service业务层处理
 *
 * @author SKaiL
 * @date 2022-09-29
 */
@Service
public class FormFileServiceImpl implements IFormFileService
{
    @Autowired
    private FormFileMapper formFileMapper;

    /**
     * 查询表单文件
     *
     * @param id 表单文件主键
     * @return 表单文件
     */
    @Override
    public FormFile selectFormFileById(Long id)
    {
        return formFileMapper.selectFormFileById(id);
    }

    /**
     * 查询表单文件列表
     *
     * @param formFile 表单文件
     * @return 表单文件
     */
    @Override
    public List<FormFile> selectFormFileList(FormFile formFile)
    {
        return formFileMapper.selectFormFileList(formFile);
    }

    /**
     * 新增表单文件
     *
     * @param formFile 表单文件
     * @return 结果
     */
    @Override
    public int insertFormFile(FormFile formFile)
    {
        formFile.setCreateTime(DateUtils.getNowDate());
        return formFileMapper.insertFormFile(formFile);
    }

    /**
     * 修改表单文件
     *
     * @param formFile 表单文件
     * @return 结果
     */
    @Override
    public int updateFormFile(FormFile formFile)
    {
        return formFileMapper.updateFormFile(formFile);
    }

    /**
     * 批量删除表单文件
     *
     * @param ids 需要删除的表单文件主键
     * @return 结果
     */
    @Override
    public int deleteFormFileByIds(String ids)
    {
        return formFileMapper.deleteFormFileByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除表单文件信息
     *
     * @param id 表单文件主键
     * @return 结果
     */
    @Override
    public int deleteFormFileById(Long id)
    {
        return formFileMapper.deleteFormFileById(id);
    }

    /**
     * 批量新增表单文件
     * @param fileList 表单文件
     * @return 结果
     */
    @Override
    public int batchFormFile(List<FormFile> fileList){
        return formFileMapper.batchFormFile(fileList);
    }
}
