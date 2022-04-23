-- 生成客户表
drop table if exists sys_client;
create table sys_client (
  client_id           bigint(20)      not null auto_increment    comment '客户ID',
  dept_id           bigint(20)      default null               comment '部门ID',
  client_key           bigint(20)      default null               comment '客户key',
  client_name         varchar(30)     default ''                 comment '客户昵称',
  client_type         varchar(2)      default '00'               comment '客户类型（1-内部用户 2-外部用户）',
  phonenumber       varchar(11)     default ''                 comment '手机号码',
  status            char(1)         default '0'                comment '帐号状态（0正常 1停用）',
  del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  login_ip          varchar(128)    default ''                 comment '最后登录IP',
  login_date        datetime                                   comment '最后登录时间',
  pwd_update_date   datetime                                   comment '密码最后更新时间',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (client_id)
) engine=innodb auto_increment=100 comment = '客户信息表';

-- 菜单 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('客户信息', '3', '1', '/system/client', 'C', '0', 'system:client:view', '#', 'admin', sysdate(), '', null, '客户信息菜单');

-- 按钮父菜单ID
SELECT @parentId := LAST_INSERT_ID();

-- 按钮 SQL
insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('客户信息查询', @parentId, '1',  '#',  'F', '0', 'system:client:list',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('客户信息新增', @parentId, '2',  '#',  'F', '0', 'system:client:add',          '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('客户信息修改', @parentId, '3',  '#',  'F', '0', 'system:client:edit',         '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('客户信息删除', @parentId, '4',  '#',  'F', '0', 'system:client:remove',       '#', 'admin', sysdate(), '', null, '');

insert into sys_menu (menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values('客户信息导出', @parentId, '5',  '#',  'F', '0', 'system:client:export',       '#', 'admin', sysdate(), '', null, '');

update sys_menu set parent_id = '1' , order_num = '10' where menu_id = '1062' and menu_name = '客户信息';