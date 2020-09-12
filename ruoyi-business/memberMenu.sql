-- 菜单 SQL
insert into sys_menu (menu_name, target, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('会员管理', 'menuItem', '0', '6', '/business/member', 'C', '0', 'business:member:view', 'fa fa-user', 'admin', '2018-03-01', 'ry', '2018-03-01', '会员菜单');


-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('会员查询', @parentId, '1',  '#',  'F', '0', 'business:member:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('会员新增', @parentId, '2',  '#',  'F', '0', 'business:member:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('会员修改', @parentId, '3',  '#',  'F', '0', 'business:member:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('会员删除', @parentId, '4',  '#',  'F', '0', 'business:member:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('会员导出', @parentId, '5',  '#',  'F', '0', 'business:member:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
