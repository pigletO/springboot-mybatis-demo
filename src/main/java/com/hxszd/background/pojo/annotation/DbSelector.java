package com.hxszd.background.pojo.annotation;

import com.hxszd.background.pojo.enums.DataSourceEnum;

import java.lang.annotation.*;

/**
 * @description: 动态数据源切换
 * @author: pig1etO
 * @create: 2020-05-21 17:31
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DbSelector {

    /**
     * 默认使用master数据源
     * @return
     */
    DataSourceEnum value() default DataSourceEnum.MASTER;

    /**
     * 以配置的数据源做完DB操作后，将数据源切换为默认数据源
     * @return
     */
    boolean clear() default false;
}
