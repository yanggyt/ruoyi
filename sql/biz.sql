CREATE TABLE `biz_member` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会员ID',
  `mobile` varchar(16) NOT NULL DEFAULT '0' COMMENT '手机号码',
  `member_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户姓名',
  `password` varchar(32) NOT NULL DEFAULT '' COMMENT '用户密码',
  `recommend_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '推荐人ID',
  `recommend_all_id` varchar(512) NOT NULL DEFAULT '' COMMENT '所有上级推荐人ID',
  `recommend_mobile` varchar(32) NOT NULL DEFAULT '' COMMENT '推荐人手机',
  `recommend_name` varchar(32) NOT NULL DEFAULT '' COMMENT '推荐人姓名',
  `member_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '会员类型',
  `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除：0-否，1-是',
  `is_enable` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否禁用：0-否，1-是',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) COMMENT='会员表';

CREATE TABLE `biz_member_address` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会员ID',
  `mobile` varchar(16) NOT NULL DEFAULT '0' COMMENT '手机号码',
  `member_name` varchar(32) NOT NULL DEFAULT '' COMMENT '收货人姓名',
  `address` varchar(64) NOT NULL DEFAULT '' COMMENT '收货人地址',
  `province_code` varchar(16) NOT NULL DEFAULT '' COMMENT '省编码',
  `province_name` varchar(16) NOT NULL DEFAULT '' COMMENT '省名称',
  `city_code` varchar(16) NOT NULL DEFAULT '' COMMENT '市编码',
  `city_name` varchar(16) NOT NULL DEFAULT '' COMMENT '市名称',
  `area_code` varchar(16) NOT NULL DEFAULT '' COMMENT '区编码',
  `area_name` varchar(16) NOT NULL DEFAULT '' COMMENT '区名称',
  `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除：0-否，1-是',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) COMMENT='会员收货地址表';

CREATE TABLE `biz_account` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会员账户ID',
  `account_no` varchar(64) NOT NULL DEFAULT '' COMMENT '会员账户编号',
  `account_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '账户类型：0-福豆余额，1-可用福豆，2-团队福豆，3-专项福豆，4-福豆田',
  `amount` decimal(12,2) NOT NULL DEFAULT 0.0 COMMENT '账户金额',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) COMMENT='会员账户表';

CREATE TABLE `biz_account_detail` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会员账户明细ID',
   `account_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '会员账户ID',
   `account_type_cn` varchar(16) NOT NULL DEFAULT 0 COMMENT '账户类型中文',
   `change_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '变更类型；1:收入(加)；-1:支出(减)',
   `type_detail` tinyint(4) NOT NULL COMMENT '变动详情.1：充值；2:提现；3:转账；',
   `before_amount` decimal(12,2) NOT NULL DEFAULT 0.0 COMMENT '账户变更前金额',
   `after_amount` decimal(12,2) NOT NULL DEFAULT 0.0 COMMENT '账户变更后金额',
   `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
   `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
   `update_time` datetime DEFAULT NULL COMMENT '更新时间',
   PRIMARY KEY (`id`)
) COMMENT='会员账户明细表';

CREATE TABLE `biz_transaction_flow` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '交易流水ID',
  `transaction_flow_no` varchar(64) NOT NULL DEFAULT '' COMMENT '交易流水编号',
  `business_no` varchar(64) NOT NULL DEFAULT '' COMMENT '业务订单编号',
  `from_acc_no` varchar(64) NOT NULL DEFAULT '' COMMENT '付款方账号',
  `from_acc_name` varchar(32) NOT NULL DEFAULT '' COMMENT '付款方名称',
  `to_acc_no` varchar(64) NOT NULL DEFAULT '' COMMENT '收款方账号',
  `to_acc_name` varchar(32) NOT NULL DEFAULT '' COMMENT '收款方名称',
  `transaction_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '交易类型；1：充值；2:提现；3:转账；',
  `transaction_amount` decimal(12,2) NOT NULL DEFAULT 0.0 COMMENT '交易金额',
  `transaction_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '交易状态:0：初始化；1：交易成功；2：交易失败；',
  `transaction_desc` varchar(64) DEFAULT '' COMMENT '交易备注：充值【一级推荐奖励】，充值【二级推荐奖励】，充值【团队奖励】，充值【专项划拨】，转账【专项划拨】',
  `pay_method` varchar(2) NOT NULL DEFAULT '' COMMENT '支付方式:1:余额支付；3:支付宝；4:微信；',
  `tripartite_pay_sn` varchar(128) NOT NULL DEFAULT '' COMMENT '第三方支付业务流水号',
  `tripartite_callback_time` datetime DEFAULT NULL COMMENT '第三方支付业务流水号',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) COMMENT='会员账户明细表';

