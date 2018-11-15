-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('年度计划', '3', '1', '/mengyu/yearPlan', 'C', '0', 'mengyu:yearPlan:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '年度计划菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('年度计划查询', @parentId, '1',  '#',  'F', '0', 'mengyu:yearPlan:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('年度计划新增', @parentId, '2',  '#',  'F', '0', 'mengyu:yearPlan:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('年度计划修改', @parentId, '3',  '#',  'F', '0', 'mengyu:yearPlan:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('年度计划删除', @parentId, '4',  '#',  'F', '0', 'mengyu:yearPlan:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
