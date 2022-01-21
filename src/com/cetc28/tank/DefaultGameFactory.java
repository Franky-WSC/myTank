package com.cetc28.tank;

import com.cetc28.tank.*;
import com.cetc28.tank.abstractfactory.BaseBullet;
import com.cetc28.tank.abstractfactory.BaseExplode;
import com.cetc28.tank.abstractfactory.BaseTank;
import com.cetc28.tank.abstractfactory.GameFactory;

/**
 * @Auther: WSC
 * @Date: 2022/1/21 - 01 - 21 - 11:03
 * @Description: com.cetc28.tank.abstractfactory
 * @version: 1.0
 */
public class DefaultGameFactory extends GameFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new Tank(x, y, dir, group, tankFrame);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new Bullet(x, y, dir, group, tankFrame);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new Explode(x, y, tankFrame);
    }
}
