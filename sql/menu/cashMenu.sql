-- 菜单 SQL
insert into sys_menu (menu_name, target, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('提现管理', 'menuItem', '0', '7', '/business/cash', 'C', '0', 'business:cash:view', 'fa fa-child', 'admin', '2018-03-01', 'ry', '2018-03-01', '提现菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('兑现申请记录查询', @parentId, '1',  '#',  'F', '0', 'business:cash:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('兑现申请记录新增', @parentId, '2',  '#',  'F', '0', 'business:cash:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('兑现申请记录修改', @parentId, '3',  '#',  'F', '0', 'business:cash:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('兑现申请记录删除', @parentId, '4',  '#',  'F', '0', 'business:cash:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('兑现申请记录导出', @parentId, '5',  '#',  'F', '0', 'business:cash:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
