package cn.com.infosouth.arj21.service.impl;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cn.com.infosouth.arj21.mapper.InfoResourceMapper;
import cn.com.infosouth.arj21.domain.InfoResource;
import cn.com.infosouth.arj21.service.IInfoResourceService;
import cn.com.infosouth.common.core.domain.Ztree;
import cn.com.infosouth.common.core.text.Convert;

/**
 * 资源目录Service业务层处理
 * 
 * @author kxnf
 * @date 2020-03-04
 */
@Service
public class InfoResourceServiceImpl implements IInfoResourceService 
{
    @Autowired
    private InfoResourceMapper infoResourceMapper;

    /**
     * 查询资源目录
     * 
     * @param id 资源目录ID
     * @return 资源目录
     */
    @Override
    public InfoResource selectInfoResourceById(String id)
    {
        return infoResourceMapper.selectInfoResourceById(id);
    }

    /**
     * 查询资源目录列表
     * 
     * @param infoResource 资源目录
     * @return 资源目录
     */
    @Override
    public List<InfoResource> selectInfoResourceList(InfoResource infoResource)
    {
        return infoResourceMapper.selectInfoResourceList(infoResource);
    }

    /**
     * 新增资源目录
     * 
     * @param infoResource 资源目录
     * @return 结果
     */
    @Override
    public int insertInfoResource(InfoResource infoResource)
    {
        return infoResourceMapper.insertInfoResource(infoResource);
    }

    /**
     * 修改资源目录
     * 
     * @param infoResource 资源目录
     * @return 结果
     */
    @Override
    public int updateInfoResource(InfoResource infoResource)
    {
        return infoResourceMapper.updateInfoResource(infoResource);
    }

    /**
     * 删除资源目录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteInfoResourceByIds(String ids)
    {
        return infoResourceMapper.deleteInfoResourceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除资源目录信息
     * 
     * @param id 资源目录ID
     * @return 结果
     */
    @Override
    public int deleteInfoResourceById(String id)
    {
        return infoResourceMapper.deleteInfoResourceById(id);
    }

    /**
     * 查询资源目录树列表
     * 
     * @return 所有资源目录信息
     */
    @Override
    public List<Ztree> selectInfoResourceTree()
    {
        List<InfoResource> infoResourceList = infoResourceMapper.selectInfoResourceList(new InfoResource());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (InfoResource infoResource : infoResourceList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(infoResource.getId());
            ztree.setpId(infoResource.getParentId());
            ztree.setName(infoResource.getName());
            ztree.setTitle(infoResource.getName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
