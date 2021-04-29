package com.ruoyi.product.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.Date;

/**
 * product对象 cbfa_aduit_message
 *
 * @author ruoyi
 * @date 2021-04-29
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class AduitMessage
{
    private static final long serialVersionUID = 1L;

    private Long id;

    //提交预审的id
    private Long applyUserId;

    //用户id
    private Long userId;

    //成功信息
    private String success;

    //失败的信息
    private String loser;

    //是否成功 0否 1成
    private String flag;

    //创建的时间
    private Date createTime;

    //修改的时间
    private Date updateTime;



}
