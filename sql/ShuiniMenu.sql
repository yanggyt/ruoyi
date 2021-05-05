-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('水泥浆', '2000', '1', '/system/Shuini', 'C', '0', 'system:Shuini:view', '#', 'admin', sysdate(), '', null, '水泥浆菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('水泥浆查询', @parentId, '1',  '#',  'F', '0', 'system:Shuini:list',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('水泥浆新增', @parentId, '2',  '#',  'F', '0', 'system:Shuini:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('水泥浆修改', @parentId, '3',  '#',  'F', '0', 'system:Shuini:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('水泥浆删除', @parentId, '4',  '#',  'F', '0', 'system:Shuini:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('水泥浆导出', @parentId, '5',  '#',  'F', '0', 'system:Shuini:export',       '#', 'admin', sysdate(), '', null, '');
