-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('品种计划模型', '3', '1', '/mengyu/goodsTradeModel', 'C', '0', 'mengyu:goodsTradeModel:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '品种计划模型菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('品种计划模型查询', @parentId, '1',  '#',  'F', '0', 'mengyu:goodsTradeModel:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('品种计划模型新增', @parentId, '2',  '#',  'F', '0', 'mengyu:goodsTradeModel:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('品种计划模型修改', @parentId, '3',  '#',  'F', '0', 'mengyu:goodsTradeModel:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('品种计划模型删除', @parentId, '4',  '#',  'F', '0', 'mengyu:goodsTradeModel:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
