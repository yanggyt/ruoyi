package com.ruoyi.store.domain;
import lombok.Data;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import java.util.Date;

/**
 * 门店管理表 v_store
 * 
 * @author Enzo
 * @date 2019-03-25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "v_store")
public class VStore extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	

	/**字段描述  ID */
	@Id
	@Column(name = "store_id")
	private Long storeId;

	/**字段描述  门店编号 */
	@Column(name = "store_code")
	private String storeCode;

	/**字段描述  门店名称 */
	@Column(name = "store_name")
	private String storeName;

	/**字段描述  门店联系人 */
	@Column(name = "store_people")
	private String storePeople;

	/**字段描述  门店 联系电话 */
	@Column(name = "store_tel")
	private String storeTel;

	/**字段描述  注册的邮箱 */
	@Column(name = "store_email")
	private String storeEmail;

	/**字段描述  门店LOGO */
	@Column(name = "store_logo")
	private String storeLogo;

	/**字段描述  门店地址 */
	@Column(name = "store_address")
	private String storeAddress;

	/**字段描述  门店状态 */
	@Column(name = "store_status")
	private Integer storeStatus;

	/**字段描述  创建时间 */
	@Column(name = "ctime")
	private Date ctime;

	/**字段描述  门店到期时间 */
	@Column(name = "stime")
	private Date stime;




}
