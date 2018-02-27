package com.ruoyi.framework.page;

import java.util.Map;
import com.ruoyi.common.utils.StringUtils;

/**
 * 表格请求参数封装
 * 
 * @author yangzz
 */
public class PageUtilEntity
{
    // 总页数
    private int totalPage;
    // 当前记录起始索引
    private int page;
    // 每页显示记录数
    private int size;
    // 排序列
    private String orderByColumn;
    // 排序的方向 "desc" 或者 "asc".
    private String isAsc;
    // 排序
    protected String orderCond;
    // true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
    private boolean entityOrField;
    // 总记录数
    private int totalResult;
    // 请求参数
    protected Map<String, String> relationMap;

    public int getTotalPage()
    {
        return totalPage;
    }

    public void setTotalPage(int totalPage)
    {
        this.totalPage = totalPage;
    }

    public int getPage()
    {
        return page;
    }

    public void setPage(int page)
    {
        this.page = page;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    public String getOrderByColumn()
    {
        return orderByColumn;
    }

    public void setOrderByColumn(String orderByColumn)
    {
        this.orderByColumn = orderByColumn;
    }

    public String getIsAsc()
    {
        return isAsc;
    }

    public void setIsAsc(String isAsc)
    {
        this.isAsc = isAsc;
    }

    public boolean isEntityOrField()
    {
        return entityOrField;
    }

    public void setEntityOrField(boolean entityOrField)
    {
        this.entityOrField = entityOrField;
    }

    public int getTotalResult()
    {
        return totalResult;
    }

    public void setTotalResult(int totalResult)
    {
        this.totalResult = totalResult;
    }

    public String getOrderCond()
    {
        String orderCond = "";
        if (StringUtils.isNotNull(orderByColumn) && StringUtils.isNotNull(isAsc))
        {
            orderCond = "ORDER BY " + this.orderByColumn + " " + this.isAsc;
        }
        return orderCond;
    }

    public void setOrderCond(String orderCond)
    {
        this.orderCond = orderCond;
    }

    public Map<String, String> getRelationMap()
    {
        return relationMap;
    }

    public void setRelationMap(Map<String, String> relationMap)
    {
        this.relationMap = relationMap;
    }

}
