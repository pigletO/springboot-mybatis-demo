//package com.hxszd.background.utils;
//
//import com.alibaba.fastjson.JSONObject;
//import com.szhl.sxtx.common.data.enums.ContentTypeEnum;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.client.config.RequestConfig;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
//import java.io.IOException;
//import java.util.concurrent.Executor;
//import java.util.concurrent.Executors;
//
///**
// * 功能：http 工具类
// * @date 2017
// */
//@Slf4j
//public class HttpClientUtil {
//
//    //
//    private static final int  socketTimeout = 30000;
//
//    // 传输超时时间，默认30秒
//    private static final int connectTimeout = 30000;
//
//    private static Executor executor = Executors.newCachedThreadPool();
//
////    public static void postAsync(String url, String json){
////        executor.execute(() -> post( url,  json));
////    }
//
//    public static void async(Runnable runnable){
//        executor.execute(runnable);
//    }
//
//    /**
//     * 功能：post 发送 json（带日志）
//     * @param url
//     * @param param
//     * @param responseClazz
//     * @param <T>
//     * @return
//     */
//    public static <T> T postJson(String url, Object param, Class<T> responseClazz){
//        String requestJsonStr = JSONObject.toJSONString(param);
//        String responseJsonStr = post(url, requestJsonStr, ContentTypeEnum.JSON, true, null);
//        return JSONObject.parseObject(responseJsonStr, responseClazz);
//    }
//
//    /**
//     * 功能：post 发送 json
//     * @param url
//     * @param bodyStr
//     * @return
//     */
//    public static String postJson(String url, String bodyStr) {
//        return post(url, bodyStr, ContentTypeEnum.JSON, true, null);
//    }
//
//    /**
//     * 功能：post 发送 json
//     * @param url
//     * @param bodyStr
//     * @return
//     */
//    public static String postJsonLogMarker(String url, String bodyStr, String marker) {
//        return post(url, bodyStr, ContentTypeEnum.JSON, false, marker);
//    }
//
//    /**
//     * 功能：post 发送 json 报文 公共方法
//     * 备注：很多老代码使用的是这些方法
//     * @param url
//     * @param bodyStr
//     * @return
//     */
//    public static String post(String url, String bodyStr) {
//        return post(url, bodyStr, ContentTypeEnum.JSON, true, null);
//    }
//
//    /**
//     * 功能：post 发送 xml str
//     * @param url
//     * @param xmlStr
//     * @return
//     */
//    public static String postXml(String url, String xmlStr) {
//        return post(url, xmlStr, ContentTypeEnum.XML, true, null);
//    }
//
//    /**
//     * 功能：post 发送 报文 公共方法
//     * @param url
//     * @param bodyStr
//     * @param contentTypeEnum  xml:请求报文类型是xml类型 json：请求报文类型是json类型
//     * @param marker 日志marker
//     * @return
//     */
//    public static String post(String url, String bodyStr, ContentTypeEnum contentTypeEnum, boolean isLog, String marker) {
//        HttpPost httpPost = new HttpPost(url);
//        //解决XStream对出现双下划线的bug
//      /*  XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));
//
//        //将要提交给API的数据对象转换成XML格式数据Post给API
//        String postDataXML = xStreamForRequestPostData.toXML(xmlObj);*/
//
//        /*Util.log("API，POST过去的数据是：");
//        Util.log(postDataXML);*/
//
//        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
//        StringEntity postEntity = new StringEntity(bodyStr, "UTF-8");
//        switch (contentTypeEnum) {
//            case XML:
//                httpPost.addHeader("Content-Type", "application/xml;charset=utf-8");
//                break;
//            case JSON:
//                httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
//                break;
//            case WWW:
//                httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
//                break;
//            default:
//                httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
//        }
//        httpPost.setEntity(postEntity);
//        //设置请求器的配置
//        RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
//        httpPost.setConfig(requestConfig);
//
//        String result = http(url, httpPost, bodyStr, isLog, marker);
//
//        return result;
//    }
//
//    /**
//     * 未优化
//     * 功能：get 发送 报文 公共方法
//     * @param url
//     * @return
//     */
//    public static String get(String url) {
//        log.info("请求地址：{}，注意：【此日志记录为 HttpClient工具类自带打印】", url);
//        HttpGet httpGet = new HttpGet(url);
//        return http(url, httpGet, "", true, null);
//    }
//
//    public static <T> T get(String url, Class<T> responseClazz) {
//        return JSONObject.parseObject(get(url), responseClazz);
//    }
//
//    public static <T> T get(String url, Class<T> responseClazz, boolean logFlag) {
//        String resp = get(url);
//        if (logFlag) {
//            log.info("请求地址：{}，返回：{}，注意：【此日志记录为 HttpClient工具类自带打印】", url, resp);
//        }
//        return JSONObject.parseObject(resp, responseClazz);
//    }
//
//    private static String http(String url, HttpUriRequest httpUriRequest, String bodyStr, boolean isLog, String marker) {
//        if (isLog) {
//            log.info("【HttpClientUtil开始】请求地址：{}，参数为：{}，注意：【此日志记录为 HttpClient工具类自带打印】", url, bodyStr);
//        }
//        long startTime = System.currentTimeMillis();
//
//        String result = null;
//        CloseableHttpClient httpClient = HttpClients.custom().build();
//        try {
//            HttpResponse response = httpClient.execute(httpUriRequest);
//            HttpEntity entity = response.getEntity();
//            result = EntityUtils.toString(entity, "UTF-8");
//        } catch (Exception e) {
//            log.error(url + "【http请求错误：{}】", e);
//        } finally {
//            httpUriRequest.abort();
//            try {
//                httpClient.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//
//        long endTime = System.currentTimeMillis();
//        if (isLog) {
//            log.info("【HttpClientUtil结束】请求地址：{}，返回值：{}，注意：【此日志记录为 HttpClient工具类自带打印】【时间{}毫秒】", url, result, endTime - startTime);
//        }
//        return result;
//    }
//}