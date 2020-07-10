-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('封铅登记', '3', '1', '/fq/fqTable', 'C', '0', 'fq:fqTable:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '封铅登记菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('封铅登记查询', @parentId, '1',  '#',  'F', '0', 'fq:fqTable:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('封铅登记新增', @parentId, '2',  '#',  'F', '0', 'fq:fqTable:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('封铅登记修改', @parentId, '3',  '#',  'F', '0', 'fq:fqTable:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('封铅登记删除', @parentId, '4',  '#',  'F', '0', 'fq:fqTable:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('封铅登记导出', @parentId, '5',  '#',  'F', '0', 'fq:fqTable:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('封铅登记导入', @parentId, '6',  '#',  'F', '0', 'fq:fqTable:import',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('封铅登记详情', @parentId, '7',  '#',  'F', '0', 'fq:fqTable:detail',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
