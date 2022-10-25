-- 菜单
-- name: 商品sku
-- url: psi/goodssku
-- perms: psi:goodssku:list,psi:goodssku:info

-- 按钮权限
-- 新增: psi:goodssku:save
-- 修改: psi:goodssku:update
-- 删除: psi:goodssku:delete

insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','商品sku管理','psi/goodssku','psi:goodssku:list,psi:goodssku:info',1,'admin',0,1);
insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','新增',null,'psi:goodssku:save',2,'admin',0,1);
insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','修改',null,'psi:goodssku:update',2,'admin',0,1);
insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','删除',null,'psi:goodssku:delete',2,'admin',0,1);

