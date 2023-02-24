package com.tongyi.core;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * 惠州市酷天科技有限公司
 *
 * @author foquanlin@163.com 林佛权
 * 2021-08-22
 */
public class PageInfo<T> implements Serializable {
    private long current;
    private long size;
    private long total;
    private List<T> list;

    public PageInfo() {
        this.list = Collections.emptyList();
        this.total = 0L;
        this.size = 10L;
        this.current = 1L;
    }

    public PageInfo(long current, long size) {
        this(current, size, 0L);
    }

    public PageInfo(long current, long size, long total) {
        this.list = Collections.emptyList();
        this.total = 0L;
        this.size = 10L;
        this.current = 1L;
        if (current > 1L) {
            this.current = current;
        }

        this.size = size;
        this.total = total;
    }

    public boolean hasPrevious() {
        return this.current > 1L;
    }

    public boolean hasNext() {
        return this.current < this.getPages();
    }

    public List<T> getList() {
        return this.list;
    }

    private long getPages() {
        if (this.getSize() == 0L) {
            return 0L;
        } else {
            long pages = this.getTotal() / this.getSize();
            if (this.getTotal() % this.getSize() != 0L) {
                ++pages;
            }

            return pages;
        }
    }

    public PageInfo<T> setList(List<T> list) {
        this.list = list;
        return this;
    }

    public long getTotal() {
        return this.total;
    }

    public PageInfo<T> setTotal(long total) {
        this.total = total;
        return this;
    }

    public long getSize() {
        return this.size;
    }

    public PageInfo<T> setSize(long size) {
        this.size = size;
        return this;
    }

    public long getCurrent() {
        return this.current;
    }

    public PageInfo<T> setCurrent(long current) {
        this.current = current;
        return this;
    }

}
