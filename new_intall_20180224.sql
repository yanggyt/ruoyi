-- ----------------------------
-- 1、创建部门表
-- ----------------------------
drop table if exists sys_dept;
create table sys_dept (
  dept_id 			int(11) 		not null auto_increment comment '部门ID',
  parent_id 		int(11) 		default 0 			    comment '父部门ID',
  dept_name 		varchar(30) 	default '' 				comment '部门名称',
  order_num 		int(4) 			default null 			comment '显示顺序',
  status 			int(1) 			default 0 				comment '部门状态:0正常,1停用',
  primary key (dept_id)
) engine=innodb auto_increment=100 default charset=utf8;

-- ----------------------------
-- 初始化-部门表数据
-- ----------------------------
insert into sys_dept values('1', '0', '研发部', '1', '0');
insert into sys_dept values('2', '0', '测试部', '2', '0');
insert into sys_dept values('3', '0', '市场部', '3', '0');

insert into sys_dept values('4',  '1', '研发一部', '1', '0');
insert into sys_dept values('5',  '1', '研发二部', '2', '0');
insert into sys_dept values('6',  '1', '研发三部', '3', '0');
insert into sys_dept values('7',  '2', '测试一部', '1', '0');
insert into sys_dept values('8',  '2', '测试二部', '2', '0');
insert into sys_dept values('9',  '3', '市场一部', '1', '0');
insert into sys_dept values('10', '3', '市场二部', '2', '0');


-- ----------------------------
-- 2、创建用户信息表
-- ----------------------------
drop table if exists sys_user;
create table sys_user (
  user_id 			int(11) 		not null auto_increment comment '用户ID',
  dept_id 			int(20) 		default null			comment '部门ID',
  login_name 		varchar(30) 	not null 				comment '登录名',
  user_name 		varchar(30) 	default '' 				comment '用户名称',
  email  			varchar(100) 	default '' 				comment '用户邮箱',
  phonenumber  		varchar(20) 	default '' 				comment '手机号码',
  password 			varchar(100) 	not null 				comment '密码',
  salt 				varchar(100) 	default '' 				comment '盐加密',
  status 			int(1) 			default 0 				comment '帐号状态:0正常,1锁定,2黑名单,3禁止',
  refuse_des 		varchar(500) 	default '' 				comment '拒绝登录描述',
  create_time 		varchar(30) 	default null			comment '创建时间',
  primary key (user_id)
) engine=innodb auto_increment=100 default charset=utf8;

-- ----------------------------
-- 初始化-用户信息表数据
-- ----------------------------
insert into sys_user values('1', '1', 'admin', '阳宗专', 'yzz_ivy@163.com', '15017911213', '172eee54aa664e9dd0536b063796e54e', '', 0, '维护中', '2018-01-01');
insert into sys_user values('2', '1', 'ry',    '阳若依', 'ry@163.com',      '15220051213', '2f59d63eddd54f3977d6fe25aec8b2bc', '', 1, '锁定中', '2018-01-01');



-- ----------------------------
-- 3、创建角色信息表
-- ----------------------------
drop table if exists sys_role;
create table sys_role (
  role_id 			int(10) 		not null auto_increment comment '角色ID',
  role_name 		varchar(30) 	not null 				comment '角色名',
  status 			int(1) 			default 0 				comment '角色状态:0正常,1禁用',
  create_time 		varchar(30) 	default null			comment '创建时间',
  update_time 		varchar(30) 	default null			comment '更新时间',
  update_by 		varchar(30) 	default null			comment '更新者',
  remark 			varchar(500) 	default '' 				comment '备注',
  primary key (role_id)
) engine=innodb auto_increment=100 default charset=utf8;

-- ----------------------------
-- 初始化-角色信息表数据
-- ----------------------------
insert into sys_role values('1', '管理员', 0, '2018-01-01', '', 'system', '管理员');
insert into sys_role values('2', '普通角色', 0, '2018-01-01', '', 'system', '普通角色');



