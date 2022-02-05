package com.cetc28.tank.net;

import com.cetc28.tank.Dir;
import com.cetc28.tank.Tank;
import com.cetc28.tank.TankFrame;

import java.io.*;
import java.util.UUID;

/**
 * @Auther: WSC
 * @Date: 2022/2/5 - 02 - 05 - 12:57
 * @Description: com.cetc28.tank.net
 * @version: 1.0
 */
public class TankStartMovingMsg extends Msg {
    UUID id;
    int x, y;
    Dir dir;

    @Override
    public String toString() {
        return "TankStartMovingMsg{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                '}';
    }

    public TankStartMovingMsg() {
    }

    public TankStartMovingMsg(Tank t){
        this.id = t.getId();
        this.x = t.getX();
        this.y = t.getY();
        this.dir = t.getDir();
    }

    public TankStartMovingMsg(UUID id, int x, int y, Dir dir) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    @Override
    public void handle() {
        //如果是自己, 就不用管
        if(this.getId().equals(TankFrame.INSTANCE.getMainTank().getId())){
            return;
        }
        //如果是其他坦克, 找到这个坦克
        Tank t = TankFrame.INSTANCE.findByUUID(this.id);
        //开始移动
        if(t != null){
            t.setbMoving(true);
            t.setX(this.x);
            t.setY(this.y);
            t.setDir(dir);
        }
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.flush();
            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(baos != null){
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(dos != null){
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }

    @Override
    public void parse(byte[] bytes) {
        ByteArrayInputStream bais = null;
        DataInputStream dis = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            dis = new DataInputStream(bais);
            this.setId(new UUID(dis.readLong(),dis.readLong()));
            this.setX(dis.readInt());
            this.setY(dis.readInt());
            this.setDir(Dir.values()[dis.readInt()]);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(dis != null){
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bais != null){
                try {
                    bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankStartMoving;
    }
}
