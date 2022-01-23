package com.cetc28.tank;

import java.awt.*;

/**
 * @Auther: WSC
 * @Date: 2022/1/22 - 01 - 22 - 17:04
 * @Description: com.cetc28.tank
 * @version: 1.0
 */
public abstract class GameObject {
    protected int x, y;
    public abstract void paint(Graphics g);
    public abstract int getWidth();
    public abstract int getHeight();
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
}