CREATE TABLE `biz_product_type` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '产品分类ID',
  `product_type_code` varchar(64) NOT NULL DEFAULT '' COMMENT '产品分类编码',
  `product_type_name` varchar(64) NOT NULL DEFAULT '' COMMENT '产品分类名称',
  `image_url` varchar(128) NOT NULL DEFAULT '' COMMENT '附件地址',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `is_enable` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否禁用：0-否，1-是',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) COMMENT='产品分类表';

CREATE TABLE `biz_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `product_code` varchar(64) NOT NULL DEFAULT '' COMMENT '产品编码',
  `product_name` varchar(64) NOT NULL DEFAULT '' COMMENT '产品名称',
  `product_type_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '产品分类ID',
  `product_class` tinyint(4) NOT NULL DEFAULT 0 COMMENT '产品类型：0-销售产品，1-兑换产品',
  `amount` decimal(12,2) NOT NULL DEFAULT 0.0 COMMENT '产品单价',
  `cashback_amount` decimal(12,2) NOT NULL DEFAULT 0.0 COMMENT '返现金额',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `online_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否上架：0-否，1-是',
  `online_time` datetime DEFAULT NULL COMMENT '上架时间',
  `offline_time` datetime DEFAULT NULL COMMENT '下架时间',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '产品描述',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) COMMENT='产品分类表';

CREATE TABLE `biz_product_image` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '产品图片ID',
  `product_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '产品ID',
  `image_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '附件类型：0-主图，1-详情图，2-轮播图',
  `image_name` varchar(64) NOT NULL DEFAULT '' COMMENT '附件名称',
  `image_url` varchar(128) NOT NULL DEFAULT '' COMMENT '附件地址',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
   PRIMARY KEY (`id`)
) COMMENT='产品图片表';

CREATE TABLE `biz_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_sn` varchar(64) NOT NULL DEFAULT '' COMMENT '订单编码',
  `member_id` bigint(20) NOT NULL COMMENT '会员ID',
  `mobile` varchar(16) NOT NULL DEFAULT '0' COMMENT '手机号码',
  `member_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户姓名',
  `order_amount` decimal(12,2) NOT NULL DEFAULT 0.0 COMMENT '订单金额',
  `order_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '订单状态：0-待支付，1-已支付，2-已取消',
  `address_id` bigint(20) NOT NULL COMMENT '收货人地址ID',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) COMMENT='订单表';

CREATE TABLE `biz_order_detail` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单明细ID',
 `order_id` bigint(20) NOT NULL COMMENT '订单ID',
 `order_sn` varchar(64) NOT NULL DEFAULT '' COMMENT '订单编码',
 `product_id` bigint(20) NOT NULL COMMENT '产品ID',
 `product_code` varchar(64) NOT NULL DEFAULT '' COMMENT '产品编码',
 `product_count` decimal(12,2) NOT NULL DEFAULT 0.0 COMMENT '商品数量',
 `product_amount` decimal(12,2) NOT NULL DEFAULT 0.0 COMMENT '商品金额',
 `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
 `create_time` datetime DEFAULT NULL COMMENT '创建时间',
 `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
 `update_time` datetime DEFAULT NULL COMMENT '更新时间',
 PRIMARY KEY (`id`)
) COMMENT='订单明细表';