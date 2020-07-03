package com.hxszd.background.service;

import com.hxszd.background.mapper.TLineMapper;
import com.hxszd.background.pojo.dto.ReserveInfoDTO;
import org.springframework.beans.factory.annotation.Autowired;

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
