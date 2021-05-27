package com.ne.sne.component.netty.initializer;

import com.ne.sne.component.netty.handle.*;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * ClassName: NettyServerHandlerInitializer
 * Description:
 * date: 2021/5/25 13:25
 *
 */
public class NettyServerHandlerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        //心跳检测处理器10s
//        pipeline.addLast(new IdleStateHandler(10, 0, 0, TimeUnit.SECONDS));
        //编解码

        pipeline.addLast(new HeartbeatDecoder());
        pipeline.addLast("decode",new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast("encode",new StringEncoder(CharsetUtil.UTF_8));
//        pipeline.addLast(new HeartBeatServerHandle());
        pipeline.addLast(new NettyServerCustomerHandle());
    }
}
