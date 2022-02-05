package com.cetc28.tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * @Auther: WSC
 * @Date: 2022/2/5 - 02 - 05 - 14:51
 * @Description: com.cetc28.tank.net
 * @version: 1.0
 */
public class TankStopMsgCodecTest {
    @Test
    public void testEncoder(){
        EmbeddedChannel ch = new EmbeddedChannel();
        ch.pipeline()
                .addLast(new MsgEncoder());

        UUID id = UUID.randomUUID();
        TankStopMsg msg = new TankStopMsg(id,5,10);

        ch.writeOutbound(msg);//channel发出去
        ByteBuf buf = (ByteBuf)ch.readOutbound();//channel读进来

        //消息类型
        MsgType type = MsgType.values()[buf.readInt()];
        assertEquals(MsgType.TankStop, type);
        //消息长度
        assertEquals(24,buf.readInt());
        //uuid
        UUID uuid = new UUID(buf.readLong(), buf.readLong());
        assertEquals(id,uuid);
        //x & y
        assertEquals(5,buf.readInt());
        assertEquals(10,buf.readInt());
    }

    @Test
    public void testDecoder(){
        EmbeddedChannel ch = new EmbeddedChannel();
        ch.pipeline()
                .addLast(new MsgDecoder());

        UUID id = UUID.randomUUID();
        TankStopMsg msg = new TankStopMsg(id,5,10);

        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(MsgType.TankStop.ordinal());
        byte[] bytes = msg.toBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);

        ch.writeInbound(buf.duplicate());

        TankStopMsg msgR = (TankStopMsg)ch.readInbound();
        assertEquals(id, msgR.getId());
        assertEquals(5, msgR.getX());
        assertEquals(10,msgR.getY());
    }
}
