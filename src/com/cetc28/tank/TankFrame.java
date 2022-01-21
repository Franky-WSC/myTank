package com.cetc28.tank;

import com.cetc28.tank.abstractfactory.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: WSC
 * @Date: 2022/1/17 - 01 - 17 - 21:41
 * @Description: com.cetc28.tank
 * @version: 1.0
 */
public class TankFrame extends Frame {
    //抽象工厂
//    GameFactory gf = new DefaultGameFactory();//默认工厂
    GameFactory gf = new RectGameFactory();//方形工厂
    //主战坦克
//    Tank myTank = new Tank(200,200,Dir.DOWN, Group.GOOD,this);
    BaseTank myTank = gf.createTank(200, 200, Dir.DOWN, Group.GOOD, this);
    //子弹容器
    List<BaseBullet> bullets = new ArrayList<>();
    //敌方坦克
    List<Tank> tanks = new ArrayList<>();
    //爆炸对象
    List<BaseExplode> explodes = new ArrayList<>();
    //屏幕宽度 高度
    static final int GAME_WIDTH = PropertyMgr.getInt("gameWidth"), GAME_HEIGHT = PropertyMgr.getInt("gameHeight");

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
                    myTank.fire(FourDireFireStrategy.getInstance());
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }
        //设置主站坦克的运行方向
        private void setMainTankDir() {
            if(!bL && !bR && !bU && !bD){
                myTank.setbMoving(false);
            }else{
                myTank.setbMoving(true);
                if(bL) myTank.setDir(Dir.LEFT);
                if(bR) myTank.setDir(Dir.RIGHT);
                if(bU) myTank.setDir(Dir.UP);
                if(bD) myTank.setDir(Dir.DOWN);
            }
        }
    }

    //双缓冲: 解决游戏界面闪烁问题
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
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量: " + bullets.size(), 10,50);
        g.drawString("敌方坦克的数量: " + tanks.size(), 10,70);
        g.drawString("爆炸的数量: " + explodes.size(), 10,90);
        g.setColor(c);
        //画出主战坦克
        myTank.paint(g);
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
        //画出爆炸
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
    }
}
