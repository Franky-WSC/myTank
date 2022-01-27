package com.cetc28.tank.cor;

import com.cetc28.tank.GameObject;
import com.cetc28.tank.Tank;
import com.cetc28.tank.Wall;

import java.io.Serializable;

/**
 * @Auther: WSC
 * @Date: 2022/1/22 - 01 - 22 - 19:46
 * @Description: com.cetc28.tank.cor
 * @version: 1.0
 */
public class TankWallCollider implements Collider, Serializable {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Wall){
            Tank t = (Tank) o1;
            Wall w = (Wall) o2;
            if(t.getRect().intersects(w.getRect())){
                t.setOppositeDir();
            }
        }else if(o1 instanceof Wall && o2 instanceof Tank){
            collide(o2, o1);
        }
        return true;
    }
}
