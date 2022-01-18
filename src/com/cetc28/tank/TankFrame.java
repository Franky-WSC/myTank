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

    Tank myTank1 = new Tank(200,200,Dir.DOWN);

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
        //四个boolean值, 添加逻辑判断, 最终判断坦克前行方向, 进而行走
        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();//取出按键代码
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();//取出按键代码
            switch (key){
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if(bL) myTank1.setDir(Dir.LEFT);
            if(bR) myTank1.setDir(Dir.RIGHT);
            if(bU) myTank1.setDir(Dir.UP);
            if(bD) myTank1.setDir(Dir.DOWN);
        }
    }
    @Override
    public void paint(Graphics g) {
        myTank1.paint(g);
    }
}
