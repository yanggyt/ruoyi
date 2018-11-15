-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('投资品种', '3', '1', '/mengyu/goods', 'C', '0', 'mengyu:goods:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '投资品种菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('投资品种查询', @parentId, '1',  '#',  'F', '0', 'mengyu:goods:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('投资品种新增', @parentId, '2',  '#',  'F', '0', 'mengyu:goods:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('投资品种修改', @parentId, '3',  '#',  'F', '0', 'mengyu:goods:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('投资品种删除', @parentId, '4',  '#',  'F', '0', 'mengyu:goods:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
