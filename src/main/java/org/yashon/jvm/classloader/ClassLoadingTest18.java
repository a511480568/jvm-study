package org.yashon.jvm.classloader;

import java.sql.Connection;

/**
 * 线程上下文加载器
 */
public class ClassLoadingTest18 {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(Thread.class.getClassLoader());
        System.out.println(Connection.class.getClassLoader());
    }
}
