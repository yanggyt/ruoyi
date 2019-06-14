

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dict_info
-- ----------------------------
DROP TABLE IF EXISTS `dict_info`;
CREATE TABLE `dict_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK',
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
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UK_T_PTL_DICT_INFO_ID` (`DICT_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8 COMMENT='字典信息表';

-- ----------------------------
-- Table structure for exp_trans_info
-- ----------------------------
DROP TABLE IF EXISTS `exp_trans_info`;
CREATE TABLE `exp_trans_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK',
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
  PRIMARY KEY (`ID`),
  UNIQUE KEY `T_PTL_EXP_TRANS_INFO_CODE` (`TRANS_CODE`,`EXP_CODE`),
  UNIQUE KEY `T_PTL_EXP_TRANS_INFO_ID` (`EXP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='异常信息转译表';

-- ----------------------------
-- Table structure for wish_applyer_info
-- ----------------------------
DROP TABLE IF EXISTS `wish_applyer_info`;
CREATE TABLE `wish_applyer_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `OPENID` varchar(20) NOT NULL COMMENT '微信OPENID',
  `NICKNAME` varchar(20) NOT NULL COMMENT '微信昵称',
  `ICON_URL` varchar(1000) NOT NULL COMMENT '微信头像路径',
  `WISH_ID` varchar(32) NOT NULL COMMENT '微心愿ID',
  `WISH_ORG` varchar(20) NOT NULL COMMENT '微心愿归属地区',
  `WISH_GET_STATUS` char(2) NOT NULL COMMENT '微心愿认领状态01.待审核02.认领成功03.认领失败',
  `GETTER_NAME` varchar(32) NOT NULL COMMENT '申请人认领人名称',
  `GETTER_PHONE` varchar(13) NOT NULL COMMENT '申请人认领人联系方式',
  `GETTER_DEPT` varchar(100) NOT NULL COMMENT '申请人认领人所在党组织',
  `GET_PLAN` varchar(2000) NOT NULL COMMENT '认领方案',
  `GMT_CREATE` datetime(6) NOT NULL COMMENT '创建日期(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `CREATE_OPER_ID` varchar(20) NOT NULL COMMENT '创建操作员',
  `GMT_MODIFIED` datetime(6) NOT NULL COMMENT '修改日期(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `MODIFIED_OPER_ID` varchar(20) NOT NULL COMMENT '修改操作员',
  `GMT_AUDIT` datetime(6) DEFAULT NULL COMMENT '审核时间(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `AUDIT_OPER_ID` varchar(20) DEFAULT NULL COMMENT '审核操作员',
  `IS_RECOMMEND` char(2) NOT NULL COMMENT '是否是推荐心愿0.是1.不是',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `WISH_ID` (`WISH_ID`,`OPENID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='认领人的相关信息';

-- ----------------------------
-- Table structure for wish_info
-- ----------------------------
DROP TABLE IF EXISTS `wish_info`;
CREATE TABLE `wish_info` (
  `WISH_ID` int(20) NOT NULL COMMENT '微心愿ID',
  `WISH_NAME` varchar(100) NOT NULL COMMENT '微心愿名称',
  `WISH_ORG` varchar(10) NOT NULL COMMENT '微心愿归属地区',
  `WISH_STATUS` char(2) NOT NULL COMMENT '微心愿状态',
  `WISH_TARGET` char(2) NOT NULL COMMENT '微心愿对象',
  `WISH_TYPE` char(2) NOT NULL COMMENT '微心愿类型',
  `WISH_COUNT` int(2) DEFAULT NULL COMMENT '申请认领次数',
  `WISH_PROGRESS` varchar(1000) DEFAULT NULL COMMENT '微心愿项目进展',
  `WISH_DESC` varchar(1000) NOT NULL COMMENT '微心愿详细描述',
  `APPLYER_NAME` varchar(10) NOT NULL COMMENT '申请人姓名',
  `APPLYER__PHONE` varchar(13) NOT NULL COMMENT '申请人联系方式',
  `APPLYER_COMP` varchar(50) NOT NULL COMMENT '申请人所在党组织',
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
-- Table structure for zhejiang_city_info
-- ----------------------------
DROP TABLE IF EXISTS `zhejiang_city_info`;
CREATE TABLE `zhejiang_city_info` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `CITY_CODE` varchar(10) NOT NULL COMMENT '城市代码',
  `CITY_NAME` varchar(10) NOT NULL COMMENT '城市名称',
  `GMT_CREATE` datetime(6) NOT NULL COMMENT '创建日期(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `CREATE_OPER_ID` varchar(20) NOT NULL COMMENT '创建操作员',
  `GMT_MODIFIED` datetime(6) NOT NULL COMMENT '修改日期(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `MODIFIED_OPER_ID` varchar(20) NOT NULL COMMENT '修改操作员',
  `GMT_AUDIT` datetime(6) DEFAULT NULL COMMENT '审核时间(YYYY-MM-DD hh:mm:ss UUUUUU)',
  `AUDIT_OPER_ID` varchar(20) DEFAULT NULL COMMENT '审核操作员',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='浙江省城市信息表';
