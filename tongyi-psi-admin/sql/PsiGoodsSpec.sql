-- 菜单
-- name: 
-- url: psi/goodsspec
-- perms: psi:goodsspec:list,psi:goodsspec:info

-- 按钮权限
-- 新增: psi:goodsspec:save
-- 修改: psi:goodsspec:update
-- 删除: psi:goodsspec:delete

insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('20','0','管理','psi/goodsspec','psi:goodsspec:list,psi:goodsspec:info',1,'admin',0,1);
insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2001','20','新增',null,'psi:goodsspec:save',2,'admin',0,1);
insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2002','20','修改',null,'psi:goodsspec:update',2,'admin',0,1);
insert into sys_menu(menu_id,parent_id,name,url,perms,type,icon,order_num,shows) values ('2003','20','删除',null,'psi:goodsspec:delete',2,'admin',0,1);

