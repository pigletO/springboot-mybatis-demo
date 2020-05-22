package com.hxszd.background.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 动态数据源枚举
 * @author: pig1etO
 * @create: 2020-05-21 15:43
 **/
@Getter
@AllArgsConstructor
public enum DataSourceEnum {

    MASTER("master"),
    SLAVER("slaver");

    private String name;
}
