package com.ruoyi.store.domain;
import lombok.Data;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

/**
 * 商品分类表 v_good_item
 * 
 * @author Enzo
 * @date 2019-03-25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "v_good_item")
public class VGoodItem extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	

	/**字段描述  主键ID */
	@Id
	@Column(name = "id")
	private Long id;

	/**字段描述  类别名称 */
	@Column(name = "item_name")
	private String itemName;

	/**字段描述  分类描述 */
	@Column(name = "item_desc")
	private String itemDesc;

	/**字段描述  创建时间 */
	@Column(name = "ctime")
	private Date ctime;

	/**字段描述  门店ID */
	@Column(name = "m_storeid")
	private Long mStoreid;

	/**字段描述  排序 */
	@Column(name = "sore")
	private Integer sore;




}
