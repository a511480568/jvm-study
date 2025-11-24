package org.yashon.jvm.classloader;

/**
 * 演示类的主动使用和被动使用
 */
public class ClassLoadingTest1 {

    public static void main(String[] args) {
        // 静态字段，触发了类的主动使用：所有的 Java 虚拟机实现必须在每个类或接口被 Java 程序“首次主动使用”时才会初始化他们
        // 这里是使用 MyChild1 调用了父类的静态字段，所以初始化的是 MyParent1
        // MyChild1 没有被初始化，那么有没有被加载呢，可以使用 -XX:+TraceClassLoading 命令行参数 来观察
        // 由于我使用的是 JDK21，需要更改成：-verbose:class 才可以，否则会报错
        // 可以看到 MyChild1 被加载了
        System.out.println(MyChild1.str);
        // 这里调用的 MyChild1 自己的静态字段，会初始化 MyChild1，但这个类继承了 MyParent1，所以要先初始化父类，可以看文档中关于七种主动使用的第五点：初始化一个类的子类
        System.out.println(MyChild1.str2);
    }
}

class MyParent1 {

    public static String str = "hello world";
    static {
        System.out.println("MyParent1 static block");
    }
}

class MyChild1 extends MyParent1 {

    public static String str2 = "welcome";
    static {
        System.out.println("MyChild1 static block");
    }
}