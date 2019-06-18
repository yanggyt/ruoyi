SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict_info
-- ----------------------------
DROP TABLE IF EXISTS `dict_info`;
CREATE TABLE `dict_info`  (
  `DICT_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典编号',
  `DICT_TYPE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典类型',
  `DICT_VALUE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典值',
  `DICT_DESC` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '字典描述',
  `DICT_SORT` int(11) NULL DEFAULT NULL COMMENT '字典排序（升序）',
  `DICT_REMARKS` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '字典备注',
  `STATUS` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT ' 状态01.正常 02.失效',
  `GMT_CREATE` datetime(6) NOT NULL COMMENT '创建日期(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `CREATE_OPER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建操作员',
  `GMT_MODIFIED` datetime(6) NOT NULL COMMENT '修改日期(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `MODIFIED_OPER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改操作员',
  `GMT_AUDIT` datetime(6) NULL DEFAULT NULL COMMENT '审核时间(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `AUDIT_OPER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核操作员',
  PRIMARY KEY (`DICT_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '字典信息表';

-- ----------------------------
-- Records of dict_info
-- ----------------------------
BEGIN;
INSERT INTO `dict_info` VALUES ('1', 'wishStatus', '01', '未认领', 0, '', '01', '2019-06-13 10:53:49.000000', '1001', '2019-06-13 10:53:57.000000', '1001', NULL, NULL);
INSERT INTO `dict_info` VALUES ('10', 'isRecommend', '0', '是', 0, NULL, '01', '2019-06-14 09:27:46.000000', '1001', '2019-06-14 09:27:50.000000', '1001', NULL, NULL);
INSERT INTO `dict_info` VALUES ('11', 'isRecommend', '1', '不是', 0, NULL, '01', '2019-06-14 09:28:09.000000', '1001', '2019-06-14 09:28:14.000000', '1001', NULL, NULL);
INSERT INTO `dict_info` VALUES ('12', 'myWishesStatus', '01', '已申请', 0, NULL, '01', '2019-06-14 09:33:36.000000', '1001', '2019-06-14 09:33:42.000000', '1001', NULL, NULL);
INSERT INTO `dict_info` VALUES ('13', 'myWishesStatus', '02', '已认领', 0, NULL, '01', '2019-06-14 09:34:05.000000', '1001', '2019-06-14 09:34:08.000000', '1001', NULL, NULL);
INSERT INTO `dict_info` VALUES ('14', 'myWishesStatus', '03', '认领失败', 0, NULL, '01', '2019-06-14 09:34:25.000000', '1001', '2019-06-14 09:34:29.000000', '1001', NULL, NULL);
INSERT INTO `dict_info` VALUES ('15', 'status', '01', '正常', 0, NULL, '01', '2019-06-14 09:35:45.000000', '1001', '2019-06-14 09:35:48.000000', '1001', NULL, NULL);
INSERT INTO `dict_info` VALUES ('16', 'status', '02', '失效', 0, NULL, '01', '2019-06-14 09:36:09.000000', '1001', '2019-06-14 09:36:13.000000', '1001', NULL, NULL);
INSERT INTO `dict_info` VALUES ('17', 'myWishesStatus', '04', '已完成', 0, NULL, '01', '2019-06-14 09:33:36.000000', '1001', '2019-06-14 09:33:36.000000', '1001', NULL, NULL);
INSERT INTO `dict_info` VALUES ('2', 'wishStatus', '02', '已认领', 0, NULL, '01', '2019-06-14 09:22:25.000000', '1001', '2019-06-14 09:22:34.000000', '1001', NULL, NULL);
INSERT INTO `dict_info` VALUES ('3', 'wishStatus', '03', '已完成', 0, NULL, '01', '2019-06-14 09:22:56.000000', '1001', '2019-06-14 09:23:01.000000', '1001', NULL, NULL);
INSERT INTO `dict_info` VALUES ('4', 'wishTarget', '01', '党组织团体', 0, NULL, '01', '2019-06-14 09:24:18.000000', '1001', '2019-06-14 09:24:23.000000', '1001', NULL, NULL);
INSERT INTO `dict_info` VALUES ('5', 'wishTarget', '02', '党员个体', 0, NULL, '01', '2019-06-14 09:24:46.000000', '1001', '2019-06-14 09:24:50.000000', '1001', NULL, NULL);
INSERT INTO `dict_info` VALUES ('6', 'wishType', '01', '组织建设类', 0, NULL, '01', '2019-06-14 09:25:34.000000', '1001', '2019-06-14 09:25:38.000000', '1001', NULL, NULL);
INSERT INTO `dict_info` VALUES ('7', 'wishType', '02', '党员需求类', 0, NULL, '01', '2019-06-14 09:26:07.000000', '1001', '2019-06-14 09:26:11.000000', '1001', NULL, NULL);
INSERT INTO `dict_info` VALUES ('8', 'wishType', '03', '初心教育类', 0, NULL, '01', '2019-06-14 09:26:37.000000', '1001', '2019-06-14 09:26:40.000000', '1001', NULL, NULL);
INSERT INTO `dict_info` VALUES ('9', 'wishType', '04', '业务合作类', 0, NULL, '01', '2019-06-14 09:27:05.000000', '1001', '2019-06-14 09:27:09.000000', '1001', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for exp_trans_info
-- ----------------------------
DROP TABLE IF EXISTS `exp_trans_info`;
CREATE TABLE `exp_trans_info`  (
  `EXP_ID` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '异常ID',
  `TRANS_CODE` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '交易代码(公共交易-common)',
  `EXP_CODE` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '异常代码',
  `EXP_MSG` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '异常信息',
  `TRANS_EXP_MSG` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '转译后异常信息',
  `STATUS` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '状态 01.正常 02.失效',
  `GMT_CREATE` datetime(6) NOT NULL COMMENT '创建日期(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `CREATE_OPER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建操作员',
  `GMT_MODIFIED` datetime(6) NOT NULL COMMENT '修改日期(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `MODIFIED_OPER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改操作员',
  `GMT_AUDIT` datetime(6) NULL DEFAULT NULL COMMENT '审核时间(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `AUDIT_OPER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核操作员',
  PRIMARY KEY (`EXP_ID`) USING BTREE,
  UNIQUE INDEX `T_PTL_EXP_TRANS_INFO_CODE`(`TRANS_CODE`, `EXP_CODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '异常信息转译表';

-- ----------------------------
-- Table structure for wish_applyer_info
-- ----------------------------
DROP TABLE IF EXISTS `wish_applyer_info`;
CREATE TABLE `wish_applyer_info`  (
  `WISH_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微心愿ID',
  `WX_OPENID` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微信用户openId',
  `WX_NICKNAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微信昵称',
  `WX_ICON_URL` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微信头像路径',
  `WISH_ORG` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微心愿归属地区',
  `MY_WISH_STATUS` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微心愿认领状态01.待审核02.认领成功03.认领失败04.已完成',
  `GETTER_NAME` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请认领人姓名',
  `GETTER_PHONE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请认领人手机号',
  `GETTER_DEPT` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请认领人所在支部',
  `GET_PLAN` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '认领方案',
  `GMT_CREATE` datetime(6) NOT NULL COMMENT '创建日期(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `CREATE_OPER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建操作员',
  `GMT_MODIFIED` datetime(6) NOT NULL COMMENT '修改日期(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `MODIFIED_OPER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改操作员',
  `GMT_AUDIT` datetime(6) NULL DEFAULT NULL COMMENT '审核时间(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `AUDIT_OPER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核操作员',
  PRIMARY KEY (`WISH_ID`, `WX_OPENID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '申请认领人信息表';

-- ----------------------------
-- Records of wish_applyer_info
-- ----------------------------
BEGIN;
INSERT INTO `wish_applyer_info` VALUES ('0001', '0872617d1a1e48c69a50', '张三', 'sdfiwoej', '3301', '04', '张三', '13355602979', '杭州市萧山区党组织', '11', '2019-06-13 19:20:35.000000', '1001', '2019-06-13 19:20:35.000000', '1001', NULL, NULL);
INSERT INTO `wish_applyer_info` VALUES ('0002', 'qwerwqwerwerwetre23', '余惠娟', 'werwer', '3301', '04', '余惠娟', '13333333333', '杭州市萧山区党组织', '11', '2019-06-13 19:22:55.000000', '1001', '2019-06-13 19:22:55.000000', '1001', NULL, NULL);
INSERT INTO `wish_applyer_info` VALUES ('0003', '377568c91dc24d6ca8f4', '王二麻子', 'sdfiwoej', '3301', '04', '王二麻子', '18888700846', '杭州市萧山区党组织', '11', '2019-06-13 19:24:31.000000', '1001', '2019-06-13 19:24:31.000000', '1001', NULL, NULL);
INSERT INTO `wish_applyer_info` VALUES ('0008', '0872617d1a1e48c69a50', '张三', 'sdfiwoej', '3301', '02', '张三', '13355602979', '杭州市萧山区党组织', '11', '2019-06-13 19:27:29.000000', '1001', '2019-06-13 19:27:29.000000', '1001', NULL, NULL);
INSERT INTO `wish_applyer_info` VALUES ('0009', '0872617d1a1e48c69a50', '张三', 'sdfiwoej', '3301', '02', '张三', '13355602979', '杭州市萧山区党组织', '11', '2019-06-13 19:27:32.000000', '1001', '2019-06-13 19:27:32.000000', '1001', NULL, NULL);
INSERT INTO `wish_applyer_info` VALUES ('0010', '0872617d1a1e48c69a50', '张三', 'sdfiwoej', '3301', '02', '张三', '13355602979', '杭州市萧山区党组织', '11', '2019-06-13 19:27:37.000000', '1001', '2019-06-13 19:27:37.000000', '1001', NULL, NULL);
INSERT INTO `wish_applyer_info` VALUES ('0011', '377568c91dc24d6ca8f4', '王二麻子', 'sdfiwoej', '3301', '02', '王二麻子', '18888700846', '杭州市萧山区党组织', '11', '2019-06-13 19:30:39.000000', '1001', '2019-06-13 19:30:39.000000', '1001', NULL, NULL);
INSERT INTO `wish_applyer_info` VALUES ('0012', '377568c91dc24d6ca8f4', '王二麻子', 'sdfiwoej', '3301', '02', '王二麻子', '18888700846', '杭州市萧山区党组织', '11', '2019-06-13 19:30:53.000000', '1001', '2019-06-13 19:30:53.000000', '1001', NULL, NULL);
INSERT INTO `wish_applyer_info` VALUES ('0014', 'cc02187b5c2c46ea9e88', '徐建梅', 'sdfiwoej', '3301', '02', '徐建梅', '13733723139', '杭州市萧山区党组织', '11', '2019-06-13 19:25:26.000000', '1001', '2019-06-13 19:25:26.000000', '1001', NULL, NULL);
INSERT INTO `wish_applyer_info` VALUES ('0017', 'qwerwqwerwerwetre23', '余惠娟', 'werwer', '3301', '01', '余惠娟', '13333333333', '杭州市萧山区党组织', '11', '2019-06-15 17:44:18.000000', '1001', '2019-06-15 17:44:18.000000', '1001', NULL, NULL);
INSERT INTO `wish_applyer_info` VALUES ('0041', 'qwerwqwerwerwetre23', '余惠娟', 'werwer', '3350', '01', '余惠娟', '13333333333', '杭州市萧山区党组织', '11', '2019-06-16 15:08:39.000000', '1001', '2019-06-16 15:08:39.000000', '1001', NULL, NULL);
INSERT INTO `wish_applyer_info` VALUES ('0051', '377568c91dc24d6ca8f4', '王二麻子', 'sdfiwoej', '3360', '01', '王二麻子', '18888700846', '杭州市萧山区党组织', '11', '2019-06-16 15:06:52.000000', '1001', '2019-06-16 15:06:52.000000', '1001', NULL, NULL);
INSERT INTO `wish_applyer_info` VALUES ('0051', '7dd8d76d21e84a31a36', 'fan', 'qwrfwefgsd', '3360', '01', '樊', '15395007883', '杭州市萧山区党组织', '11', '2019-06-16 14:59:36.000000', '1001', '2019-06-16 14:59:36.000000', '1001', NULL, NULL);
INSERT INTO `wish_applyer_info` VALUES ('0061', '377568c91dc24d6ca8f4', '王二麻子', 'sdfiwoej', '3301', '02', '王二麻子', '18888700846', '杭州市萧山区党组织', '11', '2019-06-13 19:30:58.000000', '1001', '2019-06-13 19:30:58.000000', '1001', NULL, NULL);
INSERT INTO `wish_applyer_info` VALUES ('0061', 'cc02187b5c2c46ea9e88', '徐建梅', 'saetwe', '3301', '02', '徐建梅', '13733723139', '杭州市萧山区党组织', '11', '2019-06-14 10:39:50.000000', '1001', '2019-06-14 10:39:50.000000', '1001', NULL, NULL);
INSERT INTO `wish_applyer_info` VALUES ('0061', 'da05aab2e31642e3b25', '李四', 'sdfiwoej', '3301', '02', '李四', '18356400024', '杭州市萧山区党组织', '11', '2019-06-13 19:22:55.000000', '1001', '2019-06-13 19:22:55.000000', '1001', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for wish_info
-- ----------------------------
DROP TABLE IF EXISTS `wish_info`;
CREATE TABLE `wish_info`  (
  `WISH_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微心愿ID',
  `WISH_NAME` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微心愿名称',
  `WISH_ORG` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微心愿归属地区',
  `WISH_STATUS` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微心愿状态01.未认领02.已认领03.已完成',
  `WISH_TARGET` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微心愿对象01党组织团体02党员个体',
  `WISH_TYPE` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微心愿类型01.组织建设类02.党员需求类03.初心教育类04.业务合作类',
  `WISH_COUNT` int(11) NOT NULL COMMENT '申请认领次数',
  `IS_RECOMMEND` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否推荐0.是1.不是',
  `WISH_PROGRESS` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微心愿项目进展',
  `WISH_DESC` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微心愿详细描述',
  `APPLYER_NAME` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请人姓名',
  `APPLYER__PHONE` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请人联系方式',
  `APPLYER_COMP` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '申请人所在党组织',
  `EXPIRE_DATE` datetime(6) NOT NULL COMMENT '失效日期',
  `EFFECT_DATE` datetime(6) NOT NULL COMMENT '生效日期',
  `CLAIM_DATE` datetime(6) NULL DEFAULT NULL COMMENT '认领日期',
  `COMPLETE_DATE` datetime(6) NULL DEFAULT NULL COMMENT '完成日期',
  `SAVE_PATH` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '图片存储路径',
  `GMT_CREATE` datetime(6) NOT NULL COMMENT '创建日期(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `CREATE_OPER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建操作员',
  `GMT_MODIFIED` datetime(6) NOT NULL COMMENT '修改日期(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `MODIFIED_OPER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改操作员',
  `GMT_AUDIT` datetime(6) NULL DEFAULT NULL COMMENT '审核时间(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `AUDIT_OPER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核操作员',
  PRIMARY KEY (`WISH_ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '微心愿信息表';

-- ----------------------------
-- Records of wish_info
-- ----------------------------
BEGIN;
INSERT INTO `wish_info` VALUES ('0001', '心愿1号', '3310', '03', '01', '03', 1, '0', '进展还行', '革命老区', '高伟仙', '18506145149', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:31.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:31.000000', '1001', '2019-06-14 11:33:31.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0002', '心愿2号', '3310', '03', '01', '02', 1, '0', '进展还行', '革命老区', '高伟仙', '18506145149', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:31.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:31.000000', '1001', '2019-06-14 11:33:31.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0003', '心愿3号', '3310', '03', '01', '01', 1, '0', '进展还行', '革命老区', '高伟仙', '18506145149', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:31.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:31.000000', '1001', '2019-06-14 11:33:31.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0004', '心愿4号', '3310', '03', '01', '04', 1, '0', '进展还行', '革命老区', '高伟仙', '18506145149', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:31.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:31.000000', '1001', '2019-06-14 11:33:31.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0005', '心愿5号', '3310', '03', '01', '02', 1, '1', '进展还行', '革命老区', '高伟仙', '18506145149', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0006', '心愿6号', '3310', '03', '01', '01', 1, '0', '进展还行', '革命老区', '高伟仙', '18506145149', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0007', '心愿7号', '3310', '03', '01', '01', 1, '1', '进展还行', '革命老区', '高伟仙', '18506145149', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0008', '心愿8号', '3310', '02', '01', '02', 1, '1', '进展还行', '革命老区', '高伟仙', '18506145149', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0009', '心愿9号', '3310', '02', '01', '04', 1, '1', '进展还行', '革命老区', '高伟仙', '18506145149', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0010', '心愿10号', '3310', '02', '02', '01', 1, '0', '进展还行', '革命老区', '高伟仙', '18506145149', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0011', '心愿11号', '3310', '02', '01', '01', 1, '0', '进展还行', '革命老区', '高伟仙', '18506145149', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0012', '心愿12号', '3310', '02', '02', '02', 1, '0', '进展还行', '革命老区', '高伟仙', '18506145149', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0013', '心愿13号', '3310', '02', '01', '03', 1, '0', '进展还行', '革命老区', '高伟仙', '18506145149', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0014', '心愿14号', '3310', '02', '01', '01', 1, '0', '进展还行', '革命老区', '高伟仙', '18506145149', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0015', '心愿15号', '3310', '01', '01', '03', 0, '0', '进展还行', '革命老区', '高伟仙', '18506145149', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0016', '心愿16号', '3310', '01', '02', '03', 0, '0', '进展还行', '革命老区', '高伟仙', '18506145149', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0017', '心愿17号', '3310', '01', '01', '01', 1, '0', '进展还行', '革命老区', '高伟仙', '18506145149', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0018', '心愿18号', '3310', '01', '01', '04', 0, '0', '进展还行', '革命老区', '高伟仙', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0019', '心愿19号', '3310', '01', '01', '01', 0, '0', '进展还行', '革命老区', '高伟仙', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0020', '心愿20号', '3310', '01', '01', '01', 1, '0', '进展还行', '革命老区', '高伟仙', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0021', '心愿21号', '3320', '01', '02', '02', 0, '0', '进展还行', '革命老区', 'ff', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0022', '心愿22号', '3320', '01', '01', '01', 1, '0', '进展还行', '革命老区', 'ff', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0023', '心愿23号', '3320', '01', '02', '02', 0, '0', '进展还行', '革命老区', 'ff', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0024', '心愿24号', '3320', '01', '01', '02', 1, '1', '进展还行', '革命老区', 'ff', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0025', '心愿25号', '3320', '01', '02', '02', 0, '1', '进展还行', '革命老区', 'ff', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0026', '心愿26号', '3320', '01', '01', '01', 0, '0', '进展还行', '革命老区', 'ff', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0027', '心愿27号', '3320', '01', '01', '01', 0, '1', '进展还行', '革命老区', 'ff', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0028', '心愿28号', '3320', '01', '01', '01', 0, '0', '进展还行', '革命老区', 'ff', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0029', '心愿29号', '3320', '01', '02', '04', 1, '1', '进展还行', '革命老区', 'ff', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0030', '心愿30号', '3320', '01', '02', '02', 0, '1', '进展还行', '革命老区', 'ff', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0031', '心愿31号', '3320', '01', '02', '02', 1, '1', '进展还行', '革命老区', 'ff', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0032', '心愿32号', '3330', '01', '02', '02', 0, '0', '进展还行', '革命老区', 'ff', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0033', '心愿33号', '3330', '01', '01', '01', 1, '0', '进展还行', '革命老区', 'ff', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0034', '心愿34号', '3330', '01', '01', '03', 0, '0', '进展还行', '革命老区', 'ff', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0035', '心愿35号', '3330', '01', '01', '01', 1, '0', '进展还行', '革命老区', 'ff', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0036', '心愿36号', '3330', '01', '01', '01', 0, '0', '进展还行', '革命老区', 'ff', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0037', '心愿37号', '3330', '01', '01', '01', 1, '0', '进展还行', '革命老区', 'ff', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0038', '心愿38号', '3350', '01', '01', '04', 0, '1', '进展还行', '革命老区', 'ff', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0039', '心愿39号', '3350', '01', '01', '04', 1, '1', '进展还行', '革命老区', 'ff', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0040', '心愿40号', '3350', '01', '01', '01', 0, '1', '进展还行', '革命老区', 'ff', '19906615613', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0041', '心愿41号', '3350', '01', '01', '03', 1, '1', '进展还行', '革命老区', '楼良正', '18919738778', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0042', '心愿42号', '3350', '01', '01', '01', 0, '1', '进展还行', '革命老区', '楼良正', '18919738778', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0043', '心愿43号', '3350', '01', '02', '01', 1, '0', '进展还行', '革命老区', '楼良正', '18919738778', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0044', '心愿44号', '3350', '01', '02', '02', 0, '0', '进展还行', '革命老区', '楼良正', '18919738778', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0045', '心愿45号', '3360', '01', '01', '03', 0, '0', '进展还行', '革命老区', '楼良正', '18919738778', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0046', '心愿46号', '3360', '01', '01', '01', 0, '0', '进展还行', '革命老区', '楼良正', '18919738778', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0047', '心愿47号', '3360', '01', '01', '04', 0, '0', '进展还行', '革命老区', '楼良正', '18919738778', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0048', '心愿48号', '3360', '01', '01', '01', 0, '0', '进展还行', '革命老区', '楼良正', '18919738778', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0049', '心愿49号', '3360', '01', '01', '03', 0, '0', '进展还行', '革命老区', '楼良正', '18919738778', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0050', '心愿50号', '3360', '01', '01', '01', 1, '0', '进展还行', '革命老区', '楼良正', '18919738778', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0051', '心愿51号', '3360', '01', '01', '01', 2, '0', '进展还行', '革命老区', '楼良正', '18919738778', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0052', '心愿52号', '3360', '01', '01', '01', 0, '1', '进展还行', '革命老区', '楼良正', '18919738778', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0053', '心愿53号', '3360', '01', '01', '04', 0, '1', '进展还行', '革命老区', '楼良正', '18919738778', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0054', '心愿54号', '3360', '01', '01', '03', 0, '1', '进展还行', '革命老区', '楼良正', '18919738778', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0055', '心愿55号', '3360', '01', '02', '02', 0, '1', '进展还行', '革命老区', '楼良正', '18919738778', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:32.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:32.000000', '1001', '2019-06-14 11:33:32.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0056', '心愿56号', '3370', '01', '01', '02', 0, '1', '进展还行', '革命老区', '楼良正', '18919738778', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:33.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:33.000000', '1001', '2019-06-14 11:33:33.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0057', '心愿57号', '3370', '01', '01', '01', 1, '0', '进展还行', '革命老区', '楼良正', '18919738778', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:33.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:33.000000', '1001', '2019-06-14 11:33:33.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0058', '心愿58号', '3370', '01', '01', '01', 0, '0', '进展还行', '革命老区', '楼良正', '18919738778', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:33.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:33.000000', '1001', '2019-06-14 11:33:33.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0059', '心愿59号', '3370', '01', '02', '02', 0, '0', '进展还行', '革命老区', '楼良正', '18919738778', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:33.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:33.000000', '1001', '2019-06-14 11:33:33.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0060', '心愿60号', '3370', '01', '01', '04', 0, '0', '进展还行', '革命老区', '楼良正', '18919738778', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:33.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:33.000000', '1001', '2019-06-14 11:33:33.000000', '1002', NULL, NULL);
INSERT INTO `wish_info` VALUES ('0061', '心愿61号', '3370', '01', '02', '02', 1, '0', '进展还行', '革命老区', '楼良正', '18919738778', '党组织', '2020-01-01 11:33:31.000000', '2019-06-14 11:33:33.000000', NULL, NULL, 'JDSOAIFJWEOI', '2019-06-14 11:33:33.000000', '1001', '2019-06-14 11:33:33.000000', '1002', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for zhejiang_city_info
-- ----------------------------
DROP TABLE IF EXISTS `zhejiang_city_info`;
CREATE TABLE `zhejiang_city_info`  (
  `CITY_CODE` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CITY_NAME` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `GMT_CREATE` datetime(6) NOT NULL COMMENT '创建日期(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `CREATE_OPER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '创建操作员',
  `GMT_MODIFIED` datetime(6) NOT NULL COMMENT '修改日期(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `MODIFIED_OPER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '修改操作员',
  `GMT_AUDIT` datetime(6) NULL DEFAULT NULL COMMENT '审核时间(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `AUDIT_OPER_ID` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核操作员',
  PRIMARY KEY (`CITY_CODE`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '浙江城市信息表';

-- ----------------------------
-- Records of zhejiang_city_info
-- ----------------------------
BEGIN;
INSERT INTO `zhejiang_city_info` VALUES ('3310', '杭州市', '2019-06-14 09:50:24.000000', '1001', '2019-06-14 09:50:24.000000', '1001', NULL, NULL);
INSERT INTO `zhejiang_city_info` VALUES ('3320', '宁波市', '2019-06-14 09:51:10.000000', '1001', '2019-06-14 09:51:10.000000', '1001', NULL, NULL);
INSERT INTO `zhejiang_city_info` VALUES ('3330', '温州市', '2019-06-14 09:51:10.000000', '1001', '2019-06-14 09:51:10.000000', '1001', NULL, NULL);
INSERT INTO `zhejiang_city_info` VALUES ('3350', '嘉兴市', '2019-06-14 09:51:10.000000', '1001', '2019-06-14 09:51:10.000000', '1001', NULL, NULL);
INSERT INTO `zhejiang_city_info` VALUES ('3360', '湖州市', '2019-06-14 09:51:10.000000', '1001', '2019-06-14 09:51:10.000000', '1001', NULL, NULL);
INSERT INTO `zhejiang_city_info` VALUES ('3370', '绍兴市', '2019-06-14 09:51:10.000000', '1001', '2019-06-14 09:51:10.000000', '1001', NULL, NULL);
INSERT INTO `zhejiang_city_info` VALUES ('3380', '金华市', '2019-06-14 09:51:10.000000', '1001', '2019-06-14 09:51:10.000000', '1001', NULL, NULL);
INSERT INTO `zhejiang_city_info` VALUES ('3410', '衢州市', '2019-06-14 09:51:10.000000', '1001', '2019-06-14 09:51:10.000000', '1001', NULL, NULL);
INSERT INTO `zhejiang_city_info` VALUES ('3420', '舟山市', '2019-06-14 09:51:10.000000', '1001', '2019-06-14 09:51:10.000000', '1001', NULL, NULL);
INSERT INTO `zhejiang_city_info` VALUES ('3430', '丽水市', '2019-06-14 09:51:10.000000', '1001', '2019-06-14 09:51:10.000000', '1001', NULL, NULL);
INSERT INTO `zhejiang_city_info` VALUES ('3450', '台州市', '2019-06-14 09:51:10.000000', '1001', '2019-06-14 09:51:10.000000', '1001', NULL, NULL);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
