package cn.com.infosouth.arj21.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.infosouth.arj21.mapper.InfoAcTypeMapper;
import cn.com.infosouth.arj21.domain.InfoAcType;
import cn.com.infosouth.arj21.service.IInfoAcTypeService;
import cn.com.infosouth.common.core.text.Convert;

/**
 * 机型Service业务层处理
 * 
 * @author kxnf
 * @date 2020-03-05
 */
@Service
public class InfoAcTypeServiceImpl implements IInfoAcTypeService 
{
    @Autowired
    private InfoAcTypeMapper infoAcTypeMapper;

    /**
     * 查询机型
     * 
     * @param id 机型ID
     * @return 机型
     */
    @Override
    public InfoAcType selectInfoAcTypeById(String id)
    {
        return infoAcTypeMapper.selectInfoAcTypeById(id);
    }

    /**
     * 查询机型列表
     * 
     * @param infoAcType 机型
     * @return 机型
     */
    @Override
    public List<InfoAcType> selectInfoAcTypeList(InfoAcType infoAcType)
    {
        return infoAcTypeMapper.selectInfoAcTypeList(infoAcType);
    }

    /**
     * 新增机型
     * 
     * @param infoAcType 机型
     * @return 结果
     */
    @Override
    public int insertInfoAcType(InfoAcType infoAcType)
    {
        return infoAcTypeMapper.insertInfoAcType(infoAcType);
    }

    /**
     * 修改机型
     * 
     * @param infoAcType 机型
     * @return 结果
     */
    @Override
    public int updateInfoAcType(InfoAcType infoAcType)
    {
        return infoAcTypeMapper.updateInfoAcType(infoAcType);
    }

    /**
     * 删除机型对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteInfoAcTypeByIds(String ids)
    {
        return infoAcTypeMapper.deleteInfoAcTypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除机型信息
     * 
     * @param id 机型ID
     * @return 结果
     */
    @Override
    public int deleteInfoAcTypeById(String id)
    {
        return infoAcTypeMapper.deleteInfoAcTypeById(id);
    }
}
