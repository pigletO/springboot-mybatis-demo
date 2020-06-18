package com.hxszd.background.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.channels.FileChannel;

/**
 * @description:
 * @author: pig1etO
 * @create: 2020-06-17 14:27
 **/
public class CopyFiles {

    public static void main(String[] args) {
        FileChannel inputFileChannel = null;
        FileChannel ouputFileChannel = null;
        try {
            inputFileChannel = new FileInputStream("H:\\a.txt").getChannel();
            ouputFileChannel = new FileOutputStream("H:\\c.txt").getChannel();
            try {
                inputFileChannel.transferTo(0, inputFileChannel.size(), ouputFileChannel);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                inputFileChannel.close();
                ouputFileChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
