 -- 生成代码
 alter table gen_table add form_cols char(1) default '2' COMMENT '表单列示 1单列 2两列 3三列 4四列' ;
 alter table gen_table_column add `is_readonly` char(1) DEFAULT 0 COMMENT '是否自读字段（0否 1是）'  ;
 alter table gen_table add bill_prefix char(6) default '' COMMENT '表单前缀编号' ;
   -- 菜单
 alter table sys_menu add bill_prefix char(6) default '' COMMENT '表单前缀编号' ;

