package com.cetc28.tank.net;

import com.cetc28.tank.Dir;
import com.cetc28.tank.Group;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.UUID;

/**
 * @Auther: WSC
 * @Date: 2022/2/1 - 02 - 01 - 18:48
 * @Description: com.cetc28.nettystudy.s02
 * @version: 1.0
 */
public class TankJoinMsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //处理tcp拆包 粘包的问题: 需要知道消息有多长
        //TankJoinMsg: x4 + y4 + dir4 + moving1 + group4 + id16 = 33
        //写过来至少33个字节, 因此必须读完33个字节在处理
        if(in.readableBytes() < 33){//TCP 拆包 粘包
            return;
        }

        TankJoinMsg msg = new TankJoinMsg();

        msg.x = in.readInt();
        msg.y = in.readInt();
        msg.dir = Dir.values()[in.readInt()];
        msg.moving = in.readBoolean();
        msg.group = Group.values()[in.readInt()];
        msg.id = new UUID(in.readLong(), in.readLong());

        out.add(msg);
    }
}
