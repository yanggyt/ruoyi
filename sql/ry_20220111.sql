
create sequence seq_SYS_APP
    increment by 1
    start with 100
    nomaxvalue
 nominvalue
 cache 20;
-- Create table
create table SYS_APP
(
    app_id      NUMBER(20) not null,
    app_code    VARCHAR2(64) not null,
    category_id NUMBER(20),
    app_name    VARCHAR2(50) not null,
    status      CHAR(1) default 0,
    create_by   VARCHAR2(64) default '',
    create_time DATE,
    update_by   VARCHAR2(64) default '',
    update_time DATE,
    remark      VARCHAR2(500)
)
    tablespace ERM_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table
comment on table SYS_APP
  is '编码申请表';
-- Add comments to the columns
comment on column SYS_APP.app_id
  is '编码申请单主键seq_sys_app.nextval';
comment on column SYS_APP.app_code
  is '申请编码';
comment on column SYS_APP.category_id
  is '编码体系ID';
comment on column SYS_APP.app_name
  is '申请名称';
comment on column SYS_APP.status
  is '状态（0正常 1通过2驳回）';
comment on column SYS_APP.create_by
  is '创建者';
comment on column SYS_APP.create_time
  is '创建时间';
comment on column SYS_APP.update_by
  is '更新者';
comment on column SYS_APP.update_time
  is '更新时间';
comment on column SYS_APP.remark
  is '备注';
-- Create/Recreate primary, unique and foreign key constraints
alter table SYS_APP
    add constraint PK_SYS_APP primary key (APP_ID)
    using index
  tablespace ERM_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- 菜单 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values(2004, '编码申请', '2001', '1', '/system/app', 'C', '0', 'system:app:view', '#', 'admin', sysdate, '', null, '编码申请菜单');

-- 按钮 SQL
insert into sys_menu (menu_id, menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values(seq_sys_menu.nextval, '编码申请查询', 2004, '1',  '#',  'F', '0', 'system:app:list',         '#', 'admin', sysdate, '', null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values(seq_sys_menu.nextval, '编码申请新增', 2004, '2',  '#',  'F', '0', 'system:app:add',          '#', 'admin', sysdate, '', null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values(seq_sys_menu.nextval, '编码申请修改', 2004, '3',  '#',  'F', '0', 'system:app:edit',         '#', 'admin', sysdate, '', null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values(seq_sys_menu.nextval, '编码申请删除', 2004, '4',  '#',  'F', '0', 'system:app:remove',       '#', 'admin', sysdate, '', null, '');

insert into sys_menu (menu_id, menu_name, parent_id, order_num, url, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark)
values(seq_sys_menu.nextval, '编码申请导出', 2004, '5',  '#',  'F', '0', 'system:app:export',       '#', 'admin', sysdate, '', null, '');


---20210929
create sequence seq_SYS_APP_DATA
    increment by 1
    start with 100
    nomaxvalue
 nominvalue
 cache 20;
-- Create table
create table SYS_APP_DATA
(
    app_data_id      NUMBER(20) not null,
    app_id           number(20),
    type             CHAR(1),
    cc_code          varchar2(500),
    design_desc      varchar2(4000),
    zh_cn_short_desc varchar2(4000),
    zh_cn_long_desc  varchar2(4000),
    zh_en_short_desc varchar2(4000),
    zh_en_long_desc  varchar2(4000),
    ru_cn_short_desc varchar2(4000),
    ru_cn_long_desc  varchar2(4000),
    ru_en_short_desc varchar2(4000),
    ru_en_long_desc  varchar2(4000),
    en_cn_short_desc varchar2(4000),
    en_cn_long_desc  varchar2(4000),
    en_en_short_desc varchar2(4000),
    en_en_long_desc  varchar2(4000),
    status           CHAR(1) default '0',
    create_by        VARCHAR2(64) default '',
    create_time      DATE,
    update_by        VARCHAR2(64) default '',
    update_time      DATE,
    remark           VARCHAR2(500)
)
    tablespace ERM_DATA
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table
comment on table SYS_APP_DATA  is '编码申请数据表';
-- Add comments to the columns
comment on column SYS_APP_DATA.app_data_id  is '申请单明细主键seq_sys_app_data.nextval';
comment on column SYS_APP_DATA.app_id  is '申请单ID';
comment on column SYS_APP_DATA.type  is 'CC或者ID（0:CC,1:ID）';
comment on column SYS_APP_DATA.cc_code  is 'CC代码';
comment on column SYS_APP_DATA.design_desc  is '设计描述';
comment on column SYS_APP_DATA.zh_cn_short_desc  is '中文公制短描述';
comment on column SYS_APP_DATA.zh_cn_long_desc  is '中文公制长描述';
comment on column SYS_APP_DATA.zh_en_short_desc  is '中文英制短描述';
comment on column SYS_APP_DATA.zh_en_long_desc  is '中文英制长描述';
comment on column SYS_APP_DATA.ru_cn_short_desc  is '俄文公制短描述';
comment on column SYS_APP_DATA.ru_cn_long_desc  is '俄文公制长描述';
comment on column SYS_APP_DATA.ru_en_short_desc  is '俄文英制短描述';
comment on column SYS_APP_DATA.ru_en_long_desc  is '俄文英制长描述';
comment on column SYS_APP_DATA.en_cn_short_desc  is '英文公制短描述';
comment on column SYS_APP_DATA.en_cn_long_desc  is '英文公制长描述';
comment on column SYS_APP_DATA.en_en_short_desc  is '英文英制短描述';
comment on column SYS_APP_DATA.en_en_long_desc  is '英文英制长描述';
comment on column SYS_APP_DATA.status  is '状态（0正常 1停用）';
comment on column SYS_APP_DATA.create_by  is '创建者';
comment on column SYS_APP_DATA.create_time  is '创建时间';
comment on column SYS_APP_DATA.update_by  is '更新者';
comment on column SYS_APP_DATA.update_time  is '更新时间';
comment on column SYS_APP_DATA.remark  is '备注';
-- Create/Recreate primary, unique and foreign key constraints
alter table SYS_APP_DATA
    add constraint PK_SYS_APP_DATA primary key (APP_DATA_ID)
    using index
  tablespace ERM_DATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
alter table SYS_APP_DATA
    add constraint FK_SYS_APP_DATA foreign key (APP_ID)
        references sys_app (APP_ID) on delete cascade;
alter table SYS_APP_DATA rename column cc_code to CODE;
comment on column SYS_APP_DATA.code  is 'CC代码或ID代码';
alter table SYS_OPER_LOG modify oper_param VARCHAR2(4000);
alter table SYS_OPER_LOG modify json_result VARCHAR2(4000);
