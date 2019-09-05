package com.ruoyi.common.utils.tree;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ruoyi.common.base.BaseBean;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * tree结构
 *
 * @author solo
 * @date 2019/09/05.
 */
public class Ztree extends BaseBean {
    private String id;
    private String text;
    private String state;
    private boolean isParent;
    private boolean checked;;
    private int locked;
    private List<Ztree> children;
    private String iconCls;
    private String pid;
    private Map<String, Object> attributes = new HashMap<String, Object>();
    private String comment;
    private String link;
    private String url;
    private String target;
    private Integer types;
    private String code;
    private String path;
    private Date createtime;
    private Date updatetime;
    private String object_class;
    private String title;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @JsonProperty(value = "isParent")
    public boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(boolean isParent) {
        this.isParent = isParent;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public List<Ztree> getChildren() {
        return children;
    }

    public void setChildren(List<Ztree> children) {
        this.children = children;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getObject_class() {
        return object_class;
    }

    public void setObject_class(String object_class) {
        this.object_class = object_class;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
