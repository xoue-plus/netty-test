package com.ne.sne.component.netty.handle;

import com.ne.sne.component.netty.domain.CustomProtocol;
import com.ne.sne.utils.SpringUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeartBeatClientHandle extends SimpleChannelInboundHandler<ByteBuf> {
    private final static Logger LOGGER = LoggerFactory.getLogger(HeartBeatClientHandle.class);
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt ;
            if (idleStateEvent.state() == IdleState.WRITER_IDLE){
                LOGGER.info("已经10秒没有发送信息!!!!，写通道空闲状态...");
                //向服务端发送消息
                CustomProtocol heartBeat = SpringUtils.getBean("heartBeat");
                ctx.writeAndFlush(heartBeat).addListener(ChannelFutureListener.CLOSE_ON_FAILURE) ;
                System.out.println("client test....");
            }
        }
        super.userEventTriggered(ctx, evt);
    }
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, ByteBuf in) throws Exception {
        //从服务端收到消息时被调用
        LOGGER.info("客户端收到消息=>{}",in.toString(CharsetUtil.UTF_8)) ;
    }
}