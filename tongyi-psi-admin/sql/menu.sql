    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('1009','0','单位管理','psi/unit','psi:unit:list,psi:unit:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('100901','1009','新增',null,'psi:unit:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('100902','1009','修改',null,'psi:unit:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('100903','1009','删除',null,'psi:unit:delete',2,'admin',0,1);

insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','库存管理',null,null,0,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','调拨单管理','psi/allocation','psi:allocation:list,psi:allocation:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','新增',null,'psi:allocation:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','修改',null,'psi:allocation:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','删除',null,'psi:allocation:delete',2,'admin',0,1);

    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','调拨单明细管理','psi/allocationgoods','psi:allocationgoods:list,psi:allocationgoods:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','新增',null,'psi:allocationgoods:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','修改',null,'psi:allocationgoods:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','删除',null,'psi:allocationgoods:delete',2,'admin',0,1);

    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','批次管理','psi/batch','psi:batch:list,psi:batch:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','新增',null,'psi:batch:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','修改',null,'psi:batch:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','删除',null,'psi:batch:delete',2,'admin',0,1);

    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','品牌管理','psi/brand','psi:brand:list,psi:brand:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','新增',null,'psi:brand:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','修改',null,'psi:brand:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','删除',null,'psi:brand:delete',2,'admin',0,1);

    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','商品分类管理','psi/catalog','psi:catalog:list,psi:catalog:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','新增',null,'psi:catalog:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','修改',null,'psi:catalog:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','删除',null,'psi:catalog:delete',2,'admin',0,1);

    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','盘点管理','psi/check','psi:check:list,psi:check:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','新增',null,'psi:check:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','修改',null,'psi:check:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','删除',null,'psi:check:delete',2,'admin',0,1);

    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','盘点明细管理','psi/checkdetail','psi:checkdetail:list,psi:checkdetail:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','新增',null,'psi:checkdetail:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','修改',null,'psi:checkdetail:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','删除',null,'psi:checkdetail:delete',2,'admin',0,1);

    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','商品管理','psi/goods','psi:goods:list,psi:goods:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','新增',null,'psi:goods:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','修改',null,'psi:goods:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','删除',null,'psi:goods:delete',2,'admin',0,1);

    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','商品sku管理','psi/goodssku','psi:goodssku:list,psi:goodssku:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','新增',null,'psi:goodssku:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','修改',null,'psi:goodssku:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','删除',null,'psi:goodssku:delete',2,'admin',0,1);


    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','库存管理','psi/stock','psi:stock:list,psi:stock:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','新增',null,'psi:stock:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','修改',null,'psi:stock:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','删除',null,'psi:stock:delete',2,'admin',0,1);

    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','库存流水管理','psi/stockrecord','psi:stockrecord:list,psi:stockrecord:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','新增',null,'psi:stockrecord:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','修改',null,'psi:stockrecord:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','删除',null,'psi:stockrecord:delete',2,'admin',0,1);


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
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('210401','2104','修改',null,'psi:supplierstatement:update',2,'admin',0,1);
        insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('210401','2104','删除',null,'psi:supplierstatement:delete',2,'admin',0,1);

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

insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('23','0','仓库管理','psi/warehouse','psi:warehouse:list,psi:warehouse:info',0,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2301','23','新增',null,'psi:warehouse:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2302','23','修改',null,'psi:warehouse:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2303','23','删除',null,'psi:warehouse:delete',2,'admin',0,1);

insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('24','0','财务管理',null,null,0,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','银行账户管理','psi/bank','psi:bank:list,psi:bank:info',1,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','新增',null,'psi:bank:save',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','修改',null,'psi:bank:update',2,'admin',0,1);
    insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','删除',null,'psi:bank:delete',2,'admin',0,1);

insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('25','0','报表统计',null,null,0,'admin',0,1);

insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','订单账目管理','psi/orderamount','psi:orderamount:list,psi:orderamount:info',1,'admin',0,1);
insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','新增',null,'psi:orderamount:save',2,'admin',0,1);
insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','修改',null,'psi:orderamount:update',2,'admin',0,1);
insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','删除',null,'psi:orderamount:delete',2,'admin',0,1);

insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','订单明细管理','psi/orderdetail','psi:orderdetail:list,psi:orderdetail:info',1,'admin',0,1);
insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','新增',null,'psi:orderdetail:save',2,'admin',0,1);
insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','修改',null,'psi:orderdetail:update',2,'admin',0,1);
insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','删除',null,'psi:orderdetail:delete',2,'admin',0,1);

insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','运杂费管理','psi/orderexpress','psi:orderexpress:list,psi:orderexpress:info',1,'admin',0,1);
insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','新增',null,'psi:orderexpress:save',2,'admin',0,1);
insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','修改',null,'psi:orderexpress:update',2,'admin',0,1);
insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','删除',null,'psi:orderexpress:delete',2,'admin',0,1);

insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','订单发票管理','psi/orderinvoice','psi:orderinvoice:list,psi:orderinvoice:info',1,'admin',0,1);
insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','新增',null,'psi:orderinvoice:save',2,'admin',0,1);
insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','修改',null,'psi:orderinvoice:update',2,'admin',0,1);
insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','删除',null,'psi:orderinvoice:delete',2,'admin',0,1);

insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','订单操作日志管理','psi/orderoperation','psi:orderoperation:list,psi:orderoperation:info',1,'admin',0,1);
insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','新增',null,'psi:orderoperation:save',2,'admin',0,1);
insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','修改',null,'psi:orderoperation:update',2,'admin',0,1);
insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','删除',null,'psi:orderoperation:delete',2,'admin',0,1);

