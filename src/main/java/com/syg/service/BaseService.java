package com.syg.service;

import java.util.List;

/**
 * crud 基本操作
 * @Author: shiyugang
 * @Date: 2019/12/7 15:37
 */
public interface BaseService<T> {


    /**
     * 增加
     * @param t
     */
    public abstract void save(T t);

    /**
     * 修改
     * @param t
     */
    public abstract void update(T t);

    /**
     * 删除
     * @param t
     */
    public abstract void delete(T t);

    /**
     * 查询单个
     * @param t
     * @return
     */
    public abstract T findOne(T t);

    /**
     * 查询全部
     * @return
     */
    public abstract List<T> findAll();

}
