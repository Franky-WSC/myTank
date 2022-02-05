package com.cetc28.tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Auther: WSC
 * @Date: 2022/2/1 - 02 - 01 - 18:48
 * @Description: com.cetc28.nettystudy.s02
 * @version: 1.0
 */
public class MsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        //处理tcp拆包 粘包的问题: 需要知道消息有多长
        //消息类型4 + 消息长度4
        if(in.readableBytes() < 8){//TCP 拆包 粘包
            return;
        }

        in.markReaderIndex();//标记ByteBuf中读到哪里了

        MsgType msgType = MsgType.values()[in.readInt()];//消息类型
        int length = in.readInt();//消息长度

        if(in.readableBytes() < length){//如果接着读取不够消息长度
            in.resetReaderIndex();//重设读取位置标记
            return;
        }

        byte[] bytes = new byte[length];
        in.readBytes(bytes);

        Msg msg = null;

        switch (msgType){
            case TankJoin:
                msg = new TankJoinMsg();
                break;
            case TankStartMoving:
                msg = new TankStartMovingMsg();
                break;
            case TankStop:
                msg = new TankStopMsg();
                break;
            default:
                break;
        }
        msg.parse(bytes);
        out.add(msg);
    }
}
