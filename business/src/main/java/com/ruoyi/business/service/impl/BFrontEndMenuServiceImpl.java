package com.ruoyi.business.service.impl;

import java.util.List;
import java.util.ArrayList;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BFrontEndMenuMapper;
import com.ruoyi.business.domain.BFrontEndMenu;
import com.ruoyi.business.service.IBFrontEndMenuService;
import com.ruoyi.common.core.text.Convert;

/**
 * 前端菜单Service业务层处理
 * 
 * @author anjie
 * @date 2020-06-24
 */
@Service
public class BFrontEndMenuServiceImpl implements IBFrontEndMenuService 
{
    @Autowired
    private BFrontEndMenuMapper bFrontEndMenuMapper;

    /**
     * 查询前端菜单
     * 
     * @param id 前端菜单ID
     * @return 前端菜单
     */
    @Override
    public BFrontEndMenu selectBFrontEndMenuById(Long id)
    {
        return bFrontEndMenuMapper.selectBFrontEndMenuById(id);
    }

    /**
     * 查询前端菜单列表
     * 
     * @param bFrontEndMenu 前端菜单
     * @return 前端菜单
     */
    @Override
    public List<BFrontEndMenu> selectBFrontEndMenuList(BFrontEndMenu bFrontEndMenu)
    {
        return bFrontEndMenuMapper.selectBFrontEndMenuList(bFrontEndMenu);
    }

    /**
     * 新增前端菜单
     * 
     * @param bFrontEndMenu 前端菜单
     * @return 结果
     */
    @Override
    public int insertBFrontEndMenu(BFrontEndMenu bFrontEndMenu)
    {
        bFrontEndMenu.setCreateTime(DateUtils.getNowDate());
        return bFrontEndMenuMapper.insertBFrontEndMenu(bFrontEndMenu);
    }

    /**
     * 修改前端菜单
     * 
     * @param bFrontEndMenu 前端菜单
     * @return 结果
     */
    @Override
    public int updateBFrontEndMenu(BFrontEndMenu bFrontEndMenu)
    {
        bFrontEndMenu.setUpdateTime(DateUtils.getNowDate());
        return bFrontEndMenuMapper.updateBFrontEndMenu(bFrontEndMenu);
    }

    /**
     * 删除前端菜单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBFrontEndMenuByIds(String ids)
    {
        return bFrontEndMenuMapper.deleteBFrontEndMenuByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除前端菜单信息
     * 
     * @param id 前端菜单ID
     * @return 结果
     */
    @Override
    public int deleteBFrontEndMenuById(Long id)
    {
        return bFrontEndMenuMapper.deleteBFrontEndMenuById(id);
    }

    /**
     * 查询前端菜单树列表
     * 
     * @return 所有前端菜单信息
     */
    @Override
    public List<Ztree> selectBFrontEndMenuTree()
    {
        List<BFrontEndMenu> bFrontEndMenuList = bFrontEndMenuMapper.selectBFrontEndMenuList(new BFrontEndMenu());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (BFrontEndMenu bFrontEndMenu : bFrontEndMenuList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(bFrontEndMenu.getId());
            ztree.setpId(bFrontEndMenu.getParentId());
            ztree.setName(bFrontEndMenu.getName());
            ztree.setTitle(bFrontEndMenu.getName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
