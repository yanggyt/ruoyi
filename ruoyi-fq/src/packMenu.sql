-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('封铅袋', '3', '1', '/fq/pack', 'C', '0', 'fq:pack:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '封铅袋菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('封铅袋查询', @parentId, '1',  '#',  'F', '0', 'fq:pack:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('封铅袋新增', @parentId, '2',  '#',  'F', '0', 'fq:pack:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('封铅袋修改', @parentId, '3',  '#',  'F', '0', 'fq:pack:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('封铅袋删除', @parentId, '4',  '#',  'F', '0', 'fq:pack:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('封铅袋导出', @parentId, '5',  '#',  'F', '0', 'fq:pack:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
