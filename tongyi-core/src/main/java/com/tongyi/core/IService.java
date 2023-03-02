package com.tongyi.core;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 惠州市酷天科技有限公司
 *
 * @author foquanlin@163.com 林佛权
 * 2021-08-22
 */
public interface IService<T> {
    public T getById(Serializable id);
    public boolean addEntity(T entity);
    public boolean updateEntity(T entity);
    public boolean deleteEntity(Serializable id);
    public boolean deleteBatch(Serializable[] ids);
    public List<T> listAll(Map<String, Object> params);
    public PageInfo<T> listPage(int current, int size, Map<String,Object> params);

    public void execute(Serializable id,Map<String,Object> params,ModuleExecute<T,Map<String,Object>,Void> fun);
    public void execute(T entity,Map<String,Object> params,ModuleExecute<T,Map<String,Object>,Void> fun);
    public void execute(T entity,Map<String,Object> params,ModuleExecute<T,Map<String,Object>,Void>... funs);
}
