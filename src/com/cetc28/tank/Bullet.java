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
    private Group group = Group.BAD;

    public boolean isbLive() {
        return bLive;
    }

    public void setbLive(boolean bLive) {
        this.bLive = bLive;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;
    }

    public void paint(Graphics g){
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
        if(this.group == tank.getGroup()){
            return;
        }
        //用一个rect来记录子弹/坦克的位置 : 子弹类和坦克类各自保留一个rect
        Rectangle rect1 = new Rectangle(this.x, this.y, Bullet.WIDTH, Bullet.HEIGHT);
        Rectangle rect2 = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH,Tank.HEIGHT);
        if(rect1.intersects(rect2)){
            tank.die();
            this.die();
            //碰撞检测, 爆炸
            int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            tf.explodes.add(new Explode(eX,eY,tf));
        }
    }

    private void die() {
        this.bLive = false;
    }
}
