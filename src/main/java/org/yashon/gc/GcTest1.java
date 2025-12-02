package org.yashon.gc;

import java.util.ArrayList;
import java.util.List;

/**
 * -verbose:gc
 * -Xms20m
 * -Xmx20m
 * -Xmn10m
 * -Xlog:gc+heap=trace  jdk21 使用该参数打印gc 日志
 * -XX:SurvivorRatio=8
 */
public class GcTest1 {

    public static void main(String[] args) {
        minorGC();
//        fullGC();
    }

    private static void minorGC() {
        int size = 1024 * 1024;
        byte[] allocate1 = new byte[2 * size];
        byte[] allocate2 = new byte[2 * size];
        byte[] allocate3 = new byte[2 * size];
        byte[] allocate4 = new byte[4 * size];
        byte[] allocate5 = new byte[4 * size];
//        byte[] allocate6 = new byte[4 * size];
        System.out.println("Minor GC triggered");
    }

    private static void fullGC() {
        int size = 1024 * 1024;

        // 创建大对象直接进入老年代
        byte[] largeObject = new byte[12 * size];  // 超过年轻代大小，直接进入老年代

        // 创建多个对象填满堆空间，触发Full GC
        List<byte[]> list = new ArrayList<>();
        try {
            for (int i = 0; i < 10; i++) {
                list.add(new byte[2 * size]);
            }
        } catch (OutOfMemoryError e) {
            System.out.println("OOM occurred");
        }

        System.out.println("Full GC triggered");
    }
}
