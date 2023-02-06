package com.hao.demo.netty.test;

import static sun.nio.ch.IOStatus.EOF;

import com.hao.demo.netty.util.DebugUtil;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(8080));

        ServerSocket serverSocket = new ServerSocket();

        while (!Thread.interrupted()) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            // 开启一个线程处理新连接上的数据读写，主线程在创建线程后即可继续运行，不会被数据处理阻塞
            new Thread(new ConnectionHandler(socketChannel)).start();
        }
    }

    static class ConnectionHandler implements Runnable {

        private SocketChannel socketChannel;

        private String address;

        private final ByteBuffer byteBuffer = ByteBuffer.allocate(16);

        public ConnectionHandler(SocketChannel socketChannel) {
            try {
                this.socketChannel = socketChannel;
                this.address = socketChannel.getRemoteAddress().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {//主线程死循环等待新连接到来
                    // 数据读取，同时处理正常关闭连接
                    // 对当前线程，会一直阻塞知道 Client 有数据发送过来
                    if (socketChannel.read(byteBuffer) == EOF) {
                        break;
                    }

                    String data = DebugUtil.bytesToString(byteBuffer);
                    String out = String.format("received message from %s at %s : %s\n",
                            address, DebugUtil.time(), data);
                    System.out.print(out);
                    // 移除前后空格后将同样的内容响应到客户端
                    socketChannel.write(Charset.defaultCharset().encode(data.trim()));
                }
            } catch (IOException e) {
                // 客户端强制关闭连接会触发 IO 异常
                try {
                    System.out.printf("%s 强制关闭了连接\n", address);
                    socketChannel.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
