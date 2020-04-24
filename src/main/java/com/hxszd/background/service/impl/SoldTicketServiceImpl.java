package com.hxszd.background.service.impl;

import com.hxszd.background.dao.TticketMapper;
import com.hxszd.background.pojo.dto.ReserveInfoDTO;
import com.hxszd.background.service.SoldTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

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

    public static void main(String[] args) {
        /*int CAPACITY = 2147483647;
        int n = CAPACITY - 1;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 1;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 2;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 4;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 8;
        System.out.println(Integer.toBinaryString(n));
        n |= n >>> 16;
        System.out.println(Integer.toBinaryString(n));
        System.out.println((n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1);*/
        /*HashMap map = new HashMap(2147483647);
        //HashMap mapa = new HashMap();
        String [] ss = {"GA8Mfaaa", "e4R5Blaa", "Y92RLlaa", "sCvEytaa", "ydDspvaa",
                "ofLCCCaa", "uemC0Daa", "CbgFKKaa", "d0XRbNaa", "hUzU9Saa"};

        for (String s : ss){
            map.put(s, "");
        }*/
        // Object o = map.put("1","");

        /*HashMap map = new HashMap(16);
        HashMap maps = new HashMap();
        map.put("1","2");*/
        /*System.out.println(1 << 30 );
        System.out.println((1 << 31) -1);
        System.out.println(Integer.MAX_VALUE);*/
        /*int a;
        System.out.println((a = Integer.MAX_VALUE)+1);

        List<Integer> list = Arrays.asList(1,2,3);


        System.out.println(list.get(-1));*/



        //(key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);

        HashMap<String, String> map = new HashMap(1<<30);
        for (long i = 0; i < Integer.MAX_VALUE; i++) {
            map.put(i + "", "");
        }
        map.put(Integer.MAX_VALUE + 1 + "", "1");
        System.out.println(map.get(Integer.MAX_VALUE + ""));
        System.out.println(map.get(Integer.MAX_VALUE + ""));
        System.out.println(map.get(Integer.MAX_VALUE + 1 + ""));






    }

    /*public static void main(String[] args) {
        HashMap map = new HashMap(13);
        System.out.println(2^3);
    }*/
}
