
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dict_info
-- ----------------------------
DROP TABLE IF EXISTS `dict_info`;
CREATE TABLE `dict_info` (
  `DICT_ID` varchar(32) NOT NULL COMMENT '字典编号',
  `DICT_TYPE` varchar(100) NOT NULL COMMENT '字典类型',
  `DICT_VALUE` varchar(100) NOT NULL COMMENT '字典值',
  `DICT_DESC` varchar(100) NOT NULL COMMENT '字典描述',
  `DICT_SORT` int(11) NOT NULL COMMENT '字典排序（升序）',
  `DICT_REMARKS` varchar(255) DEFAULT NULL COMMENT '字典备注',
  `STATUS` char(2) NOT NULL COMMENT '状态 01.正常 02.失效',
  `GMT_CREATE` datetime(6) NOT NULL COMMENT '创建日期(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `CREATE_OPER_ID` varchar(20) NOT NULL COMMENT '创建操作员',
  `GMT_MODIFIED` datetime(6) NOT NULL COMMENT '修改日期(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `MODIFIED_OPER_ID` varchar(20) NOT NULL COMMENT '修改操作员',
  `GMT_AUDIT` datetime(6) DEFAULT NULL COMMENT '审核时间(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `AUDIT_OPER_ID` varchar(20) DEFAULT NULL COMMENT '审核操作员',
  PRIMARY KEY (`DICT_ID`),
  UNIQUE KEY `UK_T_PTL_DICT_INFO_ID` (`DICT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典信息表';

-- ----------------------------
-- Records of dict_info
-- ----------------------------
INSERT INTO `dict_info` VALUES ('1', 'wishStatus', '01', '未认领', '0', '', '01', '2019-06-13 10:53:49.000000', '1001', '2019-06-13 10:53:57.000000', '1001', null, null);
INSERT INTO `dict_info` VALUES ('10', 'isRecommend', '0', '是', '0', null, '01', '2019-06-14 09:27:46.000000', '1001', '2019-06-14 09:27:50.000000', '1001', null, null);
INSERT INTO `dict_info` VALUES ('11', 'isRecommend', '1', '不是', '0', null, '01', '2019-06-14 09:28:09.000000', '1001', '2019-06-14 09:28:14.000000', '1001', null, null);
INSERT INTO `dict_info` VALUES ('12', 'wishGetStatus', '01', '待审核', '0', null, '01', '2019-06-14 09:33:36.000000', '1001', '2019-06-14 09:33:42.000000', '1001', null, null);
INSERT INTO `dict_info` VALUES ('13', 'wishGetStatus', '02', '认领成功', '0', null, '01', '2019-06-14 09:34:05.000000', '1001', '2019-06-14 09:34:08.000000', '1001', null, null);
INSERT INTO `dict_info` VALUES ('14', 'wishGetStatus', '03', '认领失败', '0', null, '01', '2019-06-14 09:34:25.000000', '1001', '2019-06-14 09:34:29.000000', '1001', null, null);
INSERT INTO `dict_info` VALUES ('15', 'status', '01', '正常', '0', null, '01', '2019-06-14 09:35:45.000000', '1001', '2019-06-14 09:35:48.000000', '1001', null, null);
INSERT INTO `dict_info` VALUES ('16', 'status', '02', '失效', '0', null, '01', '2019-06-14 09:36:09.000000', '1001', '2019-06-14 09:36:13.000000', '1001', null, null);
INSERT INTO `dict_info` VALUES ('2', 'wishStatus', '02', '已认领', '0', null, '01', '2019-06-14 09:22:25.000000', '1001', '2019-06-14 09:22:34.000000', '1001', null, null);
INSERT INTO `dict_info` VALUES ('3', 'wishStatus', '03', '已完成', '0', null, '01', '2019-06-14 09:22:56.000000', '1001', '2019-06-14 09:23:01.000000', '1001', null, null);
INSERT INTO `dict_info` VALUES ('4', 'wishTarget', '01', '党组织团体', '0', null, '01', '2019-06-14 09:24:18.000000', '1001', '2019-06-14 09:24:23.000000', '1001', null, null);
INSERT INTO `dict_info` VALUES ('5', 'wishTarget', '02', '党员个体', '0', null, '01', '2019-06-14 09:24:46.000000', '1001', '2019-06-14 09:24:50.000000', '1001', null, null);
INSERT INTO `dict_info` VALUES ('6', 'wishType', '01', '组织建设类', '0', null, '01', '2019-06-14 09:25:34.000000', '1001', '2019-06-14 09:25:38.000000', '1001', null, null);
INSERT INTO `dict_info` VALUES ('7', 'wishType', '02', '党员需求类', '0', null, '01', '2019-06-14 09:26:07.000000', '1001', '2019-06-14 09:26:11.000000', '1001', null, null);
INSERT INTO `dict_info` VALUES ('8', 'wishType', '03', '初心教育类', '0', null, '01', '2019-06-14 09:26:37.000000', '1001', '2019-06-14 09:26:40.000000', '1001', null, null);
INSERT INTO `dict_info` VALUES ('9', 'wishType', '04', '业务合作类', '0', null, '01', '2019-06-14 09:27:05.000000', '1001', '2019-06-14 09:27:09.000000', '1001', null, null);

-- ----------------------------
-- Table structure for exp_trans_info
-- ----------------------------
DROP TABLE IF EXISTS `exp_trans_info`;
CREATE TABLE `exp_trans_info` (
  `EXP_ID` varchar(32) NOT NULL COMMENT '异常ID',
  `TRANS_CODE` varchar(100) NOT NULL COMMENT '交易代码(公共交易-common)',
  `EXP_CODE` varchar(32) NOT NULL COMMENT '异常代码',
  `EXP_MSG` varchar(100) NOT NULL COMMENT '异常信息',
  `TRANS_EXP_MSG` varchar(100) NOT NULL COMMENT '转译后异常信息',
  `STATUS` char(2) NOT NULL COMMENT '状态 01.正常 02.失效',
  `GMT_CREATE` datetime(6) NOT NULL COMMENT '创建日期',
  `CREATE_OPER_ID` varchar(20) NOT NULL COMMENT '创建操作员',
  `GMT_MODIFIED` datetime(6) NOT NULL COMMENT '修改日期',
  `MODIFIED_OPER_ID` varchar(20) NOT NULL COMMENT '修改操作员',
  `GMT_AUDIT` datetime(6) DEFAULT NULL COMMENT '审核日期',
  `AUDIT_OPER_ID` varchar(20) DEFAULT NULL COMMENT '审核操作员',
  PRIMARY KEY (`EXP_ID`),
  UNIQUE KEY `T_PTL_EXP_TRANS_INFO_CODE` (`TRANS_CODE`,`EXP_CODE`),
  UNIQUE KEY `T_PTL_EXP_TRANS_INFO_ID` (`EXP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='异常信息转译表';

-- ----------------------------
-- Records of exp_trans_info
-- ----------------------------

-- ----------------------------
-- Table structure for wish_applyer_info
-- ----------------------------
DROP TABLE IF EXISTS `wish_applyer_info`;
CREATE TABLE `wish_applyer_info` (
  `WX_OPENID` varchar(60) NOT NULL COMMENT '微信OPENID',
  `WX_NICKNAME` varchar(100) NOT NULL COMMENT '微信昵称',
  `WX_ICON_URL` varchar(1000) NOT NULL COMMENT '微信头像路径',
  `WISH_ID` varchar(20) NOT NULL COMMENT '微心愿ID',
  `WISH_ORG` varchar(20) NOT NULL COMMENT '微心愿归属地区',
  `WISH_GET_STATUS` char(2) NOT NULL COMMENT '微心愿认领状态01.待审核02.认领成功03.认领失败',
  `GETTER_NAME` varchar(60) NOT NULL COMMENT '申请人认领人名称',
  `GETTER_PHONE` varchar(20) NOT NULL COMMENT '申请人认领人联系方式',
  `GETTER_DEPT` varchar(100) NOT NULL COMMENT '申请人认领人所在党组织',
  `GET_PLAN` varchar(2000) NOT NULL COMMENT '认领方案',
  `GMT_CREATE` datetime(6) NOT NULL COMMENT '创建日期(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `CREATE_OPER_ID` varchar(20) NOT NULL COMMENT '创建操作员',
  `GMT_MODIFIED` datetime(6) NOT NULL COMMENT '修改日期(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `MODIFIED_OPER_ID` varchar(20) NOT NULL COMMENT '修改操作员',
  `GMT_AUDIT` datetime(6) DEFAULT NULL COMMENT '审核时间(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `AUDIT_OPER_ID` varchar(20) DEFAULT NULL COMMENT '审核操作员',
  PRIMARY KEY (`WX_OPENID`,`WISH_ID`),
  UNIQUE KEY `WISH_ID` (`WISH_ID`,`WX_OPENID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='认领人的相关信息';

-- ----------------------------
-- Records of wish_applyer_info
-- ----------------------------


-- ----------------------------
-- Table structure for wish_info
-- ----------------------------
DROP TABLE IF EXISTS `wish_info`;
CREATE TABLE `wish_info` (
  `WISH_ID` int(20) NOT NULL COMMENT '微心愿ID',
  `WISH_NAME` varchar(100) NOT NULL COMMENT '微心愿名称',
  `WISH_ORG` varchar(10) NOT NULL COMMENT '微心愿归属地区',
  `WISH_STATUS` char(2) NOT NULL COMMENT '微心愿状态 01.未认领02.已认领03.已完成',
  `WISH_TARGET` char(2) NOT NULL COMMENT '微心愿对象 01党组织团体02党员个体',
  `WISH_TYPE` char(2) NOT NULL COMMENT '微心愿类型 01.组织建设类02.党员需求类03.初心教育类04.业务合作类',
  `WISH_COUNT` int(2) DEFAULT NULL COMMENT '申请认领次数',
  `IS_RECOMMEND` char(1) NOT NULL COMMENT '是不是推荐 0.是 1.不是',
  `WISH_PROGRESS` varchar(1000) DEFAULT NULL COMMENT '微心愿项目进展',
  `WISH_DESC` varchar(1000) NOT NULL COMMENT '微心愿详细描述',
  `APPLYER_NAME` varchar(60) NOT NULL COMMENT '申请人姓名',
  `APPLYER__PHONE` varchar(20) NOT NULL COMMENT '申请人联系方式',
  `APPLYER_COMP` varchar(100) NOT NULL COMMENT '申请人所在党组织',
  `EXPIRE_DATE` datetime(6) NOT NULL COMMENT '失效日期',
  `EFFECT_DATE` datetime(6) NOT NULL COMMENT '生效日期',
  `CLAIM_DATE` datetime(6) DEFAULT NULL COMMENT '认领日期',
  `COMPLETE_DATE` datetime(6) DEFAULT NULL COMMENT '完成日期',
  `SAVE_PATH` varchar(100) NOT NULL COMMENT '图片存储路径',
  `GMT_CREATE` datetime(6) NOT NULL COMMENT '创建日期(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `CREATE_OPER_ID` varchar(20) NOT NULL COMMENT '创建操作员',
  `GMT_MODIFIED` datetime(6) NOT NULL COMMENT '修改日期(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `MODIFIED_OPER_ID` varchar(20) NOT NULL COMMENT '修改操作员',
  `GMT_AUDIT` datetime(6) DEFAULT NULL COMMENT '审核时间(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `AUDIT_OPER_ID` varchar(20) DEFAULT NULL COMMENT '审核操作员',
  PRIMARY KEY (`WISH_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='记录微心愿的信息';

-- ----------------------------
-- Records of wish_info
-- ----------------------------

-- ----------------------------
-- Table structure for zhejiang_city_info
-- ----------------------------
DROP TABLE IF EXISTS `zhejiang_city_info`;
CREATE TABLE `zhejiang_city_info` (
  `CITY_CODE` varchar(10) NOT NULL COMMENT '城市代码',
  `CITY_NAME` varchar(10) NOT NULL COMMENT '城市名称',
  `GMT_CREATE` datetime(6) NOT NULL COMMENT '创建日期(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `CREATE_OPER_ID` varchar(20) NOT NULL COMMENT '创建操作员',
  `GMT_MODIFIED` datetime(6) NOT NULL COMMENT '修改日期(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `MODIFIED_OPER_ID` varchar(20) NOT NULL COMMENT '修改操作员',
  `GMT_AUDIT` datetime(6) DEFAULT NULL COMMENT '审核时间(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `AUDIT_OPER_ID` varchar(20) DEFAULT NULL COMMENT '审核操作员',
  PRIMARY KEY (`CITY_CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='浙江省城市信息表';

-- ----------------------------
-- Records of zhejiang_city_info
-- ----------------------------
INSERT INTO `zhejiang_city_info` VALUES ('3310', '杭州市', '2019-06-14 09:50:24.000000', '1001', '2019-06-14 09:50:24.000000', '1001', null, null);
INSERT INTO `zhejiang_city_info` VALUES ('3320', '宁波市', '2019-06-14 09:51:10.000000', '1001', '2019-06-14 09:51:10.000000', '1001', null, null);
INSERT INTO `zhejiang_city_info` VALUES ('3330', '温州市', '2019-06-14 09:51:10.000000', '1001', '2019-06-14 09:51:10.000000', '1001', null, null);
INSERT INTO `zhejiang_city_info` VALUES ('3350', '嘉兴市', '2019-06-14 09:51:10.000000', '1001', '2019-06-14 09:51:10.000000', '1001', null, null);
INSERT INTO `zhejiang_city_info` VALUES ('3360', '湖州市', '2019-06-14 09:51:10.000000', '1001', '2019-06-14 09:51:10.000000', '1001', null, null);
INSERT INTO `zhejiang_city_info` VALUES ('3370', '绍兴市', '2019-06-14 09:51:10.000000', '1001', '2019-06-14 09:51:10.000000', '1001', null, null);
INSERT INTO `zhejiang_city_info` VALUES ('3380', '金华市', '2019-06-14 09:51:10.000000', '1001', '2019-06-14 09:51:10.000000', '1001', null, null);
INSERT INTO `zhejiang_city_info` VALUES ('3410', '衢州市', '2019-06-14 09:51:10.000000', '1001', '2019-06-14 09:51:10.000000', '1001', null, null);
INSERT INTO `zhejiang_city_info` VALUES ('3420', '舟山市', '2019-06-14 09:51:10.000000', '1001', '2019-06-14 09:51:10.000000', '1001', null, null);
INSERT INTO `zhejiang_city_info` VALUES ('3430', '丽水市', '2019-06-14 09:51:10.000000', '1001', '2019-06-14 09:51:10.000000', '1001', null, null);
INSERT INTO `zhejiang_city_info` VALUES ('3450', '台州市', '2019-06-14 09:51:10.000000', '1001', '2019-06-14 09:51:10.000000', '1001', null, null);
