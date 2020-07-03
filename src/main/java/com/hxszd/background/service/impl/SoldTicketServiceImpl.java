package com.hxszd.background.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hxszd.background.entity.TLine;
import com.hxszd.background.mapper.TLineMapper;
import com.hxszd.background.mapper.TTicketMapper;
import com.hxszd.background.pojo.dto.ReserveInfoDTO;
import com.hxszd.background.pojo.exception.BusinessException;
import com.hxszd.background.service.SoldTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-01-18 16:55
 **/
@Service
public class SoldTicketServiceImpl extends ServiceImpl<TLineMapper, TLine> implements SoldTicketService {

    @Autowired
    private TTicketMapper ticketMapper;

    @Autowired
    private TLineMapper tLineMapper;

//    @Autowired
//    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    static int MAXIMUM_CAPACITY = 1 << 30;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer preOrder(List<ReserveInfoDTO> reserveInfoDTOS) {
        TLine tLine = new TLine();
        tLine.setName("123");
        tLine.setAbc(123);
        baseMapper.insert(tLine);

        System.out.println("插入数据");

        throw new BusinessException("出错啦");

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
