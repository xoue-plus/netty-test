package com.ne.sne.component.netty.handle;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * ClassName: NettyClientCustomerHandle
 * Description:
 * date: 2021/5/25 13:53
 *
 * @author User
 */
public class NettyClientCustomerHandle extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("ClientHandler Active");
        super.channelActive(ctx);
    }
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("--------");
        System.out.println("ClientHandler read Message:"+msg);
        //链式调用
        super.channelRead(ctx, msg);
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
