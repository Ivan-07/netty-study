package com.ivan.netty.c1;

import java.nio.ByteBuffer;

import static com.ivan.netty.c1.ByteBufferUtil.debugAll;

/**
 * @author Ivan
 * @create 2023/2/3 18:23
 */
public class TestByteBufferExam {
    public static void main(String[] args) {
        /*
        网络上有多条数据发送给服务端，数据之间使用 \n 进行分隔
        但由于某种原因这些数据在接收时，被进行了重新组合，例如原始数据有3条为
        Hello,world\n
        I'm zhangsan\n
        How are you?\n

        变成了下面的两个 byteBuffer (黏包，半包)
        Hello,world\nI'm zhangsan\nHo
        w are you?\n
     */
        ByteBuffer source = ByteBuffer.allocate(32);
        source.put("hello,world\nI'm Ivan\nHo".getBytes());
        split(source);
        source.put("w are you?\n".getBytes());
        split(source);
    }

    private static void split(ByteBuffer source) {
        source.flip();
        for (int i = 0; i < source.limit(); i++) {
            if (source.get(i) == '\n') {
                int length = i + 1 - source.position();
                ByteBuffer target = ByteBuffer.allocate(length);
                for (int j = 0; j < length; j++) {
                    target.put(source.get());
                }
                debugAll(target);
            }
        }
        source.compact();
    }

}
