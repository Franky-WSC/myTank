package com.cetc28.tank.abstractfactory;

import com.cetc28.tank.Dir;
import com.cetc28.tank.Group;
import com.cetc28.tank.TankFrame;

/**
 * @Auther: WSC
 * @Date: 2022/1/21 - 01 - 21 - 10:57
 * @Description: com.cetc28.tank.abstractfactory
 * @version: 1.0
 */
public abstract class GameFactory {
    public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tankFrame);
    public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tankFrame);
    public abstract BaseExplode createExplode(int x, int y, TankFrame tankFrame);
}
