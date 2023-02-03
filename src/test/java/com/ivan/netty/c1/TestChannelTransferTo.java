package com.ivan.netty.c1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * @author Ivan
 * @create 2023/2/3 18:54
 */
public class TestChannelTransferTo {
    public static void main(String[] args) {
        try (
                FileChannel from = new FileInputStream("data.txt").getChannel();
                FileChannel to = new FileOutputStream("do.txt").getChannel();
        ) {
            // 效率高，底层会利用操作系统的零拷贝进行优化，2g数据
            long size = from.size();
            // left变量代表还剩多少字节
            for (long left = size; left>0; ){
                left -= from.transferTo(size-left, left, to);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
