package com.cetc28.tank.abstractfactory;

import com.cetc28.tank.Dir;
import com.cetc28.tank.FireStrategy;
import com.cetc28.tank.Group;
import com.cetc28.tank.TankFrame;

import java.awt.*;

/**
 * @Auther: WSC
 * @Date: 2022/1/21 - 01 - 21 - 10:58
 * @Description: com.cetc28.tank.abstractfactory
 * @version: 1.0
 */
public abstract class BaseTank {
    public abstract void paint(Graphics g);
    public abstract void fire(FireStrategy fs);
    public abstract void setbMoving(boolean bMoving);
    public abstract void setDir(Dir dir);
    public abstract int getX();
    public abstract int getY();
    public abstract TankFrame getTf();
    public abstract Group getGroup();
    public abstract Dir getDir();
}
