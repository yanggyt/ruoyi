package cn.com.infosouth.arj21.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.infosouth.arj21.mapper.InfoVersionMapper;
import cn.com.infosouth.arj21.domain.InfoVersion;
import cn.com.infosouth.arj21.service.IInfoVersionService;
import cn.com.infosouth.common.core.text.Convert;

/**
 * 参数版本Service业务层处理
 * 
 * @author kxnf
 * @date 2020-03-05
 */
@Service
public class InfoVersionServiceImpl implements IInfoVersionService 
{
    @Autowired
    private InfoVersionMapper infoVersionMapper;

    /**
     * 查询参数版本
     * 
     * @param id 参数版本ID
     * @return 参数版本
     */
    @Override
    public InfoVersion selectInfoVersionById(Long id)
    {
        return infoVersionMapper.selectInfoVersionById(id);
    }

    /**
     * 查询参数版本列表
     * 
     * @param infoVersion 参数版本
     * @return 参数版本
     */
    @Override
    public List<InfoVersion> selectInfoVersionList(InfoVersion infoVersion)
    {
        return infoVersionMapper.selectInfoVersionList(infoVersion);
    }

    /**
     * 新增参数版本
     * 
     * @param infoVersion 参数版本
     * @return 结果
     */
    @Override
    public int insertInfoVersion(InfoVersion infoVersion)
    {
        return infoVersionMapper.insertInfoVersion(infoVersion);
    }

    /**
     * 修改参数版本
     * 
     * @param infoVersion 参数版本
     * @return 结果
     */
    @Override
    public int updateInfoVersion(InfoVersion infoVersion)
    {
        return infoVersionMapper.updateInfoVersion(infoVersion);
    }

    /**
     * 删除参数版本对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteInfoVersionByIds(String ids)
    {
        return infoVersionMapper.deleteInfoVersionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除参数版本信息
     * 
     * @param id 参数版本ID
     * @return 结果
     */
    @Override
    public int deleteInfoVersionById(Long id)
    {
        return infoVersionMapper.deleteInfoVersionById(id);
    }
}
