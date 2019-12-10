package com.syg.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.syg.dao.BaseDao;
import com.syg.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: shiyugang
 * @Date: 2019/12/7 15:37
 */
public class BaseServiceImpl<M extends BaseDao<T>, T> implements BaseService<T> {


    @Autowired
    protected M baseDao;

    public M getBaseDao() {
        return this.baseDao;
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void save(T t) {
        this.baseDao.save(t);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void update(T t) {
        this.baseDao.update(t);
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void delete(T t) {
        this.baseDao.delete(t);
    }

    @Override
    public T findOne(T t) {
        return baseDao.findOne(t);
    }

    @Override
    public List<T> findAll() {
        return baseDao.findAll();
    }
}
