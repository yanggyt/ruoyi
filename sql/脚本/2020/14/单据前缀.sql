-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('单据号迭代信息', '3', '1', '/config/no', 'C', '0', 'config:no:view', '#', 'admin', sysdate(), '', null, '单据号迭代信息菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('单据号迭代信息查询', @parentId, '1',  '#',  'F', '0', 'config:no:list',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('单据号迭代信息新增', @parentId, '2',  '#',  'F', '0', 'config:no:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('单据号迭代信息修改', @parentId, '3',  '#',  'F', '0', 'config:no:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('单据号迭代信息删除', @parentId, '4',  '#',  'F', '0', 'config:no:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('单据号迭代信息导出', @parentId, '5',  '#',  'F', '0', 'config:no:export',       '#', 'admin', sysdate(), '', null, '');
