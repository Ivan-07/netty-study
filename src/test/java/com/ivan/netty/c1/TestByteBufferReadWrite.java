package com.ivan.netty.c1;

import java.nio.ByteBuffer;

import static com.ivan.netty.c1.ByteBufferUtil.debugAll;

/**
 * @author Ivan
 * @create 2023/1/19 23:17
 */
public class TestByteBufferReadWrite {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(10);
        buffer.put((byte) 0x61);
        debugAll(buffer);
        buffer.put(new byte[]{0x62, 0x63, 0x64});
        debugAll(buffer);
        buffer.flip();
        System.out.println(buffer.get());
        debugAll(buffer);
        buffer.compact();
        debugAll(buffer);
        buffer.put(new byte[]{0x65, 0x6f});
        debugAll(buffer);
    }
}
