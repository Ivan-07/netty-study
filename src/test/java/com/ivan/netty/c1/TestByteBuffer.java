package com.ivan.netty.c1;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author Ivan
 * @create 2023/1/19 18:16
 */
@Slf4j
public class TestByteBuffer {
    public static void main(String[] args) {
        // FileChannel
        // 1. 输入输出流  2. RandomAccessFile
        try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {
            // 1. 准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            while (true) {
                // 2. 从channel中读，写入buffer
                int len = channel.read(buffer);
                log.debug("读取到的字节数 {}", len);
                if (len == - 1) {
                    break;
                }
                // 3. 打印buffer内容
                buffer.flip(); // 切换至读模式
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
                    log.debug("实际字节 {}", (char) b);
                }
                buffer.clear(); // 切换到写模式
            }
        } catch (IOException e) {
        }
    }
}
