package com.cetc28.tank;

import com.cetc28.tank.cor.BulletTankCollider;
import com.cetc28.tank.cor.Collider;
import com.cetc28.tank.cor.ColliderChain;
import com.cetc28.tank.cor.TankTankCollider;
import com.cetc28.tank.decorator.CircleDecorator;
import com.cetc28.tank.decorator.GODecorator;
import com.cetc28.tank.decorator.LineDecorator;
import com.cetc28.tank.decorator.RectDecorator;

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
    private static final GameModel INSTANCE;
    static{
        INSTANCE = new GameModel();
    }
    public static GameModel getInstance(){
        return INSTANCE;
    }
    //主战坦克
    Tank myTank = null;
    //装饰器
    GODecorator decorator = null;
    //其他所有对象
    private List<GameObject> objects = new ArrayList<>();
    //碰撞检测的责任链
    Collider chains = new ColliderChain();

    public void add(GameObject go){
        objects.add(go);
    }

    public void remove(GameObject go){
        objects.remove(go);
    }

    private GameModel() {
        //初始化主战坦克
        myTank = new Tank(200,200,Dir.DOWN, Group.GOOD);
        //装饰器
        decorator = new RectDecorator(new LineDecorator(new CircleDecorator(myTank)));

        //读取配置文件
        int tankCount = PropertyMgr.getInt("initTankCount");
        //初始化敌方坦克
        for (int i = 0; i < tankCount; i++) {
            objects.add(new Tank(50+i*100,600,Dir.DOWN, Group.BAD));
        }
        //初始化墙
        add(new Wall(150,150,200,50));
        add(new Wall(550,150,200,50));
        add(new Wall(300,300,50,200));
        add(new Wall(550,300,50,200));
    }

    public void paint(Graphics g) {
        //画当前屏幕中子弹的数量
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("物体的数量: " + objects.size(), 10,50);
        g.setColor(c);
        //画出主战坦克
//        myTank.paint(g);
        //装饰器
        decorator.paint(g);
        //画出所有物品
        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }
        //碰撞检测
        for (int i = 0; i < objects.size(); i++) {
            for(int j = i+1; j < objects.size(); j++){
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
                //碰撞检测-责任链
                chains.collide(o1, o2);
            }
        }
    }

    public Tank getMyTank() {
        return myTank;
    }
}
