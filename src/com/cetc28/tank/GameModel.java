package com.cetc28.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: WSC
 * @Date: 2022/1/22 - 01 - 22 - 14:16
 * @Description: com.cetc28.tank
 * @version: 1.0
 */
public class GameModel {
    //主战坦克
    Tank myTank = null;
    //子弹容器
    java.util.List<Bullet> bullets = new ArrayList<>();
    //敌方坦克
    java.util.List<Tank> tanks = new ArrayList<>();
    //爆炸对象
    List<Explode> explodes = new ArrayList<>();

    public GameModel() {
        //初始化主战坦克
        myTank = new Tank(200,200,Dir.DOWN, Group.GOOD,this);
        //读取配置文件
        int tankCount = PropertyMgr.getInt("initTankCount");
        //初始化敌方坦克
        for (int i = 0; i < tankCount; i++) {
            tanks.add(new Tank(50+i*100,400,Dir.DOWN, Group.BAD, this));
        }
    }

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

    public Tank getMyTank() {
        return myTank;
    }
}
