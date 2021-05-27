package com.ne.sne.component.netty.handle;

import com.ne.sne.component.netty.domain.CustomProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class CustomerServerHandle01 extends SimpleChannelInboundHandler<CustomProtocol> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, CustomProtocol customProtocol) throws Exception {

    }
}
