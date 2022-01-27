package com.cetc28.tank.cor;

import com.cetc28.tank.GameObject;
import com.cetc28.tank.Tank;

import java.io.Serializable;

/**
 * @Auther: WSC
 * @Date: 2022/1/22 - 01 - 22 - 19:46
 * @Description: com.cetc28.tank.cor
 * @version: 1.0
 */
public class TankTankCollider implements Collider, Serializable {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Tank && o2 instanceof Tank){
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if(t1.getRect().intersects(t2.getRect())){
                t1.setOppositeDir();
                t2.setOppositeDir();
            }
        }
        return true;
    }
}
