package com.hxszd.background.service.impl;

import com.hxszd.background.dao.TticketMapper;
import com.hxszd.background.pojo.dto.ReserveInfoDTO;
import com.hxszd.background.service.SoldTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
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

    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    static int MAXIMUM_CAPACITY = 1 << 30;

    @Override
    public Integer preOrder(List<ReserveInfoDTO> reserveInfoDTOS) {
        return null;
    }

    public static void main(String[] args) throws IOException {
        String filePath = "\"C:\\\\ProgramData\\\\Microsoft\\\\Windows\\\\Start Menu\\\\Programs\\\\Xmanager Enterprise 5\\\\a.txt";
        //BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
        BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
        StringBuilder sb = new StringBuilder("");
        String line = null;
        while ((line = br.readLine()) != null) {

        }
    }
}
