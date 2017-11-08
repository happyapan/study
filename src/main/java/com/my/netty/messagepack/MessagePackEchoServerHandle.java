package com.my.netty.messagepack;



import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class MessagePackEchoServerHandle extends ChannelHandlerAdapter {
    int count = 0;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object message) throws Exception {
        String info = (String) message;
        System.out.println("this is " + ++count + " receice[" + info + "]");
        info += "$_";
        ctx.writeAndFlush(Unpooled.copiedBuffer(info.getBytes()));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
