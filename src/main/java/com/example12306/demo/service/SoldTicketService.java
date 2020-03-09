package com.example12306.demo.service;

import com.example12306.demo.pojo.dto.ReserveInfoDTO;

import java.util.List;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-01-18 16:55
 **/
public interface SoldTicketService {

    /**
     * 下单
     * @param reserveInfoDTOS
     * @return
     */
    Integer preOrder(List<ReserveInfoDTO> reserveInfoDTOS);
}
