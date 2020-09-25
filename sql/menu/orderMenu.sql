-- 菜单 SQL
insert into sys_menu (menu_name, target, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单管理', 'menuItem', '0', '6', '/business/order', 'C', '0', 'business:order:view', 'fa fa-shopping-cart', 'admin', '2018-03-01', 'ry', '2018-03-01', '订单菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单查询', '@parentId', '1',  '#',  'F', '0', 'business:order:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单新增', '@parentId', '2',  '#',  'F', '0', 'business:order:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单修改', '@parentId', '3',  '#',  'F', '0', 'business:order:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单删除', '@parentId', '4',  '#',  'F', '0', 'business:order:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('订单导出', '@parentId', '5',  '#',  'F', '0', 'business:order:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
