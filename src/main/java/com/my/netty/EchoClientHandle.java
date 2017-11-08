package com.my.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by ccc016 on 2017/8/30.
 */
public class EchoClientHandle extends ChannelHandlerAdapter {
    private int count;
    static final String ECHO_HELLO="Hi Jeff, Welcome to Netty.$_";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive");
        for(int i=1;i<=10;i++){
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_HELLO.getBytes()));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("channelRead");
        System.out.println("this is " + ++count + " times receive Server:[" + msg + "}");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelReadComplete");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
