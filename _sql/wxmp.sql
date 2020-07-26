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