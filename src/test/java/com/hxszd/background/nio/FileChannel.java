package com.hxszd.background.nio;

import io.netty.util.CharsetUtil;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-06-17 13:59
 **/
public class FileChannel {

    public static void main(String[] args) {

        java.nio.channels.FileChannel fileChannel = null;
        java.nio.channels.FileChannel outFileChannel = null;
        try {

            fileChannel = new FileInputStream("H:\\a.txt").getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            try {
                fileChannel.read(byteBuffer);
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println(new String(byteBuffer.array(), CharsetUtil.UTF_8));


            outFileChannel = new FileOutputStream("H:\\b.txt").getChannel();

            try {
                outFileChannel.write(ByteBuffer.wrap("123456".getBytes(CharsetUtil.UTF_8)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                fileChannel.close();
                outFileChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
