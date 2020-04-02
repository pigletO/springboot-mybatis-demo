package com.hxszd.background.service;

import com.hxszd.background.pojo.dto.ReserveInfoDTO;

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


    public static void main(String[] args) {
        ReserveInfoDTO reserveInfoDTO = new ReserveInfoDTO("123", 30);
        StringBuilder sb = new StringBuilder();
        sb.append("asdf1a2sd1f;");
        sb.deleteCharAt(sb.length() - 1);
        System.out.println(sb.toString());
    }



}
