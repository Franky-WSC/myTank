package com.cetc28.tank;

import com.cetc28.tank.abstractfactory.BaseBullet;

import java.awt.*;

/**
 * @Auther: WSC
 * @Date: 2022/1/21 - 01 - 21 - 20:27
 * @Description: com.cetc28.tank
 * @version: 1.0
 */
public class RectBullet extends BaseBullet {
    private static final int SPEED = PropertyMgr.getInt("bulletSpeed");
    public static int WIDTH = ResourceMgr.getInstance().bulletL.getWidth();
    public static int HEIGHT = ResourceMgr.getInstance().bulletL.getHeight();
    private int x, y;
    private Dir dir;
    private boolean bLive = true;
    private TankFrame tf;
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

    public RectBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.tf = tf;

        tf.bullets.add(this);

        rect.x = this.x;
        rect.y = this.y;
        rect.width = Bullet.WIDTH;
        rect.height = Bullet.HEIGHT;
    }

    @Override
    public void paint(Graphics g){
        if(!bLive){
            tf.bullets.remove(this);
        }
        Color c = g.getColor();
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 5, 5);
        g.setColor(c);
        move();
    }

    private void move(){
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
//            tf.explodes.add(new Explode(eX,eY,tf));
            tf.explodes.add(tf.gf.createExplode(eX, eY, tf));// 工厂模式运用
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
