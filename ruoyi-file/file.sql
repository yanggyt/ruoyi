-- ry.business_file definition

CREATE TABLE `business_file` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `file_name` varchar(225) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件名',
  `file_path` varchar(225) NOT NULL COMMENT '文件路径',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='文件资源表';
