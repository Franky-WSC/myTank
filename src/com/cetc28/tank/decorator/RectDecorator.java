package com.cetc28.tank.decorator;

import com.cetc28.tank.GameObject;

import java.awt.*;
import java.io.Serializable;

/**
 * @Auther: WSC
 * @Date: 2022/1/23 - 01 - 23 - 9:50
 * @Description: com.cetc28.tank.decorator
 * @version: 1.0
 */
public class RectDecorator extends GODecorator implements Serializable {

    public RectDecorator(GameObject go) {
        super(go);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        setX(go.getX());
        setY(go.getY());

        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawRect(go.getX(),go.getY(),go.getWidth(),go.getHeight());
        g.setColor(c);
    }
    @Override
    public int getWidth() {
        return go.getWidth();
    }
    @Override
    public int getHeight() {
        return go.getHeight();
    }
}
