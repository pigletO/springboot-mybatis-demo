package com.hxszd.background.pojo.enums;

import com.hxszd.background.entity.redis.RClothingType;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @description: 服装类型enum,用作参考，数据来自对应服装类型表 {@link RClothingType}
 * @author: pig1etO
 * @create: 2020-04-02 16:11
 **/
@Getter
@AllArgsConstructor
public enum  ClothingTypEnum {

    NORMAL(0, "四季款"),
    SPRING(1, "春季款"),
    SUMMER(2, "夏季款"),
    AUTUMN(3, "秋季款"),
    WINTER(4, "冬季款");

    private Integer code;

    private String label;

    public static String getLabelByCode(Integer code){
        for (ExceptionCodeEnum e : ExceptionCodeEnum.values()){
            if (e.getCode().equals(code)){
                return e.getLabel();
            }
        }
        return "";
    }
}
