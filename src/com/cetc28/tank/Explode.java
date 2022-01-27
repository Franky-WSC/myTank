package com.cetc28.tank;

import java.awt.*;
import java.io.Serializable;

/**
 * @Auther: WSC
 * @Date: 2022/1/19 - 01 - 19 - 11:53
 * @Description: com.cetc28.tank
 * @version: 1.0
 */
public class Explode extends GameObject implements Serializable{
    public static int WIDTH = ResourceMgr.getInstance().explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.getInstance().explodes[0].getHeight();
    private boolean bLiving = true;
    private int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }



    public void paint(Graphics g){
        g.drawImage(ResourceMgr.getInstance().explodes[step++],x,y,null);
        if(step >= ResourceMgr.getInstance().explodes.length){
            GameModel.getInstance().remove(this);
        }
    }

    public void die() {
        this.bLiving = false;
    }
}
