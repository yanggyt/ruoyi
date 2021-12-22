insert into sys_dict_type values(11,  '尺码', 'busi_size',        '0', 'admin', sysdate(), '', null, '尺码列表');
insert into sys_dict_type values(12,  '颜色', 'busi_color',        '0', 'admin', sysdate(), '', null, '颜色列表');
insert into sys_dict_type values(13,  '客户角色', 'busi_role',        '0', 'admin', sysdate(), '', null, '客户角色列表');
insert into sys_dict_type values(14,  '产线状态', 'busi_line_status',        '0', 'admin', sysdate(), '', null, '产线状态');
insert into sys_dict_type values(15,  '类型', 'busi_line_type',        '0', 'admin', sysdate(), '', null, '监区产线类型');
insert into sys_dict_type values(16,  '订单类型', 'busi_order_type',        '0', 'admin', sysdate(), '', null, '订单类型');
insert into sys_dict_type values(17,  '订单状态', 'busi_order_status',        '0', 'admin', sysdate(), '', null, '订单状态');
insert into sys_dict_type values(18,  '物料类型', 'busi_material_type',        '0', 'admin', sysdate(), '', null, '物料类型');
insert into sys_dict_type values(19,  '物料单位', 'busi_material_unit',        '0', 'admin', sysdate(), '', null, '物料单位');


