package com.cetc28.tank;

import com.cetc28.tank.abstractfactory.BaseBullet;
import com.cetc28.tank.abstractfactory.BaseExplode;
import com.cetc28.tank.abstractfactory.BaseTank;
import com.cetc28.tank.abstractfactory.GameFactory;

/**
 * @Auther: WSC
 * @Date: 2022/1/21 - 01 - 21 - 20:07
 * @Description: com.cetc28.tank
 * @version: 1.0
 */
public class RectGameFactory extends GameFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new RectTank(x, y, dir, group, tankFrame);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame) {
        return new RectBullet(x, y, dir, group, tankFrame);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new RectExplode(x, y, tankFrame);
    }
}
