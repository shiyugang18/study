package com.syg.base.service;

import java.util.List;

/**
 * @Author: shiyugang
 * @Date: 2019/12/7 15:37
 */
public interface BaseService<T> {


    /**
     * 增加
     * @param t
     */
    public void save(T t);

    /**
     * 修改
     * @param t
     */
    public void update(T t);

    /**
     * 删除
     * @param t
     */
    public void delete(T t);

    /**
     * 查询单个
     * @param t
     * @return
     */
    public T findOne(T t);

    /**
     * 查询全部
     * @return
     */
    public List<T> findAll();

}
