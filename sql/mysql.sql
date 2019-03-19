/*
Navicat MySQL Data Transfer

Source Server         :
Source Server Version : 50722
Source Host           :
Source Database       : platform-plus

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-02-12 19:51:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `QRTZ_BLOB_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_IBFK_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_BLOB_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for `QRTZ_CALENDARS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_CALENDARS
-- ----------------------------

-- ----------------------------
-- Table structure for `QRTZ_CRON_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_CRON_TRIGGERS_IBFK_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for `QRTZ_FIRED_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_FIRED_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for `QRTZ_JOB_DETAILS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------

-- ----------------------------
-- Table structure for `QRTZ_LOCKS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------

-- ----------------------------
-- Table structure for `QRTZ_PAUSED_TRIGGER_GRPS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------

-- ----------------------------
-- Table structure for `QRTZ_SCHEDULER_STATE`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------

-- ----------------------------
-- Table structure for `QRTZ_SIMPLE_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_IBFK_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_SIMPLE_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for `QRTZ_SIMPROP_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_IBFK_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_SIMPROP_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for `QRTZ_TRIGGERS`
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `QRTZ_TRIGGERS_IBFK_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------

-- ----------------------------
-- Table structure for `SCHEDULE_JOB`
-- ----------------------------
DROP TABLE IF EXISTS `SCHEDULE_JOB`;
CREATE TABLE `SCHEDULE_JOB` (
  `JOB_ID` VARCHAR(32) NOT NULL COMMENT '任务id',
  `BEAN_NAME` VARCHAR(200) DEFAULT NULL COMMENT 'SPRING BEAN名称',
  `METHOD_NAME` VARCHAR(100) DEFAULT NULL COMMENT '方法名',
  `PARAMS` VARCHAR(2000) DEFAULT NULL COMMENT '参数',
  `CRON_EXPRESSION` VARCHAR(100) DEFAULT NULL COMMENT 'cron表达式',
  `STATUS` TINYINT(4) DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `REMARK` VARCHAR(255) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`JOB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务';

-- ----------------------------
-- Records of SCHEDULE_JOB
-- ----------------------------
INSERT INTO `SCHEDULE_JOB` VALUES ('f4330210e5664ebda4d56878236b572f', 'tokenTask', 'refreshToken', null, '0 0/30 * * * ?', '1', null, '2019-01-22 17:02:34');

-- ----------------------------
-- Table structure for `SCHEDULE_JOB_log`
-- ----------------------------
DROP TABLE IF EXISTS `SCHEDULE_JOB_log`;
CREATE TABLE `SCHEDULE_JOB_log` (
  `LOG_ID` VARCHAR(32) NOT NULL COMMENT '任务日志id',
  `JOB_ID` VARCHAR(32) NOT NULL COMMENT '任务id',
  `BEAN_NAME` VARCHAR(200) DEFAULT NULL COMMENT 'spring bean名称',
  `METHOD_NAME` VARCHAR(100) DEFAULT NULL COMMENT '方法名',
  `PARAMS` VARCHAR(2000) DEFAULT NULL COMMENT '参数',
  `STATUS` TINYINT(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `ERROR` VARCHAR(2000) DEFAULT NULL COMMENT '失败信息',
  `TIMES` INT(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `CREATE_TIME` DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`LOG_ID`),
  KEY `JOB_ID` (`JOB_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='定时任务日志';

-- ----------------------------
-- Records of SCHEDULE_JOB_log
-- ----------------------------

-- ----------------------------
-- Table structure for `SYS_CAPTCHA`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_CAPTCHA`;
CREATE TABLE `sys_captcha` (
  `UUID` CHAR(36) NOT NULL COMMENT 'uuid',
  `CODE` VARCHAR(6) NOT NULL COMMENT '验证码',
  `EXPIRE_TIME` DATETIME DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统验证码';

-- ----------------------------
-- Records of SYS_CAPTCHA
-- ----------------------------

-- ----------------------------
-- Table structure for `SYS_CONFIG`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_CONFIG`;
CREATE TABLE `SYS_CONFIG` (
  `ID` VARCHAR(32) NOT NULL,
  `PARAM_KEY` VARCHAR(50) DEFAULT NULL COMMENT 'key',
  `PARAM_VALUE` VARCHAR(2000) DEFAULT NULL COMMENT 'value',
  `STATUS` TINYINT(4) DEFAULT '1' COMMENT '状态   0：隐藏   1：显示',
  `REMARK` VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `PARAM_KEY` (`PARAM_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统配置信息表';

-- ----------------------------
-- Records of SYS_CONFIG
-- ----------------------------
INSERT INTO `SYS_CONFIG` VALUES ('1', 'CLOUD_STORAGE_CONFIG_KEY', '{\"type\":4,\"qiniuDomain\":\"\",\"qiniuPrefix\":\"\",\"qiniuAccessKey\":\"\",\"qiniuSecretKey\":\"\",\"qiniuBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunPrefix\":\"\",\"aliyunEndPoint\":\"\",\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qcloudBucketName\":\"\",\"diskPath\":\"/usr/local/nginx/html/upload\",\"proxyServer\":\"http://132.232.89.47/upload\"}', '0', '云存储配置信息');
INSERT INTO `SYS_CONFIG` VALUES ('2', 'SMS_CONFIG_KEY', '{\"domain\":\"http://web.cr6868.com/asmx/smsservice.aspx?\",\"name\":\"lipengjun\",\"pwd\":\"\",\"sign\":\"【微同工作室】\",\"type\":1}', '0', '短信配置');

-- ----------------------------
-- Table structure for `SYS_DICT`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_DICT`;
CREATE TABLE `SYS_DICT` (
  `ID` VARCHAR(32) NOT NULL,
  `GROUP_ID` VARCHAR(32) DEFAULT NULL COMMENT '所属分组ID',
  `NAME` VARCHAR(100) DEFAULT NULL COMMENT '字典名称',
  `VALUE` VARCHAR(64) DEFAULT NULL COMMENT '字典值',
  `SORT` INT(11) DEFAULT NULL COMMENT '排序号',
  `STATUS` INT(11) DEFAULT NULL COMMENT '状态码',
  `REMARK` TEXT COMMENT '备注',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典';

-- ----------------------------
-- Records of SYS_DICT
-- ----------------------------
INSERT INTO `SYS_DICT` VALUES ('37f73ea6b07c40ab8baec7f58b10e69e', '0b5e3fc9c30a4839a881bef0f85fc8af', '男', '1', '1', '1', null);
INSERT INTO `SYS_DICT` VALUES ('979439be76954bc1852fdf2aeccf3cbc', '0b5e3fc9c30a4839a881bef0f85fc8af', '未知', '3', '3', '1', null);
INSERT INTO `SYS_DICT` VALUES ('fc982423addd41e3852c70f8396a0c6c', '0b5e3fc9c30a4839a881bef0f85fc8af', '女', '2', '2', '1', null);
INSERT INTO `SYS_DICT` VALUES ('7936bc509417490ba0df9d938ccd1ce4', '2bbfcb36f9414b71a5d65f497be93496', '是', '1', '1', '1', null);
INSERT INTO `SYS_DICT` VALUES ('f6cf775c5cea4c7b8858eb2ce0501177', '2bbfcb36f9414b71a5d65f497be93496', '否', '0', '2', '1', null);

-- ----------------------------
-- Table structure for `SYS_DICT_GROUP`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_DICT_GROUP`;
CREATE TABLE `SYS_DICT_GROUP` (
  `ID` VARCHAR(32) NOT NULL,
  `CODE` VARCHAR(64) NOT NULL COMMENT '分组编码',
  `NAME` VARCHAR(100) DEFAULT NULL COMMENT '分组名称',
  `CREATE_TIME` DATETIME DEFAULT NULL COMMENT '创建时间',
  `REMARK` TEXT COMMENT '备注',
  PRIMARY KEY (`ID`,`CODE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据字典分组';

-- ----------------------------
-- Records of SYS_DICT_GROUP
-- ----------------------------
INSERT INTO `SYS_DICT_GROUP` VALUES ('0b5e3fc9c30a4839a881bef0f85fc8af', 'SEX', '性别', null, '性别，1：男 2：女 3：未知');
INSERT INTO `SYS_DICT_GROUP` VALUES ('2bbfcb36f9414b71a5d65f497be93496', 'IS_NOT', '是否', null, '1：是 0：否');

-- ----------------------------
-- Table structure for `SYS_LOG`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_LOG`;
CREATE TABLE `SYS_LOG` (
  `ID` VARCHAR(32) NOT NULL,
  `USER_NAME` VARCHAR(50) DEFAULT NULL COMMENT '用户名',
  `OPERATION` VARCHAR(50) DEFAULT NULL COMMENT '用户操作',
  `METHOD` VARCHAR(200) DEFAULT NULL COMMENT '请求方法',
  `PARAMS` VARCHAR(5000) DEFAULT NULL COMMENT '请求参数',
  `TIME` BIGINT(20) NOT NULL COMMENT '执行时长(毫秒)',
  `IP` VARCHAR(64) DEFAULT NULL COMMENT 'IP地址',
  `CREATE_TIME` DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of SYS_LOG
-- ----------------------------

-- ----------------------------
-- Table structure for `SYS_MENU`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_MENU`;
CREATE TABLE `SYS_MENU` (
  `MENU_ID` VARCHAR(8) NOT NULL,
  `PARENT_ID` VARCHAR(8) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `NAME` VARCHAR(50) DEFAULT NULL COMMENT '菜单名称',
  `URL` VARCHAR(200) DEFAULT NULL COMMENT '菜单URL',
  `PERMS` VARCHAR(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `TYPE` INT(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `ICON` VARCHAR(50) DEFAULT NULL COMMENT '菜单图标',
  `ORDER_NUM` INT(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of SYS_MENU
-- ----------------------------
INSERT INTO `SYS_MENU` VALUES ('10', '0', '系统管理', null, null, 0, 'system', 0);
INSERT INTO `SYS_MENU` VALUES ('1001', '10', '菜单管理', 'sys/menu', 'sys:menu:list,sys:menu:info', 1, 'menu', 1);
INSERT INTO `SYS_MENU` VALUES ('100101', '1001', '新增', null, 'sys:menu:save,sys:menu:select', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('100102', '1001', '修改', null, 'sys:menu:update,sys:menu:select', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('100103', '1001', '删除', null, 'sys:menu:delete', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('1002', '10', '组织机构', 'sys/org', 'sys:org:list,sys:org:info', 1, 'org', 2);
INSERT INTO `SYS_MENU` VALUES ('100201', '1002', '新增', null, 'sys:org:save', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('100202', '1002', '修改', null, 'sys:org:update', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('100203', '1002', '删除', null, 'sys:org:delete', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('1003', '10', '系统参数', 'sys/config', 'sys:config:list,sys:config:info', 1, 'xitongpeizhi', 3);
INSERT INTO `SYS_MENU` VALUES ('100301', '1003', '新增', null, 'sys:config:save', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('100302', '1003', '修改', null, 'sys:config:update', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('100303', '1003', '删除', null, 'sys:config:delete', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('1004', '10', '字典管理', 'sys/dictgroup', 'sys:dictgroup:list,sys:dictgroup:info,sys:dict:list,sys:dict:info', 1, 'dict', 4);
INSERT INTO `SYS_MENU` VALUES ('100401', '1004', '数据字典新增', null, 'sys:dict:save', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('100402', '1004', '数据字典修改', null, 'sys:dict:update', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('100403', '1004', '数据字典删除', null, 'sys:dict:delete', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('100404', '1004', '数据字典分组新增', null, 'sys:dictgroup:save', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('100405', '1004', '数据字典分组修改', null, 'sys:dictgroup:update', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('100406', '1004', '数据字典分组删除', null, 'sys:dictgroup:delete', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('1005', '10', '文件上传', 'oss/oss', 'sys:oss:list', 1, 'oss', 5);
INSERT INTO `SYS_MENU` VALUES ('100501', '1005', '云存储配置', null, 'sys:oss:config', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('100502', '1005', '上传文件', null, 'sys:oss:upload', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('100503', '1005', '删除', null, 'sys:oss:delete', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('1006', '10', '系统日志', 'sys/log', 'sys:log:list', 1, 'log', 6);
INSERT INTO `SYS_MENU` VALUES ('11', '0', '权限管理', null, null, 0, 'auth', 1);
INSERT INTO `SYS_MENU` VALUES ('1101', '11', '管理员列表', 'sys/user', 'sys:user:list,sys:user:info', 1, 'admin', 1);
INSERT INTO `SYS_MENU` VALUES ('110101', '1101', '重置密码', null, 'sys:user:resetPw', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('110102', '1101', '新增', null, 'sys:user:save,sys:role:select', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('110103', '1101', '修改', null, 'sys:user:update,sys:role:select', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('110104', '1101', '删除', null, 'sys:user:delete', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('1102', '11', '角色管理', 'sys/role', 'sys:role:list,sys:role:info', 1, 'role', 2);
INSERT INTO `SYS_MENU` VALUES ('110201', '1102', '新增', null, 'sys:role:save,sys:menu:list', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('110202', '1102', '修改', null, 'sys:role:update,sys:menu:list', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('110203', '1102', '删除', null, 'sys:role:delete', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('12', '0', '短信平台', null, null, 0, 'duanxinpingtai', 2);
INSERT INTO `SYS_MENU` VALUES ('1211', '12', '短信配置', 'sys/smslog', 'sys:smslog:list', 1, 'duanxin', 1);
INSERT INTO `SYS_MENU` VALUES ('121101', '1211', '修改配置', null, 'sys:smslog:config', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('121102', '1211', '删除', null, 'sys:smslog:delete', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('121103', '1211', '发送短信', null, 'sys:smslog:send', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('13', '0', '任务调度', null, null, 0, 'diaodu', 3);
INSERT INTO `SYS_MENU` VALUES ('1301', '13', '定时任务', 'job/schedule', 'sys:schedule:list,sys:schedule:info', 1, 'job', 1);
INSERT INTO `SYS_MENU` VALUES ('130101', '1301', '删除', null, 'sys:schedule:delete', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('130102', '1301', '暂停', null, 'sys:schedule:pause', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('130103', '1301', '恢复', null, 'sys:schedule:resume', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('130104', '1301', '立即执行', null, 'sys:schedule:run', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('130105', '1301', '日志列表', null, 'sys:schedule:log', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('130106', '1301', '新增', null, 'sys:schedule:save', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('130107', '1301', '修改', null, 'sys:schedule:update', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('14', '0', '工作流管理', null, null, 0, 'activiti', 4);
INSERT INTO `SYS_MENU` VALUES ('1401', '14', '流程操作', 'act/reprocdef', 'act:reprocdef:list', 1, 'procdef', 1);
INSERT INTO `SYS_MENU` VALUES ('140101', '1401', '激活,挂起', null, 'act:reprocdef:update', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('140102', '1401', '删除', null, 'act:reprocdef:delete', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('140103', '1401', '转为模型', null, 'act:reprocdef:convertToModel', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('140104', '1401', '部署流程', null, 'act:reprocdef:deploy', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('1402', '14', '模型管理', 'act/remodel', 'act:remodel:list', 1, 'model', 2);
INSERT INTO `SYS_MENU` VALUES ('140201', '1402', '新增', null, 'act:remodel:save', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('140202', '1402', '编辑', null, 'act:remodel:update', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('140203', '1402', '部署', null, 'act:remodel:deploy', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('140204', '1402', '导出', null, 'act:remodel:export', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('140205', '1402', '删除', null, 'act:remodel:delete', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('15', '0', '开发工具', null, null, 0, 'dev', 5);
INSERT INTO `SYS_MENU` VALUES ('1501', '15', '在线用户管理', 'sys/usertoken', 'sys:usertoken:list', 1, 'zaixian', 1);
INSERT INTO `SYS_MENU` VALUES ('150101', '1501', '强制下线', null, 'sys:usertoken:offline', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('1502', '15', '缓存信息', 'sys/redis', 'sys:cache:queryAll', 1, 'redis', 2);
INSERT INTO `SYS_MENU` VALUES ('150201', '1502', '删除', null, 'sys:cache:deleteCache', 2, null, 0);
INSERT INTO `SYS_MENU` VALUES ('1503', '15', 'SQL监控', 'http://localhost:8888/platform-plus/druid/sql.html', null, 1, 'sql', 3);
INSERT INTO `SYS_MENU` VALUES ('1504', '15', '接口文档', 'http://localhost:8888/platform-plus/doc.html', null, 1, 'interface', 4);
INSERT INTO `SYS_MENU` VALUES ('1505', '15', '代码生成器', 'gen/generator', 'sys:generator:list', 1, 'code', 5);
INSERT INTO `SYS_MENU` VALUES ('150501', '1505', '生成代码', null, 'sys:generator:code', 2, null, 0);

-- ----------------------------
-- Table structure for `SYS_ORG`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_ORG`;
CREATE TABLE `SYS_ORG` (
  `ORG_NO` VARCHAR(10) NOT NULL COMMENT '机构编码',
  `ORG_NAME` VARCHAR(50) DEFAULT NULL COMMENT '机构名称',
  `PARENT_NO` VARCHAR(10) DEFAULT NULL COMMENT '上级机构ID，一级机构为0',
  `ORG_TYPE` INT(11) DEFAULT NULL COMMENT '级别',
  `STATUS` INT(11) DEFAULT '1' COMMENT '状态  0：无效   1：有效',
  `SORT` INT(11) DEFAULT NULL COMMENT '排序',
  `CREATE_USER_ID` VARCHAR(32) DEFAULT NULL COMMENT '创建者ID',
  `CREATE_TIME` DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ORG_NO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构';

-- ----------------------------
-- Records of SYS_ORG
-- ----------------------------
INSERT INTO `SYS_ORG` VALUES ('01', '中华人民共和国国务院', '0', '1', '1', '0', '1', '2019-01-21 16:53:32');

-- ----------------------------
-- Table structure for `SYS_OSS`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_OSS`;
CREATE TABLE `SYS_OSS` (
  `ID` VARCHAR(32) NOT NULL,
  `URL` VARCHAR(200) DEFAULT NULL COMMENT 'URL地址',
  `CREATE_DATE` DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='文件上传';

-- ----------------------------
-- Records of SYS_OSS
-- ----------------------------

-- ----------------------------
-- Table structure for `SYS_ROLE`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_ROLE`;
CREATE TABLE `SYS_ROLE` (
  `ROLE_ID` VARCHAR(32) NOT NULL,
  `ROLE_NAME` VARCHAR(100) DEFAULT NULL COMMENT '角色名称',
  `REMARK` VARCHAR(100) DEFAULT NULL COMMENT '备注',
  `CREATE_USER_ID` VARCHAR(32) DEFAULT NULL COMMENT '创建者ID',
  `CREATE_USER_ORG_NO` VARCHAR(32) DEFAULT NULL COMMENT '创建者所属机构',
  `CREATE_TIME` DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of SYS_ROLE
-- ----------------------------

-- ----------------------------
-- Table structure for `SYS_ROLE_MENU`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_ROLE_MENU`;
CREATE TABLE `SYS_ROLE_MENU` (
  `ID` VARCHAR(32) NOT NULL,
  `ROLE_ID` VARCHAR(32) DEFAULT NULL COMMENT '角色ID',
  `MENU_ID` VARCHAR(8) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of SYS_ROLE_MENU
-- ----------------------------

-- ----------------------------
-- Table structure for `SYS_ROLE_ORG`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_ROLE_ORG`;
CREATE TABLE `SYS_ROLE_ORG` (
  `ID` VARCHAR(32) NOT NULL,
  `ROLE_ID` VARCHAR(32) DEFAULT NULL COMMENT '角色ID',
  `ORG_NO` VARCHAR(32) DEFAULT NULL COMMENT '机构ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色与机构对应关系';

-- ----------------------------
-- Records of SYS_ROLE_ORG
-- ----------------------------

-- ----------------------------
-- Table structure for `SYS_SMS_LOG`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_SMS_LOG`;
CREATE TABLE `SYS_SMS_LOG` (
  `ID` VARCHAR(32) NOT NULL COMMENT '主键',
  `USER_ID` VARCHAR(32) DEFAULT NULL COMMENT '操作人ID',
  `CONTENT` TEXT COMMENT '必填参数。发送内容（1-500 个汉字）UTF-8编码',
  `MOBILE` TEXT COMMENT '必填参数。手机号码。多个以英文逗号隔开',
  `STIME` DATETIME DEFAULT NULL COMMENT '可选参数。发送时间，填写时已填写的时间发送，不填时为当前时间发送',
  `SIGN` VARCHAR(32) DEFAULT NULL COMMENT '必填参数。用户签名',
  `TYPE` VARCHAR(32) DEFAULT NULL COMMENT '必填参数。固定值 PT',
  `EXTNO` VARCHAR(255) DEFAULT NULL COMMENT '可选参数。扩展码，用户定义扩展码，只能为数字',
  `SEND_STATUS` INT(1) DEFAULT NULL COMMENT '1成功 0失败',
  `SEND_ID` VARCHAR(32) DEFAULT NULL COMMENT '发送编号',
  `INVALID_NUM` INT(11) DEFAULT NULL COMMENT '无效号码数',
  `SUCCESS_NUM` INT(11) DEFAULT NULL COMMENT '成功提交数',
  `BLACK_NUM` INT(11) DEFAULT NULL COMMENT '黑名单数',
  `RETURN_MSG` VARCHAR(50) DEFAULT NULL COMMENT '返回消息',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='短信发送日志';

-- ----------------------------
-- Records of SYS_SMS_LOG
-- ----------------------------

-- ----------------------------
-- Table structure for `SYS_USER`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_USER`;
CREATE TABLE `SYS_USER` (
  `USER_ID` VARCHAR(32) NOT NULL,
  `USER_NAME` VARCHAR(50) NOT NULL COMMENT '用户名',
  `REAL_NAME` VARCHAR(64) NOT NULL,
  `SEX` TINYINT(4) NOT NULL,
  `ORG_NO` VARCHAR(32) DEFAULT NULL COMMENT '机构编码',
  `SALT` VARCHAR(20) DEFAULT NULL COMMENT '盐',
  `EMAIL` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',
  `MOBILE` VARCHAR(100) DEFAULT NULL COMMENT '手机号',
  `STATUS` TINYINT(4) DEFAULT NULL COMMENT '状态  0：禁用   1：正常',
  `PASSWORD` VARCHAR(100) DEFAULT NULL COMMENT '密码',
  `CREATE_USER_ID` VARCHAR(32) DEFAULT NULL COMMENT '创建者ID',
  `CREATE_USER_ORG_NO` VARCHAR(32) DEFAULT NULL COMMENT '创建人所属机构',
  `CREATE_TIME` DATETIME DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USERNAME` (`USER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户';

-- ----------------------------
-- Records of SYS_USER
-- ----------------------------
INSERT INTO `SYS_USER` VALUES ('1', 'admin', '李鹏军', 1, '01', 'YzcmCZNvbXocrsz9dm8e', '939961241@qq.com', '15209831991', '1', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', null, null, '2016-11-11 11:11:11');

-- ----------------------------
-- Table structure for `SYS_USER_ROLE`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_USER_ROLE`;
CREATE TABLE `SYS_USER_ROLE` (
  `ID` VARCHAR(32) NOT NULL,
  `USER_ID` VARCHAR(32) DEFAULT NULL COMMENT '用户ID',
  `ROLE_ID` VARCHAR(32) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of SYS_USER_ROLE
-- ----------------------------

-- ----------------------------
-- Table structure for `SYS_USER_TOKEN`
-- ----------------------------
DROP TABLE IF EXISTS `SYS_USER_TOKEN`;
CREATE TABLE `SYS_USER_TOKEN` (
  `USER_ID` VARCHAR(32) NOT NULL,
  `TOKEN` VARCHAR(100) NOT NULL COMMENT 'TOKEN',
  `EXPIRE_TIME` DATETIME DEFAULT NULL COMMENT '过期时间',
  `UPDATE_TIME` DATETIME DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `TOKEN` (`TOKEN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户Token';

-- ----------------------------
-- Records of SYS_USER_TOKEN
-- ----------------------------

-- ----------------------------
-- Table structure for `TB_USER`
-- ----------------------------
DROP TABLE IF EXISTS `TB_USER`;
CREATE TABLE `TB_USER` (
  `USER_ID` VARCHAR(32) NOT NULL,
  `USER_NAME` VARCHAR(50) NOT NULL COMMENT '用户名',
  `SUBSCRIBE` INT(1) COMMENT '关注状态（1是关注，0是未关注），未关注时获取不到其余信息',
  `SUBSCRIBE_TIME` VARCHAR(50) COMMENT '用户关注时间，为时间戳。如果用户曾多次关注，则取最后关注时间',
  `OPEN_ID` VARCHAR(100) COMMENT '用户的标识',
  `NICKNAME` VARCHAR(200) COMMENT '微信昵称',
  `HEAD_IMG_URL` VARCHAR(200) COMMENT '用户头像',
  `SEX` INT(1) COMMENT '用户的性别（1是男性，2是女性，0是未知）',
  `MOBILE` VARCHAR(20) NOT NULL COMMENT '手机号',
  `PASSWORD` VARCHAR(64) COMMENT '密码',
  `CREATE_TIME` DATETIME COMMENT '创建时间',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USER_NAME` (`USER_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';

-- ----------------------------
-- Records of TB_USER
-- ----------------------------
INSERT INTO `TB_USER` VALUES ('1', '李鹏军', 1, '1550742648', 'oxaA11ulr9134oBL9Xscon5at_Gc', 'Boy Genius', 'http://thirdwx.qlogo.cn/mmopen/PiajxSqBRaEI3eTLaf64kP7sBrpXKbJ7l4h6BWOlJjAQUqibVbsKotVWbzH6QnkTHYmuTMZXuUiaXVo7Ba02XbCxA/132', 1, '15209831990', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', '2019-03-06 02:33:16');