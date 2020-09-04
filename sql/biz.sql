CREATE TABLE `biz_member` (
  `member_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会员ID',
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
  PRIMARY KEY (`member_id`)
) COMMENT='会员表';

CREATE TABLE `biz_member_account` (
  `account_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会员账户ID',
  `account_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '账户类型：0-福豆余额，1-可用福豆，2-团队福豆，3-专项福豆，4-福豆田',
  `amount` bigint(20) NOT NULL DEFAULT 0 COMMENT '账户金额',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`account_id`)
) COMMENT='会员账户表';

CREATE TABLE `biz_product_type` (
  `product_type_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '产品分类ID',
  `product_type_code` varchar(64) NOT NULL DEFAULT '' COMMENT '产品分类编码',
  `product_type_name` varchar(64) NOT NULL DEFAULT '' COMMENT '产品分类名称',
  `image_url` varchar(128) NOT NULL DEFAULT '' COMMENT '附件地址',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `is_enable` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否禁用：0-否，1-是',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`product_type_id`)
) COMMENT='产品分类表';

CREATE TABLE `biz_product` (
  `product_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `product_code` varchar(64) NOT NULL DEFAULT '' COMMENT '产品编码',
  `product_name` varchar(64) NOT NULL DEFAULT '' COMMENT '产品名称',
  `product_type_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '产品分类ID',
  `product_class` tinyint(4) NOT NULL DEFAULT 0 COMMENT '产品类型：0-销售产品，1-兑换产品',
  `amount` bigint(20) NOT NULL DEFAULT 0 COMMENT '产品单价',
  `sort` int(11) NOT NULL DEFAULT 0 COMMENT '排序',
  `online_status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否上架：0-否，1-是',
  `online_time` datetime DEFAULT NULL COMMENT '上架时间',
  `offline_time` datetime DEFAULT NULL COMMENT '下架时间',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '产品描述',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`product_id`)
) COMMENT='产品分类表';

CREATE TABLE `biz_product_image` (
   `product_image_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '产品图片ID',
   `product_id` bigint(20) NOT NULL DEFAULT 0 COMMENT '产品ID',
   `image_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '附件类型：0-主图，1-详情图，2-轮播图',
   `image_name` varchar(64) NOT NULL DEFAULT '' COMMENT '附件名称',
   `image_url` varchar(128) NOT NULL DEFAULT '' COMMENT '附件地址',
   `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
   `create_time` datetime DEFAULT NULL COMMENT '创建时间',
   `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
   `update_time` datetime DEFAULT NULL COMMENT '更新时间',
   PRIMARY KEY (`product_id`)
) COMMENT='产品图片表'