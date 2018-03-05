-- ----------------------------
-- 1、部门表
-- ----------------------------
drop table if exists sys_dept;
create table sys_dept (
  dept_id 			int(11) 		not null auto_increment    comment '部门ID',
  parent_id 		int(11) 		default 0 			       comment '父部门ID',
  dept_name 		varchar(30) 	default '' 				   comment '部门名称',
  order_num 		int(4) 			default null 			   comment '显示顺序',
  status 			int(1) 			default 0 				   comment '部门状态:0正常,1停用',
  create_time 	    timestamp       default current_timestamp  comment '创建时间',
  primary key (dept_id)
) engine=innodb auto_increment=100 default charset=utf8;

-- ----------------------------
-- 初始化-部门表数据
-- ----------------------------
insert into sys_dept values(1,  0, '若依集团', 1, 0, '2018-03-01');
insert into sys_dept values(2,  1, '研发部门', 2, 0, '2018-03-01');
insert into sys_dept values(3,  1, '市场部门', 4, 0, '2018-03-01');
insert into sys_dept values(4,  1, '测试部门', 3, 0, '2018-03-01');
insert into sys_dept values(5,  1, '财务部门', 5, 1, '2018-03-01');
insert into sys_dept values(6,  1, '运维部门', 6, 1, '2018-03-01');
insert into sys_dept values(7,  2, '研发一部', 1, 0, '2018-03-01');
insert into sys_dept values(8,  2, '研发二部', 2, 1, '2018-03-01');
insert into sys_dept values(9,  3, '市场一部', 1, 0, '2018-03-01');
insert into sys_dept values(10, 3, '市场二部', 2, 1, '2018-03-01');

-- ----------------------------
-- 2、用户信息表
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
insert into sys_user values('1', '4', 'admin', '若依', 'yzz_ivy@163.com', '15088888888', '172eee54aa664e9dd0536b063796e54e', '', 0, '维护中', '2018-03-01');
insert into sys_user values('2', '4', 'ry',    '若依', 'ry@163.com',      '15288888888', '2f59d63eddd54f3977d6fe25aec8b2bc', '', 1, '锁定中', '2018-03-01');



