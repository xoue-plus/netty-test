package com.ne.sne.component.netty.server;

import com.ne.sne.component.netty.domain.CustomProtocol;
import com.ne.sne.component.netty.initializer.NettyClientHandleInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.net.InetSocketAddress;

@Component
@Slf4j
public class NettyClient {

    @Value("${netty.server.address}")
    private String address;
    @Value("${netty.server.port}")
    private Integer port;
    private final static EventLoopGroup group = new NioEventLoopGroup();
    private final static Bootstrap b = new Bootstrap();
    private static ChannelFuture future;

    public void start() {
        InetSocketAddress addr = new InetSocketAddress(address, port);
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new NettyClientHandleInitializer());
        try {
            future = b.connect(addr.getAddress(), addr.getPort()).sync();
        } catch (InterruptedException e) {
            log.error("client connect error ...");
        }
    }

    public void sendMessage(CustomProtocol content) {
        try {
            future.channel().writeAndFlush(content);
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
           log.error("client send message fail...");
        } finally {
            group.shutdownGracefully();
        }
    }
}
