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
    public static BufferedImage tankL, tankR, tankU, tankD;
    public static BufferedImage bulletL, bulletR, bulletU, bulletD;
    public static BufferedImage[] explores = new BufferedImage[16];
    static{
        try {
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            tankL = ImageUtil.rotateImage(tankU,-90);
            tankR = ImageUtil.rotateImage(tankU,90);
            tankD = ImageUtil.rotateImage(tankU,180);

            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletL = ImageUtil.rotateImage(bulletU,-90);
            bulletR = ImageUtil.rotateImage(bulletU,90);
            bulletD = ImageUtil.rotateImage(bulletU,180);

            for (int i = 0; i < 16; i++) {
                explores[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i+1) + ".gif"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
