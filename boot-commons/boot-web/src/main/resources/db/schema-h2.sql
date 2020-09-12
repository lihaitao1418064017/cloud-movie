DROP TABLE IF EXISTS userInfo;

CREATE TABLE userInfo
(
	id BIGINT(20) NOT NULL COMMENT '主键ID',
	name VARCHAR(30) NULL DEFAULT NULL COMMENT '名称',
	age INT(11) NULL DEFAULT NULL COMMENT '年龄',
	status INT(11) NULL DEFAULT NULL COMMENT '状态',
	creator_code VARCHAR(30) NULL DEFAULT NULL COMMENT '创建人',
	updater_code VARCHAR(30) NULL DEFAULT NULL COMMENT '更新人',
	create_time BIGINT(20) NOT NULL COMMENT '创建时间',
	update_time BIGINT(20) NOT NULL COMMENT '更新时间',
	PRIMARY KEY (id)
);
ALTER TABLE userInfo ADD update_time bigint(20)  DEFAULT 0;
ALTER TABLE userInfo ADD create_time bigint(20)  DEFAULT 0;
ALTER TABLE userInfo ADD creator_code VARCHAR(30) DEFAULT NULL ;
ALTER TABLE userInfo ADD updater_code VARCHAR(30) DEFAULT NULL ;
ALTER TABLE userInfo ADD status INT(11)  DEFAULT 0;

ALTER TABLE role ADD update_time bigint(20)  DEFAULT 0;
ALTER TABLE role ADD create_time bigint(20)  DEFAULT 0;
ALTER TABLE role ADD creator_code VARCHAR(30) DEFAULT NULL ;
ALTER TABLE role ADD updater_code VARCHAR(30) DEFAULT NULL ;
ALTER TABLE role ADD status INT(11)  DEFAULT 0;

ALTER TABLE permission ADD update_time bigint(20)  DEFAULT 0;
ALTER TABLE permission ADD create_time bigint(20)  DEFAULT 0;
ALTER TABLE permission ADD creator_code VARCHAR(30) DEFAULT NULL ;
ALTER TABLE permission ADD updater_code VARCHAR(30) DEFAULT NULL ;
ALTER TABLE permission ADD status INT(11)  DEFAULT 0;

ALTER TABLE role_permission ADD update_time bigint(20)  DEFAULT 0;
ALTER TABLE role_permission ADD create_time bigint(20)  DEFAULT 0;
ALTER TABLE role_permission ADD creator_code VARCHAR(30) DEFAULT NULL ;
ALTER TABLE role_permission ADD updater_code VARCHAR(30) DEFAULT NULL ;
ALTER TABLE role_permission ADD status INT(11)  DEFAULT 0;

ALTER TABLE user_role ADD update_time bigint(20)  DEFAULT 0;
ALTER TABLE user_role ADD create_time bigint(20)  DEFAULT 0;
ALTER TABLE user_role ADD creator_code VARCHAR(30) DEFAULT NULL ;
ALTER TABLE user_role ADD updater_code VARCHAR(30) DEFAULT NULL ;
ALTER TABLE user_role ADD status INT(11)  DEFAULT 0;

