package com.test.nio;

import org.junit.Test;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ChannelTest3 {

    @Test
    public void test01() throws IOException {
        FileChannel inChannel = FileChannel.open(Paths.get("hello.txt"), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get("hello.txt"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        ByteBuffer buffer = ByteBuffer.allocate(8);
        int num = 0;;
        while ((num = inChannel.read(buffer)) != -1) {
            buffer.flip();
            outChannel.write(buffer);
            System.out.print(new String(buffer.array(), 0, num));
            buffer.clear();
        }

        outChannel.close();
        inChannel.close();

    }
}
