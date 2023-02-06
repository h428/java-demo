package com.hao.demo.chat.server.protocol;

import com.hao.demo.chat.message.Message;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * 用于 Message 和 ByteBuf 互相编码解码的处理器，入站时必须 LengthFieldBasedFrameDecoder 配合使用
 * 若入站时字节数组不率先经过 LengthFieldBasedFrameDecoder 过滤，则会有粘包半包问题，则拿到字节数组不是完整的单个 Message，
 * 这样在使用 decode 反序列化时会出现异常
 */
@Slf4j
@Sharable
public class MessageCodecSharableHandler extends MessageToMessageCodec<ByteBuf, Message> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Message msg, List<Object> outList) throws Exception {
        log.debug("出站 encode，准备将 Message 编码为 ByteBuf");

        // 使用 ctx 分配一个 ByteBuf
        ByteBuf out = ctx.alloc().buffer();

        // 下述自定义消息协议的具体内容，我们将其称为 Message 协议

        // 1. 首先是 4 个字节的魔数，例如 Java 对象的 CAFE BABE
        out.writeBytes(new byte[]{1, 2, 3, 4});
        // 2. 1 字节的版本号
        out.writeByte(1);
        // 3. 1 字节的序列化方式：0 jdk; 1 json; ...
        out.writeByte(0);
        // 4. 1 字节的指令类型
        out.writeByte(msg.getMessageType());
        // 5. 4 字节的请求号
        out.writeInt(msg.getSeqId());
        // 6. 填充内容
        out.writeByte(0xff);

        // 利用 bos 和 oos 将 Message 对象序列化为 byte[]
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(msg);
        byte[] bytes = bos.toByteArray();

        // 7. 长度，使用 4 字节描述正文长度
        out.writeInt(bytes.length);
        // 8. 写入正文，即 Message 序列化后的字节数组
        out.writeBytes(bytes);

        // 将 ByteBuf 添加到结果集 outList
        outList.add(out);
    }

    // 入站时调用，用于将 ByteBuf 解析为 Message
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        log.debug("入站 decode，准备将 ByteBuf 解码为 Message...");
        // 按照 Message 协议格式解码得到 Message 对象即可
        int magicNum = in.readInt();
        byte version = in.readByte();
        byte serializerType = in.readByte();
        byte messageType = in.readByte();
        int seqId = in.readInt();
        in.readByte(); // padding
        int length = in.readInt();
        byte[] bytes = new byte[length];
        in.readBytes(bytes, 0, length);
        // 将读取到的 bytes 转化为 Message 对象
        ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(bytes));
        Message message = (Message) ois.readObject();
        log.debug("读取到 Message 头部：{}, {}, {}, {}, {}, {}", magicNum, version, serializerType, messageType, seqId,
                length);
        log.debug("读取到 Message 对象：{}", message);
        out.add(message);
    }
}
