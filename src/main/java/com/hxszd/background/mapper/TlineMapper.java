package com.hxszd.background.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hxszd.background.entity.Tline;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TlineMapper extends BaseMapper<Tline> {

    @Update("UPDATE t_line SET name = #{name} WHERE id = #{id}")
    Integer updateNameById(@Param("id") long id, @Param("name") String name);

    @Select("SELECT * FROM t_line WHERE id = #{id} AND name = #{name}")
    Tline findByIdAndName(@Param("id") long id, @Param("name") String name);
}