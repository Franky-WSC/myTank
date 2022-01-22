package com.cetc28.tank.cor;

import com.cetc28.tank.*;

/**
 * @Auther: WSC
 * @Date: 2022/1/22 - 01 - 22 - 19:23
 * @Description: com.cetc28.tank.cor
 * @version: 1.0
 */
public class BulletWallCollider implements Collider {
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if(o1 instanceof Bullet && o2 instanceof Wall){
            Bullet b = (Bullet)o1;
            Wall w = (Wall)o2;
            if(b.getRect().intersects(w.getRect())){
                b.die();
            }
        }else if(o1 instanceof Wall && o2 instanceof Bullet){
            return collide(o2, o1);
        }
        return true;
    }
}
