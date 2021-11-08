insert into sys_dict_type values(11,  '尺码', 'busi_size',        '0', 'admin', sysdate(), '', null, '尺码列表');
insert into sys_dict_type values(12,  '颜色', 'busi_color',        '0', 'admin', sysdate(), '', null, '颜色列表');

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

-- 一级菜单
insert into sys_menu
values ('5', '劳务生产管理', '0', '5', '#', '', 'M', '0', '1', '', 'fa fa-bars', 'admin', sysdate(), '', null, '劳务生产管理');
-- 二级菜单
insert into sys_menu values ('117', '生产信息', '5', '1', '#', '', 'C', '0', '1', '', 'fa fa-bars', 'admin', sysdate(), '', null, '生产信息菜单');
insert into sys_menu values ('118', '物料信息', '5', '2', '#', '', 'C', '0', '1', '', 'fa fa-bars', 'admin', sysdate(), '', null, '物料信息菜单');
insert into sys_menu values ('119', '订单信息', '5', '3', '#', '', 'C', '0', '1', '', 'fa fa-bars', 'admin', sysdate(), '', null, '订单信息菜单');
insert into sys_menu values ('120', '成品管理', '5', '4', '#', '', 'C', '0', '1', '', 'fa fa-bars', 'admin', sysdate(), '', null, '成品管理菜单');
insert into sys_menu values ('121', '客户管理', '5', '5', '#', '', 'C', '0', '1', '', 'fa fa-bars', 'admin', sysdate(), '', null, '客户管理菜单');
insert into sys_menu values ('122', '财务管理', '5', '6', '#', '', 'C', '0', '1', '', 'fa fa-bars', 'admin', sysdate(), '', null, '财务管理菜单');
-- 三级菜单
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
insert into sys_menu values ('514', '客户负责人信息', '121', '1', '/monitor/operlog', '', 'C', '0', '1', 'monitor:operlog:view', 'fa fa-address-book', 'admin', sysdate(), '', null, '客户负责人信息菜单');
insert into sys_menu values ('515', '跟单人员信息', '121', '2', '/monitor/operlog', '', 'C', '0', '1', 'monitor:operlog:view', 'fa fa-address-book', 'admin', sysdate(), '', null, '跟单人员信息菜单');
insert into sys_menu values ('516', '款号信息', '121', '3', '/monitor/operlog', '', 'C', '0', '1', 'monitor:operlog:view', 'fa fa-address-book', 'admin', sysdate(), '', null, '款号信息菜单');
insert into sys_menu values ('517', '客户税号', '122', '1', '/monitor/operlog', '', 'C', '0', '1', 'monitor:operlog:view', 'fa fa-address-book', 'admin', sysdate(), '', null, '客户税号菜单');
