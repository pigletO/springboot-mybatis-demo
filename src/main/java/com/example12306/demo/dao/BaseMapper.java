package com.example12306.demo.dao;

import com.example12306.demo.entity.Tstation;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-01-18 16:37
 **/
public interface BaseMapper<T> {

    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

}
