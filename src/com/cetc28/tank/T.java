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
public class T {
    // 这是程序的main函数:入口函数
    public static void main(String[] args) {
        Frame f = new Frame();
        f.setSize(800,600);
        f.setResizable(false);
        f.setTitle("tank war");
        f.setVisible(true);
        //添加一个window事件监听器
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //当接收到一个windowClosing事件时, 调用这个函数
                System.exit(0);
            }
        });
    }
}
