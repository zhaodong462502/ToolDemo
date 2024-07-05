package com.Jvm;

import java.util.ArrayList;
import java.util.List;

public class OOMTest {
    public static void main(String[] args) {
    List<byte[]> list = new ArrayList<>();
        // 模拟OOM
        while (true) {

            // 创建一个巨大的对象，导致内存溢出
            for (int i = 0; i < 1024; i++) {

                // 这里可以添加一些其他操作，以增加内存消耗
                byte[] temp = new byte[1024*1024];
                list.add(temp);

            }
        }
    }
}
