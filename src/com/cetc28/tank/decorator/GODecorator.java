package com.cetc28.tank.decorator;

import com.cetc28.tank.GameObject;

import java.awt.*;

/**
 * @Auther: WSC
 * @Date: 2022/1/23 - 01 - 23 - 9:45
 * @Description: com.cetc28.tank.decorator
 * @version: 1.0
 */
public abstract class GODecorator extends GameObject {
    GameObject go;

    public GODecorator(GameObject go) {
        this.go = go;
    }

    @Override
    public void paint(Graphics g) {
        go.paint(g);
    }

}
