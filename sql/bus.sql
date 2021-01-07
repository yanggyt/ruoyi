
-- ----------------------------
-- Table structure for bus_accessory
-- ----------------------------
DROP TABLE IF EXISTS `bus_accessory`;
CREATE TABLE `bus_accessory`  (
  `accessory_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '附件id',
  `accessory_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '附件类型',
  `accessory_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '附件名称',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '附件地址',
  `size` double(10, 2) NULL DEFAULT NULL COMMENT '附件大小',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '上传时间',
  `relevancy_id` bigint(20) NULL DEFAULT NULL COMMENT '附件关联id',
  `relevancy_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件关联类型（新闻/资源/资源需求/企业明细/公共文档等）',
  PRIMARY KEY (`accessory_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '附件信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bus_dept_apply
-- ----------------------------
DROP TABLE IF EXISTS `bus_dept_apply`;
CREATE TABLE `bus_dept_apply`  (
  `apply_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '入驻申请id',
  `apply_type` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请类型（企业注册/企业迁址）',
  `dept_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '企业名称',
  `business` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '行业',
  `dept_scope` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业经营范围',
  `dept_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业类型',
  `dept_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业链接',
  `dept_phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '企业联系电话',
  `legal_config` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '法人信息(姓名、性别、政治面貌、固定电话、手机号码等)',
  `dept_users` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '企业工作人员信息列表（姓名、性别、手机号码、车牌号等）',
  `reg_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工商注册号',
  `credit_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '统一社会信用代码',
  `org_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '组织机构代码',
  `taxpayer_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纳税人识别号',
  `taxpayer_qua` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '纳税人资质',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '00' COMMENT '审核状态（00待审核/01已通过/99未通过）',
  `audit_user_id` bigint(20) NULL DEFAULT NULL COMMENT '审核人id',
  `audit_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核人名称',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `audit_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核意见',
  PRIMARY KEY (`apply_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '企业入驻申请表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bus_news
-- ----------------------------
DROP TABLE IF EXISTS `bus_news`;
CREATE TABLE `bus_news`  (
  `news_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '新闻id',
  `title` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '新闻标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '新闻正文',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新闻标签',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '00' COMMENT '新闻状态（00待发布/01已发布/99已屏蔽）',
  `publish_dept_id` bigint(20) NOT NULL COMMENT '发布企业',
  `publish_user_id` bigint(20) NOT NULL COMMENT '发布人id',
  `publish_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布人名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`news_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '新闻信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bus_req
-- ----------------------------
DROP TABLE IF EXISTS `bus_req`;
CREATE TABLE `bus_req`  (
  `req_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '需求id',
  `req_type` bigint(20) NOT NULL COMMENT '需求类型',
  `req_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '需求名称',
  `classes` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '需求级别',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '需求标签',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '需求描述',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '00' COMMENT '需求状态（00待发布/01已发布/02处理中/03验收中/04已完成/00已屏蔽）',
  `publish_dept_id` bigint(20) NOT NULL COMMENT '发布企业',
  `publish_user_id` bigint(20) NOT NULL COMMENT '发布人id',
  `publish_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布人名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `receive_dept_id` bigint(20) NULL DEFAULT NULL COMMENT '接收企业',
  `receive_user_id` bigint(20) NULL DEFAULT NULL COMMENT '接收人id',
  `receive_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收人名称',
  `receive_time` datetime(0) NULL DEFAULT NULL COMMENT '接收时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`req_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源需求信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bus_req_apply
-- ----------------------------
DROP TABLE IF EXISTS `bus_req_apply`;
CREATE TABLE `bus_req_apply`  (
  `apply_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '申请id',
  `req_id` bigint(20) NOT NULL COMMENT '需求id',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '00' COMMENT '申请状态（00待应答/01已应答/99已废弃）',
  `apply_dept_id` bigint(20) NOT NULL COMMENT '申请企业',
  `apply_user_id` bigint(20) NOT NULL COMMENT '申请人id',
  `apply_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请人名称',
  `apply_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '申请时间',
  `apply_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请备注',
  `audit_user_id` bigint(20) NULL DEFAULT NULL COMMENT '审核人id',
  `audit_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核人名称',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `audit_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核意见',
  PRIMARY KEY (`apply_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源需求接包申请表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bus_req_comment
-- ----------------------------
DROP TABLE IF EXISTS `bus_req_comment`;
CREATE TABLE `bus_req_comment`  (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `req_id` bigint(20) NOT NULL COMMENT '资源需求id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源需求评论表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bus_req_progress
-- ----------------------------
DROP TABLE IF EXISTS `bus_req_progress`;
CREATE TABLE `bus_req_progress`  (
  `progress_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '进度id',
  `req_id` bigint(20) NOT NULL COMMENT '资源需求id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '需求当前状态',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`progress_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源需求进度表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bus_resource
-- ----------------------------
DROP TABLE IF EXISTS `bus_resource`;
CREATE TABLE `bus_resource`  (
  `resource_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `type_id` bigint(20) NOT NULL COMMENT '资源分类id',
  `classes` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源级别',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '资源基本信息',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源标签',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '00' COMMENT '资源状态（00待发布/01已发布/99已屏蔽）',
  `publish_dept_id` bigint(20) NOT NULL COMMENT '发布企业',
  `publish_user_id` bigint(20) NOT NULL COMMENT '发布人id',
  `publish_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '发布人名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`resource_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bus_resource_apply
-- ----------------------------
DROP TABLE IF EXISTS `bus_resource_apply`;
CREATE TABLE `bus_resource_apply`  (
  `apply_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '申请id',
  `resource_id` bigint(20) NOT NULL COMMENT '资源id',
  `status` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '00' COMMENT '申请状态（00待审核/01已通过/99未通过）',
  `apply_user_id` bigint(20) NOT NULL COMMENT '申请人id',
  `apply_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请人名称',
  `apply_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '申请时间',
  `apply_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请备注',
  `audit_user_id` bigint(20) NULL DEFAULT NULL COMMENT '审核人id',
  `audit_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核人名称',
  `audit_time` datetime(0) NULL DEFAULT NULL COMMENT '审核时间',
  `audit_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核意见',
  PRIMARY KEY (`apply_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源使用申请表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bus_resource_comment
-- ----------------------------
DROP TABLE IF EXISTS `bus_resource_comment`;
CREATE TABLE `bus_resource_comment`  (
  `comment_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `resource_id` bigint(20) NOT NULL COMMENT '资源id',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '评论内容',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`comment_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源评论表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bus_resource_type
-- ----------------------------
DROP TABLE IF EXISTS `bus_resource_type`;
CREATE TABLE `bus_resource_type`  (
  `type_id` bigint(20) NOT NULL COMMENT '分类id',
  `type_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '分类名称',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '上级分类id',
  `parent_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '祖籍节点列表',
  `parent_names` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '祖籍节点名称列表',
  `classes` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分类级别',
  `sort` bigint(20) NULL DEFAULT NULL COMMENT '分类排序',
  PRIMARY KEY (`type_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源分类表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bus_user_browse
-- ----------------------------
DROP TABLE IF EXISTS `bus_user_browse`;
CREATE TABLE `bus_user_browse`  (
  `browse_id` bigint(20) NOT NULL COMMENT '浏览关联id',
  `browse_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '浏览类型（01新闻/02资源/03资源需求）',
  `browse_user_id` bigint(20) NOT NULL COMMENT '浏览人id',
  `browse_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '浏览人名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '浏览时间',
  PRIMARY KEY (`browse_id`, `browse_type`, `browse_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户浏览足迹表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bus_user_collect
-- ----------------------------
DROP TABLE IF EXISTS `bus_user_collect`;
CREATE TABLE `bus_user_collect`  (
  `collect_id` bigint(20) NOT NULL COMMENT '收藏关联id',
  `collect_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收藏类型（01新闻/02资源/03资源需求）',
  `collect_user_id` bigint(20) NOT NULL COMMENT '收藏人id',
  `collect_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '收藏人名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (`collect_id`, `collect_type`, `collect_user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户收藏表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for bus_user_message
-- ----------------------------
DROP TABLE IF EXISTS `bus_user_message`;
CREATE TABLE `bus_user_message`  (
  `message_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `message_type` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '消息类型',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息内容',
  `create_time` datetime(0) NOT NULL COMMENT '消息时间',
  `receive_user_id` bigint(20) NULL DEFAULT NULL COMMENT '接收人id',
  `receive_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '接收人名称',
  `publish_user_id` bigint(20) NULL DEFAULT NULL COMMENT '发布人id',
  `publish_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发布人名称',
  PRIMARY KEY (`message_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户消息表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
