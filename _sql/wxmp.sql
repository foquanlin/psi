DROP TABLE IF EXISTS `cms_article`;
CREATE TABLE `cms_article`  (
  `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  `type` tinyint(1) NULL DEFAULT 1 COMMENT '文章类型[1:普通文章,5:帮助中心]',
  `title` varchar(1024) CHARACTER SET utf8 NOT NULL COMMENT '标题',
  `summary` varchar(1024) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '文章摘要',
  `tags` varchar(255) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '文章标签',
  `content` longtext CHARACTER SET utf8 NULL COMMENT '内容',
  `category` varchar(25) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '分类',
  `sub_category` varchar(25) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '二级目录',
  `create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `open_count` int(11) NULL DEFAULT 0 COMMENT '点击次数',
  `start_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '生效时间',
  `end_time` datetime(0) NULL DEFAULT NULL COMMENT '失效时间',
  `target_link` varchar(255) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '指向外链',
  `image` varchar(255) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '文章首图',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_title`(`title`) USING BTREE COMMENT '标题不得重复'
) ENGINE = InnoDB AUTO_INCREMENT = 337 CHARACTER SET = utf8 COMMENT = 'CMS文章中心' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wx_account
-- ----------------------------
DROP TABLE IF EXISTS `wx_account`;
CREATE TABLE `wx_account`  (
  `appid` char(20) CHARACTER SET utf8 NOT NULL COMMENT 'appid',
  `name` varchar(50) CHARACTER SET utf8 NOT NULL COMMENT '公众号名称',
  `type` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '账号类型',
  `verified` tinyint(1) UNSIGNED NULL DEFAULT 1 COMMENT '认证状态',
  `secret` char(32) CHARACTER SET utf8 NOT NULL COMMENT 'appsecret',
  `token` varchar(32) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT 'token',
  `aes_key` varchar(43) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT 'aesKey',
  PRIMARY KEY (`appid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COMMENT = '公众号账号' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wx_msg
-- ----------------------------
DROP TABLE IF EXISTS `wx_msg`;
CREATE TABLE `wx_msg`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `appid` char(20) CHARACTER SET utf8 NOT NULL COMMENT 'appid',
  `openid` varchar(32) CHARACTER SET utf8 NOT NULL COMMENT '微信用户ID',
  `in_out` tinyint(1) UNSIGNED NULL DEFAULT NULL COMMENT '消息方向',
  `msg_type` char(25) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '消息类型',
  `detail` varchar(255) NULL COMMENT '消息详情',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_appid`(`appid`) USING BTREE COMMENT 'appid'
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COMMENT = '微信消息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wx_msg_reply_rule
-- ----------------------------
DROP TABLE IF EXISTS `msg_reply_rule`;
DROP TABLE IF EXISTS `wx_msg_reply_rule`;
CREATE TABLE `wx_msg_reply_rule`  (
  `rule_id` int(11) NOT NULL AUTO_INCREMENT,
  `appid` char(20) CHARACTER SET utf8 NULL DEFAULT '' COMMENT 'appid',
  `rule_name` varchar(20) CHARACTER SET utf8 NOT NULL COMMENT '规则名称',
  `match_value` varchar(200) CHARACTER SET utf8 NOT NULL COMMENT '匹配的关键词、事件等',
  `exact_match` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否精确匹配',
  `reply_type` varchar(20) CHARACTER SET utf8 NOT NULL DEFAULT '1' COMMENT '回复消息类型',
  `reply_content` varchar(1024) CHARACTER SET utf8 NOT NULL COMMENT '回复消息内容',
  `status` tinyint(1) NOT NULL DEFAULT 1 COMMENT '规则是否有效',
  `desc` varchar(255) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '备注说明',
  `effect_time_start` time(0) NULL DEFAULT '00:00:00' COMMENT '生效起始时间',
  `effect_time_end` time(0) NULL DEFAULT '23:59:59' COMMENT '生效结束时间',
  `priority` int(3) UNSIGNED NULL DEFAULT 0 COMMENT '规则优先级',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`rule_id`) USING BTREE,
  INDEX `idx_appid`(`appid`) USING BTREE COMMENT 'appid'
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8 COMMENT = '自动回复规则' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wx_msg_reply_rule
-- ----------------------------
INSERT INTO `wx_msg_reply_rule` VALUES (1, '', '关注公众号', 'subscribe', 0, 'text', '你好，欢迎关注！\n<a href=\"https://github.com/niefy\">点击链接查看我的主页</a>', 1, '关注回复', '00:00:00', '23:59:59', 0, '2020-05-20 15:15:00');

-- ----------------------------
-- Table structure for wx_msg_template
-- ----------------------------
DROP TABLE IF EXISTS `msg_template`;
DROP TABLE IF EXISTS `wx_msg_template`;
CREATE TABLE `wx_msg_template`  (
  `id` bigint(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id',
  `appid` char(20) CHARACTER SET utf8 NOT NULL COMMENT 'appid',
  `template_id` varchar(100) CHARACTER SET utf8 NOT NULL COMMENT '公众号模板ID',
  `name` varchar(50) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '模版名称',
  `title` varchar(20) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 NULL COMMENT '模板内容',
  `data` varchar(255) NULL COMMENT '消息内容',
  `url` varchar(255) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '链接',
  `miniprogram` varchar(255) NULL COMMENT '小程序信息',
  `status` tinyint(1) UNSIGNED NOT NULL COMMENT '是否有效',
  `update_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `idx_name`(`name`) USING BTREE COMMENT '模板名称',
  INDEX `idx_status`(`status`) USING BTREE COMMENT '模板状态',
  INDEX `idx_appid`(`appid`) USING BTREE COMMENT 'appid'
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8 COMMENT = '消息模板' ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for wx_qr_code
-- ----------------------------
DROP TABLE IF EXISTS `wx_qr_code`;
CREATE TABLE `wx_qr_code`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `appid` char(20) CHARACTER SET utf8 NOT NULL COMMENT 'appid',
  `is_temp` tinyint(1) NULL DEFAULT NULL COMMENT '是否为临时二维码',
  `scene_str` varchar(64) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '场景值ID',
  `ticket` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '二维码ticket',
  `url` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '二维码图片解析后的地址',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '该二维码失效时间',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '该二维码创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_appid`(`appid`) USING BTREE COMMENT 'appid'
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COMMENT = '公众号带参二维码' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wx_template_msg_log
-- ----------------------------
DROP TABLE IF EXISTS `template_msg_log`;
DROP TABLE IF EXISTS `wx_template_msg_log`;
CREATE TABLE `wx_template_msg_log`  (
  `log_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `appid` char(20) CHARACTER SET utf8 NOT NULL COMMENT 'appid',
  `touser` varchar(50) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '用户openid',
  `template_id` varchar(50) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT 'templateid',
  `data` varchar(255) NULL COMMENT '消息数据',
  `url` varchar(255) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '消息链接',
  `miniprogram` varchar(255) NULL COMMENT '小程序信息',
  `send_time` datetime(0) NULL DEFAULT NULL COMMENT '发送时间',
  `send_result` varchar(255) CHARACTER SET utf8 NULL DEFAULT NULL COMMENT '发送结果',
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `idx_appid`(`appid`) USING BTREE COMMENT 'appid'
) ENGINE = InnoDB AUTO_INCREMENT = 116250 CHARACTER SET = utf8 COMMENT = '微信模版消息发送记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for wx_user
-- ----------------------------
DROP TABLE IF EXISTS `wx_user`;
CREATE TABLE `wx_user`  (
  `openid` varchar(50) CHARACTER SET utf8mb4 NOT NULL COMMENT '微信openid',
  `appid` char(20) CHARACTER SET utf8 NOT NULL COMMENT 'appid',
  `phone` char(11) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '手机号',
  `nickname` varchar(50) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '昵称',
  `sex` tinyint(4) NULL DEFAULT NULL COMMENT '性别(0-未知、1-男、2-女)',
  `city` varchar(20) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '城市',
  `province` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '省份',
  `headimgurl` varchar(1000) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '头像',
  `subscribe_time` datetime(0) NULL DEFAULT NULL COMMENT '订阅时间',
  `subscribe` tinyint(3) UNSIGNED NULL DEFAULT 1 COMMENT '是否关注',
  `unionid` varchar(50) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT 'unionid',
  `remark` varchar(255) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '备注',
  `tagid_list` varchar(1000) NULL COMMENT '标签ID列表',
  `subscribe_scene` varchar(50) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '关注场景',
  `qr_scene_str` varchar(64) CHARACTER SET utf8mb4 NULL DEFAULT NULL COMMENT '扫码场景值',
  PRIMARY KEY (`openid`) USING BTREE,
  INDEX `idx_unionid`(`unionid`) USING BTREE COMMENT 'unionid',
  INDEX `idx_appid`(`appid`) USING BTREE COMMENT 'appid'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COMMENT = '用户表' ROW_FORMAT = Dynamic;


INSERT INTO `SYS_MENU` VALUES ('16', '0', '微信管理', NULL, NULL, 0, 'admin', 1,1);
INSERT INTO `SYS_MENU` VALUES ('1601', '16', '公众号菜单', 'wx/wx-menu', '', 1, 'log', 0,1);
INSERT INTO `SYS_MENU` VALUES ('160101', '1601', '更新公众号菜单', '', 'wx:menu:save', 2, '', 0,1);

INSERT INTO `SYS_MENU` VALUES ('1602', '16', '素材管理', 'wx/wx-assets', '', 1, '', 0,1);
INSERT INTO `SYS_MENU` VALUES ('160201', '1602', '查看', '', 'wx:wxassets:list', 2, '', 0,1);
INSERT INTO `SYS_MENU` VALUES ('160202', '1602', '新增修改', '', 'wx:wxassets:save', 2, '', 0,1);
INSERT INTO `SYS_MENU` VALUES ('160203', '1602', '删除', '', 'wx:wxassets:delete', 2, '', 0,1);

INSERT INTO `SYS_MENU` VALUES ('1603', '16', '自动回复规则', 'wx/msg-reply-rule', NULL, 1, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('160301', '1603', '查看', NULL, 'wx:msgreplyrule:list,wx:msgreplyrule:info', 2, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('160302', '1603', '新增', NULL, 'wx:msgreplyrule:save', 2, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('160303', '1603', '修改', NULL, 'wx:msgreplyrule:update', 2, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('160304', '1603', '删除', NULL, 'wx:msgreplyrule:delete', 2, 'admin', 6,1);

INSERT INTO `SYS_MENU` VALUES ('1604', '16', '模板消息', 'wx/msg-template', NULL, 1, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('160401', '1604', '查看', NULL, 'wx:msgtemplate:list,wx:msgtemplate:info', 2, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('160402', '1604', '新增', NULL, 'wx:msgtemplate:save', 2, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('160403', '1604', '修改', NULL, 'wx:msgtemplate:update', 2, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('160404', '1604', '删除', NULL, 'wx:msgtemplate:delete', 2, 'admin', 6,1);

INSERT INTO `SYS_MENU` VALUES ('1605', '16', '带参二维码', 'wx/wx-qrcode', NULL, 1, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('160501', '1605', '查看', NULL, 'wx:wxqrcode:list,wx:wxqrcode:info', 2, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('160502', '1605', '新增', NULL, 'wx:wxqrcode:save', 2, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('160503', '1605', '删除', NULL, 'wx:wxqrcode:delete', 2, 'admin', 6,1);

INSERT INTO `SYS_MENU` VALUES ('1606', '16', '粉丝管理', 'wx/wx-user', NULL, 1, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('160601', '1606', '查看', NULL, 'wx:wxuser:list,wx:wxuser:info', 2, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('160602', '1606', '删除', NULL, 'wx:wxuser:delete', 2, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('160603', '1606', '同步', '', 'wx:wxuser:save', 2, 'admin', 6,1);

INSERT INTO `SYS_MENU` VALUES ('1607', '16', '公众号消息', 'wx/wx-msg', NULL, 1, '', 6,1);
INSERT INTO `SYS_MENU` VALUES ('160701', '1607', '查看', NULL, 'wx:wxmsg:list,wx:wxmsg:info', 2, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('160702', '1607', '新增', NULL, 'wx:wxmsg:save', 2, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('160703', '1607', '删除', NULL, 'wx:wxmsg:delete', 2, 'admin', 6,1);

INSERT INTO `SYS_MENU` VALUES ('1608', '16', '公众号账号', 'wx/wx-account', NULL, 1, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('160801', '1608', '查看', NULL, 'wx:wxaccount:list,wx:wxaccount:info', 2, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('160802', '1608', '新增', NULL, 'wx:wxaccount:save', 2, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('160803', '1608', '修改', NULL, 'wx:wxaccount:update', 2, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('160804', '1608', '删除', NULL, 'wx:wxaccount:delete', 2, 'admin', 6,1);

INSERT INTO `SYS_MENU` VALUES ('1609', '16', '内容管理', '', '', 0, 'admin', 2,1);
INSERT INTO `SYS_MENU` VALUES ('160901', '1609', '文章管理', 'wx/article', NULL, 1, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('16090101', '160901', '查看', NULL, 'wx:article:list,wx:article:info', 2, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('16090102', '160901', '新增', NULL, 'wx:article:save', 2, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('16090103', '160901', '修改', NULL, 'wx:article:update', 2, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('16090104', '160901', '删除', NULL, 'wx:article:delete', 2, 'admin', 6,1);

INSERT INTO `SYS_MENU` VALUES ('1610', '16', '日志报表', '', '', 0, 'admin', 4,1);
INSERT INTO `SYS_MENU` VALUES ('161001', '1610', '模版消息发送记录', 'wx/template-msg-log', NULL, 1, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('16100101', '161001', '列表', NULL, 'wx:templatemsglog:list', 2, 'admin', 6,1);
INSERT INTO `SYS_MENU` VALUES ('16100102', '161001', '删除', NULL, 'wx:templatemsglog:delete', 2, 'admin', 6,1);