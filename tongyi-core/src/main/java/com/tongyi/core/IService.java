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
    public boolean add(T entity);
    public boolean update(T entity);
    public boolean delete(Serializable id);
    public boolean deleteBatch(Serializable[] ids);
    public List<T> queryAll(Map<String, Object> params);
    public PageInfo<T> listPage(long current, long size, Map<String,Object> params);
}
