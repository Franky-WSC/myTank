package com.cetc28.tank;

import com.cetc28.tank.abstractfactory.BaseExplode;

import java.awt.*;

/**
 * @Auther: WSC
 * @Date: 2022/1/21 - 01 - 21 - 20:05
 * @Description: com.cetc28.tank
 * @version: 1.0
 */
public class RectExplode extends BaseExplode {
    private int x,y;
    public static int WIDTH = ResourceMgr.getInstance().explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.getInstance().explodes[0].getHeight();

    private TankFrame tf = null;
    private boolean bLiving = true;
    private int step = 0;

    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
    }

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

    public TankFrame getTf() {
        return tf;
    }

    public void setTf(TankFrame tf) {
        this.tf = tf;
    }

    @Override
    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillRect(x, y, step*20, step*20);
        g.setColor(c);
        step++;
        if(step >= 5){
            tf.explodes.remove(this);
        }
    }

    public void die() {
        this.bLiving = false;
    }
}
