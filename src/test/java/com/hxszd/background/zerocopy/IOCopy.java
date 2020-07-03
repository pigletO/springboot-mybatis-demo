package com.hxszd.background.zerocopy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @description: 普通io拷贝
 * @author: pig1etO
 * @create: 2020-06-24 14:27
 **/
public class IOCopy {

    public static void main(String[] args) {

        try {
            long start = System.currentTimeMillis();
            RandomAccessFile randomAccessFile = new RandomAccessFile("F:\\installPage\\Jetbrains\\ideaIU-2020.1.exe", "r");
            byte[] bytes = new byte[(int)randomAccessFile.length()];
            randomAccessFile.read(bytes);
            RandomAccessFile randomAccessFile1 = new RandomAccessFile("F:\\installPage\\Jetbrains\\ideaIU-2020.1.exe2", "rw");

            randomAccessFile1.write(bytes);
            System.out.println("用时" + (System.currentTimeMillis() - start) + "ms");

            randomAccessFile.close();
            randomAccessFile1.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
