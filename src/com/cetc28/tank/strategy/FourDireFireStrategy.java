package com.cetc28.tank.strategy;

import com.cetc28.tank.*;

/**
 * @Auther: WSC
 * @Date: 2022/1/20 - 01 - 20 - 21:28
 * @Description: com.cetc28.tank
 * @version: 1.0
 */
public class FourDireFireStrategy implements FireStrategy {
    private static final FourDireFireStrategy INSTANCE;
    static {
        INSTANCE = new FourDireFireStrategy();
    }
    private FourDireFireStrategy(){}
    public static FourDireFireStrategy getInstance(){
        return INSTANCE;
    }
    @Override
    public void fire(Tank t) {
        int bX = t.getX() + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = t.getY() + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        Dir[] dir = Dir.values();
        for(Dir d : dir){
            new Bullet(bX,bY,d, t.getGroup(), t.getGm());
        }
//        if(t.getGroup() == Group.GOOD){
//            new Thread(()->{
//                new Audio("audio/tank_fire.wav").play();
//            }).start();
//        }
    }
}
