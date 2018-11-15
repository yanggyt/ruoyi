-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('资金池', '3', '1', '/mengyu/moneyPool', 'C', '0', 'mengyu:moneyPool:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '资金池菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('资金池查询', @parentId, '1',  '#',  'F', '0', 'mengyu:moneyPool:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('资金池新增', @parentId, '2',  '#',  'F', '0', 'mengyu:moneyPool:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('资金池修改', @parentId, '3',  '#',  'F', '0', 'mengyu:moneyPool:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('资金池删除', @parentId, '4',  '#',  'F', '0', 'mengyu:moneyPool:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
