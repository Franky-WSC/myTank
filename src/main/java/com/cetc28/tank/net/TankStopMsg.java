package com.cetc28.tank.net;

import com.cetc28.tank.Tank;
import com.cetc28.tank.TankFrame;

import java.io.*;
import java.util.UUID;

/**
 * @Auther: WSC
 * @Date: 2022/2/5 - 02 - 05 - 14:06
 * @Description: com.cetc28.tank.net
 * @version: 1.0
 */
public class TankStopMsg extends Msg {
    UUID id;
    int x, y;

    public TankStopMsg() {
    }

    public TankStopMsg(Tank tank){
        this.id = tank.getId();
        this.x = tank.getX();
        this.y = tank.getY();
    }

    public TankStopMsg(UUID id, int x, int y) {
        this.id = id;
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "TankStopMsg{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                '}';
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

    @Override
    public void handle() {
        //如果是自己的主战坦克 就不用做任何操作
        if(this.getId().equals(TankFrame.INSTANCE.getMainTank().getId())){
            return;
        }
        Tank t = TankFrame.INSTANCE.findByUUID(this.getId());
        if(t != null){
            t.setbMoving(false);
            t.setX(this.getX());
            t.setY(this.getY());
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

            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(dos != null){
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(baos != null){
                try {
                    baos.close();
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

            setId(new UUID(dis.readLong(),dis.readLong()));
            setX(dis.readInt());
            setY(dis.readInt());

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
        return MsgType.TankStop;
    }
}
