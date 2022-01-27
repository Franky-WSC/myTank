package com.cetc28.tank;

import java.awt.*;
import java.io.Serializable;

/**
 * @Auther: WSC
 * @Date: 2022/1/22 - 01 - 22 - 23:02
 * @Description: com.cetc28.tank
 * @version: 1.0
 */
public class Wall extends GameObject implements Serializable{
    private Rectangle rect;
    private int w,h;

    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        this.rect = new Rectangle(x,y,w,h);
    }

    @Override
    public int getWidth() {
        return w;
    }

    @Override
    public int getHeight() {
        return h;
    }

    public Rectangle getRect() {
        return rect;
    }

    public void setRect(Rectangle rect) {
        this.rect = rect;
    }
    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GRAY);
        g.fillRect(x, y, w, h);
        g.setColor(c);
    }
}
