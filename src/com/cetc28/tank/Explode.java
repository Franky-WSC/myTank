package com.cetc28.tank;

import java.awt.*;
import java.util.Random;

/**
 * @Auther: WSC
 * @Date: 2022/1/19 - 01 - 19 - 11:53
 * @Description: com.cetc28.tank
 * @version: 1.0
 */
public class Explode {
    private int x,y;
    public static int WIDTH = ResourceMgr.explores[0].getWidth();
    public static int HEIGHT = ResourceMgr.explores[0].getHeight();

    private TankFrame tf = null;
    private boolean bLiving = true;
    private int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;

        //new Audio("audio/explode.wav").play();
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

    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explores[step++],x,y,null);
        if(step >= ResourceMgr.explores.length){
            tf.explodes.remove(this);
        }
    }

    public void die() {
        this.bLiving = false;
    }
}
