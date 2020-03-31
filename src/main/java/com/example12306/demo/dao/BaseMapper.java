package com.example12306.demo.dao;

import java.util.List;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-01-18 16:37
 **/
public interface BaseMapper<T> {

    int deleteByPrimaryKey(Integer id);

    int insert(T record);

    List<T> selectAll();

    int insertSelective(T record);

    T selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

}
