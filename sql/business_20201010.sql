------------------------------
-- 1、服务组织表
------------------------------
drop table if exists service_organization;
create table service_organization (
  id  bigint(20)  not null auto_increment  comment 'ID',
  name   varchar(120) not null  comment '企业名称',
  contacts varchar(50) not null  comment '联系人',
  phone  varchar(12)  not null  comment '联系电话',
  license_url  varchar(500)  not null  comment '营业执照图片地址（多个地址用，分隔）',
  title  varchar(50) not null  comment '标题',
  introduction  text not null comment '简介',
  content  text  not null comment '机构详情内容',
  hits  int (6)  default 0 comment '点击量',
  status  char(1)  default '0'  comment '状态（0:待审核，1：审核不通过，2：审核通过）',
  picture_url  varchar(500)  not null  comment '图片地址（多个地址用，分隔）',
  status  char(1) default '0' comment '状态（0正常 1停用）',
  del_flag  char(1)  default '0' comment '删除标志（0代表存在 2代表删除）',
  create_by  varchar(64)  default '' comment '创建者',
  create_time  datetime    comment '创建时间',
  update_by  varchar(64)  default ''  comment '更新者',
  update_time  datetime  comment '更新时间',
  check_by  varchar(64)  default ''  comment '审核者',
  check_time  datetime  comment '审核时间',
  remark  varchar(255)    default null  comment '备注',
  primary key (id)
) engine=innodb auto_increment=10 comment = '服务组织表';

-- ----------------------------
-- 2、 合同分类表
-- ----------------------------
drop table if exists contract_type;
create table contract_type (
  id  bigint(20) not null auto_increment  comment 'ID',
  name  varchar(50) not null  comment '分类名称',
  english_name varchar(50) not null comment '',
  del_flag  char(1)  default '0' comment '删除标志（0代表存在 2代表删除）',
  create_by  varchar(64)  default '' comment '创建者',
  create_time  datetime    comment '创建时间',
  update_by  varchar(64)  default ''  comment '更新者',
  update_time  datetime  comment '更新时间',
  remark  varchar(255)    default null  comment '备注',
  primary key (id)
) engine=innodb auto_increment=10 comment = '合同分类表';

------------------------------
-- 3、 合同模板表
------------------------------
drop table if exists contract_template;
create table contract_template (
  id  bigint(20) not null auto_increment  comment 'ID',
  type varchar(100)  not null comment '合同分类id(contract_type表主键id)',
  title  varchar(50) not null  comment '标题',
  introduction  text not null comment '简介',
  content  text  not null comment '详情内容',
  hits  int (6)  default 0 comment '点击量',
  enclosure_url  varchar(500)  not null  comment '附件下载（多个地址用，分隔）',
  status  char(1) default '0' comment '状态（0正常 1停用）',
  del_flag  char(1)  default '0' comment '删除标志（0代表存在 2代表删除）',
  create_by  varchar(64)  default '' comment '创建者',
  create_time  datetime    comment '创建时间',
  update_by  varchar(64)  default ''  comment '更新者',
  update_time  datetime  comment '更新时间',
  remark  varchar(255)    default null  comment '备注',
  primary key (id)
) engine=innodb auto_increment=10 comment = '合同模板表';


-- ----------------------------
-- 4、 典型案例表
-- ----------------------------
drop table if exists classsic_cases;
create table classsic_cases (
  id  bigint(20) not null auto_increment  comment 'ID',
  title  varchar(50) not null  comment '标题',
  introduction  text not null comment '简介',
  content  text  not null comment '详情内容',
  type varchar(100)  not null comment '案例类型(来至于字典表)',
  hits  int (6)  default 0 comment '点击量',
  picture_url  varchar(500)  not null  comment '图片地址（多个地址用，分隔）',
  status  char(1) default '0' comment '状态（0正常 1停用）',
  del_flag  char(1)  default '0' comment '删除标志（0代表存在 2代表删除）',
  create_by  varchar(64)  default '' comment '创建者',
  create_time  datetime    comment '创建时间',
  update_by  varchar(64)  default ''  comment '更新者',
  update_time  datetime  comment '更新时间',
  primary key (id)
) engine=innodb auto_increment=10 comment = '典型案例表';


