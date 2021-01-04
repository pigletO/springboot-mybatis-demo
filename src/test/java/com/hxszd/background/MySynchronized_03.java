package com.hxszd.background;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 锁定的对象 this 静态锁 class
 */
public class MySynchronized_03 {

    public synchronized void m1() throws InterruptedException {
        String name = Thread.currentThread().getName();
        System.out.println(name + " 启动");
        for (int i = 0; i < 5; i++) {
            System.out.println(name + " " + i);
            TimeUnit.SECONDS.sleep(2);
        }
        System.out.println(name + " 结束");
    }

    public void m2() throws InterruptedException {
        String name = Thread.currentThread().getName();
        System.out.println(name + " 启动");
        TimeUnit.SECONDS.sleep(2);
        System.out.println(name + " 结束");
    }

    public static void main(String[] args) {
        MySynchronized_03 mc = new MySynchronized_03();
        new Thread(()-> {
            try {
                mc.m1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(()-> {
            try {
                mc.m1();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }


}
