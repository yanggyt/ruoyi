package com.ruoyi.common.core.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * Entity基类
 * 
 * @author ruoyi
 */
@ApiModel(value = "Entity基类")
@Data
public class BaseEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(notes = "主键ID")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 搜索值 */
    @ApiModelProperty(notes = "搜索值",hidden = true)
    @Transient
    private String searchValue;

    /** 创建者 */
    @ApiModelProperty(notes = "创建者",hidden = true)
    @Transient
    private String createBy;

    /** 创建时间 */
    @ApiModelProperty(notes = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /** 更新者 */
    @ApiModelProperty(notes = "更新者",hidden = true)
    @Transient
    private String updateBy;

    /** 更新时间 */
    @ApiModelProperty(notes = "更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Transient
    private Date updateTime;

    /** 备注 */
    @ApiModelProperty(notes = "备注",hidden = true)
    @Transient
    private String remark;

    /** 请求参数 */
    @ApiModelProperty(notes = "请求参数",hidden = true)
    @Transient
    private Map<String, Object> params;

    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }
}
