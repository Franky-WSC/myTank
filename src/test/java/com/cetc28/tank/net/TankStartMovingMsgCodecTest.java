package com.cetc28.tank.net;

import com.cetc28.tank.Dir;
import com.cetc28.tank.Group;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * @Auther: WSC
 * @Date: 2022/2/5 - 02 - 05 - 13:13
 * @Description: com.cetc28.tank.net
 * @version: 1.0
 */
public class TankStartMovingMsgCodecTest {
    @Test
    public void testEncoder(){
        EmbeddedChannel ch = new EmbeddedChannel();

        UUID id = UUID.randomUUID();
        TankStartMovingMsg msg = new TankStartMovingMsg(id,5, 10, Dir.DOWN);
        ch.pipeline()
                .addLast(new MsgEncoder());

        ch.writeOutbound(msg);

        ByteBuf buf = (ByteBuf)ch.readOutbound();
        MsgType msgType = MsgType.values()[buf.readInt()];//读取消息类型
        assertEquals(MsgType.TankStartMoving, msgType);//消息类型是否相同

        int length = buf.readInt();//读取消息长度
        assertEquals(28,length);//消息长度是否相同

        //读取字节数组的内容
        UUID uuid = new UUID(buf.readLong(),buf.readLong());
        int x = buf.readInt();
        int y = buf.readInt();
        int dirOrdinal = buf.readInt();
        Dir dir = Dir.values()[dirOrdinal];

        //字节数组内容是否相同
        assertEquals(id,uuid);
        assertEquals(5,x);
        assertEquals(10,y);
        assertEquals(Dir.DOWN,dir);
    }

    @Test
    public void testDecoder(){
        EmbeddedChannel ch = new EmbeddedChannel();

        UUID id = UUID.randomUUID();
        TankStartMovingMsg msg = new TankStartMovingMsg(id, 5, 10, Dir.DOWN);
        ch.pipeline()
                .addLast(new MsgDecoder());

        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(MsgType.TankStartMoving.ordinal());
        byte[] bytes = msg.toBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);

        ch.writeInbound(buf.duplicate());

        TankStartMovingMsg msgR = (TankStartMovingMsg)ch.readInbound();

        assertEquals(id, msgR.getId());
        assertEquals(5, msgR.getX());
        assertEquals(10, msgR.getY());
        assertEquals(Dir.DOWN, msgR.getDir());
    }
}
