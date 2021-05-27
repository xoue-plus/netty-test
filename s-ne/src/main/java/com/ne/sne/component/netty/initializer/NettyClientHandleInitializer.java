package com.ne.sne.component.netty.initializer;

import com.ne.sne.component.netty.handle.*;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

/**
 * ClassName: NettyClientHandleInitializer
 * Description:
 * date: 2021/5/25 13:57
 */
public class NettyClientHandleInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        //client的10秒未写，加入处理器链
        pipeline.addLast(new IdleStateHandler(0, 10, 0));
        pipeline.addLast(new HeartbeatEncode());
        pipeline.addLast("encode",new StringEncoder(CharsetUtil.UTF_8));
        pipeline.addLast("decode",new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast(new HeartBeatClientHandle());
        pipeline.addLast(new NettyClientCustomerHandle());
        pipeline.addLast(new CustomerCliHandle01());
    }
}
