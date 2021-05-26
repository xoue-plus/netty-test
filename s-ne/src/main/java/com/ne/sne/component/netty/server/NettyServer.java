package com.ne.sne.component.netty.server;

import com.ne.sne.component.netty.initializer.NettyServerHandlerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

@Component
@Slf4j
public class NettyServer {

    private final EventLoopGroup boss = new NioEventLoopGroup();

    private final EventLoopGroup work = new NioEventLoopGroup();

    @Value("${netty.server.port}")
    private Integer port;

    /**
     * 启动Netty Server
     */

    @PostConstruct
    public void start() throws InterruptedException {
        InetSocketAddress address = new InetSocketAddress(port);
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, work)

                .channel(NioServerSocketChannel.class)

                .localAddress(address)

                //服务端可连接队列数,对应TCP/IP协议listen函数中backlog参数
                .option(ChannelOption.SO_BACKLOG, 1024)

                //tcp长连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)

                .childOption(ChannelOption.TCP_NODELAY, true)

                .childHandler(new NettyServerHandlerInitializer());

        ChannelFuture future = bootstrap.bind(address.getPort()).sync();

        if (future.isSuccess()) {
            log.info("启动 Netty Server");
        } else {
            log.error("server start fail ...");
        }
    }

    @PreDestroy
    public void destory() {
        try {
            boss.shutdownGracefully().sync();
            work.shutdownGracefully().sync();
            log.info("关闭Netty");
        } catch (Exception e) {
            log.error("server服务端的线程组销毁失败...");
        }
    }
}

