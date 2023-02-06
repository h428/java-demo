package com.hao.demo.chat.server.protocol;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * 用于解决粘包半包的 InboundHandler
 * 自定义消息协议为 CLV 格式，故需要在入站时首先添加该处理器分隔出各个正确的消息
 */
public class ProtocolFrameDecoder extends LengthFieldBasedFrameDecoder {

    public ProtocolFrameDecoder() {
        this(1024, 12, 4, 0, 0);
    }

    public ProtocolFrameDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment,
            int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }
}
