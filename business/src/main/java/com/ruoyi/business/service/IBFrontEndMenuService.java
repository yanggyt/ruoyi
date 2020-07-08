package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.BFrontEndMenu;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 前端菜单Service接口
 * 
 * @author anjie
 * @date 2020-06-24
 */
public interface IBFrontEndMenuService 
{
    /**
     * 查询前端菜单
     * 
     * @param id 前端菜单ID
     * @return 前端菜单
     */
    public BFrontEndMenu selectBFrontEndMenuById(Long id);

    /**
     * 查询前端菜单列表
     * 
     * @param bFrontEndMenu 前端菜单
     * @return 前端菜单集合
     */
    public List<BFrontEndMenu> selectBFrontEndMenuList(BFrontEndMenu bFrontEndMenu);

    /**
     * 新增前端菜单
     * 
     * @param bFrontEndMenu 前端菜单
     * @return 结果
     */
    public int insertBFrontEndMenu(BFrontEndMenu bFrontEndMenu);

    /**
     * 修改前端菜单
     * 
     * @param bFrontEndMenu 前端菜单
     * @return 结果
     */
    public int updateBFrontEndMenu(BFrontEndMenu bFrontEndMenu);

    /**
     * 批量删除前端菜单
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBFrontEndMenuByIds(String ids);

    /**
     * 删除前端菜单信息
     * 
     * @param id 前端菜单ID
     * @return 结果
     */
    public int deleteBFrontEndMenuById(Long id);

    /**
     * 查询前端菜单树列表
     * 
     * @return 所有前端菜单信息
     */
    public List<Ztree> selectBFrontEndMenuTree();
}
