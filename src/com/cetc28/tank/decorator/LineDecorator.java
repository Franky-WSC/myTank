package com.cetc28.tank.decorator;

import com.cetc28.tank.GameObject;

import java.awt.*;
import java.io.Serializable;

/**
 * @Auther: WSC
 * @Date: 2022/1/23 - 01 - 23 - 14:25
 * @Description: com.cetc28.tank.decorator
 * @version: 1.0
 */
public class LineDecorator extends GODecorator implements Serializable {
    public LineDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        setX(go.getX());
        setY(go.getY());

        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawLine(go.getX(), go.getY(), go.getX() + go.getWidth(), go.getY() + go.getHeight());
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
