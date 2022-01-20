package com.cetc28.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Auther: WSC
 * @Date: 2022/1/18 - 01 - 18 - 19:59
 * @Description: com.cetc28.tank
 * @version: 1.0
 */
public class ResourceMgr {
    // 单例模式
    private ResourceMgr(){
        try {
            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            goodTankL = ImageUtil.rotateImage(goodTankU,-90);
            goodTankR = ImageUtil.rotateImage(goodTankU,90);
            goodTankD = ImageUtil.rotateImage(goodTankU,180);

            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank2.png"));
            badTankL = ImageUtil.rotateImage(badTankU,-90);
            badTankR = ImageUtil.rotateImage(badTankU,90);
            badTankD = ImageUtil.rotateImage(badTankU,180);

            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU,-90);
            bulletR = ImageUtil.rotateImage(bulletU,90);
            bulletD = ImageUtil.rotateImage(bulletU,180);

            for (int i = 0; i < 16; i++) {
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i+1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static final ResourceMgr INSTANCE;
    static{
        INSTANCE = new ResourceMgr();
    }
    public static ResourceMgr getInstance(){
        return INSTANCE;
    }
    public BufferedImage goodTankL, goodTankR, goodTankU, goodTankD;
    public BufferedImage badTankL, badTankR, badTankU, badTankD;
    public BufferedImage bulletL, bulletR, bulletU, bulletD;
    public BufferedImage[] explodes = new BufferedImage[16];

}
