package cn.com.infosouth.arj21.mapper;

import java.util.List;
import cn.com.infosouth.arj21.domain.InfoResource;

/**
 * 资源目录Mapper接口
 * 
 * @author kxnf
 * @date 2020-03-04
 */
public interface InfoResourceMapper 
{
    /**
     * 查询资源目录
     * 
     * @param id 资源目录ID
     * @return 资源目录
     */
    public InfoResource selectInfoResourceById(String id);

    /**
     * 查询资源目录列表
     * 
     * @param infoResource 资源目录
     * @return 资源目录集合
     */
    public List<InfoResource> selectInfoResourceList(InfoResource infoResource);

    /**
     * 新增资源目录
     * 
     * @param infoResource 资源目录
     * @return 结果
     */
    public int insertInfoResource(InfoResource infoResource);

    /**
     * 修改资源目录
     * 
     * @param infoResource 资源目录
     * @return 结果
     */
    public int updateInfoResource(InfoResource infoResource);

    /**
     * 删除资源目录
     * 
     * @param id 资源目录ID
     * @return 结果
     */
    public int deleteInfoResourceById(String id);

    /**
     * 批量删除资源目录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteInfoResourceByIds(String[] ids);
}
