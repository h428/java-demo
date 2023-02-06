package com.hao.demo.netty.test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.AbstractMap.SimpleEntry;
import java.util.Random;
import java.util.stream.IntStream;

public class Client {
    public static void main(String[] args) {
        batchStart(6);
    }

    private static final Random random = new Random();

    public static void batchStart(int batchSize) {
        IntStream.range(0, batchSize)
            .mapToObj(idx -> new SimpleEntry<>(idx, new Thread(Client::startClient)))
            .peek(entry -> entry.getValue().setName("th" + entry.getKey()))
            .forEach(entry -> entry.getValue().start());
    }

    public static void startClient() {
        try {
            int count = 0;
            ByteBuffer byteBuffer = ByteBuffer.allocate(16);
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress(8080));
            String address = socketChannel.getLocalAddress().toString();
            String threadName = Thread.currentThread().getName();
            while (count < 10) {
                Thread.sleep(1000L);
                // 从标准流读取数据，实际上 scanner.nextLine() 也是一种阻塞 IO
                String input = String.valueOf((random.nextInt() >>> 1) % 1000);
                ++count;

                // 编码后发送到服务端
                System.out.printf("%s(%s) will send data to server : %s\n", address, threadName, input);
                socketChannel.write(Charset.defaultCharset().encode(input));

                // 获取服务端的响应，也是阻塞式 IO，会阻塞住直到收到服务端的数据
                socketChannel.read(byteBuffer);

                // 打印响应内容
                byteBuffer.flip();
                String resp = Charset.defaultCharset().decode(byteBuffer).toString();
                System.out.printf("%s(%s) get res from server : %s\n", address, threadName, resp);
                byteBuffer.clear();
            }

            socketChannel.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
