package com.ruoyi.fq.mapper;

import com.ruoyi.fq.domain.FqPackage;

import java.util.List;

/**
 * 封铅袋Mapper接口
 *
 * @author mario
 * @date 2020-07-07
 */
public interface FqPackageMapper
{
    /**
     * 查询封铅袋
     *
     * @param id 封铅袋ID
     * @return 封铅袋
     */
    public FqPackage selectFqPackageById(Long id);

    /**
     * 查询封铅袋列表
     *
     * @param fqPackage 封铅袋
     * @return 封铅袋集合
     */
    public List<FqPackage> selectFqPackageList(FqPackage fqPackage);

    /**
     * 新增封铅袋
     *
     * @param fqPackage 封铅袋
     * @return 结果
     */
    public int insertFqPackage(FqPackage fqPackage);

    /**
     * 修改封铅袋
     *
     * @param fqPackage 封铅袋
     * @return 结果
     */
    public int updateFqPackage(FqPackage fqPackage);

    /**
     * 删除封铅袋
     *
     * @param id 封铅袋ID
     * @return 结果
     */
    public int deleteFqPackageById(Long id);

    /**
     * 批量删除封铅袋
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteFqPackageByIds(String[] ids);
}

