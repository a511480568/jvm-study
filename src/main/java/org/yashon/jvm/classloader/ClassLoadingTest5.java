package org.yashon.jvm.classloader;

import java.util.Random;

/**
 * 演示接口引用，当一个接口初始化的时候并不会要求其父接口都完成了初始化
 * 只有在真正使用到父接口的时候（如引用接口中定义的常量）的时候，才会触发父接口的初始化
 *
 * 在该类中，编译后把 MyParent5.class 文件删除，程序照样可以运行，但是如果把这两个接口改成类就报错了（前提是不能有 final 关键字）
 * 因为在初始化 MyChild5 的时候会先初始化MyParent5，但是 MyParent5.class 文件被删除了，所以会报错
 */
public class ClassLoadingTest5 {

    public static void main(String[] args) {
        System.out.println(MyChild5.b);
    }
}

interface MyParent5 {
    public static final int a = new Random().nextInt();
}

interface MyChild5 extends MyParent5 {
    public static final int b = 1;
}