-- 尺码字典
insert into sys_dict_data values (30, 1, 'XXXXXS', '1', 'busi_size', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (31, 2, 'XXXXS', '2', 'busi_size', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (32, 3, 'XXXS', '3', 'busi_size', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (33, 4, 'XXS', '4', 'busi_size', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (34, 5, 'XS', '5', 'busi_size', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (35, 6, 'S', '6', 'busi_size', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (36, 7, 'M', '7', 'busi_size', '', '', 'Y', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (37, 8, 'L', '8', 'busi_size', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (38, 9, 'XL', '9', 'busi_size', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (39, 10, 'XXL', '10', 'busi_size', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (40, 11, 'XXXL', '11', 'busi_size', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (41, 12, 'XXXXL', '12', 'busi_size', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (42, 13, 'XXXXXL', '13', 'busi_size', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (43, 14, 'XXXXXXL', '14', 'busi_size', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (44, 15, '通码', '15', 'busi_size', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
-- 颜色字典
insert into sys_dict_data values (45, 1, '白色', '1', 'busi_color', '', '', 'Y', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (46, 2, '黑色', '2', 'busi_color', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (47, 3, '红色', '3', 'busi_color', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (48, 4, '黄色', '4', 'busi_color', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (49, 5, '蓝色', '5', 'busi_color', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (50, 6, '绿色', '6', 'busi_color', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (51, 7, '橙色', '7', 'busi_color', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (52, 8, '青色', '8', 'busi_color', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (53, 9, '紫色', '9', 'busi_color', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (54, 10, '卡其色', '10', 'busi_color', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (55, 11, '灰色', '11', 'busi_color', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (56, 12, '默认', '12', 'busi_color', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (57, 13, '铁色', '13', 'busi_color', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (58, 14, '粉色', '14', 'busi_color', '', '', 'N', '0', 'admin', sysdate(), '', null, '');

-- 客户角色字典
insert into sys_dict_data values (59, 1, '负责人', '1', 'busi_role', '', '', 'Y', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (60, 2, '跟单人员', '2', 'busi_role', '', '', 'N', '0', 'admin', sysdate(), '', null, '');

-- 产线状态字典
insert into sys_dict_data values (61, 0, '空闲', '0', 'busi_line_status', '', '', 'Y', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (62, 1, '生产中', '1', 'busi_line_status', '', '', 'N', '0', 'admin', sysdate(), '', null, '');

-- 产线类型字典
insert into sys_dict_data values (63, 1, '监区', 'J', 'busi_line_type', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (64, 2, '产线', 'C', 'busi_line_type', '', '', 'Y', '0', 'admin', sysdate(), '', null, '');

-- 订单类型
insert into sys_dict_data values (65, 1, '外套', '1', 'busi_order_type', '', '', 'Y', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (66, 2, '裤子', '2', 'busi_order_type', '', '', 'N', '0', 'admin', sysdate(), '', null, '');

-- 订单状态
insert into sys_dict_data values (67, 1, '创建', '1', 'busi_order_status', '', '', 'Y', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (68, 2, '生产中', '2', 'busi_order_status', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (69, 3, '完成', '3', 'busi_order_status', '', '', 'N', '0', 'admin', sysdate(), '', null, '');

-- 物料类型
insert into sys_dict_data values (70, 1, '布', '1', 'busi_material_type', '', '', 'Y', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (71, 2, '拉链', '2', 'busi_material_type', '', '', 'N', '0', 'admin', sysdate(), '', null, '');

-- 物料单位
insert into sys_dict_data values (72, 1, '米', '1', 'busi_material_unit', '', '', 'Y', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values (73, 2, '件', '2', 'busi_material_unit', '', '', 'N', '0', 'admin', sysdate(), '', null, '');



-- 一级菜单
insert into sys_menu values ('117', '生产信息', '0', '1', '#', '', 'M', '0', '1', '', 'fa fa-wrench', 'admin', sysdate(), '', null, '生产信息菜单');
insert into sys_menu values ('118', '物料信息', '0', '2', '#', '', 'M', '0', '1', '', 'fa fa-cubes', 'admin', sysdate(), '', null, '物料信息菜单');
insert into sys_menu values ('119', '订单信息', '0', '3', '#', '', 'M', '0', '1', '', 'fa fa-tasks', 'admin', sysdate(), '', null, '订单信息菜单');
insert into sys_menu values ('120', '成品管理', '0', '4', '#', '', 'M', '0', '1', '', 'fa fa-male', 'admin', sysdate(), '', null, '成品管理菜单');
insert into sys_menu values ('121', '客户管理', '0', '5', '#', '', 'M', '0', '1', '', 'fa fa-address-book-o', 'admin', sysdate(), '', null, '客户管理菜单');
insert into sys_menu values ('122', '财务管理', '0', '6', '#', '', 'M', '0', '1', '', 'fa fa-cny', 'admin', sysdate(), '', null, '财务管理菜单');
-- 二级菜单
insert into sys_menu values ('502', '生产经营总览', '117', '1', '/monitor/operlog', '', 'C', '0', '1', 'monitor:operlog:view', 'fa fa-address-book', 'admin', sysdate(), '', null, '生产经营总览菜单');
insert into sys_menu values ('503', '生产线总产值', '117', '2', '/monitor/operlog', '', 'C', '0', '1', 'monitor:operlog:view', 'fa fa-address-book', 'admin', sysdate(), '', null, '生产经营总览菜单');
insert into sys_menu values ('504', '布料管理', '118', '1', '/monitor/operlog', '', 'C', '0', '1', 'monitor:operlog:view', 'fa fa-address-book', 'admin', sysdate(), '', null, '布料管理菜单');
insert into sys_menu values ('505', '辅料管理', '118', '2', '/monitor/operlog', '', 'C', '0', '1', 'monitor:operlog:view', 'fa fa-address-book', 'admin', sysdate(), '', null, '辅料管理菜单');
insert into sys_menu values ('506', '填充物管理', '118', '3', '/monitor/operlog', '', 'C', '0', '1', 'monitor:operlog:view', 'fa fa-address-book', 'admin', sysdate(), '', null, '填充物管理菜单');
insert into sys_menu values ('507', '上料管理', '118', '4', '/monitor/operlog', '', 'C', '0', '1', 'monitor:operlog:view', 'fa fa-address-book', 'admin', sysdate(), '', null, '上料管理菜单');
insert into sys_menu values ('508', '历史订单', '119', '1', '/monitor/operlog', '', 'C', '0', '1', 'monitor:operlog:view', 'fa fa-address-book', 'admin', sysdate(), '', null, '历史订单菜单');
insert into sys_menu values ('509', '在线订单', '119', '2', '/monitor/operlog', '', 'C', '0', '1', 'monitor:operlog:view', 'fa fa-address-book', 'admin', sysdate(), '', null, '在线订单菜单');
insert into sys_menu values ('510', '衔接订单', '119', '3', '/monitor/operlog', '', 'C', '0', '1', 'monitor:operlog:view', 'fa fa-address-book', 'admin', sysdate(), '', null, '衔接订单菜单');
insert into sys_menu values ('511', '出货管理', '120', '1', '/monitor/operlog', '', 'C', '0', '1', 'monitor:operlog:view', 'fa fa-address-book', 'admin', sysdate(), '', null, '出货管理菜单');
insert into sys_menu values ('512', '交货管理', '120', '2', '/monitor/operlog', '', 'C', '0', '1', 'monitor:operlog:view', 'fa fa-address-book', 'admin', sysdate(), '', null, '交货管理菜单');
insert into sys_menu values ('513', '返修管理', '120', '3', '/monitor/operlog', '', 'C', '0', '1', 'monitor:operlog:view', 'fa fa-address-book', 'admin', sysdate(), '', null, '返修管理菜单');
insert into sys_menu values ('517', '客户税号', '122', '1', '/monitor/operlog', '', 'C', '0', '1', 'monitor:operlog:view', 'fa fa-address-book', 'admin', sysdate(), '', null, '客户税号菜单');
