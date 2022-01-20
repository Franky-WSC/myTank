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
        TankFrame tf = new TankFrame();
        //读取配置文件
        int tankCount = Integer.parseInt((String)PropertyMgr.get("initTankCount"));
        //初始化敌方坦克
        for (int i = 0; i < tankCount; i++) {
            tf.tanks.add(new Tank(50+i*100,400,Dir.DOWN, Group.BAD, tf));
        }

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
