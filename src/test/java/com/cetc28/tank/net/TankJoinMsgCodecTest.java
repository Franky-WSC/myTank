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
 * @Date: 2022/2/4 - 02 - 04 - 15:17
 * @Description: com.cetc28.tank.net
 * @version: 1.0
 */
public class TankJoinMsgCodecTest {
    @Test
    public void testEncoder(){
        EmbeddedChannel ch = new EmbeddedChannel();

        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(5, 10, Dir.DOWN, true, Group.GOOD, id);
        ch.pipeline()
                .addLast(new TankJoinMsgEncoder());

        ch.writeOutbound(msg);

        ByteBuf buf = (ByteBuf)ch.readOutbound();
        MsgType msgType = MsgType.values()[buf.readInt()];//读取消息类型
        assertEquals(MsgType.TankJoin, msgType);//消息类型是否相同

        int length = buf.readInt();//读取消息长度
        assertEquals(33,length);//消息长度是否相同

        //读取字节数组的内容
        int x = buf.readInt();
        int y = buf.readInt();
        int dirOrdinal = buf.readInt();
        Dir dir = Dir.values()[dirOrdinal];
        boolean moving = buf.readBoolean();
        int groupOrdinal = buf.readInt();
        Group g = Group.values()[groupOrdinal];
        UUID uuid = new UUID(buf.readLong(),buf.readLong());

        //字节数组内容是否相同
        assertEquals(5,x);
        assertEquals(10,y);
        assertEquals(Dir.DOWN,dir);
        assertEquals(true,moving);
        assertEquals(Group.GOOD,g);
        assertEquals(id,uuid);
    }

    @Test
    public void testDecoder(){
        EmbeddedChannel ch = new EmbeddedChannel();

        UUID id = UUID.randomUUID();
        TankJoinMsg msg = new TankJoinMsg(5, 10, Dir.DOWN, true, Group.GOOD, id);
        ch.pipeline()
                .addLast(new TankJoinMsgDecoder());

        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(MsgType.TankJoin.ordinal());
        byte[] bytes = msg.toBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);

        ch.writeInbound(buf.duplicate());

        TankJoinMsg msgR = (TankJoinMsg)ch.readInbound();

        assertEquals(5, msgR.x);
        assertEquals(10, msgR.y);
        assertEquals(Dir.DOWN, msgR.dir);
        assertEquals(true, msgR.moving);
        assertEquals(Group.GOOD, msgR.group);
        assertEquals(id, msgR.id);
    }
}