-- ----------------------------
-- 4、创建菜单权限表
-- ----------------------------
drop table if exists sys_menu;
create table sys_menu (
  menu_id 			int(11) 		not null auto_increment comment '菜单ID',
  menu_name 		varchar(50) 	not null 				comment '菜单名称',
  parent_id 		int(11) 		default 0 			    comment '父菜单ID',
  order_num 		int(4) 			default null 			comment '显示顺序',
  url 				varchar(200) 	default ''				comment '菜单URL',
  menu_type 		char(1) 		default '' 			    comment '类型:M目录,C菜单,F按钮',
  visible 			int(1) 			default 0 				comment '菜单状态:0显示,1隐藏',
  perms 			varchar(100) 	default '' 				comment '权限字符串',
  icon 				varchar(100) 	default '' 				comment '菜单图标',
  create_time 		varchar(30) 	default null			comment '创建时间',
  update_time 		varchar(30) 	default null			comment '更新时间',
  update_by 		varchar(30) 	default null			comment '更新者',
  remark 			varchar(500) 	default '' 				comment '备注',
  primary key (menu_id)
) engine=innodb auto_increment=1000 default charset=utf8;

-- ----------------------------
-- 初始化-菜单信息表数据
-- ----------------------------
-- 一级菜单
insert into sys_menu values('1', '系统管理', '0', '1', '#', 'M', '0', 'system', 'fa fa-gear', '2018-01-01', '', 'system', '系统管理目录');
insert into sys_menu values('2', '系统监控', '0', '2', '#', 'M', '0', 'monitor', 'fa fa-video-camera', '2018-01-01', '', 'system', '系统监控目录');
-- 二级菜单
insert into sys_menu values('3', '用户管理', '1', '1', '/system/user/userList',    'C', '0', 'system:user:list',    '#', '2018-01-01', '', 'system', '用户管理菜单');
insert into sys_menu values('4', '角色管理', '1', '2', '/system/role/roleList',    'C', '0', 'system:role:list',    '#', '2018-01-01', '', 'system', '角色管理菜单');
insert into sys_menu values('5', '菜单管理', '1', '3', '/system/menu/menuList',    'C', '0', 'system:menu:list',    '#', '2018-01-01', '', 'system', '菜单管理菜单');
insert into sys_menu values('6', '操作日志', '1', '4', '/system/operlog/operlogList', 'C', '0', 'system:operlog:list', '#', '2018-01-01', '', 'system', '操作日志菜单');
insert into sys_menu values('7', '登录日志', '1', '5', '/system/userlog/userlogList', 'C', '0', 'system:userlog:list', '#', '2018-01-01', '', 'system', '登录日志菜单');
insert into sys_menu values('8', '数据监控', '2', '1', '/monitor/druid/index.html',  'C', '0', 'monitor:druid:list',  '#', '2018-01-01', '', 'system', '数据监控菜单');
--- 三级用户按钮
insert into sys_menu values('9',  '用户新增', '3', '1', '/system/user/add',       'F', '0', 'sys:user:add',       '#', '2018-01-01', '', 'system', '用户管理新增按钮');
insert into sys_menu values('10', '用户修改', '3', '2', '/system/user/update',    'F', '0', 'sys:user:update',    '#', '2018-01-01', '', 'system', '用户管理修改按钮');
insert into sys_menu values('11', '用户删除', '3', '3', '/system/user/delete',    'F', '0', 'sys:user:delete',    '#', '2018-01-01', '', 'system', '用户管理删除按钮');
insert into sys_menu values('12', '用户查询', '3', '4', '/system/user/select',    'F', '0', 'sys:user:select',    '#', '2018-01-01', '', 'system', '用户管理查询按钮');
insert into sys_menu values('13', '密码修改', '3', '5', '/system/user/pwdUpdate', 'F', '0', 'sys:user:pwdUpdate', '#', '2018-01-01', '', 'system', '用户密码修改按钮');
--- 三级角色按钮
insert into sys_menu values('14', '角色新增', '4', '1', '/system/role/add',    'F', '0', 'sys:role:add',    '#', '2018-01-01', '', 'system', '角色管理新增按钮');
insert into sys_menu values('15', '角色修改', '4', '2', '/system/role/update', 'F', '0', 'sys:role:update', '#', '2018-01-01', '', 'system', '角色管理修改按钮');
insert into sys_menu values('16', '角色删除', '4', '3', '/system/role/delete', 'F', '0', 'sys:role:delete', '#', '2018-01-01', '', 'system', '角色管理删除按钮');
insert into sys_menu values('17', '角色查询', '4', '4', '/system/role/select', 'F', '0', 'sys:role:select', '#', '2018-01-01', '', 'system', '角色管理查询按钮');
insert into sys_menu values('18', '角色授权', '4', '5', '/system/role/auth',   'F', '0', 'sys:role:auth',   '#', '2018-01-01', '', 'system', '角色管理授权按钮');
--- 三级菜单按钮
insert into sys_menu values('19', '菜单新增', '5', '1', '/system/menu/add',    'F', '0', 'sys:menu:add',    '#', '2018-01-01', '', 'system', '菜单管理新增按钮');
insert into sys_menu values('20', '菜单修改', '5', '2', '/system/menu/update', 'F', '0', 'sys:menu:update', '#', '2018-01-01', '', 'system', '菜单管理修改按钮');
insert into sys_menu values('21', '菜单删除', '5', '3', '/system/menu/delete', 'F', '0', 'sys:menu:delete', '#', '2018-01-01', '', 'system', '菜单管理删除按钮');
insert into sys_menu values('22', '菜单查询', '5', '4', '/system/menu/select', 'F', '0', 'sys:menu:select', '#', '2018-01-01', '', 'system', '菜单管理查询按钮');
--- 三级日志按钮
insert into sys_menu values('23', '操作日志查询', '4', '5', '/system/operlog/auth', 'F', '0', 'sys:operlog:select', '#', '2018-01-01', '', 'system', '操作日志查询按钮');
insert into sys_menu values('24', '登录日志查询', '4', '5', '/system/userlog/auth', 'F', '0', 'sys:userlog:select', '#', '2018-01-01', '', 'system', '登录日志查询按钮');


