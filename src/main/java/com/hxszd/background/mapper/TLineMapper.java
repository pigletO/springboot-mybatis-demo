package com.hxszd.background.mapper;

import com.hxszd.background.entity.TLine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pig1etO
 * @since 2020-08-26
 */
public interface TLineMapper extends BaseMapper<TLine> {

    @Select("SELECT * FROM t_line WHERE id = #{id}")
    TLine findByid(@Param("id") Integer id);

    @Delete("DELETE FROM t_line WHERE id = #{id}")
    Integer delete(@Param("id") Long id);
}
