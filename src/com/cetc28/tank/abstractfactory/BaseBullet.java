package com.cetc28.tank.abstractfactory;

import com.cetc28.tank.Tank;

import java.awt.*;

/**
 * @Auther: WSC
 * @Date: 2022/1/21 - 01 - 21 - 10:59
 * @Description: com.cetc28.tank.abstractfactory
 * @version: 1.0
 */
public abstract class BaseBullet {
    public abstract void paint(Graphics g);
    public abstract void collideWith(Tank tank);
}