------------------------------
-- 5、 线上课程代表
------------------------------
drop table if exists online_courses;
create table online_courses (
  id  bigint(20) not null auto_increment  comment 'ID',
  title  varchar(50) not null  comment '标题',
  introduction  text not null comment '简介',
  hits  int (6)  default 0 comment '点击量',
  target_people varchar(64)  not null comment '针对人群',
  courses_duration varchar(12)  not null comment '视频课程时长',
  courses_level int (1)  not null comment '课程难度等级',
  picture_url  varchar(500)  not null  comment '图片地址（多个地址用，分隔）',
  video_url  varchar(500)  not null  comment '视频地址（多个地址用，分隔）',
  status  char(1) default '0' comment '状态（0正常 1停用）',
  del_flag  char(1)  default '0' comment '删除标志（0代表存在 2代表删除）',
  create_by  varchar(64)  default '' comment '创建者',
  create_time  datetime    comment '创建时间',
  update_by  varchar(64)  default ''  comment '更新者',
  update_time  datetime  comment '更新时间',
  primary key (id)
) engine=innodb auto_increment=10 comment = '线上课程表';


-- ----------------------------
-- 6、 线上课程评价表
-- ----------------------------
drop table if exists online_courses_evaluate;
create table online_courses_evaluate (
  id  bigint(20) not null auto_increment  comment 'ID',
  online_courses_id  bigint(20) not null  comment '线上课程ID',
  evaluate_content  varchar(1500) not null comment '评价内容',
  anonymous_flag  char(1)  not null  comment '匿名标志（0匿名 1用户）',
  del_flag   char(1)   default '0' comment '删除标志（0代表存在 2代表删除）',
  create_by  varchar(64)  default '' comment '创建者',
  create_time  datetime    comment '创建时间',
  update_by  varchar(64)  default ''  comment '更新者',
  update_time  datetime  comment '更新时间',
  primary key (id)
) engine=innodb auto_increment=10 comment = '线上课程评价表';

-- ----------------------------
-- 7、 新闻动态表
-- ----------------------------
drop table if exists news_information;
create table news_information (
  id  bigint(20) not null auto_increment  comment 'ID',
  title  varchar(50) not null  comment '标题',
  introduction  text not null comment '简介',
  content  text  not null comment '详情内容',
  hits  int (6)  default 0 comment '点击量',
  status  char(1) default '0' comment '状态（0正常 1停用）',
  del_flag  char(1)  default '0' comment '删除标志（0代表存在 2代表删除）',
  create_by  varchar(64)  default '' comment '创建者',
  create_time  datetime    comment '创建时间',
  update_by  varchar(64)  default ''  comment '更新者',
  update_time  datetime  comment '更新时间',
  primary key (id)
) engine=innodb auto_increment=10 comment = '新闻动态表';


-- ----------------------------
-- 8、在线留言表
-- ----------------------------
drop table if exists online_message;
create table online_message (
  id  bigint(20) not null auto_increment  comment 'ID',
  name  varchar(50) not null  comment '姓名',
  email  varchar(32) not null comment '邮箱',
  contact_information  varchar(32) not null comment '联系方式',
  message_content  varchar(1200) not null comment '留言内容',
  del_flag  char(1)  default '0' comment '删除标志（0代表存在 2代表删除）',
  create_by  varchar(64)  default '' comment '创建者',
  create_time  datetime    comment '创建时间',
  update_by  varchar(64)  default ''  comment '更新者',
  update_time  datetime  comment '更新时间',
  primary key (id)
) engine=innodb auto_increment=10 comment = '在线留言表';

