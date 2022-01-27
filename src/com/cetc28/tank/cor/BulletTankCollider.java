package com.cetc28.tank.cor;

import com.cetc28.tank.*;

import java.io.Serializable;

/**
 * @Auther: WSC
 * @Date: 2022/1/22 - 01 - 22 - 19:23
 * @Description: com.cetc28.tank.cor
 * @version: 1.0
 */
public class BulletTankCollider implements Collider, Serializable {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Tank){
            Bullet b = (Bullet)o1;
            Tank t = (Tank)o2;
            if(b.getGroup() == t.getGroup()){
                return true;
            }
            if(b.getRect().intersects(t.getRect())){
                b.die();
                t.die();
                //碰撞检测, 爆炸
                int eX = t.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
                int eY = t.getY() + Tank.WIDTH / 2 - Explode.HEIGHT / 2;
                GameModel.getInstance().add(new Explode(eX, eY));
                //发出声音
//                new Thread(()->{
//                    new Audio("audio/explode.wav").play();
//                }).start();
                return false;
            }
        }else if(o1 instanceof Tank && o2 instanceof Bullet){
            return collide(o2, o1);
        }
        return true;
    }
}
