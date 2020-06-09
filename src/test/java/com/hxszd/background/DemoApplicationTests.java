package com.hxszd.background;

import com.alibaba.fastjson.JSONObject;
import com.hxszd.background.mapper.TLineMapper;
import com.hxszd.background.pojo.base.BaseData;
import com.hxszd.background.service.common.IRedisService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private TLineMapper tlineMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private IRedisService redisService;


    @Value("${variables:999}")
    private String variables;

    @Autowired
    private Environment env;

    @Test
    void contextLoads() throws InterruptedException {

        redisService.saveObj("list", Arrays.asList(1, 2, 3), 30L);

        List list = redisService.getObj("list", List.class);
        System.out.println(list.toString());


        /*String s = redisTemplate.opsForValue().get("key");
        System.out.println(s);*/

        /*tlineMapper.updateNameById(2, "K9999");
        Tline tline = tlineMapper.findByIdAndName(2, "K9999");
        log.info(tline.toString());*/
        /*log.info(tlineMapper.findByIds(Arrays.asList(1L, 2L)).toString());*/
        /*System.out.println(variables);

        String s = redisTemplate.opsForValue().get("key");

        System.out.println(s);


        tlineMapper.insert(new Tline(5, "k123", new Date(), new Date()));

        Map map = new HashMap();*/




       /* for (int i=0;i<100;i++) {

            Thread.sleep(2000);
            String s = StringUtils.isEmpty(env.getProperty("variables")) ? "kong" : env.getProperty("variables");
            System.out.println(s);
        }*/

        /*Tline tline = new Tline();

        tline.setName("k110");
        Object obj = tline;
        if (obj instanceof Tline){
            System.out.println(((Tline) obj).getName());
        }*/

       /* System.out.println(new BigDecimal(10.10).toString().split(".")[1].length());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(new BigDecimal(10.1).toString().split(".")[0]);
        String s = new BigDecimal(10.1).toString().split(".")[1];
        if (s.length() < 2){
            for (int i=s.length();i<2;i++){
                s += "0";
            }
            stringBuilder.append("." + s);
        }
        System.out.println(stringBuilder.toString());*/
        //tlineMapper.insert(tline);

        //System.out.println("301".equals("30230022000089".substring(0, 3)));
    }

    @Test
    public void test() {
        /*redisTemplate.opsForValue().set("object", JSONObject.toJSONString(new ArrayList<BaseData>()));

        List baseData = JSONObject.parseArray(redisTemplate.opsForValue().get("object"), BaseData.class);

        System.out.println(baseData.size());*/


    }



}
