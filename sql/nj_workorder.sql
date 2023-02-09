/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : nj_workorder

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 26/09/2022 16:04:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gen_table
-- ----------------------------
DROP TABLE IF EXISTS `gen_table`;
CREATE TABLE `gen_table`  (
  `table_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表名称',
  `table_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '表描述',
  `sub_table_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '关联子表的表名',
  `sub_table_fk_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '子表关联的外键名',
  `class_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '实体类名称',
  `tpl_category` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'crud' COMMENT '使用的模板（crud单表操作 tree树表操作 sub主子表操作）',
  `package_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成包路径',
  `module_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成模块名',
  `business_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成业务名',
  `function_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能名',
  `function_author` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '生成功能作者',
  `gen_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '生成代码方式（0zip压缩包 1自定义路径）',
  `gen_path` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '/' COMMENT '生成路径（不填默认项目路径）',
  `options` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '其它生成选项',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`table_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table
-- ----------------------------
INSERT INTO `gen_table` VALUES (1, 'sys_payment_request', '请款单', 'sys_payment_request_dt1', 'request_id', 'PaymentRequest', 'sub', 'com.ruoyi.system', 'system', 'paymentRequest', '请款单', 'SKaiL', '0', '/', '{\"parentMenuId\":\"2000\",\"treeName\":\"\",\"treeParentCode\":\"\",\"parentMenuName\":\"表单\",\"treeCode\":\"\"}', 'admin', '2022-09-19 11:29:42', '', '2022-09-21 16:26:27', '');
INSERT INTO `gen_table` VALUES (2, 'sys_payment_request_dt1', '请款单dt1', '', NULL, 'PaymentRequestDt1', 'crud', 'com.ruoyi.system', 'system', 'dt1', '请款单dt1', 'SKaiL', '0', '/', '{\"parentMenuId\":\"2000\",\"treeName\":\"\",\"treeParentCode\":\"\",\"parentMenuName\":\"表单\",\"treeCode\":\"\"}', 'admin', '2022-09-19 11:29:42', '', '2022-09-19 13:48:16', '请款单dt1');
INSERT INTO `gen_table` VALUES (6, 'sys_requisition', '请购单', 'sys_requisition_dt1', 'requisition_id', 'Requisition', 'sub', 'com.ruoyi.system', 'system', 'requisition', '请购单', 'SKaiL', '0', '/', '{\"parentMenuId\":\"2000\",\"treeName\":\"\",\"treeParentCode\":\"\",\"parentMenuName\":\"表单\",\"treeCode\":\"\"}', 'admin', '2022-09-26 10:26:16', '', '2022-09-26 11:12:03', '');
INSERT INTO `gen_table` VALUES (7, 'sys_requisition_dt1', '请购单产品明细dt1', '', NULL, 'RequisitionDt1', 'crud', 'com.ruoyi.system', 'system', 'dt1', '请购单产品明细dt1', 'SKaiL', '0', '/', '{\"parentMenuId\":\"2001\",\"treeName\":\"\",\"treeParentCode\":\"\",\"parentMenuName\":\"请款单\",\"treeCode\":\"\"}', 'admin', '2022-09-26 10:26:16', '', '2022-09-26 10:26:57', '请购单产品明细dt1');
INSERT INTO `gen_table` VALUES (8, 'sys_requisition_dt2', '请购单供应商明显dt2', '', NULL, 'RequisitionDt2', 'crud', 'com.ruoyi.system', 'system', 'dt2', '请购单供应商明显dt2', 'SKaiL', '0', '/', '{\"parentMenuId\":\"\",\"treeName\":\"\",\"treeParentCode\":\"\",\"parentMenuName\":\"\",\"treeCode\":\"\"}', 'admin', '2022-09-26 10:26:16', '', '2022-09-26 10:27:13', '请购单供应商明显dt2');
INSERT INTO `gen_table` VALUES (9, 'sys_petition', '电子签呈', 'sys_petition_sign', 'petition_id', 'Petition', 'sub', 'com.ruoyi.system', 'system', 'petition', '电子签呈', 'SKaiL', '0', '/', '{\"parentMenuId\":\"2000\",\"treeName\":\"\",\"treeParentCode\":\"\",\"parentMenuName\":\"表单\",\"treeCode\":\"\"}', 'admin', '2022-09-26 11:05:40', '', '2022-09-26 11:07:17', '');
INSERT INTO `gen_table` VALUES (10, 'sys_petition_sign', '电子签呈核准人员表', '', NULL, 'PetitionSign', 'crud', 'com.ruoyi.system', 'system', 'sign', '核准人员', 'SKaiL', '0', '/', '{\"parentMenuId\":\"\",\"treeName\":\"\",\"treeParentCode\":\"\",\"parentMenuName\":\"\",\"treeCode\":\"\"}', 'admin', '2022-09-26 11:05:40', '', '2022-09-26 11:06:34', '电子签呈核准人员表');
INSERT INTO `gen_table` VALUES (11, 'sys_process_flow', '流程中间表', '', NULL, 'ProcessFlow', 'crud', 'com.ruoyi.system', 'system', 'processFlow', '流程中间', 'SKaiL', '0', '/', '{\"parentMenuId\":\"2000\",\"treeName\":\"\",\"treeParentCode\":\"\",\"parentMenuName\":\"表单\",\"treeCode\":\"\"}', 'admin', '2022-09-26 14:13:21', '', '2022-09-26 14:15:38', '流程中间表');
INSERT INTO `gen_table` VALUES (12, 'sys_payment_account', '付款账号信息表', '', NULL, 'PaymentAccount', 'crud', 'com.ruoyi.system', 'system', 'paymentAccount', '付款账号信息', 'SKaiL', '0', '/', '{\"parentMenuId\":\"2000\",\"treeName\":\"\",\"treeParentCode\":\"\",\"parentMenuName\":\"表单\",\"treeCode\":\"\"}', 'admin', '2022-09-26 15:12:04', '', '2022-09-26 15:12:55', '付款账号信息表');

-- ----------------------------
-- Table structure for gen_table_column
-- ----------------------------
DROP TABLE IF EXISTS `gen_table_column`;
CREATE TABLE `gen_table_column`  (
  `column_id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
  `table_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '归属表编号',
  `column_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列名称',
  `column_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列描述',
  `column_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '列类型',
  `java_type` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA类型',
  `java_field` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'JAVA字段名',
  `is_pk` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否主键（1是）',
  `is_increment` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否自增（1是）',
  `is_required` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否必填（1是）',
  `is_insert` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否为插入字段（1是）',
  `is_edit` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否编辑字段（1是）',
  `is_list` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否列表字段（1是）',
  `is_query` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否查询字段（1是）',
  `query_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'EQ' COMMENT '查询方式（等于、不等于、大于、小于、范围）',
  `html_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  `dict_type` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `sort` int NULL DEFAULT NULL COMMENT '排序',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`column_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 81 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '代码生成业务表字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gen_table_column
-- ----------------------------
INSERT INTO `gen_table_column` VALUES (1, '1', 'id', '', 'bigint', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (2, '1', 'payment_request_no', '单据编号', 'varchar(32)', 'String', 'paymentRequestNo', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (4, '1', 'status', '1：用户保存未提交 2：待部门主管审批 3：主管加签状态 5：财务部门财务审核 6：财务部门审核加签 7：财务总账确认 8：财务总账审核加签 9：游总核决 10：核决加签 11：出纳确认 12：出纳加签 13：财务经理审核 14：财务经理加签 15：财务总监审核 16：财务总监加签 18：前加签状态 19：否决 20：撤回 21：完成', 'smallint unsigned', 'String', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 3, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (5, '1', 'company', '公司', 'smallint', 'Long', 'company', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (7, '1', 'project_code', '项目代码', 'varchar(64)', 'String', 'projectCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (8, '1', 'applicant', '请款人', 'varchar(64)', 'String', 'applicant', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (9, '1', 'employee_no', '员工编号', 'varchar(32)', 'String', 'employeeNo', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (10, '1', 'dept', '使用部门', 'varchar(32)', 'String', 'dept', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (11, '1', 'dept_code', '部门代码', 'varchar(32)', 'String', 'deptCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (12, '1', 'offices', '办公室', 'varchar(32)', 'String', 'offices', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (13, '1', 'application_date', '申请日期', 'datetime', 'Date', 'applicationDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 11, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (14, '1', 'payment_date', '付款日期', 'datetime', 'Date', 'paymentDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 12, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (15, '1', 'instead_payment', '是否代请款(0:否 1:是)', 'smallint', 'Long', 'insteadPayment', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (16, '1', 'actual_user_no', '实际使用人编号', 'varchar(32)', 'String', 'actualUserNo', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (17, '1', 'actual_user', '实际使用人', 'varchar(64)', 'String', 'actualUser', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (18, '1', 'actual_dept', '实际使用部门', 'varchar(64)', 'String', 'actualDept', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (19, '1', 'actual_dept_code', '实际使用部门代码', 'varchar(32)', 'String', 'actualDeptCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 17, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (20, '1', 'payment_type', '请款类别(A:生产性支出-汇总请款 B:非生产性支出-持续性 C:非生产性支出-非持续性 E:交际费 X:个人报销 Y:生产性支出', 'varchar(20)', 'String', 'paymentType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 18, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (21, '1', 'summary_file', '生产性支出汇总文件地址', 'varchar(255)', 'String', 'summaryFile', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'upload', '', 19, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (22, '1', 'summary_file_name', '生产性支出汇总文件名', 'varchar(255)', 'String', 'summaryFileName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 20, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (23, '1', 'prepaid', '是否预付', 'smallint', 'Long', 'prepaid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 21, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (24, '1', 'entertainment_expense', '是否交际费(0:否 1:是)', 'smallint', 'Long', 'entertainmentExpense', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 22, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (25, '1', 'payment_amount', '合计应付金额', 'bigint', 'String', 'paymentAmount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 23, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (32, '1', 'payment_method', '付款方式(1:转账汇款 2:其他)', 'smallint', 'Long', 'paymentMethod', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 24, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (33, '1', 'others', '付款方式为其他时需要填写的备注', 'varchar(255)', 'String', 'others', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 25, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (34, '1', 'payee', '收款人', 'varchar(255)', 'String', 'payee', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 26, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (35, '1', 'bank_name', '开户行', 'varchar(255)', 'String', 'bankName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 27, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (36, '1', 'account_number', '账号', 'varchar(255)', 'String', 'accountNumber', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 28, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (37, '1', 'address', '收款人地址', 'varchar(255)', 'String', 'address', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 29, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (38, '1', 'swift_code', '开户行代码', 'varchar(255)', 'String', 'swiftCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 30, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (39, '1', 'bank_address', '开户行地址', 'varchar(255)', 'String', 'bankAddress', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 31, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (43, '1', 'acct_supervisor', '财务主管', 'varchar(32)', 'String', 'acctSupervisor', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 32, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (44, '1', 'cashier', '出纳', 'varchar(32)', 'String', 'cashier', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 33, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (45, '1', 'approved_by', '核准', 'varchar(32)', 'String', 'approvedBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 34, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (46, '1', 'rechecked_by', '复核', 'varchar(32)', 'String', 'recheckedBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 35, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (47, '1', 'checked_by', '初核', 'varchar(32)', 'String', 'checkedBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 36, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (48, '1', 'create_by_code', '创建人code', 'varchar(64)', 'String', 'createByCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 37, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (49, '1', 'send_to_code', '将要处理者的code，如果是发起人提交，里面将保存自己', 'varchar(64)', 'String', 'sendToCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 38, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (50, '1', 'handles_code', '已经处理完的code，存储处理历史相关人员的code', 'varchar(255)', 'String', 'handlesCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 39, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (51, '1', 'create_by', '创建人名字', 'varchar(64)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 52, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (52, '1', 'submit_time', '提交时间', 'datetime', 'Date', 'submitTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 40, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (53, '1', 'old_send_to_code', '用来前加签保存上一个的send code', 'varchar(32)', 'String', 'oldSendToCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 41, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (54, '1', 'old_status', '用来前加签保存上一个状态', 'smallint', 'Long', 'oldStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 42, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (55, '1', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 53, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (56, '1', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 54, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (57, '1', 'close_file', '上传文件导致关闭文件地址', 'varchar(255)', 'String', 'closeFile', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'upload', '', 43, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (58, '1', 'close_file_name', '上传文件导致关闭文件名', 'varchar(255)', 'String', 'closeFileName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 44, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (59, '1', 'del_flag', '删除标志（0代表删除 1代表存在）', 'smallint', 'Long', 'delFlag', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 45, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (60, '1', 'before_send_to_code', '用来前加签保存上一个的send code', 'varchar(32)', 'String', 'beforeSendToCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 46, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (61, '1', 'before_status', '用来前加签保存上一个状态', 'smallint', 'Long', 'beforeStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 47, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (62, '1', 'after_send_to_code', '用来后加签保存下一个的send code', 'varchar(32)', 'String', 'afterSendToCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 48, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (63, '1', 'after_status', '用来后加签保存上一个状态', 'smallint', 'Long', 'afterStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 49, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (64, '1', 'reserve_send_to_code', '用来保留保存下一个的send code', 'varchar(32)', 'String', 'reserveSendToCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 50, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (65, '1', 'reserve_status', '用来保留保存上一个状态', 'smallint', 'Long', 'reserveStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 51, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (66, '1', 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 55, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-21 16:26:27');
INSERT INTO `gen_table_column` VALUES (67, '2', 'id', '', 'bigint', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-19 13:48:16');
INSERT INTO `gen_table_column` VALUES (68, '2', 'request_id', '关联id', 'bigint', 'Long', 'requestId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-19 13:48:16');
INSERT INTO `gen_table_column` VALUES (69, '2', 'project_code', '项目编号', 'varchar(255)', 'String', 'projectCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-19 13:48:16');
INSERT INTO `gen_table_column` VALUES (70, '2', 'content', '摘 要 说 明', 'varchar(255)', 'String', 'content', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'summernote', '', 4, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-19 13:48:16');
INSERT INTO `gen_table_column` VALUES (71, '2', 'unit', '单位', 'varchar(32)', 'String', 'unit', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-19 13:48:16');
INSERT INTO `gen_table_column` VALUES (72, '2', 'quantity', '数量', 'varchar(32)', 'String', 'quantity', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-19 13:48:16');
INSERT INTO `gen_table_column` VALUES (73, '2', 'unit_price', '单价', 'bigint', 'String', 'unitPrice', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-19 13:48:16');
INSERT INTO `gen_table_column` VALUES (74, '2', 'total_amount', '总金额', 'bigint', 'String', 'totalAmount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-19 13:48:16');
INSERT INTO `gen_table_column` VALUES (75, '2', 'ratio', '预付比例', 'bigint', 'String', 'ratio', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-19 13:48:16');
INSERT INTO `gen_table_column` VALUES (76, '2', 'currency', '币别', 'smallint', 'Long', 'currency', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-19 13:48:16');
INSERT INTO `gen_table_column` VALUES (77, '2', 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 11, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-19 13:48:16');
INSERT INTO `gen_table_column` VALUES (78, '2', 'create_time', '创建日期', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 12, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-19 13:48:16');
INSERT INTO `gen_table_column` VALUES (79, '2', 'update_time', '更新日期', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 13, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-19 13:48:16');
INSERT INTO `gen_table_column` VALUES (80, '2', 'del_flag', '删除标志（0代表删除 1代表存在）', 'varchar(2)', 'String', 'delFlag', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 14, 'admin', '2022-09-19 11:29:42', NULL, '2022-09-19 13:48:16');
INSERT INTO `gen_table_column` VALUES (152, '6', 'id', '', 'bigint', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (153, '6', 'requisition_no', '唯一编号', 'varchar(64)', 'String', 'requisitionNo', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (154, '6', 'status', '状态', 'smallint', 'Long', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 3, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (155, '6', 'old_status', '老状态', 'smallint', 'Long', 'oldStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 4, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (156, '6', 'old_retain_status', '保留状态', 'varchar(8)', 'String', 'oldRetainStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 5, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (157, '6', 'old_send_to_code', '老将要处理者的code', 'varchar(255)', 'String', 'oldSendToCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (158, '6', 'employee_no', '员工编号', 'varchar(64)', 'String', 'employeeNo', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (159, '6', 'hr_sign', '新员工入职hr签字', 'varchar(255)', 'String', 'hrSign', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (160, '6', 'new_staff', '是否新员工', 'smallint', 'Long', 'newStaff', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (161, '6', 'product_line', '产品线', 'varchar(255)', 'String', 'productLine', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (162, '6', 'product_line1', '产品线详细', 'varchar(64)', 'String', 'productLine1', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (163, '6', 'product_line_other', '产品线其他', 'varchar(64)', 'String', 'productLineOther', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 12, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (164, '6', 'user_department', '部门编号', 'varchar(64)', 'String', 'userDepartment', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (165, '6', 'company', '公司', 'smallint', 'Long', 'company', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (166, '6', 'purchase_code', '申购单号', 'varchar(255)', 'String', 'purchaseCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (167, '6', 'department_code', '地区部门编号', 'varchar(255)', 'String', 'departmentCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (168, '6', 'office', '地区', 'varchar(255)', 'String', 'office', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 17, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (169, '6', 'user_name', '员工姓名', 'varchar(64)', 'String', 'userName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 18, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (170, '6', 'user_date', '需求日期', 'datetime', 'Date', 'userDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 19, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (171, '6', 'type_of_application', '申请类别', 'smallint', 'Long', 'typeOfApplication', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 20, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (172, '6', 'asset_management', '资产管理人员，可填可不填，如果填1，则是桌面，如果填2则是测试，需要转到相应的人员进行审核', 'smallint', 'Long', 'assetManagement', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 21, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (173, '6', 'category', '所属类别', 'smallint', 'Long', 'category', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 22, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (174, '6', 'reason_for_purchase', '请购原因', 'text', 'String', 'reasonForPurchase', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 23, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (175, '6', 'price_comparison', '是否须比议价', 'smallint', 'Long', 'priceComparison', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 24, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (176, '6', 'special_reason', '特殊原因说明', 'text', 'String', 'specialReason', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 25, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (177, '6', 'total_amount', 'dt1合计金额', 'varchar(0)', 'String', 'totalAmount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 26, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (179, '6', 'submit_time', '提交时间，用来保存提交流程的时刻', 'datetime', 'Date', 'submitTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 27, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (180, '6', 'create_by_code', '创建人code', 'varchar(64)', 'String', 'createByCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 28, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (181, '6', 'send_to_code', '将要处理者的code，如果是发起人提交，里面将保存自己', 'varchar(255)', 'String', 'sendToCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 29, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (182, '6', 'handles_code', '已经处理完的code，存储处理历史相关人员的code', 'varchar(255)', 'String', 'handlesCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 30, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (183, '6', 'create_by', '创建人名字', 'varchar(255)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 31, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (184, '6', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 32, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (185, '6', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 33, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (186, '6', 'close_file', '上传文件导致关闭文件地址', 'varchar(128)', 'String', 'closeFile', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'upload', '', 34, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (187, '6', 'close_file_name', '上传文件导致关闭文件名', 'varchar(128)', 'String', 'closeFileName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 35, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (188, '6', 'del_flag', '删除标志（0代表删除 1代表存在）', 'smallint', 'Long', 'delFlag', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 36, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (189, '6', 'before_send_to_code', '用来前加签保存上一个的send code', 'varchar(32)', 'String', 'beforeSendToCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 37, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (190, '6', 'before_status', '用来前加签保存上一个状态', 'smallint', 'Long', 'beforeStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 38, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (191, '6', 'after_send_to_code', '用来后加签保存下一个的send code', 'varchar(32)', 'String', 'afterSendToCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 39, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (192, '6', 'after_status', '用来后加签保存上一个状态', 'smallint', 'Long', 'afterStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 40, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (193, '6', 'reserve_send_to_code', '用来保留保存下一个的send code', 'varchar(32)', 'String', 'reserveSendToCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 41, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (194, '6', 'reserve_status', '用来保留保存上一个状态', 'smallint', 'Long', 'reserveStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 42, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 11:12:03');
INSERT INTO `gen_table_column` VALUES (195, '7', 'id', '', 'bigint', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:26:57');
INSERT INTO `gen_table_column` VALUES (196, '7', 'requisition_id', '主表id', 'bigint', 'Long', 'requisitionId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:26:57');
INSERT INTO `gen_table_column` VALUES (197, '7', 'project_code', '项目编号', 'varchar(255)', 'String', 'projectCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:26:57');
INSERT INTO `gen_table_column` VALUES (198, '7', 'project_name', '项目名称', 'varchar(255)', 'String', 'projectName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:26:57');
INSERT INTO `gen_table_column` VALUES (199, '7', 'specifications', '规格说明', 'text', 'String', 'specifications', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 5, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:26:57');
INSERT INTO `gen_table_column` VALUES (200, '7', 'reason', '购买原因', 'text', 'String', 'reason', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 6, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:26:57');
INSERT INTO `gen_table_column` VALUES (201, '7', 'quantity', '购买数量', 'int', 'Long', 'quantity', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:26:57');
INSERT INTO `gen_table_column` VALUES (202, '7', 'unit_price', '单价', 'varchar(0)', 'String', 'unitPrice', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:26:57');
INSERT INTO `gen_table_column` VALUES (203, '7', 'amount', '金额', 'varchar(0)', 'String', 'amount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:26:57');
INSERT INTO `gen_table_column` VALUES (204, '7', 'coin', '购买币别', 'smallint', 'Long', 'coin', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:26:57');
INSERT INTO `gen_table_column` VALUES (205, '7', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 11, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:26:57');
INSERT INTO `gen_table_column` VALUES (206, '7', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 12, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:26:57');
INSERT INTO `gen_table_column` VALUES (207, '8', 'id', '', 'bigint', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:27:13');
INSERT INTO `gen_table_column` VALUES (208, '8', 'requisition_id', '主表id', 'bigint', 'Long', 'requisitionId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:27:13');
INSERT INTO `gen_table_column` VALUES (209, '8', 'suppliers', '供应商是否选中', 'smallint', 'Long', 'suppliers', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:27:13');
INSERT INTO `gen_table_column` VALUES (210, '8', 'project_name', '项目名称', 'varchar(255)', 'String', 'projectName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:27:13');
INSERT INTO `gen_table_column` VALUES (211, '8', 'suppliers_name', '供应商名称', 'varchar(64)', 'String', 'suppliersName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 5, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:27:13');
INSERT INTO `gen_table_column` VALUES (212, '8', 'suppliers_code', '供应商编号', 'varchar(64)', 'String', 'suppliersCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:27:13');
INSERT INTO `gen_table_column` VALUES (213, '8', 'tax_rate', '税率', 'varchar(0)', 'String', 'taxRate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:27:13');
INSERT INTO `gen_table_column` VALUES (214, '8', 'coin', '币别', 'smallint', 'Long', 'coin', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:27:13');
INSERT INTO `gen_table_column` VALUES (215, '8', 'total_amount', '总金额', 'varchar(0)', 'String', 'totalAmount', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:27:13');
INSERT INTO `gen_table_column` VALUES (216, '8', 'untaxed_unit_price', '未税单价', 'varchar(0)', 'String', 'untaxedUnitPrice', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:27:13');
INSERT INTO `gen_table_column` VALUES (217, '8', 'remark', '备注', 'varchar(255)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 11, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:27:13');
INSERT INTO `gen_table_column` VALUES (218, '8', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 12, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:27:13');
INSERT INTO `gen_table_column` VALUES (219, '8', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 13, 'admin', '2022-09-26 10:26:16', NULL, '2022-09-26 10:27:13');
INSERT INTO `gen_table_column` VALUES (220, '9', 'id', 'id', 'bigint', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (221, '9', 'type_type', '类型', 'smallint', 'Long', 'typeType', '0', '0', '1', '1', '1', '1', '1', 'EQ', 'select', '', 2, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (222, '9', 'com_no', '表单编号', 'char(32)', 'String', 'comNo', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (223, '9', 'topic_obj', '主题', 'varchar(1000)', 'String', 'topicObj', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 4, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (224, '9', 'explanation', '说明文本', 'varchar(1000)', 'String', 'explanation', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 5, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (225, '9', 'seal_type', '印章类型(公章 1 ,法人章2 ,财务章 3 ,合同章 4 ,法人章(经济部 小章)5 ,法人章(经济部 小章) 6 ,印鉴大章 7 印鉴小章8 )', 'varchar(32)', 'String', 'sealType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 6, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (226, '9', 'curr', '币别', 'varchar(255)', 'String', 'curr', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (227, '9', 'money_m', '金额', 'varchar(20)', 'String', 'moneyM', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (228, '9', 'file_id', '文件id', 'char(20)', 'String', 'fileId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (229, '9', 'seal', '使用印监', 'varchar(1000)', 'String', 'seal', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 10, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (230, '9', 'seal_explain', '用印使用说明', 'varchar(255)', 'String', 'sealExplain', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (231, '9', 'seal_date', '用印借出日期', 'datetime', 'Date', 'sealDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 12, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (232, '9', 'more_explain', '补充内容', 'varchar(1000)', 'String', 'moreExplain', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'textarea', '', 13, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (233, '9', 'company', '公司代码', 'varchar(50)', 'String', 'company', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 14, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (234, '9', 'dept', '部门', 'varchar(20)', 'String', 'dept', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (235, '9', 'sid', '员工编号', 'varchar(20)', 'String', 'sid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 16, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (236, '9', 'applyname', '申请人名字', 'varchar(255)', 'String', 'applyname', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 17, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (237, '9', 'applydate', '申请日期', 'datetime', 'Date', 'applydate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 18, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (238, '9', 'deptmanager', '部门主管', 'varchar(20)', 'String', 'deptmanager', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 19, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (239, '9', 'deptmanager_sta', '部门主管审核意见( 1 同意 2 不同意)', 'smallint', 'Long', 'deptmanagerSta', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 20, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (240, '9', 'deptmanager_idea', '主管审核备注', 'varchar(255)', 'String', 'deptmanagerIdea', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 21, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (241, '9', 'deptmanage_date', '审核日期', 'datetime', 'Date', 'deptmanageDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 22, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (242, '9', 'hr', 'HR选择', 'varchar(20)', 'String', 'hr', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 23, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (243, '9', 'hr_sta', 'hr意见(1 同意,2 不同意)', 'smallint', 'Long', 'hrSta', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 24, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (244, '9', 'hr_idea', 'hr审核备注', 'varchar(255)', 'String', 'hrIdea', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 25, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (245, '9', 'hr_sta_date', '审核日期', 'datetime', 'Date', 'hrStaDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 26, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (246, '9', 'law', '法务', 'varchar(255)', 'String', 'law', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 27, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (247, '9', 'law_sta', '法务意见', 'smallint', 'Long', 'lawSta', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 28, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (248, '9', 'law_idea', '法务审核备注', 'varchar(255)', 'String', 'lawIdea', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 29, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (249, '9', 'law_date', '法务审核时间', 'datetime', 'Date', 'lawDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 30, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (250, '9', 'ac_officer', '会计主管', 'varchar(255)', 'String', 'acOfficer', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 31, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (251, '9', 'ac_officer_date', '审核日期', 'datetime', 'Date', 'acOfficerDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 32, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (252, '9', 'ac_officer_idea', '会计主管审核备注', 'varchar(255)', 'String', 'acOfficerIdea', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 33, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (253, '9', 'ac_officer_sta', '会计主管审核意见', 'smallint', 'Long', 'acOfficerSta', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 34, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (254, '9', 'fc_manager', '财务总账', 'varchar(255)', 'String', 'fcManager', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 35, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (255, '9', 'fc_manager_sta', '财务总账意见', 'smallint', 'Long', 'fcManagerSta', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 36, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (256, '9', 'fc_manager_idea', '财务总账审核备注', 'varchar(255)', 'String', 'fcManagerIdea', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 37, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (257, '9', 'fc_manager_date', '财务总账审核日期', 'datetime', 'Date', 'fcManagerDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 38, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (258, '9', 'cfco_manager', '财务长', 'varchar(255)', 'String', 'cfcoManager', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 39, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (259, '9', 'cfco_manager_sta', '财务长审核意见', 'smallint', 'Long', 'cfcoManagerSta', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 40, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (260, '9', 'cfco_manager_idea', '财务长审核备注', 'varchar(255)', 'String', 'cfcoManagerIdea', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 41, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (261, '9', 'cfco_manager_date', '财务长审核日期', 'datetime', 'Date', 'cfcoManagerDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 42, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (262, '9', 'gm', '总经理', 'varchar(255)', 'String', 'gm', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 43, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (263, '9', 'gm_sta', '总经理审核意见', 'smallint', 'Long', 'gmSta', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 44, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (264, '9', 'gm_idea', '总经理审核备注', 'varchar(255)', 'String', 'gmIdea', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 45, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (265, '9', 'gm_date', '总经理审核日期', 'datetime', 'Date', 'gmDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 46, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (266, '9', 'creat_by', '创建人', 'varchar(32)', 'String', 'creatBy', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 47, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (267, '9', 'creat_date', '创建日期', 'datetime', 'Date', 'creatDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 48, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (268, '9', 'update_by', '更新者', 'varchar(32)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 49, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (269, '9', 'update_date', '更新日期', 'datetime', 'Date', 'updateDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 50, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (270, '9', 'status', '状态(.....)', 'smallint', 'Long', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 51, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (271, '9', 'from_sendto', '发送接收者', 'varchar(255)', 'String', 'fromSendto', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 52, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (272, '9', 'form_sta', '表单状态(10  未完成 8 完成)', 'smallint', 'Long', 'formSta', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 53, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (273, '9', 'old_sta', '加签状态( 1 加签  0 非加签)', 'smallint', 'Long', 'oldSta', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 54, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (274, '9', 'addaudit_sta', '1 是前加签 2 是后加签', 'smallint', 'Long', 'addauditSta', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 55, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (275, '9', 'before_send_to_code', '用来前加签保存上一个的send code', 'varchar(255)', 'String', 'beforeSendToCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 56, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (276, '9', 'before_status', '用来前加签保存上一个状态', 'smallint', 'Long', 'beforeStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 57, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (277, '9', 'after_send_to_code', '用来后加签保存下一个的send code', 'varchar(255)', 'String', 'afterSendToCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 58, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (278, '9', 'after_status', '用来后加签保存上一个状态', 'smallint', 'Long', 'afterStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 59, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (279, '9', 'flag', '0 隐藏  1  显示', 'int', 'Long', 'flag', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 60, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (280, '9', 'recall_date', '撤回时间', 'datetime', 'Date', 'recallDate', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 61, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (281, '9', 'recall_reason', '撤回原因', 'varchar(255)', 'String', 'recallReason', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 62, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (282, '9', 'close_file', '上传文件导致关闭文件地址', 'varchar(128)', 'String', 'closeFile', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'upload', '', 63, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (283, '9', 'close_file_name', '上传文件导致关闭文件名字', 'varchar(128)', 'String', 'closeFileName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 64, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:07:18');
INSERT INTO `gen_table_column` VALUES (284, '10', 'id', '', 'bigint', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:06:34');
INSERT INTO `gen_table_column` VALUES (285, '10', 'sign_type', '签核角色', 'int', 'Long', 'signType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 2, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:06:34');
INSERT INTO `gen_table_column` VALUES (286, '10', 'add_name', '签核人员', 'varchar(255)', 'String', 'addName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 3, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:06:34');
INSERT INTO `gen_table_column` VALUES (287, '10', 'sid', '工号', 'varchar(20)', 'String', 'sid', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:06:34');
INSERT INTO `gen_table_column` VALUES (288, '10', 'result', '审核结果', 'int', 'Long', 'result', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:06:34');
INSERT INTO `gen_table_column` VALUES (289, '10', 'remark', '备注意见', 'varchar(255)', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'input', '', 6, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:06:34');
INSERT INTO `gen_table_column` VALUES (290, '10', 'add_time', '签核时间', 'datetime', 'Date', 'addTime', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'datetime', '', 7, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:06:34');
INSERT INTO `gen_table_column` VALUES (291, '10', 'tosend', '发送到', 'varchar(255)', 'String', 'tosend', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:06:34');
INSERT INTO `gen_table_column` VALUES (292, '10', 'petition_id', '关联id', 'bigint', 'Long', 'petitionId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 9, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:06:34');
INSERT INTO `gen_table_column` VALUES (293, '10', 'fromsend', '发送者', 'varchar(255)', 'String', 'fromsend', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 10, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:06:34');
INSERT INTO `gen_table_column` VALUES (294, '10', 'old_status', '老状态', 'int', 'Long', 'oldStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 11, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:06:34');
INSERT INTO `gen_table_column` VALUES (295, '10', 'new_status', '', 'int', 'Long', 'newStatus', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 12, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:06:34');
INSERT INTO `gen_table_column` VALUES (296, '10', 'last_add', '1 是最后一个后加签人员', 'int', 'Long', 'lastAdd', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 13, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:06:34');
INSERT INTO `gen_table_column` VALUES (297, '10', 'create_by', '创建者', 'varchar(255)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 14, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:06:34');
INSERT INTO `gen_table_column` VALUES (298, '10', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 15, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:06:34');
INSERT INTO `gen_table_column` VALUES (299, '10', 'del_flag', '1是删除不会显示', 'int', 'Long', 'delFlag', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 16, 'admin', '2022-09-26 11:05:40', NULL, '2022-09-26 11:06:34');
INSERT INTO `gen_table_column` VALUES (300, '11', 'id', '', 'int', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-09-26 14:13:21', NULL, '2022-09-26 14:15:38');
INSERT INTO `gen_table_column` VALUES (301, '11', 'order_id', '关联流程id', 'int', 'Long', 'orderId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2022-09-26 14:13:21', NULL, '2022-09-26 14:15:38');
INSERT INTO `gen_table_column` VALUES (302, '11', 'create_by_id', '创建人id', 'int', 'Long', 'createById', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2022-09-26 14:13:21', NULL, '2022-09-26 14:15:38');
INSERT INTO `gen_table_column` VALUES (303, '11', 'create_by_code', '创建人UserCode', 'varchar(255)', 'String', 'createByCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 4, 'admin', '2022-09-26 14:13:21', NULL, '2022-09-26 14:15:38');
INSERT INTO `gen_table_column` VALUES (304, '11', 'create_by', '创建人', 'varchar(255)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 5, 'admin', '2022-09-26 14:13:21', NULL, '2022-09-26 14:15:38');
INSERT INTO `gen_table_column` VALUES (305, '11', 'send_to_id', '发送到id', 'varchar(255)', 'String', 'sendToId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2022-09-26 14:13:21', NULL, '2022-09-26 14:15:38');
INSERT INTO `gen_table_column` VALUES (306, '11', 'status', '流程当前的状态', 'int', 'Long', 'status', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'radio', '', 7, 'admin', '2022-09-26 14:13:21', NULL, '2022-09-26 14:15:38');
INSERT INTO `gen_table_column` VALUES (307, '11', 'endorsement_name', '加签人员的名字', 'varchar(255)', 'String', 'endorsementName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 8, 'admin', '2022-09-26 14:13:21', NULL, '2022-09-26 14:15:38');
INSERT INTO `gen_table_column` VALUES (308, '11', 'remark', '备注', 'text', 'String', 'remark', '0', '0', NULL, '1', '1', '1', NULL, 'EQ', 'textarea', '', 9, 'admin', '2022-09-26 14:13:21', NULL, '2022-09-26 14:15:38');
INSERT INTO `gen_table_column` VALUES (309, '11', 'order_type', '流程类型', 'int', 'Long', 'orderType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 10, 'admin', '2022-09-26 14:13:21', NULL, '2022-09-26 14:15:38');
INSERT INTO `gen_table_column` VALUES (310, '11', 'audit_result', '审核结果，不同流程不一样', 'smallint', 'Long', 'auditResult', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 11, 'admin', '2022-09-26 14:13:21', NULL, '2022-09-26 14:15:38');
INSERT INTO `gen_table_column` VALUES (311, '11', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 12, 'admin', '2022-09-26 14:13:21', NULL, '2022-09-26 14:15:38');
INSERT INTO `gen_table_column` VALUES (312, '11', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 13, 'admin', '2022-09-26 14:13:21', NULL, '2022-09-26 14:15:38');
INSERT INTO `gen_table_column` VALUES (313, '11', 'order_node_type', '流程中间表新增某一个流程当前所处的节点', 'int', 'Long', 'orderNodeType', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'select', '', 14, 'admin', '2022-09-26 14:13:21', NULL, '2022-09-26 14:15:38');
INSERT INTO `gen_table_column` VALUES (314, '11', 'review_order', '审核顺序', 'char(2)', 'String', 'reviewOrder', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 15, 'admin', '2022-09-26 14:13:21', NULL, '2022-09-26 14:15:38');
INSERT INTO `gen_table_column` VALUES (315, '11', 'del_flag', '删除标志(0 隐藏 1 显示)', 'smallint(1) unsigned zerofill', 'Integer', 'delFlag', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 16, 'admin', '2022-09-26 14:13:21', NULL, '2022-09-26 14:15:38');
INSERT INTO `gen_table_column` VALUES (316, '12', 'id', 'ID', 'bigint', 'Long', 'id', '1', '1', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 1, 'admin', '2022-09-26 15:12:04', NULL, '2022-09-26 15:12:55');
INSERT INTO `gen_table_column` VALUES (317, '12', 'parent_id', '父级ID', 'bigint', 'Long', 'parentId', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 2, 'admin', '2022-09-26 15:12:04', NULL, '2022-09-26 15:12:55');
INSERT INTO `gen_table_column` VALUES (318, '12', 'payee', '收款人', 'varchar(255)', 'String', 'payee', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 3, 'admin', '2022-09-26 15:12:04', NULL, '2022-09-26 15:12:55');
INSERT INTO `gen_table_column` VALUES (319, '12', 'bank_name', '收款银行', 'varchar(255)', 'String', 'bankName', '0', '0', NULL, '1', '1', '1', '1', 'LIKE', 'input', '', 4, 'admin', '2022-09-26 15:12:04', NULL, '2022-09-26 15:12:55');
INSERT INTO `gen_table_column` VALUES (320, '12', 'account_number', '收款账号', 'varchar(255)', 'String', 'accountNumber', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 5, 'admin', '2022-09-26 15:12:04', NULL, '2022-09-26 15:12:55');
INSERT INTO `gen_table_column` VALUES (321, '12', 'address', '收款人地址', 'varchar(255)', 'String', 'address', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 6, 'admin', '2022-09-26 15:12:04', NULL, '2022-09-26 15:12:55');
INSERT INTO `gen_table_column` VALUES (322, '12', 'swift_code', '开户行代码', 'varchar(255)', 'String', 'swiftCode', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 7, 'admin', '2022-09-26 15:12:04', NULL, '2022-09-26 15:12:55');
INSERT INTO `gen_table_column` VALUES (323, '12', 'bank_address', '开户行地址', 'varchar(255)', 'String', 'bankAddress', '0', '0', NULL, '1', '1', '1', '1', 'EQ', 'input', '', 8, 'admin', '2022-09-26 15:12:04', NULL, '2022-09-26 15:12:55');
INSERT INTO `gen_table_column` VALUES (324, '12', 'create_by', '创建者', 'varchar(255)', 'String', 'createBy', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 9, 'admin', '2022-09-26 15:12:04', NULL, '2022-09-26 15:12:55');
INSERT INTO `gen_table_column` VALUES (325, '12', 'create_time', '创建时间', 'datetime', 'Date', 'createTime', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'datetime', '', 10, 'admin', '2022-09-26 15:12:04', NULL, '2022-09-26 15:12:55');
INSERT INTO `gen_table_column` VALUES (326, '12', 'update_time', '更新时间', 'datetime', 'Date', 'updateTime', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'datetime', '', 11, 'admin', '2022-09-26 15:12:04', NULL, '2022-09-26 15:12:55');
INSERT INTO `gen_table_column` VALUES (327, '12', 'update_by', '更信者', 'varchar(255)', 'String', 'updateBy', '0', '0', NULL, '1', '1', NULL, NULL, 'EQ', 'input', '', 12, 'admin', '2022-09-26 15:12:04', NULL, '2022-09-26 15:12:55');
INSERT INTO `gen_table_column` VALUES (328, '12', 'del_flag', '删除标志（0代表删除 1代表存在）', 'bigint', 'Long', 'delFlag', '0', '0', NULL, '1', NULL, NULL, NULL, 'EQ', 'input', '', 13, 'admin', '2022-09-26 15:12:04', NULL, '2022-09-26 15:12:55');

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `blob_data` blob NULL COMMENT '存放持久化Trigger对象',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Blob类型的触发器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `calendar_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日历名称',
  `calendar` blob NOT NULL COMMENT '存放持久化calendar对象',
  PRIMARY KEY (`sched_name`, `calendar_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '日历信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `cron_expression` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'cron表达式',
  `time_zone_id` varchar(80) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '时区',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = 'Cron类型的触发器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `entry_id` varchar(95) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度器实例id',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `instance_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度器实例名',
  `fired_time` bigint NOT NULL COMMENT '触发的时间',
  `sched_time` bigint NOT NULL COMMENT '定时器制定的时间',
  `priority` int NOT NULL COMMENT '优先级',
  `state` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态',
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务组名',
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否并发',
  `requests_recovery` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '是否接受恢复执行',
  PRIMARY KEY (`sched_name`, `entry_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '已触发的触发器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `description` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '相关介绍',
  `job_class_name` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '执行任务类名称',
  `is_durable` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否持久化',
  `is_nonconcurrent` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否并发',
  `is_update_data` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否更新数据',
  `requests_recovery` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否接受恢复执行',
  `job_data` blob NULL COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '任务详细信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `lock_name` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '悲观锁名称',
  PRIMARY KEY (`sched_name`, `lock_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '存储的悲观锁信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  PRIMARY KEY (`sched_name`, `trigger_group`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '暂停的触发器表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `instance_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实例名称',
  `last_checkin_time` bigint NOT NULL COMMENT '上次检查时间',
  `checkin_interval` bigint NOT NULL COMMENT '检查间隔时间',
  PRIMARY KEY (`sched_name`, `instance_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '调度器状态表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `repeat_count` bigint NOT NULL COMMENT '重复的次数统计',
  `repeat_interval` bigint NOT NULL COMMENT '重复的间隔时间',
  `times_triggered` bigint NOT NULL COMMENT '已经触发的次数',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '简单触发器的信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_name的外键',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_triggers表trigger_group的外键',
  `str_prop_1` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'String类型的trigger的第一个参数',
  `str_prop_2` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'String类型的trigger的第二个参数',
  `str_prop_3` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'String类型的trigger的第三个参数',
  `int_prop_1` int NULL DEFAULT NULL COMMENT 'int类型的trigger的第一个参数',
  `int_prop_2` int NULL DEFAULT NULL COMMENT 'int类型的trigger的第二个参数',
  `long_prop_1` bigint NULL DEFAULT NULL COMMENT 'long类型的trigger的第一个参数',
  `long_prop_2` bigint NULL DEFAULT NULL COMMENT 'long类型的trigger的第二个参数',
  `dec_prop_1` decimal(13, 4) NULL DEFAULT NULL COMMENT 'decimal类型的trigger的第一个参数',
  `dec_prop_2` decimal(13, 4) NULL DEFAULT NULL COMMENT 'decimal类型的trigger的第二个参数',
  `bool_prop_1` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Boolean类型的trigger的第一个参数',
  `bool_prop_2` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'Boolean类型的trigger的第二个参数',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `trigger_name`, `trigger_group`) REFERENCES `qrtz_triggers` (`sched_name`, `trigger_name`, `trigger_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '同步机制的行锁表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `sched_name` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调度名称',
  `trigger_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '触发器的名字',
  `trigger_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '触发器所属组的名字',
  `job_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_job_details表job_name的外键',
  `job_group` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'qrtz_job_details表job_group的外键',
  `description` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '相关介绍',
  `next_fire_time` bigint NULL DEFAULT NULL COMMENT '上一次触发时间（毫秒）',
  `prev_fire_time` bigint NULL DEFAULT NULL COMMENT '下一次触发时间（默认为-1表示不触发）',
  `priority` int NULL DEFAULT NULL COMMENT '优先级',
  `trigger_state` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '触发器状态',
  `trigger_type` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '触发器的类型',
  `start_time` bigint NOT NULL COMMENT '开始时间',
  `end_time` bigint NULL DEFAULT NULL COMMENT '结束时间',
  `calendar_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日程表名称',
  `misfire_instr` smallint NULL DEFAULT NULL COMMENT '补偿执行的策略',
  `job_data` blob NULL COMMENT '存放持久化job对象',
  PRIMARY KEY (`sched_name`, `trigger_name`, `trigger_group`) USING BTREE,
  INDEX `sched_name`(`sched_name`, `job_name`, `job_group`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`sched_name`, `job_name`, `job_group`) REFERENCES `qrtz_job_details` (`sched_name`, `job_name`, `job_group`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '触发器详细信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
  `config_id` int NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_config
-- ----------------------------
INSERT INTO `sys_config` VALUES (1, '主框架页-默认皮肤样式名称', 'sys.index.skinName', 'skin-blue', 'Y', 'admin', '2022-09-19 10:49:17', '', NULL, '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
INSERT INTO `sys_config` VALUES (2, '用户管理-账号初始密码', 'sys.user.initPassword', '123456', 'Y', 'admin', '2022-09-19 10:49:17', '', NULL, '初始化密码 123456');
INSERT INTO `sys_config` VALUES (3, '主框架页-侧边栏主题', 'sys.index.sideTheme', 'theme-dark', 'Y', 'admin', '2022-09-19 10:49:17', '', NULL, '深黑主题theme-dark，浅色主题theme-light，深蓝主题theme-blue');
INSERT INTO `sys_config` VALUES (4, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false', 'Y', 'admin', '2022-09-19 10:49:17', '', NULL, '是否开启注册用户功能（true开启，false关闭）');
INSERT INTO `sys_config` VALUES (5, '用户管理-密码字符范围', 'sys.account.chrtype', '0', 'Y', 'admin', '2022-09-19 10:49:17', '', NULL, '默认任意字符范围，0任意（密码可以输入任意字符），1数字（密码只能为0-9数字），2英文字母（密码只能为a-z和A-Z字母），3字母和数字（密码必须包含字母，数字）,4字母数字和特殊字符（目前支持的特殊字符包括：~!@#$%^&*()-=_+）');
INSERT INTO `sys_config` VALUES (6, '用户管理-初始密码修改策略', 'sys.account.initPasswordModify', '0', 'Y', 'admin', '2022-09-19 10:49:17', '', NULL, '0：初始密码修改策略关闭，没有任何提示，1：提醒用户，如果未修改初始密码，则在登录时就会提醒修改密码对话框');
INSERT INTO `sys_config` VALUES (7, '用户管理-账号密码更新周期', 'sys.account.passwordValidateDays', '0', 'Y', 'admin', '2022-09-19 10:49:17', '', NULL, '密码更新周期（填写数字，数据初始化值为0不限制，若修改必须为大于0小于365的正整数），如果超过这个周期登录系统时，则在登录时就会提醒修改密码对话框');
INSERT INTO `sys_config` VALUES (8, '主框架页-菜单导航显示风格', 'sys.index.menuStyle', 'default', 'Y', 'admin', '2022-09-19 10:49:17', '', NULL, '菜单导航显示风格（default为左侧导航菜单，topnav为顶部导航菜单）');
INSERT INTO `sys_config` VALUES (9, '主框架页-是否开启页脚', 'sys.index.footer', 'true', 'Y', 'admin', '2022-09-19 10:49:17', '', NULL, '是否开启底部页脚显示（true显示，false隐藏）');
INSERT INTO `sys_config` VALUES (10, '主框架页-是否开启页签', 'sys.index.tagsView', 'true', 'Y', 'admin', '2022-09-19 10:49:17', '', NULL, '是否开启菜单多页签显示（true显示，false隐藏）');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `dept_id` bigint NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父部门id',
  `ancestors` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '祖级列表',
  `dept_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `order_num` int NULL DEFAULT 0 COMMENT '显示顺序',
  `leader` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '负责人',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系电话',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (100, 0, '0', '若依科技', 0, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-09-19 10:49:17', '', NULL);
INSERT INTO `sys_dept` VALUES (101, 100, '0,100', '深圳总公司', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-09-19 10:49:17', '', NULL);
INSERT INTO `sys_dept` VALUES (102, 100, '0,100', '长沙分公司', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-09-19 10:49:17', '', NULL);
INSERT INTO `sys_dept` VALUES (103, 101, '0,100,101', '研发部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-09-19 10:49:17', '', NULL);
INSERT INTO `sys_dept` VALUES (104, 101, '0,100,101', '市场部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-09-19 10:49:17', '', NULL);
INSERT INTO `sys_dept` VALUES (105, 101, '0,100,101', '测试部门', 3, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-09-19 10:49:17', '', NULL);
INSERT INTO `sys_dept` VALUES (106, 101, '0,100,101', '财务部门', 4, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-09-19 10:49:17', '', NULL);
INSERT INTO `sys_dept` VALUES (107, 101, '0,100,101', '运维部门', 5, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-09-19 10:49:17', '', NULL);
INSERT INTO `sys_dept` VALUES (108, 102, '0,100,102', '市场部门', 1, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-09-19 10:49:17', '', NULL);
INSERT INTO `sys_dept` VALUES (109, 102, '0,100,102', '财务部门', 2, '若依', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2022-09-19 10:49:17', '', NULL);

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', '', 'Y', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 99, '其他', '0', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '其他操作');
INSERT INTO `sys_dict_data` VALUES (19, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (20, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (21, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (22, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (23, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (24, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (25, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (26, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (27, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (28, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (29, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '停用状态');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 101 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '登录状态列表');
INSERT INTO `sys_dict_type` VALUES (100, '公司抬头', 'sys_company', '0', 'admin', '2022-09-19 14:25:55', '', NULL, '公司抬头');

-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`  (
  `job_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `cron_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT 'cron执行表达式',
  `misfire_policy` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  `concurrent` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1暂停）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注信息',
  PRIMARY KEY (`job_id`, `job_name`, `job_group`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job
-- ----------------------------
INSERT INTO `sys_job` VALUES (1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams', '0/10 * * * * ?', '3', '1', '1', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_job` VALUES (2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')', '0/15 * * * * ?', '3', '1', '1', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_job` VALUES (3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)', '0/20 * * * * ?', '3', '1', '1', 'admin', '2022-09-19 10:49:17', '', NULL, '');

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`  (
  `job_log_id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务日志ID',
  `job_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `job_group` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务组名',
  `invoke_target` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '调用目标字符串',
  `job_message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '日志信息',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
  `exception_info` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '异常信息',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_log_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '定时任务调度日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`  (
  `info_id` bigint NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录账号',
  `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '提示消息',
  `login_time` datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 104 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_logininfor
-- ----------------------------
INSERT INTO `sys_logininfor` VALUES (100, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-09-19 10:53:44');
INSERT INTO `sys_logininfor` VALUES (101, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-09-19 13:45:22');
INSERT INTO `sys_logininfor` VALUES (102, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-09-19 14:54:12');
INSERT INTO `sys_logininfor` VALUES (103, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-09-21 16:26:01');
INSERT INTO `sys_logininfor` VALUES (104, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-09-26 09:40:41');
INSERT INTO `sys_logininfor` VALUES (105, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-09-26 09:56:55');
INSERT INTO `sys_logininfor` VALUES (106, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-09-26 11:05:26');
INSERT INTO `sys_logininfor` VALUES (107, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-09-26 11:23:14');
INSERT INTO `sys_logininfor` VALUES (108, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-09-26 11:24:06');
INSERT INTO `sys_logininfor` VALUES (109, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-09-26 11:25:22');
INSERT INTO `sys_logininfor` VALUES (110, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-09-26 14:07:51');
INSERT INTO `sys_logininfor` VALUES (111, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-09-26 15:11:55');
INSERT INTO `sys_logininfor` VALUES (112, 'admin', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', '0', '登录成功', '2022-09-26 15:34:40');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `menu_id` bigint NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单名称',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父菜单ID',
  `order_num` int NULL DEFAULT 0 COMMENT '显示顺序',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '请求地址',
  `target` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '打开方式（menuItem页签 menuBlank新窗口）',
  `menu_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `is_refresh` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '是否刷新（0刷新 1不刷新）',
  `perms` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2020 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, '系统管理', 0, 1, '#', '', 'M', '0', '1', '', 'fa fa-gear', 'admin', '2022-09-19 10:49:17', '', NULL, '系统管理目录');
INSERT INTO `sys_menu` VALUES (2, '系统监控', 0, 2, '#', '', 'M', '0', '1', '', 'fa fa-video-camera', 'admin', '2022-09-19 10:49:17', '', NULL, '系统监控目录');
INSERT INTO `sys_menu` VALUES (3, '系统工具', 0, 3, '#', '', 'M', '0', '1', '', 'fa fa-bars', 'admin', '2022-09-19 10:49:17', '', NULL, '系统工具目录');
INSERT INTO `sys_menu` VALUES (4, 'Silergy Corp', 0, 4, 'http://internal.silergycorp.com', 'menuBlank', 'C', '0', '1', '', 'fa fa-location-arrow', 'admin', '2022-09-19 10:49:17', 'admin', '2022-09-19 14:10:58', '若依官网地址');
INSERT INTO `sys_menu` VALUES (100, '用户管理', 1, 1, '/system/user', '', 'C', '0', '1', 'system:user:view', 'fa fa-user-o', 'admin', '2022-09-19 10:49:17', '', NULL, '用户管理菜单');
INSERT INTO `sys_menu` VALUES (101, '角色管理', 1, 2, '/system/role', '', 'C', '0', '1', 'system:role:view', 'fa fa-user-secret', 'admin', '2022-09-19 10:49:17', '', NULL, '角色管理菜单');
INSERT INTO `sys_menu` VALUES (102, '菜单管理', 1, 3, '/system/menu', '', 'C', '0', '1', 'system:menu:view', 'fa fa-th-list', 'admin', '2022-09-19 10:49:17', '', NULL, '菜单管理菜单');
INSERT INTO `sys_menu` VALUES (103, '部门管理', 1, 4, '/system/dept', '', 'C', '0', '1', 'system:dept:view', 'fa fa-outdent', 'admin', '2022-09-19 10:49:17', '', NULL, '部门管理菜单');
INSERT INTO `sys_menu` VALUES (104, '岗位管理', 1, 5, '/system/post', '', 'C', '0', '1', 'system:post:view', 'fa fa-address-card-o', 'admin', '2022-09-19 10:49:17', '', NULL, '岗位管理菜单');
INSERT INTO `sys_menu` VALUES (105, '字典管理', 1, 6, '/system/dict', '', 'C', '0', '1', 'system:dict:view', 'fa fa-bookmark-o', 'admin', '2022-09-19 10:49:17', '', NULL, '字典管理菜单');
INSERT INTO `sys_menu` VALUES (106, '参数设置', 1, 7, '/system/config', '', 'C', '0', '1', 'system:config:view', 'fa fa-sun-o', 'admin', '2022-09-19 10:49:17', '', NULL, '参数设置菜单');
INSERT INTO `sys_menu` VALUES (107, '通知公告', 1, 8, '/system/notice', '', 'C', '0', '1', 'system:notice:view', 'fa fa-bullhorn', 'admin', '2022-09-19 10:49:17', '', NULL, '通知公告菜单');
INSERT INTO `sys_menu` VALUES (108, '日志管理', 1, 9, '#', '', 'M', '0', '1', '', 'fa fa-pencil-square-o', 'admin', '2022-09-19 10:49:17', '', NULL, '日志管理菜单');
INSERT INTO `sys_menu` VALUES (109, '在线用户', 2, 1, '/monitor/online', '', 'C', '0', '1', 'monitor:online:view', 'fa fa-user-circle', 'admin', '2022-09-19 10:49:17', '', NULL, '在线用户菜单');
INSERT INTO `sys_menu` VALUES (110, '定时任务', 2, 2, '/monitor/job', '', 'C', '0', '1', 'monitor:job:view', 'fa fa-tasks', 'admin', '2022-09-19 10:49:17', '', NULL, '定时任务菜单');
INSERT INTO `sys_menu` VALUES (111, '数据监控', 2, 3, '/monitor/data', '', 'C', '0', '1', 'monitor:data:view', 'fa fa-bug', 'admin', '2022-09-19 10:49:17', '', NULL, '数据监控菜单');
INSERT INTO `sys_menu` VALUES (112, '服务监控', 2, 4, '/monitor/server', '', 'C', '0', '1', 'monitor:server:view', 'fa fa-server', 'admin', '2022-09-19 10:49:17', '', NULL, '服务监控菜单');
INSERT INTO `sys_menu` VALUES (113, '缓存监控', 2, 5, '/monitor/cache', '', 'C', '0', '1', 'monitor:cache:view', 'fa fa-cube', 'admin', '2022-09-19 10:49:17', '', NULL, '缓存监控菜单');
INSERT INTO `sys_menu` VALUES (114, '表单构建', 3, 1, '/tool/build', '', 'C', '0', '1', 'tool:build:view', 'fa fa-wpforms', 'admin', '2022-09-19 10:49:17', '', NULL, '表单构建菜单');
INSERT INTO `sys_menu` VALUES (115, '代码生成', 3, 2, '/tool/gen', '', 'C', '0', '1', 'tool:gen:view', 'fa fa-code', 'admin', '2022-09-19 10:49:17', '', NULL, '代码生成菜单');
INSERT INTO `sys_menu` VALUES (116, '系统接口', 3, 3, '/tool/swagger', '', 'C', '0', '1', 'tool:swagger:view', 'fa fa-gg', 'admin', '2022-09-19 10:49:17', '', NULL, '系统接口菜单');
INSERT INTO `sys_menu` VALUES (500, '操作日志', 108, 1, '/monitor/operlog', '', 'C', '0', '1', 'monitor:operlog:view', 'fa fa-address-book', 'admin', '2022-09-19 10:49:17', '', NULL, '操作日志菜单');
INSERT INTO `sys_menu` VALUES (501, '登录日志', 108, 2, '/monitor/logininfor', '', 'C', '0', '1', 'monitor:logininfor:view', 'fa fa-file-image-o', 'admin', '2022-09-19 10:49:17', '', NULL, '登录日志菜单');
INSERT INTO `sys_menu` VALUES (1000, '用户查询', 100, 1, '#', '', 'F', '0', '1', 'system:user:list', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1001, '用户新增', 100, 2, '#', '', 'F', '0', '1', 'system:user:add', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1002, '用户修改', 100, 3, '#', '', 'F', '0', '1', 'system:user:edit', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1003, '用户删除', 100, 4, '#', '', 'F', '0', '1', 'system:user:remove', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1004, '用户导出', 100, 5, '#', '', 'F', '0', '1', 'system:user:export', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1005, '用户导入', 100, 6, '#', '', 'F', '0', '1', 'system:user:import', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1006, '重置密码', 100, 7, '#', '', 'F', '0', '1', 'system:user:resetPwd', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1007, '角色查询', 101, 1, '#', '', 'F', '0', '1', 'system:role:list', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1008, '角色新增', 101, 2, '#', '', 'F', '0', '1', 'system:role:add', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1009, '角色修改', 101, 3, '#', '', 'F', '0', '1', 'system:role:edit', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1010, '角色删除', 101, 4, '#', '', 'F', '0', '1', 'system:role:remove', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1011, '角色导出', 101, 5, '#', '', 'F', '0', '1', 'system:role:export', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1012, '菜单查询', 102, 1, '#', '', 'F', '0', '1', 'system:menu:list', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1013, '菜单新增', 102, 2, '#', '', 'F', '0', '1', 'system:menu:add', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1014, '菜单修改', 102, 3, '#', '', 'F', '0', '1', 'system:menu:edit', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1015, '菜单删除', 102, 4, '#', '', 'F', '0', '1', 'system:menu:remove', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1016, '部门查询', 103, 1, '#', '', 'F', '0', '1', 'system:dept:list', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1017, '部门新增', 103, 2, '#', '', 'F', '0', '1', 'system:dept:add', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1018, '部门修改', 103, 3, '#', '', 'F', '0', '1', 'system:dept:edit', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1019, '部门删除', 103, 4, '#', '', 'F', '0', '1', 'system:dept:remove', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1020, '岗位查询', 104, 1, '#', '', 'F', '0', '1', 'system:post:list', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1021, '岗位新增', 104, 2, '#', '', 'F', '0', '1', 'system:post:add', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1022, '岗位修改', 104, 3, '#', '', 'F', '0', '1', 'system:post:edit', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1023, '岗位删除', 104, 4, '#', '', 'F', '0', '1', 'system:post:remove', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1024, '岗位导出', 104, 5, '#', '', 'F', '0', '1', 'system:post:export', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1025, '字典查询', 105, 1, '#', '', 'F', '0', '1', 'system:dict:list', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1026, '字典新增', 105, 2, '#', '', 'F', '0', '1', 'system:dict:add', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1027, '字典修改', 105, 3, '#', '', 'F', '0', '1', 'system:dict:edit', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1028, '字典删除', 105, 4, '#', '', 'F', '0', '1', 'system:dict:remove', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1029, '字典导出', 105, 5, '#', '', 'F', '0', '1', 'system:dict:export', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1030, '参数查询', 106, 1, '#', '', 'F', '0', '1', 'system:config:list', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1031, '参数新增', 106, 2, '#', '', 'F', '0', '1', 'system:config:add', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1032, '参数修改', 106, 3, '#', '', 'F', '0', '1', 'system:config:edit', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1033, '参数删除', 106, 4, '#', '', 'F', '0', '1', 'system:config:remove', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1034, '参数导出', 106, 5, '#', '', 'F', '0', '1', 'system:config:export', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1035, '公告查询', 107, 1, '#', '', 'F', '0', '1', 'system:notice:list', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1036, '公告新增', 107, 2, '#', '', 'F', '0', '1', 'system:notice:add', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1037, '公告修改', 107, 3, '#', '', 'F', '0', '1', 'system:notice:edit', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1038, '公告删除', 107, 4, '#', '', 'F', '0', '1', 'system:notice:remove', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1039, '操作查询', 500, 1, '#', '', 'F', '0', '1', 'monitor:operlog:list', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1040, '操作删除', 500, 2, '#', '', 'F', '0', '1', 'monitor:operlog:remove', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1041, '详细信息', 500, 3, '#', '', 'F', '0', '1', 'monitor:operlog:detail', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1042, '日志导出', 500, 4, '#', '', 'F', '0', '1', 'monitor:operlog:export', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1043, '登录查询', 501, 1, '#', '', 'F', '0', '1', 'monitor:logininfor:list', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1044, '登录删除', 501, 2, '#', '', 'F', '0', '1', 'monitor:logininfor:remove', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1045, '日志导出', 501, 3, '#', '', 'F', '0', '1', 'monitor:logininfor:export', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1046, '账户解锁', 501, 4, '#', '', 'F', '0', '1', 'monitor:logininfor:unlock', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1047, '在线查询', 109, 1, '#', '', 'F', '0', '1', 'monitor:online:list', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1048, '批量强退', 109, 2, '#', '', 'F', '0', '1', 'monitor:online:batchForceLogout', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1049, '单条强退', 109, 3, '#', '', 'F', '0', '1', 'monitor:online:forceLogout', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1050, '任务查询', 110, 1, '#', '', 'F', '0', '1', 'monitor:job:list', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1051, '任务新增', 110, 2, '#', '', 'F', '0', '1', 'monitor:job:add', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1052, '任务修改', 110, 3, '#', '', 'F', '0', '1', 'monitor:job:edit', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1053, '任务删除', 110, 4, '#', '', 'F', '0', '1', 'monitor:job:remove', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1054, '状态修改', 110, 5, '#', '', 'F', '0', '1', 'monitor:job:changeStatus', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1055, '任务详细', 110, 6, '#', '', 'F', '0', '1', 'monitor:job:detail', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1056, '任务导出', 110, 7, '#', '', 'F', '0', '1', 'monitor:job:export', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1057, '生成查询', 115, 1, '#', '', 'F', '0', '1', 'tool:gen:list', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1058, '生成修改', 115, 2, '#', '', 'F', '0', '1', 'tool:gen:edit', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1059, '生成删除', 115, 3, '#', '', 'F', '0', '1', 'tool:gen:remove', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1060, '预览代码', 115, 4, '#', '', 'F', '0', '1', 'tool:gen:preview', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (1061, '生成代码', 115, 5, '#', '', 'F', '0', '1', 'tool:gen:code', '#', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2000, '表单', 0, 0, '#', 'menuItem', 'M', '0', '1', '', 'fa fa-newspaper-o', 'admin', '2022-09-19 13:46:58', 'admin', '2022-09-19 14:09:22', '');
INSERT INTO `sys_menu` VALUES (2001, '请款单', 2000, 1, '/system/paymentRequest', 'menuItem', 'C', '0', '1', 'system:paymentRequest:view', '#', 'admin', '2022-09-19 14:08:25', 'admin', '2022-09-26 09:45:52', '请款单菜单');
INSERT INTO `sys_menu` VALUES (2002, '请款单查询', 2001, 6, '#', '', 'F', '0', '1', 'system:paymentRequest:list', '#', 'admin', '2022-09-19 14:08:25', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2003, '请款单新增', 2001, 7, '#', '', 'F', '0', '1', 'system:paymentRequest:add', '#', 'admin', '2022-09-19 14:08:25', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2004, '请款单修改', 2001, 8, '#', '', 'F', '0', '1', 'system:paymentRequest:edit', '#', 'admin', '2022-09-19 14:08:25', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2005, '请款单删除', 2001, 9, '#', '', 'F', '0', '1', 'system:paymentRequest:remove', '#', 'admin', '2022-09-19 14:08:25', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2006, '请款单导出', 2001, 10, '#', '', 'F', '0', '1', 'system:paymentRequest:export', '#', 'admin', '2022-09-19 14:08:25', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2007, '请款列表', 2001, 1, '/system/paymentRequest', 'menuItem', 'C', '0', '1', 'system:paymentRequest:finance', '#', 'admin', '2022-09-19 14:15:20', 'admin', '2022-09-19 14:16:28', '');
INSERT INTO `sys_menu` VALUES (2008, '我的发起', 2001, 2, '/system/paymentRequest/create', 'menuItem', 'C', '0', '1', 'system:paymentRequest:list', '#', 'admin', '2022-09-19 14:16:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2009, '我的草稿', 2001, 3, '/system/paymentRequest/draft', 'menuItem', 'C', '0', '1', 'system:paymentRequest:list', '#', 'admin', '2022-09-19 14:17:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2010, '我的待办', 2001, 4, '/system/paymentRequest/todo', 'menuItem', 'C', '0', '1', 'system:paymentRequest:list', '#', 'admin', '2022-09-19 14:18:34', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2011, '我的已办', 2001, 5, '/system/paymentRequest/do', 'menuItem', 'C', '0', '1', 'system:paymentRequest:list', '#', 'admin', '2022-09-19 14:19:06', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2012, '请款单批量上传审批截图', 2001, 11, '#', 'menuItem', 'F', '0', '1', 'system:paymentRequest:finance', '#', 'admin', '2022-09-19 14:21:13', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2013, '请款单模版操作', 2001, 12, '#', 'menuItem', 'F', '0', '1', 'system:paymentRequest:templateOperation', '#', 'admin', '2022-09-19 14:21:46', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2038, '请购单', 2000, 2, '/system/requisition', 'menuItem', 'C', '0', '1', 'system:requisition:view', '#', 'admin', '2022-09-26 11:09:27', 'admin', '2022-09-26 11:28:03', '请购单主菜单');
INSERT INTO `sys_menu` VALUES (2039, '请购单查询', 2038, 5, '#', '', 'F', '0', '1', 'system:requisition:list', '#', 'admin', '2022-09-26 11:09:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2040, '请购单新增', 2038, 6, '#', '', 'F', '0', '1', 'system:requisition:add', '#', 'admin', '2022-09-26 11:09:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2041, '请购单修改', 2038, 7, '#', '', 'F', '0', '1', 'system:requisition:edit', '#', 'admin', '2022-09-26 11:09:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2042, '请购单删除', 2038, 8, '#', '', 'F', '0', '1', 'system:requisition:remove', '#', 'admin', '2022-09-26 11:09:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2043, '请购单导出', 2038, 9, '#', '', 'F', '0', '1', 'system:requisition:export', '#', 'admin', '2022-09-26 11:09:27', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2044, '电子签呈', 2000, 3, '/system/petition', 'menuItem', 'C', '0', '1', 'system:petition:view', '#', 'admin', '2022-09-26 11:09:36', 'admin', '2022-09-26 11:28:12', '电子签呈菜单');
INSERT INTO `sys_menu` VALUES (2045, '电子签呈查询', 2044, 5, '#', '', 'F', '0', '1', 'system:petition:list', '#', 'admin', '2022-09-26 11:09:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2046, '电子签呈新增', 2044, 6, '#', '', 'F', '0', '1', 'system:petition:add', '#', 'admin', '2022-09-26 11:09:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2047, '电子签呈修改', 2044, 7, '#', '', 'F', '0', '1', 'system:petition:edit', '#', 'admin', '2022-09-26 11:09:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2048, '电子签呈删除', 2044, 8, '#', '', 'F', '0', '1', 'system:petition:remove', '#', 'admin', '2022-09-26 11:09:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2049, '电子签呈导出', 2044, 9, '#', '', 'F', '0', '1', 'system:petition:export', '#', 'admin', '2022-09-26 11:09:36', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2050, '我的发起', 2044, 1, '/system/petition/create', 'menuItem', 'C', '0', '1', 'system:petition:view', '#', 'admin', '2022-09-26 11:29:16', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2051, '我的待办', 2044, 3, '/system/petition/todo', 'menuItem', 'C', '0', '1', 'system:petition:view', '#', 'admin', '2022-09-26 11:29:48', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2052, '我的已办', 2044, 4, '/system/petition/did', 'menuItem', 'C', '0', '1', 'system:petition:view', '#', 'admin', '2022-09-26 11:30:14', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2053, '我的草稿', 2044, 2, '#', 'menuItem', 'C', '0', '1', NULL, '#', 'admin', '2022-09-26 11:30:45', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2054, '我的发起', 2038, 1, '/system/requisition/create', 'menuItem', 'C', '0', '1', 'system:requisition:list', '#', 'admin', '2022-09-26 11:33:30', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2055, '我的草稿', 2038, 2, '/system/requisition/draft', 'menuItem', 'C', '0', '1', 'system:requisition:list', '#', 'admin', '2022-09-26 11:33:50', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2056, '我的待办', 2038, 3, '/system/requisition/todo', 'menuItem', 'C', '0', '1', 'system:requisition:list', '#', 'admin', '2022-09-26 11:35:54', '', NULL, '');
INSERT INTO `sys_menu` VALUES (2057, '我的已办', 2038, 4, '/system/requisition/do', 'menuItem', 'C', '0', '1', 'system:requisition:list', '#', 'admin', '2022-09-26 11:36:28', '', NULL, '');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `notice_id` int NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
  `notice_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '公告内容',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知公告表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '温馨提醒：2018-07-01 若依新版本发布啦', '2', '新版本内容', '0', 'admin', '2022-09-19 10:49:18', '', NULL, '管理员');
INSERT INTO `sys_notice` VALUES (2, '维护通知：2018-07-01 若依系统凌晨维护', '1', '维护内容', '0', 'admin', '2022-09-19 10:49:18', '', NULL, '管理员');

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`  (
  `oper_id` bigint NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '模块标题',
  `business_type` int NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求方式',
  `operator_type` int NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作人员',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `oper_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '返回参数',
  `status` int NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 126 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_oper_log
-- ----------------------------
INSERT INTO `sys_oper_log` VALUES (100, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":[\"sys_payment_request,sys_payment_request_dt1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 11:29:42');
INSERT INTO `sys_oper_log` VALUES (101, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.synchDb()', 'GET', 1, 'admin', '研发部门', '/tool/gen/synchDb/sys_payment_request', '127.0.0.1', '内网IP', '\"sys_payment_request\"', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 13:45:32');
INSERT INTO `sys_oper_log` VALUES (102, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.synchDb()', 'GET', 1, 'admin', '研发部门', '/tool/gen/synchDb/sys_payment_request_dt1', '127.0.0.1', '内网IP', '\"sys_payment_request_dt1\"', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 13:45:35');
INSERT INTO `sys_oper_log` VALUES (103, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\"parentId\":[\"0\"],\"menuType\":[\"M\"],\"menuName\":[\"表单\"],\"url\":[\"\"],\"target\":[\"menuItem\"],\"perms\":[\"\"],\"orderNum\":[\"0\"],\"icon\":[\"fa fa-edit\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 13:46:58');
INSERT INTO `sys_oper_log` VALUES (104, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"1\"],\"tableName\":[\"sys_payment_request\"],\"tableComment\":[\"请款单\"],\"className\":[\"PaymentRequest\"],\"functionAuthor\":[\"SKaiL\"],\"remark\":[\"请款单\"],\"columns[0].columnId\":[\"1\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"2\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"单据编号\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"paymentRequestNo\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"4\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"1：用户保存未提交 2：待部门主管审批 3：主管加签状态 5：财务部门财务审核 6：财务部门审核加签 7：财务总账确认 8：财务总账审核加签 9：游总核决 10：核决加签 11：出纳确认 12：出纳加签 13：财务经理审核 14：财务经理加签 15：财务总监审核 16：财务总监加签 18：前加签状态 19：否决 20：撤回 21：完成\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"status\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"radio\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"5\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"公司\"],\"columns[3].javaType\":[\"Long\"],\"columns[3].javaField\":[\"company\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"7\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"项目代码\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"projectCode\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"input\"],\"columns[4].dictType\":[\"\"],\"column', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 13:47:34');
INSERT INTO `sys_oper_log` VALUES (105, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"2\"],\"tableName\":[\"sys_payment_request_dt1\"],\"tableComment\":[\"请款单dt1\"],\"className\":[\"PaymentRequestDt1\"],\"functionAuthor\":[\"SKaiL\"],\"remark\":[\"请款单dt1\"],\"columns[0].columnId\":[\"67\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"68\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"关联id\"],\"columns[1].javaType\":[\"Long\"],\"columns[1].javaField\":[\"requestId\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"69\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"项目编号\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"projectCode\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"70\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"摘 要 说 明\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"content\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"summernote\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"71\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"单位\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"unit\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"input\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"72\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"数量\"],\"columns[5].javaType\":[\"String\"],\"columns[5].javaField\":[\"quantity\"', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 13:48:16');
INSERT INTO `sys_oper_log` VALUES (106, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"1\"],\"tableName\":[\"sys_payment_request\"],\"tableComment\":[\"请款单\"],\"className\":[\"PaymentRequest\"],\"functionAuthor\":[\"SKaiL\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"1\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"2\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"单据编号\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"paymentRequestNo\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"4\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"1：用户保存未提交 2：待部门主管审批 3：主管加签状态 5：财务部门财务审核 6：财务部门审核加签 7：财务总账确认 8：财务总账审核加签 9：游总核决 10：核决加签 11：出纳确认 12：出纳加签 13：财务经理审核 14：财务经理加签 15：财务总监审核 16：财务总监加签 18：前加签状态 19：否决 20：撤回 21：完成\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"status\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"radio\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"5\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"公司\"],\"columns[3].javaType\":[\"Long\"],\"columns[3].javaField\":[\"company\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"7\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"项目代码\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"projectCode\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"input\"],\"columns[4].dictType\":[\"\"],\"columns[5', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 13:48:31');
INSERT INTO `sys_oper_log` VALUES (107, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.download()', 'GET', 1, 'admin', '研发部门', '/tool/gen/download/sys_payment_request', '127.0.0.1', '内网IP', '\"sys_payment_request\"', NULL, 0, NULL, '2022-09-19 14:02:05');
INSERT INTO `sys_oper_log` VALUES (108, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\"menuId\":[\"2000\"],\"parentId\":[\"0\"],\"menuType\":[\"M\"],\"menuName\":[\"表单\"],\"url\":[\"#\"],\"target\":[\"menuItem\"],\"perms\":[\"\"],\"orderNum\":[\"0\"],\"icon\":[\"fa fa-newspaper-o\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 14:09:22');
INSERT INTO `sys_oper_log` VALUES (109, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\"menuId\":[\"4\"],\"parentId\":[\"0\"],\"menuType\":[\"C\"],\"menuName\":[\"若依官网\"],\"url\":[\"http://internal.silergycorp.com\"],\"target\":[\"menuBlank\"],\"perms\":[\"\"],\"orderNum\":[\"4\"],\"icon\":[\"fa fa-location-arrow\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 14:10:03');
INSERT INTO `sys_oper_log` VALUES (110, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\"menuId\":[\"4\"],\"parentId\":[\"0\"],\"menuType\":[\"C\"],\"menuName\":[\"Silergy Corp\"],\"url\":[\"http://internal.silergycorp.com\"],\"target\":[\"menuBlank\"],\"perms\":[\"\"],\"orderNum\":[\"4\"],\"icon\":[\"fa fa-location-arrow\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 14:10:17');
INSERT INTO `sys_oper_log` VALUES (111, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\"menuId\":[\"4\"],\"parentId\":[\"0\"],\"menuType\":[\"C\"],\"menuName\":[\"Silergy Corp\"],\"url\":[\"http://internal.silergycorp.com\"],\"target\":[\"menuBlank\"],\"perms\":[\"\"],\"orderNum\":[\"4\"],\"icon\":[\"fa fa-location-arrow\"],\"visible\":[\"0\"],\"isRefresh\":[\"0\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 14:10:40');
INSERT INTO `sys_oper_log` VALUES (112, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\"menuId\":[\"4\"],\"parentId\":[\"0\"],\"menuType\":[\"C\"],\"menuName\":[\"Silergy Corp\"],\"url\":[\"http://internal.silergycorp.com\"],\"target\":[\"menuBlank\"],\"perms\":[\"\"],\"orderNum\":[\"4\"],\"icon\":[\"fa fa-location-arrow\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 14:10:50');
INSERT INTO `sys_oper_log` VALUES (113, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\"menuId\":[\"4\"],\"parentId\":[\"0\"],\"menuType\":[\"C\"],\"menuName\":[\"Silergy Corp\"],\"url\":[\"http://internal.silergycorp.com\"],\"target\":[\"menuBlank\"],\"perms\":[\"\"],\"orderNum\":[\"4\"],\"icon\":[\"fa fa-location-arrow\"],\"visible\":[\"0\"],\"isRefresh\":[\"0\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 14:10:53');
INSERT INTO `sys_oper_log` VALUES (114, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\"menuId\":[\"4\"],\"parentId\":[\"0\"],\"menuType\":[\"C\"],\"menuName\":[\"Silergy Corp\"],\"url\":[\"http://internal.silergycorp.com\"],\"target\":[\"menuBlank\"],\"perms\":[\"\"],\"orderNum\":[\"4\"],\"icon\":[\"fa fa-location-arrow\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 14:10:58');
INSERT INTO `sys_oper_log` VALUES (115, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\"parentId\":[\"2001\"],\"menuType\":[\"C\"],\"menuName\":[\"请款列表\"],\"url\":[\"/system/paymentRequest\"],\"target\":[\"menuItem\"],\"perms\":[\"system:PaymentRequest:finance\"],\"orderNum\":[\"6\"],\"icon\":[\"\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 14:15:20');
INSERT INTO `sys_oper_log` VALUES (116, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\"parentId\":[\"2001\"],\"menuType\":[\"C\"],\"menuName\":[\"我的发起\"],\"url\":[\"/system/PaymentRequest/create\"],\"target\":[\"menuItem\"],\"perms\":[\"system:PaymentRequest:list\"],\"orderNum\":[\"7\"],\"icon\":[\"\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 14:16:14');
INSERT INTO `sys_oper_log` VALUES (117, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\"menuId\":[\"2007\"],\"parentId\":[\"2001\"],\"menuType\":[\"C\"],\"menuName\":[\"请款列表\"],\"url\":[\"/system/PaymentRequest\"],\"target\":[\"menuItem\"],\"perms\":[\"system:PaymentRequest:finance\"],\"orderNum\":[\"6\"],\"icon\":[\"#\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 14:16:28');
INSERT INTO `sys_oper_log` VALUES (118, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\"parentId\":[\"2001\"],\"menuType\":[\"C\"],\"menuName\":[\"我的草稿\"],\"url\":[\"/system/PaymentRequest/draft\"],\"target\":[\"menuItem\"],\"perms\":[\"system:PaymentRequest:list\"],\"orderNum\":[\"8\"],\"icon\":[\"\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 14:17:36');
INSERT INTO `sys_oper_log` VALUES (119, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\"parentId\":[\"2001\"],\"menuType\":[\"C\"],\"menuName\":[\"我的待办\"],\"url\":[\"/system/PaymentRequest/todo\"],\"target\":[\"menuItem\"],\"perms\":[\"system:PaymentRequest:list\"],\"orderNum\":[\"9\"],\"icon\":[\"\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 14:18:34');
INSERT INTO `sys_oper_log` VALUES (120, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\"parentId\":[\"2001\"],\"menuType\":[\"C\"],\"menuName\":[\"我的已办\"],\"url\":[\"/system/PaymentRequest/do\"],\"target\":[\"menuItem\"],\"perms\":[\"system:PaymentRequest:list\"],\"orderNum\":[\"10\"],\"icon\":[\"\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 14:19:06');
INSERT INTO `sys_oper_log` VALUES (121, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\"parentId\":[\"2001\"],\"menuType\":[\"F\"],\"menuName\":[\"请款单批量上传审批截图\"],\"url\":[\"\"],\"target\":[\"menuItem\"],\"perms\":[\"system:PaymentRequest:finance\"],\"orderNum\":[\"11\"],\"icon\":[\"\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 14:21:13');
INSERT INTO `sys_oper_log` VALUES (122, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\"parentId\":[\"2001\"],\"menuType\":[\"F\"],\"menuName\":[\"请款单模版操作\"],\"url\":[\"\"],\"target\":[\"menuItem\"],\"perms\":[\"system:PaymentRequest:templateOperation\"],\"orderNum\":[\"12\"],\"icon\":[\"\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 14:21:46');
INSERT INTO `sys_oper_log` VALUES (123, '字典类型', 1, 'com.ruoyi.web.controller.system.SysDictTypeController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/dict/add', '127.0.0.1', '内网IP', '{\"dictName\":[\"公司抬头\"],\"dictType\":[\"sys_company\"],\"status\":[\"0\"],\"remark\":[\"公司抬头\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-19 14:25:56');
INSERT INTO `sys_oper_log` VALUES (124, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"1\"],\"tableName\":[\"sys_payment_request\"],\"tableComment\":[\"请款单\"],\"className\":[\"PaymentRequest\"],\"functionAuthor\":[\"SKaiL\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"1\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"2\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"单据编号\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"paymentRequestNo\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"4\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"1：用户保存未提交 2：待部门主管审批 3：主管加签状态 5：财务部门财务审核 6：财务部门审核加签 7：财务总账确认 8：财务总账审核加签 9：游总核决 10：核决加签 11：出纳确认 12：出纳加签 13：财务经理审核 14：财务经理加签 15：财务总监审核 16：财务总监加签 18：前加签状态 19：否决 20：撤回 21：完成\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"status\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"radio\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"5\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"公司\"],\"columns[3].javaType\":[\"Long\"],\"columns[3].javaField\":[\"company\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"7\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"项目代码\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"projectCode\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"input\"],\"columns[4].dictType\":[\"\"],\"columns[5', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-21 16:26:27');
INSERT INTO `sys_oper_log` VALUES (125, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.download()', 'GET', 1, 'admin', '研发部门', '/tool/gen/download/sys_payment_request', '127.0.0.1', '内网IP', '\"sys_payment_request\"', NULL, 0, NULL, '2022-09-21 16:26:31');
INSERT INTO `sys_oper_log` VALUES (126, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/2014', '127.0.0.1', '内网IP', '2014', '{\"msg\":\"存在子菜单,不允许删除\",\"code\":301}', 0, NULL, '2022-09-26 09:44:44');
INSERT INTO `sys_oper_log` VALUES (127, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/2015', '127.0.0.1', '内网IP', '2015', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 09:44:51');
INSERT INTO `sys_oper_log` VALUES (128, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/2016', '127.0.0.1', '内网IP', '2016', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 09:45:19');
INSERT INTO `sys_oper_log` VALUES (129, '菜单管理', 3, 'com.ruoyi.web.controller.system.SysMenuController.remove()', 'GET', 1, 'admin', '研发部门', '/system/menu/remove/2014', '127.0.0.1', '内网IP', '2014', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 09:45:35');
INSERT INTO `sys_oper_log` VALUES (130, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\"menuId\":[\"2001\"],\"parentId\":[\"2000\"],\"menuType\":[\"C\"],\"menuName\":[\"请款单\"],\"url\":[\"/system/paymentRequest\"],\"target\":[\"menuItem\"],\"perms\":[\"system:paymentRequest:view\"],\"orderNum\":[\"1\"],\"icon\":[\"#\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 09:45:52');
INSERT INTO `sys_oper_log` VALUES (131, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":[\"sys_requisition,sys_requisition_dt1,sys_requisition_dt2\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 09:57:54');
INSERT INTO `sys_oper_log` VALUES (132, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.synchDb()', 'GET', 1, 'admin', '研发部门', '/tool/gen/synchDb/sys_requisition', '127.0.0.1', '内网IP', '\"sys_requisition\"', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 10:25:59');
INSERT INTO `sys_oper_log` VALUES (133, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.synchDb()', 'GET', 1, 'admin', '研发部门', '/tool/gen/synchDb/sys_requisition_dt1', '127.0.0.1', '内网IP', '\"sys_requisition_dt1\"', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 10:26:01');
INSERT INTO `sys_oper_log` VALUES (134, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.synchDb()', 'GET', 1, 'admin', '研发部门', '/tool/gen/synchDb/sys_requisition_dt2', '127.0.0.1', '内网IP', '\"sys_requisition_dt2\"', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 10:26:04');
INSERT INTO `sys_oper_log` VALUES (135, '代码生成', 3, 'com.ruoyi.generator.controller.GenController.remove()', 'POST', 1, 'admin', '研发部门', '/tool/gen/remove', '127.0.0.1', '内网IP', '{\"ids\":[\"3,4,5\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 10:26:11');
INSERT INTO `sys_oper_log` VALUES (136, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":[\"sys_requisition,sys_requisition_dt1,sys_requisition_dt2\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 10:26:17');
INSERT INTO `sys_oper_log` VALUES (137, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"7\"],\"tableName\":[\"sys_requisition_dt1\"],\"tableComment\":[\"请购单产品明细dt1\"],\"className\":[\"RequisitionDt1\"],\"functionAuthor\":[\"SKaiL\"],\"remark\":[\"请购单产品明细dt1\"],\"columns[0].columnId\":[\"195\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"196\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"主表id\"],\"columns[1].javaType\":[\"Long\"],\"columns[1].javaField\":[\"requisitionId\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"197\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"项目编号\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"projectCode\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"198\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"项目名称\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"projectName\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"LIKE\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"199\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"规格说明\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"specifications\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"textarea\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"200\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"购买原因\"],\"columns[5].javaType\":[\"String\"],\"columns', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 10:26:57');
INSERT INTO `sys_oper_log` VALUES (138, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"8\"],\"tableName\":[\"sys_requisition_dt2\"],\"tableComment\":[\"请购单供应商明显dt2\"],\"className\":[\"RequisitionDt2\"],\"functionAuthor\":[\"SKaiL\"],\"remark\":[\"请购单供应商明显dt2\"],\"columns[0].columnId\":[\"207\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"208\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"主表id\"],\"columns[1].javaType\":[\"Long\"],\"columns[1].javaField\":[\"requisitionId\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"209\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"供应商是否选中\"],\"columns[2].javaType\":[\"Long\"],\"columns[2].javaField\":[\"suppliers\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"210\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"项目名称\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"projectName\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"LIKE\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"211\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"供应商名称\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"suppliersName\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"LIKE\"],\"columns[4].htmlType\":[\"input\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"212\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"供应商编号\"],\"columns[5].javaType\":[\"String\"],\"column', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 10:27:13');
INSERT INTO `sys_oper_log` VALUES (139, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"6\"],\"tableName\":[\"sys_requisition\"],\"tableComment\":[\"请购单\"],\"className\":[\"Requisition\"],\"functionAuthor\":[\"SKaiL\"],\"remark\":[\"请购单主表\"],\"columns[0].columnId\":[\"152\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"153\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"唯一编号\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"requisitionNo\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"154\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"状态\"],\"columns[2].javaType\":[\"Long\"],\"columns[2].javaField\":[\"status\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"radio\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"155\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"老状态\"],\"columns[3].javaType\":[\"Long\"],\"columns[3].javaField\":[\"oldStatus\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"radio\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"156\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"保留状态\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"oldRetainStatus\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"radio\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"157\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"老将要处理者的code\"],\"columns[5].javaType\":[\"String\"],\"columns[5].javaField\":[\"oldSendToCo', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 10:28:11');
INSERT INTO `sys_oper_log` VALUES (140, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.download()', 'GET', 1, 'admin', '研发部门', '/tool/gen/download/sys_requisition', '127.0.0.1', '内网IP', '\"sys_requisition\"', NULL, 0, NULL, '2022-09-26 10:30:00');
INSERT INTO `sys_oper_log` VALUES (141, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":[\"sys_petition,sys_petition_sign\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 11:05:40');
INSERT INTO `sys_oper_log` VALUES (142, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"9\"],\"tableName\":[\"sys_petition\"],\"tableComment\":[\"电子签呈\"],\"className\":[\"Petition\"],\"functionAuthor\":[\"SKaiL\"],\"remark\":[\"电子签呈\"],\"columns[0].columnId\":[\"220\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"id\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"221\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"类型\"],\"columns[1].javaType\":[\"Long\"],\"columns[1].javaField\":[\"typeType\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].isRequired\":[\"1\"],\"columns[1].htmlType\":[\"select\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"222\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"表单编号\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"comNo\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"223\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"主题\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"topicObj\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"textarea\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"224\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"说明文本\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"explanation\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"textarea\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"225\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"印章类型(公章 1 ,法人章2 ,财务章 3 ,合同章 4 ,法人章(经济部 小章)5 ,法人章(经济部 小章) 6 ,', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 11:06:07');
INSERT INTO `sys_oper_log` VALUES (143, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"10\"],\"tableName\":[\"sys_petition_sign\"],\"tableComment\":[\"电子签呈核准人员表\"],\"className\":[\"PetitionSign\"],\"functionAuthor\":[\"SKaiL\"],\"remark\":[\"电子签呈核准人员表\"],\"columns[0].columnId\":[\"284\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"285\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"签核角色\"],\"columns[1].javaType\":[\"Long\"],\"columns[1].javaField\":[\"signType\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"select\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"286\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"签核人员\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"addName\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"LIKE\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"287\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"工号\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"sid\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"288\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"审核结果\"],\"columns[4].javaType\":[\"Long\"],\"columns[4].javaField\":[\"result\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"input\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"289\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"备注意见\"],\"columns[5].javaType\":[\"String\"],\"columns[5].javaField\":[\"remark\"],\"columns[5', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 11:06:34');
INSERT INTO `sys_oper_log` VALUES (144, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"9\"],\"tableName\":[\"sys_petition\"],\"tableComment\":[\"电子签呈\"],\"className\":[\"Petition\"],\"functionAuthor\":[\"SKaiL\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"220\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"id\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"221\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"类型\"],\"columns[1].javaType\":[\"Long\"],\"columns[1].javaField\":[\"typeType\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].isRequired\":[\"1\"],\"columns[1].htmlType\":[\"select\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"222\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"表单编号\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"comNo\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"223\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"主题\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"topicObj\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"textarea\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"224\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"说明文本\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"explanation\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"textarea\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"225\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"印章类型(公章 1 ,法人章2 ,财务章 3 ,合同章 4 ,法人章(经济部 小章)5 ,法人章(经济部 小章) 6 ,印鉴大章', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 11:07:18');
INSERT INTO `sys_oper_log` VALUES (145, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.download()', 'GET', 1, 'admin', '研发部门', '/tool/gen/download/sys_petition', '127.0.0.1', '内网IP', '\"sys_petition\"', NULL, 0, NULL, '2022-09-26 11:07:26');
INSERT INTO `sys_oper_log` VALUES (146, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.synchDb()', 'GET', 1, 'admin', '研发部门', '/tool/gen/synchDb/sys_requisition', '127.0.0.1', '内网IP', '\"sys_requisition\"', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 11:10:41');
INSERT INTO `sys_oper_log` VALUES (147, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"6\"],\"tableName\":[\"sys_requisition\"],\"tableComment\":[\"请购单\"],\"className\":[\"Requisition\"],\"functionAuthor\":[\"SKaiL\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"152\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"153\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"唯一编号\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"requisitionNo\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"154\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"状态\"],\"columns[2].javaType\":[\"Long\"],\"columns[2].javaField\":[\"status\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"radio\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"155\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"老状态\"],\"columns[3].javaType\":[\"Long\"],\"columns[3].javaField\":[\"oldStatus\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"radio\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"156\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"保留状态\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"oldRetainStatus\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"radio\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"157\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"老将要处理者的code\"],\"columns[5].javaType\":[\"String\"],\"columns[5].javaField\":[\"oldSendToCode\"],', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 11:10:51');
INSERT INTO `sys_oper_log` VALUES (148, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.download()', 'GET', 1, 'admin', '研发部门', '/tool/gen/download/sys_requisition', '127.0.0.1', '内网IP', '\"sys_requisition\"', NULL, 0, NULL, '2022-09-26 11:10:56');
INSERT INTO `sys_oper_log` VALUES (149, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"6\"],\"tableName\":[\"sys_requisition\"],\"tableComment\":[\"请购单\"],\"className\":[\"Requisition\"],\"functionAuthor\":[\"SKaiL\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"152\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"153\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"唯一编号\"],\"columns[1].javaType\":[\"String\"],\"columns[1].javaField\":[\"requisitionNo\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"154\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"状态\"],\"columns[2].javaType\":[\"Long\"],\"columns[2].javaField\":[\"status\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"radio\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"155\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"老状态\"],\"columns[3].javaType\":[\"Long\"],\"columns[3].javaField\":[\"oldStatus\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"radio\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"156\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"保留状态\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"oldRetainStatus\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"radio\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"157\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"老将要处理者的code\"],\"columns[5].javaType\":[\"String\"],\"columns[5].javaField\":[\"oldSendToCode\"],', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 11:12:03');
INSERT INTO `sys_oper_log` VALUES (150, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.download()', 'GET', 1, 'admin', '研发部门', '/tool/gen/download/sys_requisition', '127.0.0.1', '内网IP', '\"sys_requisition\"', NULL, 0, NULL, '2022-09-26 11:12:09');
INSERT INTO `sys_oper_log` VALUES (151, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\"menuId\":[\"2038\"],\"parentId\":[\"2000\"],\"menuType\":[\"C\"],\"menuName\":[\"请购单\"],\"url\":[\"/system/requisition\"],\"target\":[\"menuItem\"],\"perms\":[\"system:requisition:view\"],\"orderNum\":[\"2\"],\"icon\":[\"#\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 11:28:03');
INSERT INTO `sys_oper_log` VALUES (152, '菜单管理', 2, 'com.ruoyi.web.controller.system.SysMenuController.editSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/edit', '127.0.0.1', '内网IP', '{\"menuId\":[\"2044\"],\"parentId\":[\"2000\"],\"menuType\":[\"C\"],\"menuName\":[\"电子签呈\"],\"url\":[\"/system/petition\"],\"target\":[\"menuItem\"],\"perms\":[\"system:petition:view\"],\"orderNum\":[\"3\"],\"icon\":[\"#\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 11:28:12');
INSERT INTO `sys_oper_log` VALUES (153, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\"parentId\":[\"2044\"],\"menuType\":[\"C\"],\"menuName\":[\"我的发起\"],\"url\":[\"/system/petition/create\"],\"target\":[\"menuItem\"],\"perms\":[\"system:petition:view\"],\"orderNum\":[\"1\"],\"icon\":[\"\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 11:29:16');
INSERT INTO `sys_oper_log` VALUES (154, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\"parentId\":[\"2044\"],\"menuType\":[\"C\"],\"menuName\":[\"我的待办\"],\"url\":[\"/system/petition/todo\"],\"target\":[\"menuItem\"],\"perms\":[\"system:petition:view\"],\"orderNum\":[\"2\"],\"icon\":[\"\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 11:29:48');
INSERT INTO `sys_oper_log` VALUES (155, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\"parentId\":[\"2044\"],\"menuType\":[\"C\"],\"menuName\":[\"我的已办\"],\"url\":[\"/system/petition/did\"],\"target\":[\"menuItem\"],\"perms\":[\"system:petition:view\"],\"orderNum\":[\"3\"],\"icon\":[\"\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 11:30:14');
INSERT INTO `sys_oper_log` VALUES (156, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\"parentId\":[\"2044\"],\"menuType\":[\"C\"],\"menuName\":[\"我的草稿\"],\"url\":[\"\"],\"target\":[\"menuItem\"],\"perms\":[\"\"],\"orderNum\":[\"2\"],\"icon\":[\"\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 11:30:45');
INSERT INTO `sys_oper_log` VALUES (157, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\"parentId\":[\"2038\"],\"menuType\":[\"C\"],\"menuName\":[\"我的发起\"],\"url\":[\"/system/requisition/create\"],\"target\":[\"menuItem\"],\"perms\":[\"system:requisition:list\"],\"orderNum\":[\"1\"],\"icon\":[\"\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 11:33:30');
INSERT INTO `sys_oper_log` VALUES (158, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\"parentId\":[\"2038\"],\"menuType\":[\"C\"],\"menuName\":[\"我的草稿\"],\"url\":[\"/system/requisition/draft\"],\"target\":[\"menuItem\"],\"perms\":[\"system:requisition:list\"],\"orderNum\":[\"2\"],\"icon\":[\"\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 11:33:50');
INSERT INTO `sys_oper_log` VALUES (159, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\"parentId\":[\"2038\"],\"menuType\":[\"C\"],\"menuName\":[\"我的待办\"],\"url\":[\"/system/requisition/todo\"],\"target\":[\"menuItem\"],\"perms\":[\"system:requisition:list\"],\"orderNum\":[\"3\"],\"icon\":[\"\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 11:35:54');
INSERT INTO `sys_oper_log` VALUES (160, '菜单管理', 1, 'com.ruoyi.web.controller.system.SysMenuController.addSave()', 'POST', 1, 'admin', '研发部门', '/system/menu/add', '127.0.0.1', '内网IP', '{\"parentId\":[\"2038\"],\"menuType\":[\"C\"],\"menuName\":[\"我的已办\"],\"url\":[\"/system/requisition/do\"],\"target\":[\"menuItem\"],\"perms\":[\"system:requisition:list\"],\"orderNum\":[\"4\"],\"icon\":[\"\"],\"visible\":[\"0\"],\"isRefresh\":[\"1\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 11:36:28');
INSERT INTO `sys_oper_log` VALUES (161, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":[\"sys_process_flow\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 14:13:21');
INSERT INTO `sys_oper_log` VALUES (162, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"11\"],\"tableName\":[\"sys_process_flow\"],\"tableComment\":[\"流程中间表\"],\"className\":[\"ProcessFlow\"],\"functionAuthor\":[\"SKaiL\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"300\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"301\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"关联流程id\"],\"columns[1].javaType\":[\"Long\"],\"columns[1].javaField\":[\"orderId\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"302\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"创建人id\"],\"columns[2].javaType\":[\"Long\"],\"columns[2].javaField\":[\"createById\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"303\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"创建人UserCode\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"createByCode\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"304\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"创建人\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"createBy\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"input\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"305\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"发送到id\"],\"columns[5].javaType\":[\"String\"],\"columns[5].javaField\":[\"sendToId\"],\"columns[5].isInsert\":[\"1\"],\"columns[5].isEdit\":[\"1\"],\"columns[5].isList\":[\"1\"],', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 14:13:35');
INSERT INTO `sys_oper_log` VALUES (163, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"11\"],\"tableName\":[\"sys_process_flow\"],\"tableComment\":[\"流程中间表\"],\"className\":[\"ProcessFlow\"],\"functionAuthor\":[\"SKaiL\"],\"remark\":[\"流程中间表\"],\"columns[0].columnId\":[\"300\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"301\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"关联流程id\"],\"columns[1].javaType\":[\"Long\"],\"columns[1].javaField\":[\"orderId\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"302\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"创建人id\"],\"columns[2].javaType\":[\"Long\"],\"columns[2].javaField\":[\"createById\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"303\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"创建人UserCode\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"createByCode\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"304\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"创建人\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"createBy\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"input\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"305\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"发送到id\"],\"columns[5].javaType\":[\"String\"],\"columns[5].javaField\":[\"sendToId\"],\"columns[5].isInsert\":[\"1\"],\"columns[5].isEdit\":[\"1\"],\"columns[5].isList\":[', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 14:13:59');
INSERT INTO `sys_oper_log` VALUES (164, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"11\"],\"tableName\":[\"sys_process_flow\"],\"tableComment\":[\"流程中间表\"],\"className\":[\"ProcessFlow\"],\"functionAuthor\":[\"SKaiL\"],\"remark\":[\"流程中间表\"],\"columns[0].columnId\":[\"300\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"301\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"关联流程id\"],\"columns[1].javaType\":[\"Long\"],\"columns[1].javaField\":[\"orderId\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"302\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"创建人id\"],\"columns[2].javaType\":[\"Long\"],\"columns[2].javaField\":[\"createById\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"303\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"创建人UserCode\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"createByCode\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"304\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"创建人\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"createBy\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"input\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"305\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"发送到id\"],\"columns[5].javaType\":[\"String\"],\"columns[5].javaField\":[\"sendToId\"],\"columns[5].isInsert\":[\"1\"],\"columns[5].isEdit\":[\"1\"],\"columns[5].isList\":[', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 14:14:49');
INSERT INTO `sys_oper_log` VALUES (165, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"11\"],\"tableName\":[\"sys_process_flow\"],\"tableComment\":[\"流程中间表\"],\"className\":[\"ProcessFlow\"],\"functionAuthor\":[\"SKaiL\"],\"remark\":[\"\"],\"columns[0].columnId\":[\"300\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"301\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"关联流程id\"],\"columns[1].javaType\":[\"Long\"],\"columns[1].javaField\":[\"orderId\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"302\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"创建人id\"],\"columns[2].javaType\":[\"Long\"],\"columns[2].javaField\":[\"createById\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"303\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"创建人UserCode\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"createByCode\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"304\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"创建人\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"createBy\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"input\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"305\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"发送到id\"],\"columns[5].javaType\":[\"String\"],\"columns[5].javaField\":[\"sendToId\"],\"columns[5].isInsert\":[\"1\"],\"columns[5].isEdit\":[\"1\"],\"columns[5].isList\":[\"1\"],', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 14:15:24');
INSERT INTO `sys_oper_log` VALUES (166, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"11\"],\"tableName\":[\"sys_process_flow\"],\"tableComment\":[\"流程中间表\"],\"className\":[\"ProcessFlow\"],\"functionAuthor\":[\"SKaiL\"],\"remark\":[\"流程中间表\"],\"columns[0].columnId\":[\"300\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"301\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"关联流程id\"],\"columns[1].javaType\":[\"Long\"],\"columns[1].javaField\":[\"orderId\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"302\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"创建人id\"],\"columns[2].javaType\":[\"Long\"],\"columns[2].javaField\":[\"createById\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"303\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"创建人UserCode\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"createByCode\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"EQ\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"304\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"创建人\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"createBy\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"input\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"305\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"发送到id\"],\"columns[5].javaType\":[\"String\"],\"columns[5].javaField\":[\"sendToId\"],\"columns[5].isInsert\":[\"1\"],\"columns[5].isEdit\":[\"1\"],\"columns[5].isList\":[', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 14:15:38');
INSERT INTO `sys_oper_log` VALUES (167, '代码生成', 6, 'com.ruoyi.generator.controller.GenController.importTableSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/importTable', '127.0.0.1', '内网IP', '{\"tables\":[\"sys_payment_account\"]}', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 15:12:04');
INSERT INTO `sys_oper_log` VALUES (168, '代码生成', 2, 'com.ruoyi.generator.controller.GenController.editSave()', 'POST', 1, 'admin', '研发部门', '/tool/gen/edit', '127.0.0.1', '内网IP', '{\"tableId\":[\"12\"],\"tableName\":[\"sys_payment_account\"],\"tableComment\":[\"付款账号信息表\"],\"className\":[\"PaymentAccount\"],\"functionAuthor\":[\"SKaiL\"],\"remark\":[\"付款账号信息表\"],\"columns[0].columnId\":[\"316\"],\"columns[0].sort\":[\"1\"],\"columns[0].columnComment\":[\"ID\"],\"columns[0].javaType\":[\"Long\"],\"columns[0].javaField\":[\"id\"],\"columns[0].isInsert\":[\"1\"],\"columns[0].queryType\":[\"EQ\"],\"columns[0].htmlType\":[\"input\"],\"columns[0].dictType\":[\"\"],\"columns[1].columnId\":[\"317\"],\"columns[1].sort\":[\"2\"],\"columns[1].columnComment\":[\"父级ID\"],\"columns[1].javaType\":[\"Long\"],\"columns[1].javaField\":[\"parentId\"],\"columns[1].isInsert\":[\"1\"],\"columns[1].isEdit\":[\"1\"],\"columns[1].isList\":[\"1\"],\"columns[1].isQuery\":[\"1\"],\"columns[1].queryType\":[\"EQ\"],\"columns[1].htmlType\":[\"input\"],\"columns[1].dictType\":[\"\"],\"columns[2].columnId\":[\"318\"],\"columns[2].sort\":[\"3\"],\"columns[2].columnComment\":[\"收款人\"],\"columns[2].javaType\":[\"String\"],\"columns[2].javaField\":[\"payee\"],\"columns[2].isInsert\":[\"1\"],\"columns[2].isEdit\":[\"1\"],\"columns[2].isList\":[\"1\"],\"columns[2].isQuery\":[\"1\"],\"columns[2].queryType\":[\"EQ\"],\"columns[2].htmlType\":[\"input\"],\"columns[2].dictType\":[\"\"],\"columns[3].columnId\":[\"319\"],\"columns[3].sort\":[\"4\"],\"columns[3].columnComment\":[\"收款银行\"],\"columns[3].javaType\":[\"String\"],\"columns[3].javaField\":[\"bankName\"],\"columns[3].isInsert\":[\"1\"],\"columns[3].isEdit\":[\"1\"],\"columns[3].isList\":[\"1\"],\"columns[3].isQuery\":[\"1\"],\"columns[3].queryType\":[\"LIKE\"],\"columns[3].htmlType\":[\"input\"],\"columns[3].dictType\":[\"\"],\"columns[4].columnId\":[\"320\"],\"columns[4].sort\":[\"5\"],\"columns[4].columnComment\":[\"收款账号\"],\"columns[4].javaType\":[\"String\"],\"columns[4].javaField\":[\"accountNumber\"],\"columns[4].isInsert\":[\"1\"],\"columns[4].isEdit\":[\"1\"],\"columns[4].isList\":[\"1\"],\"columns[4].isQuery\":[\"1\"],\"columns[4].queryType\":[\"EQ\"],\"columns[4].htmlType\":[\"input\"],\"columns[4].dictType\":[\"\"],\"columns[5].columnId\":[\"321\"],\"columns[5].sort\":[\"6\"],\"columns[5].columnComment\":[\"收款人地址\"],\"columns[5].javaType\":[\"String\"],\"columns[5].javaField\":[\"addr', '{\"msg\":\"操作成功\",\"code\":0}', 0, NULL, '2022-09-26 15:12:55');
INSERT INTO `sys_oper_log` VALUES (169, '代码生成', 8, 'com.ruoyi.generator.controller.GenController.download()', 'GET', 1, 'admin', '研发部门', '/tool/gen/download/sys_payment_account', '127.0.0.1', '内网IP', '\"sys_payment_account\"', NULL, 0, NULL, '2022-09-26 15:12:59');

-- ----------------------------
-- Table structure for sys_payment_account
-- ----------------------------
DROP TABLE IF EXISTS `sys_payment_account`;
CREATE TABLE `sys_payment_account`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `parent_id` bigint NULL DEFAULT NULL COMMENT '父级ID',
  `payee` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款人',
  `bank_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款银行',
  `account_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款账号',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款人地址',
  `swift_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户行代码',
  `bank_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户行地址',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更信者',
  `del_flag` bigint NULL DEFAULT NULL COMMENT '删除标志（0代表删除 1代表存在）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1906 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '付款账号信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_payment_account
-- ----------------------------

-- ----------------------------
-- Table structure for sys_payment_request
-- ----------------------------
DROP TABLE IF EXISTS `sys_payment_request`;
CREATE TABLE `sys_payment_request`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `payment_request_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单据编号',
  `status` smallint UNSIGNED NULL DEFAULT NULL COMMENT '1：用户保存未提交 2：待部门主管审批 3：主管加签状态 5：财务部门财务审核 6：财务部门审核加签 7：财务总账确认 8：财务总账审核加签 9：游总核决 10：核决加签 11：出纳确认 12：出纳加签 13：财务经理审核 14：财务经理加签 15：财务总监审核 16：财务总监加签 18：前加签状态 19：否决 20：撤回 21：完成',
  `company` smallint NULL DEFAULT NULL COMMENT '公司',
  `project_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目代码',
  `applicant` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请款人',
  `employee_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工编号',
  `dept` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用部门',
  `dept_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门代码',
  `offices` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '办公室',
  `application_date` datetime(0) NULL DEFAULT NULL COMMENT '申请日期',
  `payment_date` datetime(0) NULL DEFAULT NULL COMMENT '付款日期',
  `instead_payment` smallint NULL DEFAULT NULL COMMENT '是否代请款(0:否 1:是)',
  `actual_user_no` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实际使用人编号',
  `actual_user` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实际使用人',
  `actual_dept` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实际使用部门',
  `actual_dept_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '实际使用部门代码',
  `payment_type` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请款类别(A:生产性支出-汇总请款 B:非生产性支出-持续性 C:非生产性支出-非持续性 E:交际费 X:个人报销 Y:生产性支出',
  `summary_file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产性支出汇总文件地址',
  `summary_file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '生产性支出汇总文件名',
  `prepaid` smallint NULL DEFAULT NULL COMMENT '是否预付',
  `entertainment_expense` smallint NULL DEFAULT NULL COMMENT '是否交际费(0:否 1:是)',
  `payment_amount` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '合计应付金额',
  `payment_method` smallint NULL DEFAULT NULL COMMENT '付款方式(1:转账汇款 2:其他)',
  `others` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '付款方式为其他时需要填写的备注',
  `payee` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款人',
  `bank_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户行',
  `account_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '收款人地址',
  `swift_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户行代码',
  `bank_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '开户行地址',
  `acct_supervisor` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '财务主管',
  `cashier` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '出纳',
  `approved_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '核准',
  `rechecked_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '复核',
  `checked_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '初核',
  `create_by_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人code',
  `send_to_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '将要处理者的code，如果是发起人提交，里面将保存自己',
  `handles_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '已经处理完的code，存储处理历史相关人员的code',
  `submit_time` datetime(0) NULL DEFAULT NULL COMMENT '提交时间',
  `old_send_to_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用来前加签保存上一个的send code',
  `old_status` smallint NULL DEFAULT NULL COMMENT '用来前加签保存上一个状态',
  `close_file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上传文件导致关闭文件地址',
  `close_file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上传文件导致关闭文件名',
  `del_flag` smallint NULL DEFAULT NULL COMMENT '删除标志（0代表删除 1代表存在）',
  `before_send_to_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用来前加签保存上一个的send code',
  `before_status` smallint NULL DEFAULT NULL COMMENT '用来前加签保存上一个状态',
  `after_send_to_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用来后加签保存下一个的send code',
  `after_status` smallint NULL DEFAULT NULL COMMENT '用来后加签保存上一个状态',
  `reserve_send_to_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用来保留保存下一个的send code',
  `reserve_status` smallint NULL DEFAULT NULL COMMENT '用来保留保存上一个状态',
  `create_by` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人名字',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4392 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '请款' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_payment_request
-- ----------------------------

-- ----------------------------
-- Table structure for sys_payment_request_dt1
-- ----------------------------
DROP TABLE IF EXISTS `sys_payment_request_dt1`;
CREATE TABLE `sys_payment_request_dt1`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `request_id` bigint NULL DEFAULT NULL COMMENT '关联id',
  `project_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目编号',
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '摘 要 说 明',
  `unit` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单位',
  `quantity` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数量',
  `unit_price` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单价',
  `total_amount` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总金额',
  `ratio` varchar(15) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预付比例',
  `currency` smallint NULL DEFAULT NULL COMMENT '币别',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `del_flag` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '删除标志（0代表删除 1代表存在）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9774 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '请款dt1' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_payment_request_dt1
-- ----------------------------

-- ----------------------------
-- Table structure for sys_payment_request_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_payment_request_file`;
CREATE TABLE `sys_payment_request_file`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `association_id` bigint NULL DEFAULT NULL COMMENT '相关联主键id',
  `type` smallint NULL DEFAULT NULL COMMENT '类别',
  `file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5260 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '请款相关文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_payment_request_file
-- ----------------------------

-- ----------------------------
-- Table structure for sys_petition
-- ----------------------------
DROP TABLE IF EXISTS `sys_petition`;
CREATE TABLE `sys_petition`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type_type` smallint NOT NULL COMMENT '类型',
  `com_no` char(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表单编号',
  `topic_obj` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主题',
  `explanation` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '说明文本',
  `seal_type` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '印章类型(公章 1 ,法人章2 ,财务章 3 ,合同章 4 ,法人章(经济部 小章)5 ,法人章(经济部 小章) 6 ,印鉴大章 7 印鉴小章8 )',
  `curr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '币别',
  `money_m` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '金额',
  `file_id` char(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件id',
  `seal` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '使用印监',
  `seal_explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用印使用说明',
  `seal_date` datetime(0) NULL DEFAULT NULL COMMENT '用印借出日期',
  `more_explain` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '补充内容',
  `company` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '公司代码',
  `dept` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门',
  `sid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工编号',
  `applyname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申请人名字',
  `applydate` datetime(0) NULL DEFAULT NULL COMMENT '申请日期',
  `deptmanager` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门主管',
  `deptmanager_sta` smallint NULL DEFAULT NULL COMMENT '部门主管审核意见( 1 同意 2 不同意)',
  `deptmanager_idea` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主管审核备注',
  `deptmanage_date` datetime(0) NULL DEFAULT NULL COMMENT '审核日期',
  `hr` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'HR选择',
  `hr_sta` smallint NULL DEFAULT NULL COMMENT 'hr意见(1 同意,2 不同意)',
  `hr_idea` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'hr审核备注',
  `hr_sta_date` datetime(0) NULL DEFAULT NULL COMMENT '审核日期',
  `law` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法务',
  `law_sta` smallint NULL DEFAULT NULL COMMENT '法务意见',
  `law_idea` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '法务审核备注',
  `law_date` datetime(0) NULL DEFAULT NULL COMMENT '法务审核时间',
  `ac_officer` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会计主管',
  `ac_officer_date` datetime(0) NULL DEFAULT NULL COMMENT '审核日期',
  `ac_officer_idea` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会计主管审核备注',
  `ac_officer_sta` smallint NULL DEFAULT NULL COMMENT '会计主管审核意见',
  `fc_manager` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '财务总账',
  `fc_manager_sta` smallint NULL DEFAULT NULL COMMENT '财务总账意见',
  `fc_manager_idea` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '财务总账审核备注',
  `fc_manager_date` datetime(0) NULL DEFAULT NULL COMMENT '财务总账审核日期',
  `cfco_manager` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '财务长',
  `cfco_manager_sta` smallint NULL DEFAULT NULL COMMENT '财务长审核意见',
  `cfco_manager_idea` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '财务长审核备注',
  `cfco_manager_date` datetime(0) NULL DEFAULT NULL COMMENT '财务长审核日期',
  `gm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总经理',
  `gm_sta` smallint NULL DEFAULT NULL COMMENT '总经理审核意见',
  `gm_idea` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总经理审核备注',
  `gm_date` datetime(0) NULL DEFAULT NULL COMMENT '总经理审核日期',
  `creat_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `creat_date` datetime(0) NULL DEFAULT NULL COMMENT '创建日期',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新日期',
  `status` smallint NULL DEFAULT NULL COMMENT '状态(.....)',
  `from_sendto` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送接收者',
  `form_sta` smallint NULL DEFAULT NULL COMMENT '表单状态(10  未完成 8 完成)',
  `old_sta` smallint NULL DEFAULT NULL COMMENT '加签状态( 1 加签  0 非加签)',
  `addaudit_sta` smallint NULL DEFAULT NULL COMMENT '1 是前加签 2 是后加签',
  `before_send_to_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用来前加签保存上一个的send code',
  `before_status` smallint NULL DEFAULT NULL COMMENT '用来前加签保存上一个状态',
  `after_send_to_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用来后加签保存下一个的send code',
  `after_status` smallint NULL DEFAULT NULL COMMENT '用来后加签保存上一个状态',
  `flag` int NULL DEFAULT NULL COMMENT '0 隐藏  1  显示',
  `recall_date` datetime(0) NULL DEFAULT NULL COMMENT '撤回时间',
  `recall_reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '撤回原因',
  `close_file` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上传文件导致关闭文件地址',
  `close_file_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上传文件导致关闭文件名字',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1659 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '电子签呈' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_petition
-- ----------------------------

-- ----------------------------
-- Table structure for sys_petition_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_petition_file`;
CREATE TABLE `sys_petition_file`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `association_id` bigint NULL DEFAULT NULL COMMENT '相关联主键id',
  `type` smallint NULL DEFAULT NULL COMMENT '类别',
  `file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1573 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '签呈单文件' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_petition_file
-- ----------------------------

-- ----------------------------
-- Table structure for sys_petition_sign
-- ----------------------------
DROP TABLE IF EXISTS `sys_petition_sign`;
CREATE TABLE `sys_petition_sign`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `sign_type` int NULL DEFAULT NULL COMMENT '签核角色',
  `add_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '签核人员',
  `sid` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工号',
  `result` int NULL DEFAULT NULL COMMENT '审核结果',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注意见',
  `add_time` datetime(0) NULL DEFAULT NULL COMMENT '签核时间',
  `tosend` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送到',
  `petition_id` bigint NULL DEFAULT NULL COMMENT '关联id',
  `fromsend` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送者',
  `old_status` int NULL DEFAULT NULL COMMENT '老状态',
  `new_status` int NULL DEFAULT NULL,
  `last_add` int NULL DEFAULT NULL COMMENT '1 是最后一个后加签人员',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `del_flag` int NULL DEFAULT NULL COMMENT '1是删除不会显示',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '核准人员表' ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of sys_petition_sign
-- ----------------------------

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`  (
  `post_id` bigint NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
  `post_sort` int NOT NULL COMMENT '显示顺序',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '岗位信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_post
-- ----------------------------
INSERT INTO `sys_post` VALUES (1, 'ceo', '董事长', 1, '0', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_post` VALUES (2, 'se', '项目经理', 2, '0', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_post` VALUES (3, 'hr', '人力资源', 3, '0', 'admin', '2022-09-19 10:49:17', '', NULL, '');
INSERT INTO `sys_post` VALUES (4, 'user', '普通员工', 4, '0', 'admin', '2022-09-19 10:49:17', '', NULL, '');

-- ----------------------------
-- Table structure for sys_process_flow
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_flow`;
CREATE TABLE `sys_process_flow`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `order_id` int NULL DEFAULT NULL COMMENT '关联流程id',
  `create_by_id` int NULL DEFAULT NULL COMMENT '创建人id',
  `create_by_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人UserCode',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `send_to_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '发送到id',
  `status` int NULL DEFAULT NULL COMMENT '流程当前的状态',
  `endorsement_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加签人员的名字',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  `order_type` int NULL DEFAULT NULL COMMENT '流程类型',
  `audit_result` smallint NULL DEFAULT NULL COMMENT '审核结果，不同流程不一样',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `order_node_type` int NULL DEFAULT NULL COMMENT '流程中间表新增某一个流程当前所处的节点',
  `review_order` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '审核顺序',
  `del_flag` smallint(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '删除标志(0 隐藏 1 显示)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34388 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '流程中间表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_process_flow
-- ----------------------------

-- ----------------------------
-- Table structure for sys_requisition
-- ----------------------------
DROP TABLE IF EXISTS `sys_requisition`;
CREATE TABLE `sys_requisition`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `requisition_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '唯一编号',
  `status` smallint NULL DEFAULT NULL COMMENT '状态',
  `old_status` smallint NULL DEFAULT NULL COMMENT '老状态',
  `old_retain_status` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '保留状态',
  `old_send_to_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '老将要处理者的code',
  `employee_no` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工编号',
  `hr_sign` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '新员工入职hr签字',
  `new_staff` smallint NULL DEFAULT NULL COMMENT '是否新员工',
  `product_line` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品线',
  `product_line1` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品线详细',
  `product_line_other` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '产品线其他',
  `user_department` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门编号',
  `company` smallint NULL DEFAULT NULL COMMENT '公司',
  `purchase_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '申购单号',
  `department_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区部门编号',
  `office` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地区',
  `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工姓名',
  `user_date` datetime(0) NULL DEFAULT NULL COMMENT '需求日期',
  `type_of_application` smallint NULL DEFAULT NULL COMMENT '申请类别',
  `asset_management` smallint NULL DEFAULT NULL COMMENT '资产管理人员，可填可不填，如果填1，则是桌面，如果填2则是测试，需要转到相应的人员进行审核',
  `category` smallint NULL DEFAULT NULL COMMENT '所属类别',
  `reason_for_purchase` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请购原因',
  `price_comparison` smallint NULL DEFAULT NULL COMMENT '是否须比议价',
  `special_reason` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '特殊原因说明',
  `total_amount` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'dt1合计金额',
  `submit_time` datetime(0) NULL DEFAULT NULL COMMENT '提交时间，用来保存提交流程的时刻',
  `create_by_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人code',
  `send_to_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '将要处理者的code，如果是发起人提交，里面将保存自己',
  `handles_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '已经处理完的code，存储处理历史相关人员的code',
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人名字',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `close_file` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上传文件导致关闭文件地址',
  `close_file_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上传文件导致关闭文件名',
  `del_flag` smallint NULL DEFAULT NULL COMMENT '删除标志（0代表删除 1代表存在）',
  `before_send_to_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用来前加签保存上一个的send code',
  `before_status` smallint NULL DEFAULT NULL COMMENT '用来前加签保存上一个状态',
  `after_send_to_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用来后加签保存下一个的send code',
  `after_status` smallint NULL DEFAULT NULL COMMENT '用来后加签保存上一个状态',
  `reserve_send_to_code` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用来保留保存下一个的send code',
  `reserve_status` smallint NULL DEFAULT NULL COMMENT '用来保留保存上一个状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2482 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '请购单主表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_requisition
-- ----------------------------

-- ----------------------------
-- Table structure for sys_requisition_dt1
-- ----------------------------
DROP TABLE IF EXISTS `sys_requisition_dt1`;
CREATE TABLE `sys_requisition_dt1`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `requisition_id` bigint NULL DEFAULT NULL COMMENT '主表id',
  `project_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目编号',
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `specifications` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '规格说明',
  `reason` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '购买原因',
  `quantity` int NULL DEFAULT NULL COMMENT '购买数量',
  `unit_price` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单价',
  `amount` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '金额',
  `coin` smallint NULL DEFAULT NULL COMMENT '购买币别',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10266 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '请购单产品明细dt1' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_requisition_dt1
-- ----------------------------

-- ----------------------------
-- Table structure for sys_requisition_dt2
-- ----------------------------
DROP TABLE IF EXISTS `sys_requisition_dt2`;
CREATE TABLE `sys_requisition_dt2`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `requisition_id` bigint NULL DEFAULT NULL COMMENT '主表id',
  `suppliers` smallint NULL DEFAULT NULL COMMENT '供应商是否选中',
  `project_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '项目名称',
  `suppliers_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商名称',
  `suppliers_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '供应商编号',
  `tax_rate` varchar(0) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '税率',
  `coin` smallint NULL DEFAULT NULL COMMENT '币别',
  `total_amount` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '总金额',
  `untaxed_unit_price` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '未税单价',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5248 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '请购单供应商明显dt2' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_requisition_dt2
-- ----------------------------

-- ----------------------------
-- Table structure for sys_requisition_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_requisition_file`;
CREATE TABLE `sys_requisition_file`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `association_id` bigint NULL DEFAULT NULL COMMENT '相关联主键id',
  `type` smallint NULL DEFAULT NULL COMMENT '类别',
  `file` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件路径',
  `file_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '文件名',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2432 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文件' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_requisition_file
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `role_id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色权限字符串',
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', 'admin', 1, '1', '0', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '超级管理员');
INSERT INTO `sys_role` VALUES (2, '普通角色', 'common', 2, '2', '0', '0', 'admin', '2022-09-19 10:49:17', '', NULL, '普通角色');

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`  (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `dept_id` bigint NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`, `dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_dept
-- ----------------------------
INSERT INTO `sys_role_dept` VALUES (2, 100);
INSERT INTO `sys_role_dept` VALUES (2, 101);
INSERT INTO `sys_role_dept` VALUES (2, 105);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES (2, 1);
INSERT INTO `sys_role_menu` VALUES (2, 2);
INSERT INTO `sys_role_menu` VALUES (2, 3);
INSERT INTO `sys_role_menu` VALUES (2, 4);
INSERT INTO `sys_role_menu` VALUES (2, 100);
INSERT INTO `sys_role_menu` VALUES (2, 101);
INSERT INTO `sys_role_menu` VALUES (2, 102);
INSERT INTO `sys_role_menu` VALUES (2, 103);
INSERT INTO `sys_role_menu` VALUES (2, 104);
INSERT INTO `sys_role_menu` VALUES (2, 105);
INSERT INTO `sys_role_menu` VALUES (2, 106);
INSERT INTO `sys_role_menu` VALUES (2, 107);
INSERT INTO `sys_role_menu` VALUES (2, 108);
INSERT INTO `sys_role_menu` VALUES (2, 109);
INSERT INTO `sys_role_menu` VALUES (2, 110);
INSERT INTO `sys_role_menu` VALUES (2, 111);
INSERT INTO `sys_role_menu` VALUES (2, 112);
INSERT INTO `sys_role_menu` VALUES (2, 113);
INSERT INTO `sys_role_menu` VALUES (2, 114);
INSERT INTO `sys_role_menu` VALUES (2, 115);
INSERT INTO `sys_role_menu` VALUES (2, 116);
INSERT INTO `sys_role_menu` VALUES (2, 500);
INSERT INTO `sys_role_menu` VALUES (2, 501);
INSERT INTO `sys_role_menu` VALUES (2, 1000);
INSERT INTO `sys_role_menu` VALUES (2, 1001);
INSERT INTO `sys_role_menu` VALUES (2, 1002);
INSERT INTO `sys_role_menu` VALUES (2, 1003);
INSERT INTO `sys_role_menu` VALUES (2, 1004);
INSERT INTO `sys_role_menu` VALUES (2, 1005);
INSERT INTO `sys_role_menu` VALUES (2, 1006);
INSERT INTO `sys_role_menu` VALUES (2, 1007);
INSERT INTO `sys_role_menu` VALUES (2, 1008);
INSERT INTO `sys_role_menu` VALUES (2, 1009);
INSERT INTO `sys_role_menu` VALUES (2, 1010);
INSERT INTO `sys_role_menu` VALUES (2, 1011);
INSERT INTO `sys_role_menu` VALUES (2, 1012);
INSERT INTO `sys_role_menu` VALUES (2, 1013);
INSERT INTO `sys_role_menu` VALUES (2, 1014);
INSERT INTO `sys_role_menu` VALUES (2, 1015);
INSERT INTO `sys_role_menu` VALUES (2, 1016);
INSERT INTO `sys_role_menu` VALUES (2, 1017);
INSERT INTO `sys_role_menu` VALUES (2, 1018);
INSERT INTO `sys_role_menu` VALUES (2, 1019);
INSERT INTO `sys_role_menu` VALUES (2, 1020);
INSERT INTO `sys_role_menu` VALUES (2, 1021);
INSERT INTO `sys_role_menu` VALUES (2, 1022);
INSERT INTO `sys_role_menu` VALUES (2, 1023);
INSERT INTO `sys_role_menu` VALUES (2, 1024);
INSERT INTO `sys_role_menu` VALUES (2, 1025);
INSERT INTO `sys_role_menu` VALUES (2, 1026);
INSERT INTO `sys_role_menu` VALUES (2, 1027);
INSERT INTO `sys_role_menu` VALUES (2, 1028);
INSERT INTO `sys_role_menu` VALUES (2, 1029);
INSERT INTO `sys_role_menu` VALUES (2, 1030);
INSERT INTO `sys_role_menu` VALUES (2, 1031);
INSERT INTO `sys_role_menu` VALUES (2, 1032);
INSERT INTO `sys_role_menu` VALUES (2, 1033);
INSERT INTO `sys_role_menu` VALUES (2, 1034);
INSERT INTO `sys_role_menu` VALUES (2, 1035);
INSERT INTO `sys_role_menu` VALUES (2, 1036);
INSERT INTO `sys_role_menu` VALUES (2, 1037);
INSERT INTO `sys_role_menu` VALUES (2, 1038);
INSERT INTO `sys_role_menu` VALUES (2, 1039);
INSERT INTO `sys_role_menu` VALUES (2, 1040);
INSERT INTO `sys_role_menu` VALUES (2, 1041);
INSERT INTO `sys_role_menu` VALUES (2, 1042);
INSERT INTO `sys_role_menu` VALUES (2, 1043);
INSERT INTO `sys_role_menu` VALUES (2, 1044);
INSERT INTO `sys_role_menu` VALUES (2, 1045);
INSERT INTO `sys_role_menu` VALUES (2, 1046);
INSERT INTO `sys_role_menu` VALUES (2, 1047);
INSERT INTO `sys_role_menu` VALUES (2, 1048);
INSERT INTO `sys_role_menu` VALUES (2, 1049);
INSERT INTO `sys_role_menu` VALUES (2, 1050);
INSERT INTO `sys_role_menu` VALUES (2, 1051);
INSERT INTO `sys_role_menu` VALUES (2, 1052);
INSERT INTO `sys_role_menu` VALUES (2, 1053);
INSERT INTO `sys_role_menu` VALUES (2, 1054);
INSERT INTO `sys_role_menu` VALUES (2, 1055);
INSERT INTO `sys_role_menu` VALUES (2, 1056);
INSERT INTO `sys_role_menu` VALUES (2, 1057);
INSERT INTO `sys_role_menu` VALUES (2, 1058);
INSERT INTO `sys_role_menu` VALUES (2, 1059);
INSERT INTO `sys_role_menu` VALUES (2, 1060);
INSERT INTO `sys_role_menu` VALUES (2, 1061);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `dept_id` bigint NULL DEFAULT NULL COMMENT '部门ID',
  `login_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录账号',
  `user_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户昵称',
  `user_type` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户 01注册用户）',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
  `phonenumber` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
  `sex` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
  `avatar` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像路径',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '盐加密',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
  `pwd_update_date` datetime(0) NULL DEFAULT NULL COMMENT '密码最后更新时间',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 100 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 103, 'admin', '若依', '00', 'ry@163.com', '15888888888', '1', '', '29c67a30398638269fe600f73a054934', '111111', '0', '0', '127.0.0.1', '2022-09-26 15:34:40', '2022-09-19 10:49:17', 'admin', '2022-09-19 10:49:17', '', '2022-09-26 15:34:40', '管理员');
INSERT INTO `sys_user` VALUES (2, 105, 'ry', '若依', '00', 'ry@qq.com', '15666666666', '1', '', '8e6d98b90472783cc73c17047ddccf36', '222222', '0', '0', '127.0.0.1', '2022-09-19 10:49:17', '2022-09-19 10:49:17', 'admin', '2022-09-19 10:49:17', '', NULL, '测试员');

-- ----------------------------
-- Table structure for sys_user_online
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_online`;
CREATE TABLE `sys_user_online`  (
  `sessionId` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '用户会话id',
  `login_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录账号',
  `dept_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
  `ipaddr` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作系统',
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '在线状态on_line在线off_line离线',
  `start_timestamp` datetime(0) NULL DEFAULT NULL COMMENT 'session创建时间',
  `last_access_time` datetime(0) NULL DEFAULT NULL COMMENT 'session最后访问时间',
  `expire_time` int NULL DEFAULT 0 COMMENT '超时时间，单位为分钟',
  PRIMARY KEY (`sessionId`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '在线用户记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_online
-- ----------------------------
INSERT INTO `sys_user_online` VALUES ('676657b9-7043-4ee4-a09a-deaf4dcf831d', 'admin', '研发部门', '127.0.0.1', '内网IP', 'Chrome 10', 'Windows 10', 'on_line', '2022-09-26 15:11:54', '2022-09-26 15:34:40', 1800000);

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`  (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `post_id` bigint NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`, `post_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_post
-- ----------------------------
INSERT INTO `sys_user_post` VALUES (1, 1);
INSERT INTO `sys_user_post` VALUES (2, 2);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (2, 2);

SET FOREIGN_KEY_CHECKS = 1;
