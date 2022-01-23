package com.cetc28.tank.strategy;

import com.cetc28.tank.*;
import com.cetc28.tank.decorator.CircleDecorator;
import com.cetc28.tank.decorator.LineDecorator;
import com.cetc28.tank.decorator.RectDecorator;

/**
 * @Auther: WSC
 * @Date: 2022/1/20 - 01 - 20 - 20:51
 * @Description: com.cetc28.tank
 * @version: 1.0
 */
public class DefaultFireStrategy implements FireStrategy {
    private static final DefaultFireStrategy INSTANCE;
    static {
        INSTANCE = new DefaultFireStrategy();
    }
    private DefaultFireStrategy(){}
    public static DefaultFireStrategy getInstance(){
        return INSTANCE;
    }
    @Override
    public void fire(Tank t) {
        int bX = t.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = t.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        new Bullet(bX,bY,t.getDir(), t.getGroup());
//        GameModel.getInstance().add(new RectDecorator(new LineDecorator(new CircleDecorator(new Bullet(bX,bY,t.getDir(), t.getGroup())))));
        //声音
//        if(t.getGroup() == Group.GOOD){
//            new Thread(()->{
//                new Audio("audio/tank_fire.wav").play();
//            }).start();
//        }
    }
}
