package com.example12306.demo.dao;

import com.example12306.demo.entity.Tline;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TlineMapper extends BaseMapper<Tline> {

    //    @Insert("INSERT INTO t_line(name) VALUES(#{name})")
//    int insert(Tline record);
    @Update("UPDATE t_line SET name = #{name} WHERE id = #{id}")
    Integer updateNameById(@Param("id") long id, @Param("name") String name);

    @Select("SELECT * FROM t_line WHERE id = #{id} AND name = #{name}")
    Tline findByIdAndName(@Param("id") long id, @Param("name") String name);

    List<Tline> findByIds(@Param("ids") List<Long> ids);
}