package com.my.netty.messagepack;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;

public class MessagePackEchoClient {
    public void connect(int port, String host) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
//                            ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
//                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
//                            ch.pipeline().addLast(new StringDecoder());
//                            ch.pipeline().addLast(new MessagePackEchoClientHandle());
                            ch.pipeline().addLast("frame decode", new LengthFieldBasedFrameDecoder(65535,0,2,0,2));
                            ch.pipeline().addLast("msgpack decode", new MsgPackDecode());
                            ch.pipeline().addLast("frame encode",new LengthFieldPrepender(2));
                            ch.pipeline().addLast("msgpack encode", new MsgPackEncode());
                            ch.pipeline().addLast(new MessagePackEchoClientHandle());
                        }

                    });
//            发起异步连接操作
            ChannelFuture f=b.connect(host,port).sync();
//            等待客户端链路关闭
            f.channel().closeFuture().sync()
;        } catch (Exception e) {

        }finally {
            group.shutdownGracefully();
        }
    }

    public static  void  main(String[]args)throws Exception{

        new MessagePackEchoClient().connect(8080,"127.0.0.1");
    }

}
