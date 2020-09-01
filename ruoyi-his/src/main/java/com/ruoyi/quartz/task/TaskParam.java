package com.ruoyi.quartz.task;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "定时任务参数")
public class TaskParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "机构编码")
    private String orgCode;

    @ApiModelProperty(value = "日期天数")
    private Integer days;
}