package com.example12306.demo.service.impl;

import com.example12306.demo.dao.TticketMapper;
import com.example12306.demo.pojo.dto.ReserveInfoDTO;
import com.example12306.demo.service.SoldTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-01-18 16:55
 **/
@Service
public class SoldTicketServiceImpl implements SoldTicketService {

    @Autowired
    private TticketMapper ticketMapper;

    @Override
    public Integer preOrder(List<ReserveInfoDTO> reserveInfoDTOS) {
        return null;
    }
}
