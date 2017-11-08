package com.my.netty.messagepack;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * Created by ccc016 on 2017/8/29.
 */
public class MsgPackDecode extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        final  int len=byteBuf.readableBytes();
        final  byte[] array=new byte[len];
        byteBuf.getBytes(byteBuf.readerIndex(),array,0,len);
        MessagePack messagePack=new MessagePack();
        list.add(messagePack.read(array));

    }
}
