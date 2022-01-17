package com.cetc28.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Auther: WSC
 * @Date: 2022/1/17 - 01 - 17 - 21:41
 * @Description: com.cetc28.tank
 * @version: 1.0
 */
public class TankFrame extends Frame {
    int x = 200, y = 200;
    //构造函数
    public TankFrame() throws HeadlessException {
        setSize(800,600);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        //添加一个window事件监听器
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //当接收到一个windowClosing事件时, 调用这个函数
                System.exit(0);
            }
        });
        addKeyListener(new MyKeyListener());
    }
    //内部类
    class MyKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            x += 20;
            y += 20;
            //会默认调用一次paint方法
            //repaint();
        }
        @Override
        public void keyReleased(KeyEvent e) {
            //System.out.println("released");
        }
    }
    @Override
    public void paint(Graphics g) {
        g.fillRect(x,y,100,100);
        x += 20;
    }
}
