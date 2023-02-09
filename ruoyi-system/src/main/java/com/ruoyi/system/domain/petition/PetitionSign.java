package com.ruoyi.system.domain.petition;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 核准人员对象 sys_petition_sign
 *
 * @author SKaiL
 * @date 2022-09-26
 */
@Data
public class PetitionSign extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/**  */
	private Long id;

	/** 关联id */
	@Excel(name = "关联id")
	private Long petitionId;

	/** 签核角色 1=核审人，2=会审人，3=加签 */
	@Excel(name = "签核角色")
	private Integer signType;

	/** 签核人员 */
	@Excel(name = "签核人员")
	private String addName;

	/** 工号 */
	@Excel(name = "工号")
	private String sid;

	/** 审核结果 */
	@Excel(name = "审核结果")
	private Integer result;

	/** 签核时间 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	@Excel(name = "签核时间", width = 30, dateFormat = "yyyy-MM-dd")
	private Date addTime;

	/** 发送到 */
	@Excel(name = "发送到")
	private String tosend;
	

	/** 发送者 */
	@Excel(name = "发送者")
	private String fromsend;

	/** 老状态 */
	@Excel(name = "老状态")
	private Integer oldStatus;

	/**  */
	@Excel(name = "")
	private Long newStatus;

	/** 1 是最后一个后加签人员 */
	@Excel(name = "1 是最后一个后加签人员")
	private Integer lastAdd;

	/** 1是删除不会显示 */
	private Long delFlag;

}
