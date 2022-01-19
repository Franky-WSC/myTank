package com.cetc28.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

/**
 * @Auther: WSC
 * @Date: 2022/1/17 - 01 - 17 - 21:41
 * @Description: com.cetc28.tank
 * @version: 1.0
 */
public class TankFrame extends Frame {
    //主战坦克
    Tank myTank1 = new Tank(200,200,Dir.DOWN, Group.GOOD,this);
    //子弹容器
    List<Bullet> bullets = new ArrayList<>();
    //敌方坦克
    List<Tank> tanks = new ArrayList<>();
    //爆炸对象
    Explore e = new Explore(300, 300, this);
    //屏幕宽度 高度
    static final int GAME_WIDTH = 1000, GAME_HEIGHT = 800;

    //构造函数
    public TankFrame() throws HeadlessException {
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        //添加一个window事件监听器
        addWindowListener(new WindowAdapter() {
            //当接收到一个windowClosing事件时, 调用这个函数
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //添加一个Key事件监听器
        addKeyListener(new MyKeyListener());
    }
    //内部类
    class MyKeyListener extends KeyAdapter{
        //四个boolean值, 添加逻辑判断, 最终判断坦克前行方向, 进而行走
        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;
        //当键盘按键被按下时的操作
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
        //当键盘按键被松开时的操作
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
                case KeyEvent.VK_CONTROL:
                    myTank1.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }
        //设置主站坦克的运行方向
        private void setMainTankDir() {
            if(!bL && !bR && !bU && !bD){
                myTank1.setbMoving(false);
            }else{
                myTank1.setbMoving(true);
                if(bL) myTank1.setDir(Dir.LEFT);
                if(bR) myTank1.setDir(Dir.RIGHT);
                if(bU) myTank1.setDir(Dir.UP);
                if(bD) myTank1.setDir(Dir.DOWN);
            }
        }
    }
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        //画当前屏幕中子弹的数量
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawString("子弹的数量: " + bullets.size(), 100,100);
        g.drawString("敌方坦克的数量: " + tanks.size(), 100,120);
        g.setColor(c);
        //画出主战坦克
        myTank1.paint(g);
        //画出子弹
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        //画出敌方坦克
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        //碰撞检测
        for (int i = 0; i < bullets.size(); i++) {
            for(int j = 0; j < tanks.size(); j++){
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
        //爆炸
        e.paint(g);
    }
}
