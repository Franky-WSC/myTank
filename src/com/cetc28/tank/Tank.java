package com.cetc28.tank;

import java.awt.*;
import java.util.Random;

/**
 * @Auther: WSC
 * @Date: 2022/1/18 - 01 - 18 - 13:31
 * @Description: com.cetc28.tank
 * @version: 1.0
 */
public class Tank {
    private int x,y;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = PropertyMgr.getInt("tankSpeed");
    public static int WIDTH = ResourceMgr.getInstance().goodTankL.getWidth();
    public static int HEIGHT = ResourceMgr.getInstance().goodTankL.getHeight();
    private boolean bMoving = true;
    private TankFrame tf;
    private boolean bLiving = true;
    private Random random = new Random();
    private Group group = Group.BAD;
    Rectangle rect = new Rectangle();
//    Audio audio = new Audio("audio/explode.wav");

    public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        rect.x = this.x;
        rect.y = this.y;
        rect.width = Tank.WIDTH;
        rect.height = Tank.HEIGHT;
    }

    public boolean isbMoving() {
        return bMoving;
    }

    public void setbMoving(boolean bMoving) {
        this.bMoving = bMoving;
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

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public TankFrame getTf() {
        return tf;
    }

    public void setTf(TankFrame tf) {
        this.tf = tf;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void paint(Graphics g){
        if(!bLiving){
            this.tf.tanks.remove(this);
        }
        switch(dir){
            case LEFT:
                g.drawImage(this.getGroup() == Group.GOOD ? ResourceMgr.getInstance().goodTankL : ResourceMgr.getInstance().badTankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(this.getGroup() == Group.GOOD ? ResourceMgr.getInstance().goodTankR : ResourceMgr.getInstance().badTankR,x,y,null);
                break;
            case UP:
                g.drawImage(this.getGroup() == Group.GOOD ? ResourceMgr.getInstance().goodTankU : ResourceMgr.getInstance().badTankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(this.getGroup() == Group.GOOD ? ResourceMgr.getInstance().goodTankD : ResourceMgr.getInstance().badTankD,x,y,null);
                break;
            default:
                break;
        }
        move();
    }

    private void move(){
        if(!bMoving){
            return;
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
        //敌方坦克自主发射子弹
        if(this.group == Group.BAD && random.nextInt(10) > 8){
            this.fire();
        }
        //敌方坦克自主改变方向
        if(this.group == Group.BAD && random.nextInt(100) > 95){
            randomDir();
        }
        //边界检测
        boundsCheck();

        rect.x = this.x;
        rect.y = this.y;
    }

    private void boundsCheck() {
        if(this.getX() < 0) x = 0;
        else if(this.getY() < 30) y = 30;
        else if(this.getX() > TankFrame.GAME_WIDTH - Tank.WIDTH) x = TankFrame.GAME_WIDTH - Tank.WIDTH;
        else if(this.getY() > TankFrame.GAME_HEIGHT - Tank.HEIGHT) y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
    }

    private void randomDir() {
        setDir(Dir.values()[random.nextInt(4)]);
    }

    public void fire() {
        int bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tf.bullets.add(new Bullet(bX,bY,this.dir, this.group, tf));
    }

    public void die() {
        this.bLiving = false;
    }
}
