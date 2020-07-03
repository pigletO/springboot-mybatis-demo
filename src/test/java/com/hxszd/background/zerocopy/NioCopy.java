package com.hxszd.background.zerocopy;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * @description: nio零拷贝
 * @author: pig1etO
 * @create: 2020-06-24 15:46
 **/
public class NioCopy {

    public static void main(String[] args) {
        try {
            long start = System.currentTimeMillis();
            RandomAccessFile randomAccessFile = new RandomAccessFile("F:\\installPage\\Jetbrains\\ideaIU-2020.1.exe", "r");
            FileChannel fileChannel = randomAccessFile.getChannel();

            RandomAccessFile randomAccessFile1 = new RandomAccessFile("F:\\installPage\\Jetbrains\\ideaIU-2020.1.exe22", "rw");
            fileChannel.transferTo(0, fileChannel.size(), randomAccessFile1.getChannel());
            System.out.println(System.currentTimeMillis() - start + "ms");
            fileChannel.close();
            randomAccessFile.close();
            randomAccessFile1.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
