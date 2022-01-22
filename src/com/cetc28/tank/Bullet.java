package com.cetc28.tank;

import java.awt.*;

/**
 * @Auther: WSC
 * @Date: 2022/1/18 - 01 - 18 - 15:44
 * @Description: com.cetc28.tank
 * @version: 1.0
 */
public class Bullet {
    private static final int SPEED = PropertyMgr.getInt("bulletSpeed");
    public static int WIDTH = ResourceMgr.getInstance().bulletL.getWidth();
    public static int HEIGHT = ResourceMgr.getInstance().bulletL.getHeight();
    private int x, y;
    private Dir dir;
    private boolean bLive = true;
    private GameModel gm;
    private Group group = Group.BAD;
    Rectangle rect = new Rectangle();

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

    public Bullet(int x, int y, Dir dir, Group group, GameModel gm) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gm = gm;

        gm.bullets.add(this);

        rect.x = this.x;
        rect.y = this.y;
        rect.width = Bullet.WIDTH;
        rect.height = Bullet.HEIGHT;
    }

    public void paint(Graphics g){
        switch(dir){
            case LEFT:
                g.drawImage(ResourceMgr.getInstance().bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.getInstance().bulletR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceMgr.getInstance().bulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.getInstance().bulletD,x,y,null);
                break;
            default:
                break;
        }
        move();
    }

    private void move(){
        if(!bLive){
            gm.bullets.remove(this);
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
        rect.x = this.x;
        rect.y = this.y;
    }

    public void collideWith(Tank tank) {
        if(this.group == tank.getGroup()){
            return;
        }
        if(rect.intersects(tank.rect)){
            tank.die();
            this.die();
            //碰撞检测, 爆炸
            int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            gm.explodes.add(new Explode(eX,eY, gm));
            //发出声音
            new Thread(()->{
                new Audio("audio/explode.wav").play();
            }).start();
        }
    }

    private void die() {
        this.bLive = false;
    }
}
