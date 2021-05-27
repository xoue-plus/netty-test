package com.ne.sne.component.netty.server;

import com.ne.sne.component.netty.domain.TransBean;
import com.ne.sne.component.netty.initializer.NettyClientHandleInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * ClassName: NettyClient
 * Description:
 * date: 2021/5/25 14:00
 *
 * @author User
 */
public class NettyClientBak {

    public static void main(String[] args) throws Exception {
        sendMessage(new TransBean(1,"jack",new ArrayList<>(Arrays.asList("1","2","3"))).toString()
                ,new InetSocketAddress("localhost",9900));
    }
    public static void sendMessage(String content,InetSocketAddress address) throws InterruptedException{
        // Configure the client.
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY, true)
                    .handler(new NettyClientHandleInitializer());

            ChannelFuture future = b.connect(address.getAddress(), address.getPort()).sync();
            future.channel().writeAndFlush(content);
            future.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
