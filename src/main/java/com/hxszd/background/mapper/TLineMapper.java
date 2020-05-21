package com.hxszd.background.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hxszd.background.entity.TLine;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author pig1etO
 * @since 2020-05-21
 */
public interface TLineMapper extends BaseMapper<TLine> {

    @Select("SELECT * FROM t_line WHERE id = #{id}")
    TLine findByid(@Param("id") Integer id);
}