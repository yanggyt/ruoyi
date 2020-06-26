package com.ruoyi.businessfile.service;

import java.util.List;
import com.ruoyi.businessfile.domain.BusinessFile;

/**
 * 文件资源Service接口
 * 
 * @author anjie
 * @date 2020-06-01
 */
public interface IBusinessFileService 
{
    /**
     * 查询文件资源
     * 
     * @param id 文件资源ID
     * @return 文件资源
     */
    public BusinessFile selectBusinessFileById(Long id);

    /**
     * 查询文件资源列表
     * 
     * @param businessFile 文件资源
     * @return 文件资源集合
     */
    public List<BusinessFile> selectBusinessFileList(BusinessFile businessFile);

    /**
     * 新增文件资源
     * 
     * @param businessFile 文件资源
     * @return 结果
     */
    public int insertBusinessFile(BusinessFile businessFile);

    /**
     * 修改文件资源
     * 
     * @param businessFile 文件资源
     * @return 结果
     */
    public int updateBusinessFile(BusinessFile businessFile);

    /**
     * 批量删除文件资源
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBusinessFileByIds(String ids);

    /**
     * 删除文件资源信息
     * 
     * @param id 文件资源ID
     * @return 结果
     */
    public int deleteBusinessFileById(Long id);
}
