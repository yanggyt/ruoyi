create table enroll_user (
  id           bigint(20)      not null auto_increment    comment '主键ID',
  enroll_user_no      bigint(20)      default null               comment '报名用户编号',
  login_name        varchar(30)     not null                   comment '登录账号',
	nickname              varchar(100)     default ''             comment '昵称',
  postname             varchar(50)     default ''             comment '职务名称',
  phonenumber       varchar(11)     default ''                 comment '手机号码',
  playbill_address   varchar(100)     default ''             comment '海报地址',
  recommender_no        varchar(20)     default ''             comment '推荐人编号',
  del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (id)
) engine=innodb auto_increment=100 comment = '报名用户信息表';
