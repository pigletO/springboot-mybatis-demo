package com.hxszd.background.conf.datasource;

/**
 * @description: 动态数据源ThreadLocal
 * @author: pig1etO
 * @create: 2020-05-21 15:28
 **/
public class DataSourceHolder {

    private static DataSourceHolder dataSourceHolder = new DataSourceHolder();

    private DataSourceHolder(){};

    public static DataSourceHolder getInstance() {
        return dataSourceHolder;
    }

    public static final ThreadLocal<String> dataSourceName = new ThreadLocal<>();

    public static String get() {
        return dataSourceName.get();
    }

    public static void set(String name) {
        dataSourceName.set(name);
    }

    public static void clear() {
        dataSourceName.remove();
    }
}
