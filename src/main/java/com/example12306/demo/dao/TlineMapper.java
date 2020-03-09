package com.example12306.demo.dao;

import com.example12306.demo.entity.Tline;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
public interface TlineMapper extends BaseMapper<Tline> {

//    @Insert("INSERT INTO t_line(name) VALUES(#{name})")
//    int insert(Tline record);
}