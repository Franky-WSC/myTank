package com.cetc28.tank.cor;

import com.cetc28.tank.GameObject;

/**
 * @Auther: WSC
 * @Date: 2022/1/22 - 01 - 22 - 17:52
 * @Description: com.cetc28.tank.cor
 * @version: 1.0
 */
public interface Collider {
    //false: 不让链条继续执行; true: 链条继续执行
    boolean collide(GameObject o1, GameObject o2);
}
