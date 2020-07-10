-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('封铅袋出入库记录', '3', '1', '/fq/logs', 'C', '0', 'fq:logs:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '封铅袋出入库记录菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('封铅袋出入库记录查询', @parentId, '1',  '#',  'F', '0', 'fq:logs:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('封铅袋出入库记录新增', @parentId, '2',  '#',  'F', '0', 'fq:logs:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('封铅袋出入库记录修改', @parentId, '3',  '#',  'F', '0', 'fq:logs:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('封铅袋出入库记录删除', @parentId, '4',  '#',  'F', '0', 'fq:logs:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('封铅袋出入库记录导出', @parentId, '5',  '#',  'F', '0', 'fq:logs:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('封铅袋出入库记录导入', @parentId, '6',  '#',  'F', '0', 'fq:logs:import',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('封铅袋出入库记录详情', @parentId, '7',  '#',  'F', '0', 'fq:logs:detail',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