-- ----------------------------
-- 9、联系方式表
-- ----------------------------
drop table if exists contact_information;
create table contact_information (
  id  bigint(20) not null auto_increment  comment 'ID',
  address  varchar(150) not null  comment '机构地址',
  service_phone  varchar(32) not null comment '服务电话',
  supervise_phone  varchar(32) not null comment '监督电话',
  supervise_dept  varchar(32) not null comment '监督部门',
  email  varchar(32) not null comment '邮箱',
  service_date  varchar(64) not null comment '服务时间',
  copyright varchar(64) not null comment '版权所有',
  del_flag  char(1)  default '0' comment '删除标志（0代表存在 2代表删除）',
  create_by  varchar(64)  default '' comment '创建者',
  create_time  datetime    comment '创建时间',
  update_by  varchar(64)  default ''  comment '更新者',
  update_time  datetime  comment '更新时间',
  primary key (id)
) engine=innodb auto_increment=10 comment = '联系方式表';


-- ----------------------------
-- 10、法律服务表
-- ----------------------------
drop table if exists legal_services;
create table legal_services (
  id  bigint(20) not null auto_increment  comment 'ID',
  name varchar(12) not null  comment '服务名称',
  address  varchar(120) not null comment '地址',
  del_flag  char(1)  default '0' comment '删除标志（0代表存在 2代表删除）',
  create_by  varchar(64)  default '' comment '创建者',
  create_time  datetime    comment '创建时间',
  update_by  varchar(64)  default ''  comment '更新者',
  update_time  datetime  comment '更新时间',
  primary key (id)
) engine=innodb auto_increment=10 comment = '法律服务表';

-- ----------------------------
-- 11、 常见问题表
-- ----------------------------
drop table if exists common_problem;
create table common_problem (
  id  bigint(20) not null auto_increment  comment 'ID',
  title  varchar(50) not null  comment '标题',
  content  text  not null comment '内容',
  hits  int (6)  default 0 comment '点击量',
  del_flag  char(1)  default '0' comment '删除标志（0代表存在 2代表删除）',
  create_by  varchar(64)  default '' comment '创建者',
  create_time  datetime    comment '创建时间',
  update_by  varchar(64)  default ''  comment '更新者',
  update_time  datetime  comment '更新时间',
  primary key (id)
) engine=innodb auto_increment=10 comment = '常见问题表';


-- ----------------------------
-- 12、 活动招募表
-- ----------------------------
drop table if exists event_recruitment;
create table event_recruitment (
  id  bigint(20) not null auto_increment  comment 'ID',
  title  varchar(50) not null  comment '标题',
  introduction  text  not null comment '简介',
  activity_time  varchar(32)  comment '活动时间',
  address  varchar(120) not null comment '地址',
  organizer  varchar(120) not null comment '主办单位',
  picture_url  varchar(120)  not null  comment '图片地址',
  del_flag  char(1)  default '0' comment '删除标志（0代表存在 2代表删除）',
  create_by  varchar(64)  default '' comment '创建者',
  create_time  datetime    comment '创建时间',
  update_by  varchar(64)  default ''  comment '更新者',
  update_time  datetime  comment '更新时间',
  primary key (id)
) engine=innodb auto_increment=10 comment = '活动招募表';

-- ----------------------------
-- 13、首页轮播图表
-- ----------------------------
drop table if exists home_page_carousel;
create table home_page_carousel (
  id  bigint(20) not null auto_increment  comment 'ID',
  picture_url  varchar(120) not null comment '图片地址',
  del_flag  char(1)  default '0' comment '删除标志（0代表存在 2代表删除）',
  create_by  varchar(64)  default '' comment '创建者',
  create_time  datetime    comment '创建时间',
  update_by  varchar(64)  default ''  comment '更新者',
  update_time  datetime  comment '更新时间',
  primary key (id)
) engine=innodb auto_increment=10 comment = '首页轮播图表';