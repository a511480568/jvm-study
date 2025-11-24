package org.yashon.jvm.classloader;

import java.util.UUID;

/**
 * 演示调用 MyParent3 中的 final 常量，这个 final 常量是在编译期不确定的，这时候看下 MyParent3 中的 static 静态代码块会不会被执行
 * 如果把 MyParent3.class 文件删除的话，再运行会报错，正好和 {@link ClassLoadingTest2} 相反，在 {@link ClassLoadingTest2} 中
 * 将 MyParent2.class 文件删除后再次运行就不会报错，因为在 {@link ClassLoadingTest2} 中, MyParent2 中的常量是编译期就确定的
 * @see {@link ClassLoadingTest2}
 */
public class ClassLoadingTest3 {

    public static void main(String[] args) {
        System.out.println(MyParent3.str);
    }
}

class MyParent3 {
    public static final String str = UUID.randomUUID().toString();
//    public static String str2 = "hello world";
    static {
        System.out.println("MyParent3 static block");
    }

    // 这个不会执行，因为这个需要实例化才能执行，实例化和初始化是不一样的
    public MyParent3() {
        System.out.println("MyParent3 constructor block");
    }
}
