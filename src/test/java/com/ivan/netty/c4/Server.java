package com.ivan.netty.c4;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import static com.ivan.netty.c1.ByteBufferUtil.debugRead;

/**
 * @author Ivan
 * @create 2023/2/3 20:06
 */
@Slf4j
public class Server {
    public static void main(String[] args) throws IOException {
        // 使用nio来理解阻塞模式，单线程
        // 0. ByteBuffer
        ByteBuffer buffer = ByteBuffer.allocate(16);
        // 1. 创建服务器
        ServerSocketChannel ssc = ServerSocketChannel.open();

        // 2. 绑定监听端口
        ssc.bind(new InetSocketAddress(8085));

        // 3. 连接集合
        List<SocketChannel> channels = new ArrayList<>();
        while (true) {
            // 4. accept建立与客户端连接
            log.debug("connecting...");
            SocketChannel sc = ssc.accept();
            log.debug("connected... {}", sc);
            channels.add(sc);
            for (SocketChannel channel : channels) {
                // 5. 接收客户端数据
                log.debug("before reading..., {}", channel);
                channel.read(buffer);
                buffer.flip();
                debugRead(buffer);
                buffer.clear();
                log.debug("after reading..., {}", channel);
            }
        }
    }
}
