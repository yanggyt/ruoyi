package cn.com.infosouth.arj21.mapper;

import java.util.List;
import cn.com.infosouth.arj21.domain.InfoVersion;

/**
 * 参数版本Mapper接口
 * 
 * @author kxnf
 * @date 2020-03-05
 */
public interface InfoVersionMapper 
{
    /**
     * 查询参数版本
     * 
     * @param id 参数版本ID
     * @return 参数版本
     */
    public InfoVersion selectInfoVersionById(Long id);

    /**
     * 查询参数版本列表
     * 
     * @param infoVersion 参数版本
     * @return 参数版本集合
     */
    public List<InfoVersion> selectInfoVersionList(InfoVersion infoVersion);

    /**
     * 新增参数版本
     * 
     * @param infoVersion 参数版本
     * @return 结果
     */
    public int insertInfoVersion(InfoVersion infoVersion);

    /**
     * 修改参数版本
     * 
     * @param infoVersion 参数版本
     * @return 结果
     */
    public int updateInfoVersion(InfoVersion infoVersion);

    /**
     * 删除参数版本
     * 
     * @param id 参数版本ID
     * @return 结果
     */
    public int deleteInfoVersionById(Long id);

    /**
     * 批量删除参数版本
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteInfoVersionByIds(String[] ids);
}
