-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('资金池交明细', '3', '1', '/mengyu/moneyPoolLog', 'C', '0', 'mengyu:moneyPoolLog:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '资金池交明细菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('资金池交明细查询', @parentId, '1',  '#',  'F', '0', 'mengyu:moneyPoolLog:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('资金池交明细新增', @parentId, '2',  '#',  'F', '0', 'mengyu:moneyPoolLog:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('资金池交明细修改', @parentId, '3',  '#',  'F', '0', 'mengyu:moneyPoolLog:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('资金池交明细删除', @parentId, '4',  '#',  'F', '0', 'mengyu:moneyPoolLog:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
