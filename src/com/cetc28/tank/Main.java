package com.cetc28.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Auther: WSC
 * @Date: 2022/1/17 - 01 - 17 - 20:16
 * @Description: com.cetc28.tank
 * @version: 1.0
 */
public class Main {
    // 这是程序的main函数:入口函数
    public static void main(String[] args) {
        //启动坦克运行界面(view)
        TankFrame tf = new TankFrame();

        //BGM
        new Thread(()->{
            new Audio("audio/war1.wav").play();
        }).start();

        //每50ms刷新一次界面
        while (true){
            try {
                Thread.sleep(50);
                tf.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
