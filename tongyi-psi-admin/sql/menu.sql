    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('1009','10','单位管理','psi/unit','psi:unit:list,psi:unit:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('100901','1009','新增',null,'psi:unit:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('100902','1009','修改',null,'psi:unit:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('100903','1009','删除',null,'psi:unit:delete',2,'admin',0,1);

    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('1010','10','仓库管理','psi/warehouse','psi:warehouse:list,psi:warehouse:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('101001','1010','新增',null,'psi:warehouse:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('101002','1010','修改',null,'psi:warehouse:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('101003','1010','删除',null,'psi:warehouse:delete',2,'admin',0,1);
    INSERT INTO sys_menu (menu_id,parent_id,name,url,perms,type,icon,order_num,shows) VALUES ('101004','1010','默认仓库',NULL,'psi:warehouse:default',2,'admin',0,1);
    INSERT INTO sys_menu (menu_id, parent_id, name, url, perms, type, icon, order_num, shows) VALUES ('101005', '1010', '仓库状态', NULL, 'psi:warehouse:status', 2, 'admin', 0, 1);
insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','库存管理',null,null,0,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','调拨单管理','psi/allocation','psi:allocation:list,psi:allocation:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200101','2001','新增',null,'psi:allocation:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200102','2001','修改',null,'psi:allocation:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200103','2001','删除',null,'psi:allocation:delete',2,'admin',0,1);

#     insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','调拨单明细管理','psi/allocationgoods','psi:allocationgoods:list,psi:allocationgoods:info',1,'admin',0,1);
#     insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200201','2002','新增',null,'psi:allocationgoods:save',2,'admin',0,1);
#     insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200202','2002','修改',null,'psi:allocationgoods:update',2,'admin',0,1);
#     insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200203','2002','删除',null,'psi:allocationgoods:delete',2,'admin',0,1);

    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','批次管理','psi/batch','psi:batch:list,psi:batch:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200301','2003','新增',null,'psi:batch:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200302','2003','修改',null,'psi:batch:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200303','2003','删除',null,'psi:batch:delete',2,'admin',0,1);

    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2004','20','品牌管理','psi/brand','psi:brand:list,psi:brand:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200401','2004','新增',null,'psi:brand:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200402','2004','修改',null,'psi:brand:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200403','2004','删除',null,'psi:brand:delete',2,'admin',0,1);

    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2005','20','商品分类','psi/catalog','psi:catalog:list,psi:catalog:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200501','2005','新增',null,'psi:catalog:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200502','2005','修改',null,'psi:catalog:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200503','2005','删除',null,'psi:catalog:delete',2,'admin',0,1);

    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2006','20','盘点管理','psi/check','psi:check:list,psi:check:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200601','2006','新增',null,'psi:check:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200602','2006','修改',null,'psi:check:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200603','2006','删除',null,'psi:check:delete',2,'admin',0,1);

#     insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2007','20','盘点明细管理','psi/checkdetail','psi:checkdetail:list,psi:checkdetail:info',1,'admin',0,1);
#     insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200701','2007','新增',null,'psi:checkdetail:save',2,'admin',0,1);
#     insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200702','2007','修改',null,'psi:checkdetail:update',2,'admin',0,1);
#     insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200703','2007','删除',null,'psi:checkdetail:delete',2,'admin',0,1);

    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2008','20','商品管理','psi/goods','psi:goods:list,psi:goods:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200801','2008','新增',null,'psi:goods:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200802','2008','修改',null,'psi:goods:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200803','2008','删除',null,'psi:goods:delete',2,'admin',0,1);

#     insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2009','20','商品sku管理','psi/goodssku','psi:goodssku:list,psi:goodssku:info',1,'admin',0,1);
#     insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200901','2009','新增',null,'psi:goodssku:save',2,'admin',0,1);
#     insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200902','2009','修改',null,'psi:goodssku:update',2,'admin',0,1);
#     insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('200903','2009','删除',null,'psi:goodssku:delete',2,'admin',0,1);

#     insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2010','20','规格管理','psi/goodsspec','psi:goodsspec:list,psi:goodsspec:info',1,'admin',0,1);
#     insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('201001','2010','新增',null,'psi:goodsspec:save',2,'admin',0,1);
#     insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('201002','2010','修改',null,'psi:goodsspec:update',2,'admin',0,1);
#     insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('201003','2010','删除',null,'psi:goodsspec:delete',2,'admin',0,1);

    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2011','20','库存管理','psi/stock','psi:stock:list,psi:stock:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('201101','2011','新增',null,'psi:stock:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('201102','2011','修改',null,'psi:stock:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('201103','2011','删除',null,'psi:stock:delete',2,'admin',0,1);

    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2012','20','出入库明细','psi/stockrecord','psi:stockrecord:list,psi:stockrecord:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('201201','2012','新增',null,'psi:stockrecord:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('201202','2012','修改',null,'psi:stockrecord:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('201203','2012','删除',null,'psi:stockrecord:delete',2,'admin',0,1);


insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('21','0','采购管理',null,null,0,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2101','21','供应商管理','psi/supplier','psi:supplier:list,psi:supplier:info',1,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('210101','2101','新增',null,'psi:supplier:save',2,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('210102','2101','修改',null,'psi:supplier:update',2,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('210103','2101','删除',null,'psi:supplier:delete',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2102','21','采购订单','psi/buyorder','psi:buyorder:list,psi:buyorder:info',1,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('210201','2102','新增',null,'psi:buyorder:save',2,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('210202','2102','修改',null,'psi:buyorder:update',2,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('210203','2102','删除',null,'psi:buyorder:delete',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2103','21','采购退单','psi/buyrefundorder','psi:buyrefundorder:list,psi:buyrefundorder:info',1,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('210301','2103','新增',null,'psi:buyrefundorder:save',2,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('210302','2103','修改',null,'psi:buyrefundorder:update',2,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('210303','2103','删除',null,'psi:buyrefundorder:delete',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2104','21','供应商对账','psi/supplierstatement','psi:supplierstatement:list,psi:supplierstatement:info',1,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('210401','2104','新增',null,'psi:supplierstatement:save',2,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('210402','2104','修改',null,'psi:supplierstatement:update',2,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('210403','2104','删除',null,'psi:supplierstatement:delete',2,'admin',0,1);

insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('22','0','销售管理',null,null,0,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2201','22','客户管理','psi/customer','psi:customer:list,psi:customer:info',1,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('220101','2201','新增',null,'psi:customer:save',2,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('220102','2201','修改',null,'psi:customer:update',2,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('220103','2201','删除',null,'psi:customer:delete',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2202','22','销售订单','psi/saleorder','psi:saleorder:list,psi:saleorder:info',1,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('220201','2202','新增',null,'psi:saleorder:save',2,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('220202','2202','修改',null,'psi:saleorder:update',2,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('220203','2202','删除',null,'psi:saleorder:delete',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2203','22','销售退单','psi/salerefundorder','psi:salerefundorder:list,psi:salerefundorder:info',1,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('220301','2203','新增',null,'psi:salerefundorder:save',2,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('220302','2203','修改',null,'psi:salerefundorder:update',2,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('220303','2203','删除',null,'psi:salerefundorder:delete',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2204','22','客户对账','psi/customerstatement','psi:customerstatement:list,psi:customerstatement:info',1,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('220401','2204','新增',null,'psi:customerstatement:save',2,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('220402','2204','修改',null,'psi:customerstatement:update',2,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('220403','2204','删除',null,'psi:customerstatement:delete',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2205','22','客户订购统计','psi/customerorderstatistics','psi:customerorderstatistics:list,psi:customerorderstatistics:info',1,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('220501','2205','新增',null,'psi:customerorderstatistics:save',2,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('220502','2205','修改',null,'psi:customerorderstatistics:update',2,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('220503','2205','删除',null,'psi:customerorderstatistics:delete',2,'admin',0,1);

insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('25','0','报表统计',null,null,0,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2501','25','分类统计',null,'psi:inout:report',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2502','25','采购销售',null,'psi:buysale:report',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2503','25','销售排行',null,'psi:sale:rank',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2504','25','序列号销售统计',null,'psi:bank:save',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2505','25','客户销售统计',null,'psi:customer:report',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2506','25','商品销售统计',null,'psi:goods:report',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2507','25','商品分类统计',null,'psi:catalog:report',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2508','25','销售货款统计',null,'psi:sale:report',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2509','25','采购货款统计',null,'psi:buy:report',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2510','25','员工销售统计',null,'psi:staff:report',1,'admin',0,1);

insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('24','0','财务管理',null,null,0,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2401','24','银行账户管理','psi/bank','psi:bank:list,psi:bank:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('240101','2401','新增',null,'psi:bank:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('240102','2401','修改',null,'psi:bank:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('240103','2401','删除',null,'psi:bank:delete',2,'admin',0,1);
    INSERT INTO sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) VALUES ('240104', '2401', '设置默认账户', NULL, 'psi:bank:default', 2, 'admin', 0, 1);

    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2402','24','运杂费管理','psi/orderexpress','psi:orderexpress:list,psi:orderexpress:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('240201','2402','新增',null,'psi:orderexpress:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('240202','2402','修改',null,'psi:orderexpress:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('240203','2402','删除',null,'psi:orderexpress:delete',2,'admin',0,1);

    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2403','24','非销售收入','psi/nosalein','psi:nosalein:list,psi:nosalein:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('240301','2403','新增',null,'psi:nosalein:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('240302','2403','修改',null,'psi:nosalein:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('240303','2403','删除',null,'psi:nosalein:delete',2,'admin',0,1);

    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2404','24','非采购支出','psi/nobuyout','psi:nobuyout:list,psi:nobuyout:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('240401','2404','新增',null,'psi:nobuyout:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('240402','2404','修改',null,'psi:nobuyout:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('240403','2404','删除',null,'psi:nobuyout:delete',2,'admin',0,1);

# insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2402','24','订单账目管理','psi/orderamount','psi:orderamount:list,psi:orderamount:info',1,'admin',0,1);
# insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('240201','2402','新增',null,'psi:orderamount:save',2,'admin',0,1);
# insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('240202','2402','修改',null,'psi:orderamount:update',2,'admin',0,1);
# insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('240203','2402','删除',null,'psi:orderamount:delete',2,'admin',0,1);

# insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','订单明细管理','psi/orderdetail','psi:orderdetail:list,psi:orderdetail:info',1,'admin',0,1);
# insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','新增',null,'psi:orderdetail:save',2,'admin',0,1);
# insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','修改',null,'psi:orderdetail:update',2,'admin',0,1);
# insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','删除',null,'psi:orderdetail:delete',2,'admin',0,1);

# insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','订单发票管理','psi/orderinvoice','psi:orderinvoice:list,psi:orderinvoice:info',1,'admin',0,1);
# insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','新增',null,'psi:orderinvoice:save',2,'admin',0,1);
# insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','修改',null,'psi:orderinvoice:update',2,'admin',0,1);
# insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','删除',null,'psi:orderinvoice:delete',2,'admin',0,1);

# insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','订单操作日志管理','psi/orderoperation','psi:orderoperation:list,psi:orderoperation:info',1,'admin',0,1);
# insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','新增',null,'psi:orderoperation:save',2,'admin',0,1);
# insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','修改',null,'psi:orderoperation:update',2,'admin',0,1);
# insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','删除',null,'psi:orderoperation:delete',2,'admin',0,1);


