package com.cetc28.tank.net;

/**
 * @Auther: WSC
 * @Date: 2022/2/4 - 02 - 04 - 21:52
 * @Description: com.cetc28.tank.net
 * @version: 1.0
 */
public abstract class Msg {
    //对消息的处理
    public abstract void handle();
    //将消息转换成字节数组
    public abstract byte[] toBytes();
    //将字节数组转换成消息
    public abstract void parse(byte[] bytes);
    //获取消息类型
    public abstract MsgType getMsgType();
}
