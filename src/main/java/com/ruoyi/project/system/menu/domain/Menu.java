package com.ruoyi.project.system.menu.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色对象 sys_menu
 * 
 * @author yangzz
 */
public class Menu implements Serializable
{
    private static final long serialVersionUID = 1L;
    // 菜单ID
    private Integer menuId;
    // 菜单名称
    private String menuName;
    // 父菜单ID
    private Integer parentId;
    // 显示顺序
    private String orderNum;
    // 菜单URL
    private String url;
    // 类型:0目录,1菜单,2按钮
    private String menuType;
    // 菜单状态:0显示,1隐藏
    private String visible;
    // 权限字符串
    private String perms;
    // 菜单图标
    private String icon;
    // 创建时间
    private String createTime;
    // 更新时间
    private String updateTime;
    // 更新者
    private String updateBy;
    // 备注
    private String remark;
    // 子菜单
    private List<Menu> children = new ArrayList<Menu>();

    public Integer getMenuId()
    {
        return menuId;
    }

    public void setMenuId(Integer menuId)
    {
        this.menuId = menuId;
    }

    public String getMenuName()
    {
        return menuName;
    }

    public void setMenuName(String menuName)
    {
        this.menuName = menuName;
    }

    public Integer getParentId()
    {
        return parentId;
    }

    public void setParentId(Integer parentId)
    {
        this.parentId = parentId;
    }

    public String getOrderNum()
    {
        return orderNum;
    }

    public void setOrderNum(String orderNum)
    {
        this.orderNum = orderNum;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getMenuType()
    {
        return menuType;
    }

    public void setMenuType(String menuType)
    {
        this.menuType = menuType;
    }

    public String getVisible()
    {
        return visible;
    }

    public void setVisible(String visible)
    {
        this.visible = visible;
    }

    public String getPerms()
    {
        return perms;
    }

    public void setPerms(String perms)
    {
        this.perms = perms;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }

    public String getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(String createTime)
    {
        this.createTime = createTime;
    }

    public String getUpdateTime()
    {
        return updateTime;
    }

    public void setUpdateTime(String updateTime)
    {
        this.updateTime = updateTime;
    }

    public String getUpdateBy()
    {
        return updateBy;
    }

    public void setUpdateBy(String updateBy)
    {
        this.updateBy = updateBy;
    }

    public String getRemark()
    {
        return remark;
    }

    public void setRemark(String remark)
    {
        this.remark = remark;
    }

    public List<Menu> getChildren()
    {
        return children;
    }

    public void setChildren(List<Menu> children)
    {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuName=" + menuName +
                ", parentId='" + parentId + '\'' +
                ", orderNum='" + orderNum + '\'' +
                ", url='" + url + '\'' +
                ", menuType=" + menuType +
                ", visible='" + visible + '\'' +
                ", perms=" + perms +
                ", icon=" + icon +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", updateBy=" + updateBy +
                ", remark=" + remark +
                '}';
    }
}
