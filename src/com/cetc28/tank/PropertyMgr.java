package com.cetc28.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @Auther: WSC
 * @Date: 2022/1/20 - 01 - 20 - 14:54
 * @Description: com.cetc28.tank
 * @version: 1.0
 */
public class PropertyMgr {
    static Properties props = new Properties();
    static{
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object get(String key){
        if(props == null){
            return null;
        }
        return props.get(key);
    }

    // 这是程序的main函数:入口函数
    public static void main(String[] args) {
        System.out.println(PropertyMgr.get("initTankCount"));
    }
}
