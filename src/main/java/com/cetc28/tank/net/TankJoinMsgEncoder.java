package com.cetc28.tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Auther: WSC
 * @Date: 2022/2/1 - 02 - 01 - 18:45
 * @Description: com.cetc28.nettystudy.s02
 * @version: 1.0
 */
public class TankJoinMsgEncoder extends MessageToByteEncoder<Msg> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Msg msg, ByteBuf buf) throws Exception {
        buf.writeInt(msg.getMsgType().ordinal());//写消息类型
        byte[] bytes = msg.toBytes();
        buf.writeInt(bytes.length);//写消息长度
        buf.writeBytes(bytes);//写消息
    }
}
