package com.example12306.demo.pojo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 预定信息
 * @author: pig1etO
 * @create: 2020-01-18 16:57
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReserveInfoDTO {

    /**
     * 车次
     */
    private String lineName;

    /**
     * 预定数量
     */
    private Integer num;
}
