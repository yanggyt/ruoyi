-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('计划模型实例', '3', '1', '/mengyu/goodsTradeModelIns', 'C', '0', 'mengyu:goodsTradeModelIns:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '计划模型实例菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('计划模型实例查询', @parentId, '1',  '#',  'F', '0', 'mengyu:goodsTradeModelIns:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('计划模型实例新增', @parentId, '2',  '#',  'F', '0', 'mengyu:goodsTradeModelIns:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('计划模型实例修改', @parentId, '3',  '#',  'F', '0', 'mengyu:goodsTradeModelIns:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('计划模型实例删除', @parentId, '4',  '#',  'F', '0', 'mengyu:goodsTradeModelIns:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
