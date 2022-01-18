package com.cetc28.tank;

import java.awt.*;

/**
 * @Auther: WSC
 * @Date: 2022/1/18 - 01 - 18 - 15:44
 * @Description: com.cetc28.tank
 * @version: 1.0
 */
public class Bullet {
    private static final int SPEED = 20;
    public static int WIDTH = ResourceMgr.bulletL.getWidth();
    public static int HEIGHT = ResourceMgr.bulletL.getHeight();
    private int x, y;
    private Dir dir;
    private boolean bLive = true;
    private TankFrame tf;

    public boolean isbLive() {
        return bLive;
    }

    public void setbLive(boolean bLive) {
        this.bLive = bLive;
    }

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g){
//        Color c = g.getColor();
//        g.setColor(Color.red);
//        g.fillOval(x,y,WIDTH,HEIGHT);
//        g.setColor(c);
        switch(dir){
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
            default:
                break;
        }
        move();
    }

    private void move(){
        if(!bLive){
            tf.bullets.remove(this);
        }
        switch(dir){
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            setbLive(false);
        }
    }

    public void collideWith(Tank tank) {
        Rectangle rect1 = new Rectangle(this.x, this.y, Bullet.WIDTH, Bullet.HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH,Tank.HEIGHT);
        if(rect1.intersects(rect2)){
            tank.die();
            this.die();
        }
    }

    private void die() {
        this.bLive = false;
    }
}
