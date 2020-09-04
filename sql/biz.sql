CREATE TABLE `biz_member` (
  `member_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '会员ID',
  `mobile` varchar(16) NOT NULL DEFAULT '0' COMMENT '手机号码',
  `member_name` varchar(32) NOT NULL DEFAULT '' COMMENT '用户姓名',
  `recommend_id` varchar(512) NOT NULL DEFAULT '' COMMENT '推荐人ID',
  `recommend_mobile` varchar(32) NOT NULL DEFAULT '' COMMENT '推荐人手机',
  `recommend_name` varchar(32) NOT NULL DEFAULT '' COMMENT '推荐人姓名',
  `member_type` varchar(32) NOT NULL DEFAULT '' COMMENT '会员类型',
  `is_delete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除：0-否，1-是',
  `is_enable` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否禁用：0-否，1-是',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`member_id`)
) COMMENT='会员表'