-- ----------------------------
-- 5、创建用户和角色关联表  用户N-1角色
-- ----------------------------
drop table if exists sys_user_role;
create table sys_user_role (
  user_id 	int(11) not null comment '用户ID',
  role_id 	int(11) not null comment '角色ID',
  primary key(user_id, role_id)
) engine=innodb default charset=utf8;

-- ----------------------------
-- 初始化-用户和角色关联表数据
-- ----------------------------
insert into sys_user_role values ('1', '1');
insert into sys_user_role values ('2', '2');



-- ----------------------------
-- 6、创建角色和菜单关联表  角色1-N菜单
-- ----------------------------
drop table if exists sys_role_menu;
create table sys_role_menu (
  role_id 	int(11) not null comment '角色ID',
  menu_id 	int(11) not null comment '菜单ID',
  primary key(role_id, menu_id)
) engine=innodb default charset=utf8;

-- ----------------------------
-- 初始化-角色和菜单关联表数据
-- ----------------------------
insert into sys_role_menu values ('1', '1');
insert into sys_role_menu values ('1', '2');
insert into sys_role_menu values ('1', '3');
insert into sys_role_menu values ('1', '4');
insert into sys_role_menu values ('1', '5');
insert into sys_role_menu values ('1', '6');
insert into sys_role_menu values ('1', '7');
insert into sys_role_menu values ('1', '8');


-- ----------------------------
-- 7、创建操作日志管理表
-- ----------------------------
drop table if exists oper_log;
create table oper_log (
  operid 			int(11) 		not null auto_increment comment '日志主键',
  opername 			varchar(50) 	default '' 				comment '操作员名称',  
  opertime 			varchar(30) 	default null			comment '操作时间',
  opertype 			varchar(50) 	default ''				comment '操作类型',
  opertparam 		varchar(255) 	default '' 				comment '操作参数',
  opertip 			varchar(30) 	default '' 				comment '执行地址',
  operturl 			varchar(255) 	default '' 				comment '请求URL',
  status 			varchar(3) 		default '' 				comment '状态0正常 1错误',
  message 			varchar(255) 	default '' 				comment '错误消息',
  primary key (operid)
) engine=innodb auto_increment=100 default charset=utf8;

insert into oper_log values(1, 'admin', '2018-01-01', '系统管理-启用/停用-用户', 'delete.do?id=1', '127.0.0.1', 'system/changeUserStatus', '0', '');

