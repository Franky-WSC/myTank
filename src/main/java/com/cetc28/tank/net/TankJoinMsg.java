package com.cetc28.tank.net;

import com.cetc28.tank.Dir;
import com.cetc28.tank.Group;
import com.cetc28.tank.Tank;

import java.io.*;
import java.util.UUID;

/**
 * @Auther: WSC
 * @Date: 2022/2/1 - 02 - 01 - 18:32
 * @Description: com.cetc28.nettystudy.s02
 * @version: 1.0
 */
public class TankJoinMsg {
    public int x,y;
    public Dir dir;
    public boolean moving;
    public Group group;
    public UUID id;//在网上玩,每个坦克的独一无二的id号来标识自己 universally unique identifier

    public TankJoinMsg(){

    }

    public TankJoinMsg(Tank t){
        this.x = t.getX();
        this.y = t.getY();
        this.dir = t.getDir();
        this.moving = t.isbMoving();
        this.group = t.getGroup();
        this.id = t.getId();
    }

    public TankJoinMsg(int x, int y, Dir dir, boolean moving, Group group, UUID id) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.moving = moving;
        this.group = group;
        this.id = id;
    }

//    public void parse(byte[] bytes){
//        DataInputStream dis = new DataInputStream(new ByteArrayInputStream());
//        try {
//            this.x = dis.readInt();
//            this.y = dis.readInt();
//            this.dir = Dir.values()[dis.readInt()];
//            this.moving = dis.readBoolean();
//            this.group = Group.values()[dis.readInt()];
//            this.id = new UUID(dis.readLong(),dis.readLong());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }finally{
//            try {
//                dis.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    //将消息转换成为字节数组, 方便后面调用
    public byte[] toBytes(){
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            //自定义协议
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());//ordinal: enum数组的下标
            dos.writeBoolean(moving);
            dos.writeInt(group.ordinal());
            dos.writeLong(id.getMostSignificantBits());//前面64位
            dos.writeLong(id.getLeastSignificantBits());//后面64位
            dos.flush();;
            bytes = baos.toByteArray();//将内存中的字节数组保存为我们要保存的字节数组
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(baos != null){
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(dos != null){
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    @Override
    public String toString() {
        return "TankJoinMsg{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", moving=" + moving +
                ", group=" + group +
                ", id=" + id +
                '}';
    }
}
