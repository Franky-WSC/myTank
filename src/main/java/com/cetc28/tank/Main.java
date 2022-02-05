package com.cetc28.tank;

import com.cetc28.tank.net.Client;

/**
 * @Auther: WSC
 * @Date: 2022/1/17 - 01 - 17 - 20:16
 * @Description: com.cetc28.tank
 * @version: 1.0
 */
public class Main {
    // 这是程序的main函数:入口函数
    public static void main(String[] args) {
        TankFrame tf = TankFrame.INSTANCE;
        tf.setVisible(true);

//        new Thread(()->new Audio("audio/war1.wav").loop()).start();

        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tf.repaint();
            }
        }).start();

//        Client c = new Client();
        Client.INSTANCE.connect();
    }
}
