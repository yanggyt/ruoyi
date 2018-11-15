-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('交易计划操作', '3', '1', '/mengyu/goodsTradeModelInsNode', 'C', '0', 'mengyu:goodsTradeModelInsNode:view', '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '交易计划操作菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('交易计划操作查询', @parentId, '1',  '#',  'F', '0', 'mengyu:goodsTradeModelInsNode:list',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('交易计划操作新增', @parentId, '2',  '#',  'F', '0', 'mengyu:goodsTradeModelInsNode:add',          '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('交易计划操作修改', @parentId, '3',  '#',  'F', '0', 'mengyu:goodsTradeModelInsNode:edit',         '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');

insert into sys_menu  (menu_name, parent_id, order_num, url,menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('交易计划操作删除', @parentId, '4',  '#',  'F', '0', 'mengyu:goodsTradeModelInsNode:remove',       '#', 'admin', '2018-03-01', 'ry', '2018-03-01', '');
