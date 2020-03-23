-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('单点登录应用', '3', '1', '/sso/ssoApplication', 'C', '0', 'sso:ssoApplication:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '单点登录应用菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('单点登录应用查询', @parentId, '1',  '#',  'F', '0', 'sso:ssoApplication:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('单点登录应用新增', @parentId, '2',  '#',  'F', '0', 'sso:ssoApplication:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('单点登录应用修改', @parentId, '3',  '#',  'F', '0', 'sso:ssoApplication:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('单点登录应用删除', @parentId, '4',  '#',  'F', '0', 'sso:ssoApplication:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('单点登录应用导出', @parentId, '5',  '#',  'F', '0', 'sso:ssoApplication:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
