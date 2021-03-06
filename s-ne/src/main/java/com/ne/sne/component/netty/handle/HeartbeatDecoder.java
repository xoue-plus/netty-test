package com.ne.sne.component.netty.handle;

import com.ne.sne.component.netty.domain.CustomProtocol;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

public class HeartbeatDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        long id = in.readLong() ;
        byte[] bytes = new byte[in.readableBytes()] ;
        in.readBytes(bytes) ;
        String content = new String(bytes, CharsetUtil.UTF_8) ;
        CustomProtocol customProtocol = new CustomProtocol() ;
        customProtocol.setId(id);
        customProtocol.setContent(content) ;
        out.add(customProtocol) ;
    }
}