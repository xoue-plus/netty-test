package com.ne.sne.component.netty.handle;

import com.ne.sne.component.netty.domain.CustomProtocol;
import com.ne.sne.utils.NettySocketHolder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HeartBeatServerHandle extends SimpleChannelInboundHandler<CustomProtocol> {
    private final static Logger LOGGER = LoggerFactory.getLogger(HeartBeatServerHandle.class);
    private static final ByteBuf HEART_BEAT =  Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(new CustomProtocol(123456L,"pong").toString(), CharsetUtil.UTF_8));
    /**
     * 取消绑定
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        NettySocketHolder.remove((NioSocketChannel) ctx.channel());
    }
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt ;
            if (idleStateEvent.state() == IdleState.READER_IDLE){
                LOGGER.info("已经10秒没有收到信息！处于读空闲状态!!!!!");
                //向客户端发送消息
                ctx.writeAndFlush(HEART_BEAT).addListener(ChannelFutureListener.CLOSE_ON_FAILURE) ;
            }
        }
        super.userEventTriggered(ctx, evt);
    }
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CustomProtocol customProtocol) throws Exception {
        LOGGER.info("收到customProtocol={}", customProtocol);
        //保存客户端与 Channel 之间的关系
        NettySocketHolder.put(customProtocol.getId(),(NioSocketChannel)ctx.channel()) ;
    }
}