-- ----------------------------
-- 8、创建数据字典表
-- ----------------------------
drop table if exists sys_code;
create table sys_code (
   codekey      	varchar(50)  not null 		comment '标识',
   codeid           varchar(50)  not null 		comment '键',
   codevalue        varchar(50)  not null 		comment '值',
   codedesc         varchar(50)  default '' 	comment '描述',
   codestyle        varchar(255) default '' 	comment '样式',
   codeorder        varchar(2)   default '' 	comment '排序',
   primary key (codeid, codekey)
) engine=innodb default charset=utf8;

-- ----------------------------
-- 初始化用户状态选项列表
-- ----------------------------
insert into sys_code values('system-user-status', '0', '正常',   '', '', '1');
insert into sys_code values('system-user-status', '1', '锁定',   '', '', '2');
insert into sys_code values('system-user-status', '2', '黑名单', '', '', '3');
insert into sys_code values('system-user-status', '3', '禁止',   '', '', '4');

-- ----------------------------
-- 初始化菜单选项列表
-- ----------------------------
insert into sys_code values('system-menu-status', '0', '显示', '', '', '1');
insert into sys_code values('system-menu-status', '1', '隐藏', '', '', '2');

-- ----------------------------
-- 初始化登录日志状态选项列表
-- ----------------------------
insert into sys_code values('system-operlog-status', '0', '成功', '', '', '1');
insert into sys_code values('system-operlog-status', '1', '失败', '', '', '2');



-- ----------------------------
-- 9、系统访问日志情况信息
-- ----------------------------
drop table if exists sys_logininfor;
create table sys_logininfor (
  info_id 		int(11) 	 not null auto_increment comment '访问ID',
  login_name 	varchar(50)  default '' 			 comment '登录名',
  status 		int(1) 		 default 0 			 	 comment '登录状态 0成功 1失败',
  ipaddr 		varchar(50)  default '' 			 comment '登录IP地址',
  browser  		varchar(50)  default '' 			 comment '浏览器类型',
  os      		varchar(50)  default '' 			 comment '操作系统',
  msg      		varchar(255) default '' 			 comment '提示消息',
  logondate 	timestamp    default current_timestamp comment '访问时间',
  primary key (info_id)
) engine=innodb auto_increment=100 default charset=utf8;

insert into sys_logininfor values(1, 'admin', 0 , '127.0.0.1', 'Chrome 45', 'Windows 7', '登录成功' ,'2018-01-01');

-- ----------------------------
-- 10、在线用户
-- ----------------------------
drop table if exists sys_user_online;
create table sys_user_online (
  sessionId 	    varchar(100) default ''              	comment '用户会话id',
  user_id 		    int(11)      default 0                  comment '用户ID',
  login_name 	    varchar(50)  default '' 		 	 	comment '登录名',
  ipaddr 		    varchar(50)  default '' 			 	comment '登录IP地址',
  browser  		    varchar(50)  default '' 			 	comment '浏览器类型',
  os      		    varchar(50)  default '' 			 	comment '操作系统',
  status      	    varchar(10)  default '' 			 	comment '在线状态',
  start_timestsamp 	timestamp    default current_timestamp  comment 'session创建时间',
  last_access_time  timestamp    default current_timestamp  comment 'session最后访问时间',
  timeout 	        int(5) 		 default 0 			 	 	comment '超时时间',
  onlineSession     varchar(50)  default '' 			 	comment '备份的当前用户会话',
  primary key (sessionId)
) engine=innodb default charset=utf8;

insert into sys_user_online(sessionId, user_id, login_name, ipaddr, browser, os, status) 
values('c3b252c3-2229-4be4-a5f7-7aba4b0c314c', 1, 'admin', '127.0.0.1', 'Chrome 45', 'Windows 7', 'on_line');

-- 用户部门表
SELECT * FROM sys_dept;

-- 用户信息表
SELECT * FROM sys_user;

-- 角色信息表
SELECT * FROM sys_role;

-- 菜单信息表
SELECT * FROM sys_menu;

-- 创建用户和角色关联表  用户N-1角色
SELECT * FROM sys_user_role;

-- 创建角色和菜单关联表  角色1-N菜单
SELECT * FROM sys_role_menu;

-- 创建操作日志管理表
SELECT * FROM oper_log;

-- 创建数据字典表
SELECT * FROM sys_code;

-- 系统访问日志情况信息
SELECT * FROM sys_logininfor;

-- 在线用户信息
SELECT * FROM sys_user_online;
