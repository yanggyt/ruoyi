-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('液体数据', '0', '1', '/system/liquid', 'C', '0', 'system:liquid:view', '#', 'admin', sysdate(), '', null, '液体数据菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('液体数据查询', @parentId, '1',  '#',  'F', '0', 'system:liquid:list',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('液体数据新增', @parentId, '2',  '#',  'F', '0', 'system:liquid:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('液体数据修改', @parentId, '3',  '#',  'F', '0', 'system:liquid:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('液体数据删除', @parentId, '4',  '#',  'F', '0', 'system:liquid:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('液体数据导出', @parentId, '5',  '#',  'F', '0', 'system:liquid:export',       '#', 'admin', sysdate(), '', null, '');
