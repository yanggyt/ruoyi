package com.ruoyi.dfm.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单类
 * @author Kfighter
 *
 */
public class MenuInfo  implements Serializable {
	private static final long serialVersionUID = 1343486203321146073L;
	private int id;
	private String url;
	private String menuName;
	private int menuLevel;
	private int parentId;
	private String icon;
	private String target;


	/** 子菜单 */
	private List<MenuInfo> children = new ArrayList<>();

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public List<MenuInfo> getChildren() {
		return children;
	}

	public void setChildren(List<MenuInfo> children) {
		this.children = children;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
}
