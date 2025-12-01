package org.yashon.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试堆内存溢出
 * 添加 VM options 参数
 * -Xms2m -Xmx2m -XX:+HeapDumpOnOutOfMemoryError -XX:+PrintGCDetails
 */
public class MemoryTest1 {

    public static void main(String[] args) {
        List<MemoryTest1> list = new ArrayList<>();
        for (;;) {
            list.add(new MemoryTest1());
        }
    }
}
