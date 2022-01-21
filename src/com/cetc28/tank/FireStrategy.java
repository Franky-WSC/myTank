package com.cetc28.tank;

import com.cetc28.tank.abstractfactory.BaseTank;

/**
 * @Auther: WSC
 * @Date: 2022/1/20 - 01 - 20 - 20:51
 * @Description: com.cetc28.tank
 * @version: 1.0
 */
public interface FireStrategy {
    //fire方法
    void fire(BaseTank t);
}
