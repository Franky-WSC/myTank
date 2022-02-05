package com.cetc28.tank.net;

import com.cetc28.tank.Tank;
import com.cetc28.tank.TankFrame;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

/**
 * @Auther: WSC
 * @Date: 2022/1/31 - 01 - 31 - 21:25
 * @Description: com.cetc28.nettystudy.s02
 * @version: 1.0
 */
public class Client {
    public static final Client INSTANCE = new Client();
    private Client(){

    }
    //client端保存一个channel用来发送消息
    private Channel channel = null;

    public void connect(){
        EventLoopGroup group = new NioEventLoopGroup(1);
        Bootstrap b = new Bootstrap();

        try {
            ChannelFuture cf = b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer())
                    .connect("localhost", 8888);

            cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if(future.isSuccess()){
//                        System.out.println("连接成功!");
                        ServerFrame.INSTANCE.updateClientMsg("客户端连接成功");
//                        System.out.println("operationComplete监听器监听连接成功后的future中的channel: " + future.channel());
                        ServerFrame.INSTANCE.updateClientMsg("监听器监听连接成功后的future中的channel: " + future.channel());
                        channel = future.channel();//客户端连接成功后即初始化
                    }else{
                        System.out.println("连接失败!");
                    }
                }
            });

            cf.sync();
            System.out.println("...");
            cf.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            group.shutdownGracefully();
        }
    }

    public void send(Msg msg){
        channel.writeAndFlush(msg);
    }

    public void closeConnect(){
//        this.send("_bye_");
    }

}

class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
//        System.out.println("initChannel的SocketChannel: " + ch);
        ServerFrame.INSTANCE.updateClientMsg("initChannel的SocketChannel: " + ch);
        //添加pipeline(责任链模式) 一般业务逻辑处理加到最后 编码解码等处理加到前面
        ch.pipeline()
                .addLast(new TankJoinMsgEncoder())//自定义协议的编码处理
                .addLast(new TankJoinMsgDecoder())//自定义协议的解码处理
                .addLast(new ClientHandler());//channel消息io的处理
    }
}

class ClientHandler extends SimpleChannelInboundHandler<TankJoinMsg>{
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TankJoinMsg msg) throws Exception {
        msg.handle();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ServerFrame.INSTANCE.updateClientMsg("channelActive中的channel: " + ctx.channel());

        //加入了编码责任链之后, 可以直接写入对象信息, 会经过msgencode编码之后直接写出去
        ctx.writeAndFlush(new TankJoinMsg(TankFrame.INSTANCE.getMainTank()));
    }
}