-- ----------------------------
-- 3、角色信息表
-- ----------------------------
drop table if exists sys_role;
create table sys_role (
  role_id 			int(10) 		not null auto_increment comment '角色ID',
  role_name 		varchar(30) 	not null 				comment '角色名',
  role_key 		    varchar(100) 	not null 				comment '角色权限字符串',
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
insert into sys_role values('1', '管理员',   'admin',  0, '2018-03-01', '', 'system', '管理员');
insert into sys_role values('2', '普通角色', 'common', 0, '2018-03-01', '', 'system', '普通角色');



-- ----------------------------
-- 4、菜单权限表
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
  perms 			varchar(100) 	default '' 				comment '菜单权限字符串',
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
insert into sys_menu values('1', '系统管理', '0', '1', '#', 'M', '0', '#', 'fa fa-gear',         '2018-03-01', '', 'admin', '系统管理目录');
insert into sys_menu values('2', '系统监控', '0', '2', '#', 'M', '0', '#', 'fa fa-video-camera', '2018-03-01', '', 'admin', '系统监控目录');
-- 二级菜单
insert into sys_menu values('3',  '用户管理', '1', '1', '/system/user',        'C', '0', 'system:user:view',         '#', '2018-03-01', '', 'admin', '用户管理菜单');
insert into sys_menu values('4',  '角色管理', '1', '2', '/system/role',        'C', '0', 'system:role:view',         '#', '2018-03-01', '', 'admin', '角色管理菜单');
insert into sys_menu values('5',  '菜单管理', '1', '3', '/system/menu',        'C', '0', 'system:menu:view',         '#', '2018-03-01', '', 'admin', '菜单管理菜单');
insert into sys_menu values('6',  '部门管理', '1', '3', '/system/dept',        'C', '0', 'system:dept:view',         '#', '2018-03-01', '', 'admin', '部门管理菜单');
insert into sys_menu values('7',  '操作日志', '2', '1', '/monitor/operlog',    'C', '0', 'monitor:operlog:view',     '#', '2018-03-01', '', 'admin', '操作日志菜单');
insert into sys_menu values('8',  '登录日志', '2', '2', '/monitor/logininfor', 'C', '0', 'monitor:logininfor:view',  '#', '2018-03-01', '', 'admin', '登录日志菜单');
insert into sys_menu values('9',  '在线用户', '2', '3', '/monitor/online',     'C', '0', 'monitor:online:view',      '#', '2018-03-01', '', 'admin', '在线用户菜单');
insert into sys_menu values('10', '数据监控', '2', '4', '/monitor/data',       'C', '0', 'monitor:data:view',        '#', '2018-03-01', '', 'admin', '数据监控菜单');
-- 部门管理按钮
insert into sys_menu values('11', '部门新增', '6', '1', '/system/dept/add',    'F', '0', 'system:dept:add',          '#', '2018-03-01', '', 'admin', '');
insert into sys_menu values('12', '部门修改', '6', '2', '/system/dept/edit',   'F', '0', 'system:dept:edit',         '#', '2018-03-01', '', 'admin', '');
insert into sys_menu values('13', '部门删除', '6', '3', '/system/dept/remove', 'F', '0', 'system:dept:remove',       '#', '2018-03-01', '', 'admin', '');
insert into sys_menu values('14', '部门保存', '6', '4', '/system/dept/save',   'F', '0', 'system:dept:save',         '#', '2018-03-01', '', 'admin', '');
-- 操作日志按钮
insert into sys_menu values('15', '批量删除', '7', '1', '/monitor/operlog/batchRemove',     'F', '0', 'monitor:operlog:batchRemove',     '#', '2018-03-01', '', 'admin', '');
insert into sys_menu values('16', '详细信息', '7', '2', '/monitor/operlog/detail',          'F', '0', 'monitor:operlog:detail',          '#', '2018-03-01', '', 'admin', '');
-- 登录日志按钮
insert into sys_menu values('17', '批量删除', '8', '1', '/monitor/logininfor/batchRemove',  'F', '0', 'monitor:logininfor:batchRemove',  '#', '2018-03-01', '', 'admin', '');
-- 在线用户按钮
insert into sys_menu values('18', '批量强退', '9', '1', '/monitor/online/batchForceLogout', 'F', '0', 'monitor:online:batchForceLogout', '#', '2018-03-01', '', 'admin', '');
insert into sys_menu values('19', '单条强退', '9', '2', '/monitor/online/forceLogout',      'F', '0', 'monitor:online:forceLogout',      '#', '2018-03-01', '', 'admin', '');

-- ----------------------------
-- 5、用户和角色关联表  用户N-1角色
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
-- 6、角色和菜单关联表  角色1-N菜单
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
insert into sys_role_menu values ('1', '9');
insert into sys_role_menu values ('1', '10');
insert into sys_role_menu values ('1', '11');
insert into sys_role_menu values ('1', '12');
insert into sys_role_menu values ('1', '13');
insert into sys_role_menu values ('1', '14');
insert into sys_role_menu values ('1', '15');
insert into sys_role_menu values ('1', '16');
insert into sys_role_menu values ('1', '17');
insert into sys_role_menu values ('1', '18');
insert into sys_role_menu values ('1', '19');
-- ----------------------------
-- 7、操作日志记录
-- ----------------------------
drop table if exists sys_oper_log;
create table sys_oper_log (
  oper_id 			int(11) 		not null auto_increment    comment '日志主键',
  title             varchar(50)     default ''                 comment '模块标题',
  action            varchar(100)    default ''                 comment '功能请求',
  method            varchar(100)    default ''                 comment '方法名称',
  channel           varchar(20)     default ''                 comment '来源渠道',
  login_name 	    varchar(50)     default '' 		 	 	   comment '登录名称',
  dept_name 		varchar(50)     default '' 		 	 	   comment '部门名称',
  oper_url 		    varchar(255) 	default '' 				   comment '请求URL',
  oper_ip 			varchar(30) 	default '' 				   comment '主机地址',
  oper_param 		varchar(255) 	default '' 				   comment '请求参数',
  status 			int(1) 		    default 0				   comment '操作状态 0正常 1异常',
  error_msg 		varchar(255) 	default '' 				   comment '错误消息',
  oper_time 		timestamp       default current_timestamp  comment '操作时间',
  primary key (oper_id)
) engine=innodb auto_increment=100 default charset=utf8;

insert into sys_oper_log values(1, '监控管理', '在线用户-强退用户', 'com.ruoyi.project.monitor.online.controller.UserOnlineController()', 'web', 'admin', '研发部门', 'delete.do?id=1', '127.0.0.1', 'JSON参数', 0, '错误描述', '2018-03-01');

-- ----------------------------
-- 8、数据字典表
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
-- 9、系统访问记录
-- ----------------------------
drop table if exists sys_logininfor;
create table sys_logininfor (
  info_id 		int(11) 	 not null auto_increment   comment '访问ID',
  login_name 	varchar(50)  default '' 			   comment '登录名',
  ipaddr 		varchar(50)  default '' 			   comment '登录IP地址',
  browser  		varchar(50)  default '' 			   comment '浏览器类型',
  os      		varchar(50)  default '' 			   comment '操作系统',
  status 		int(1) 		 default 0 			 	   comment '登录状态 0成功 1失败',
  msg      		varchar(255) default '' 			   comment '提示消息',
  login_time 	timestamp    default current_timestamp comment '访问时间',
  primary key (info_id)
) engine=innodb auto_increment=100 default charset=utf8;

insert into sys_logininfor values(1, 'admin', '127.0.0.1', 'Chrome 45', 'Windows 7', 0, '登录成功' ,'2018-03-01');

-- ----------------------------
-- 10、在线用户记录
-- ----------------------------
drop table if exists sys_user_online;
create table sys_user_online (
  sessionId 	    varchar(50)  default ''              	comment '用户会话id',
  login_name 	    varchar(50)  default '' 		 	 	comment '登录名称',
  dept_name 		varchar(50)  default '' 		 	 	comment '部门名称',
  ipaddr 		    varchar(50)  default '' 			 	comment '登录IP地址',
  browser  		    varchar(50)  default '' 			 	comment '浏览器类型',
  os      		    varchar(50)  default '' 			 	comment '操作系统',
  status      	    varchar(10)  default '' 			 	comment '在线状态on_line在线off_line离线',
  start_timestsamp 	timestamp    default current_timestamp  comment 'session创建时间',
  last_access_time  timestamp    default current_timestamp  comment 'session最后访问时间',
  expire_time 	    int(5) 		 default 0 			 	    comment '超时时间，单位为分钟',
  primary key (sessionId)
) engine=innodb default charset=utf8;

insert into sys_user_online(sessionId, login_name, dept_name, ipaddr, browser, os, status) 
values('c3b252c3-2229-4be4-a5f7-7aba4b0c314c', 'admin', '研发部门', '127.0.0.1', 'Chrome 45', 'Windows 7', 'on_line');

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
