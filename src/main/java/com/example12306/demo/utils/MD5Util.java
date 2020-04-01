package com.example12306.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 * Created by jay on 2016-10-19.
 */
@Slf4j
public class MD5Util {

    private final static String[] hexDigits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
            "e", "f"};

    /**
     * 转换字节数组为16进制字串
     *
     * @param b 字节数组
     * @return 16进制字串
     */

    public static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin) throws Exception {
        String resultString ;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
        } catch (Exception ex) {
            log.error("MD5加密失败！{}", ex);
            throw ex;
        }
        return resultString;
    }
    
    public static String MD5(String string) {

    	byte[] hash;  
    		try {  
	           hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));  
    		} catch (NoSuchAlgorithmException e) {  
	           throw new RuntimeException("Huh, MD5 should be supported?", e);  
    		} catch (UnsupportedEncodingException e) {  
	           throw new RuntimeException("Huh, UTF-8 should be supported?", e);  
	       }  
	 
	       StringBuilder hex = new StringBuilder(hash.length * 2);  
	       for (byte b : hash) {  
	           if ((b & 0xFF) < 0x10) hex.append("0");  
	           hex.append(Integer.toHexString(b & 0xFF));  
	       }  
	       return hex.toString();  

	}

    public static String MD5Encode(String origin, String salt) throws Exception {
        return MD5Encode(MD5Encode(origin) + salt);
    }
}
