package com.hxszd.background.utils;

import java.io.*;

/**
 * @description: 将指定文件读取成字符串
 * @author: pig1etO
 * @create: 2020-04-24 16:00
 **/
public class ReadFileToString {

    public static void main(String[] args) throws IOException {
        String filePath = "H:\\userid.txt";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filePath))));
        //BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = br.readLine()) != null) {
            sb.append(line + ",");
        }
        sb.deleteCharAt(sb.length() - 1);

        System.out.println(sb.toString());
    }
}
