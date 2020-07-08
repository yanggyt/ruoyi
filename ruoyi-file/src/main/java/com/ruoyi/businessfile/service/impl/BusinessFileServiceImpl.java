package com.ruoyi.businessfile.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.businessfile.mapper.BusinessFileMapper;
import com.ruoyi.businessfile.domain.BusinessFile;
import com.ruoyi.businessfile.service.IBusinessFileService;
import com.ruoyi.common.core.text.Convert;

/**
 * 文件资源Service业务层处理
 * 
 * @author anjie
 * @date 2020-06-01
 */
@Service
public class BusinessFileServiceImpl implements IBusinessFileService 
{
    @Autowired
    private BusinessFileMapper businessFileMapper;

    /**
     * 查询文件资源
     * 
     * @param id 文件资源ID
     * @return 文件资源
     */
    @Override
    public BusinessFile selectBusinessFileById(Long id)
    {
        return businessFileMapper.selectBusinessFileById(id);
    }

    /**
     * 查询文件资源列表
     * 
     * @param businessFile 文件资源
     * @return 文件资源
     */
    @Override
    public List<BusinessFile> selectBusinessFileList(BusinessFile businessFile)
    {
        return businessFileMapper.selectBusinessFileList(businessFile);
    }

    /**
     * 新增文件资源
     * 
     * @param businessFile 文件资源
     * @return 结果
     */
    @Override
    public int insertBusinessFile(BusinessFile businessFile)
    {
        businessFile.setCreateTime(DateUtils.getNowDate());
        return businessFileMapper.insertBusinessFile(businessFile);
    }

    /**
     * 修改文件资源
     * 
     * @param businessFile 文件资源
     * @return 结果
     */
    @Override
    public int updateBusinessFile(BusinessFile businessFile)
    {
        return businessFileMapper.updateBusinessFile(businessFile);
    }

    /**
     * 删除文件资源对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBusinessFileByIds(String ids)
    {
        return businessFileMapper.deleteBusinessFileByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除文件资源信息
     * 
     * @param id 文件资源ID
     * @return 结果
     */
    @Override
    public int deleteBusinessFileById(Long id)
    {
        return businessFileMapper.deleteBusinessFileById(id);
    }
}
