package com.ne.sne.component.netty.handle;

import com.ne.sne.component.netty.domain.CustomProtocol;
import com.ne.sne.utils.SpringUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * ClassName: NettyCustomerHandle
 * Description:
 * date: 2021/5/25 13:33
 *
 * @author User
 */
public class NettyServerCustomerHandle extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive----->");
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server channelRead......");
        System.out.println(ctx.channel().remoteAddress()+"----->Server :"+ msg.toString());
        //将客户端的信息直接返回写入ctx
        if (SpringUtils.getBean("heartBeat").equals(msg)){
            ctx.writeAndFlush("心跳检测成功!!!!");
            //心跳检测几次，包括时长设置
        }
        ctx.write("server say :"+msg);
        //服务调用
        //业务如果时间较长,不要阻塞
//        ctx.channel().eventLoop().execute(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
//        ctx.channel().eventLoop().scheduler(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        });
        //刷新缓存区
        ctx.flush();
        //链式调用
        super.channelRead(ctx,msg);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
