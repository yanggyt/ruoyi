-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('产品分类', '2000', '1', '/business/productType', 'C', '0', 'business:productType:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '产品分类菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('产品分类查询', @parentId, '1',  '#',  'F', '0', 'business:productType:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('产品分类新增', @parentId, '2',  '#',  'F', '0', 'business:productType:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('产品分类修改', @parentId, '3',  '#',  'F', '0', 'business:productType:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('产品分类删除', @parentId, '4',  '#',  'F', '0', 'business:productType:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('产品分类导出', @parentId, '5',  '#',  'F', '0', 'business:productType:export',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
