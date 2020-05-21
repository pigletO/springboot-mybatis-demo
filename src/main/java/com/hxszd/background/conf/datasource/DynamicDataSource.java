package com.hxszd.background.conf.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @description: 动态数据源
 *      继承动态数据源的核心父类AbstractRoutingDataSource
 *      这个类是实现多数据源的关键，他的作用就是动态切换数据源
 *      实质：有多少个数据源就存多少个数据源在targetDataSources（是AbstractRoutingDataSource的一个map类型的属性，其中value为每个数据源，
 *      key表示每个数据源的名字）这个属性中，然后根据determineCurrentLookupKey（）这个方法获取当前数据源在map中的key值
 *
 * @author: pig1etO
 * @create: 2020-05-21 14:50
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 获取当前数据源在map中的key值，若找不到指定key的数据源，则使用默认数据源，再则抛出异常
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceHolder.get();
    }
}
