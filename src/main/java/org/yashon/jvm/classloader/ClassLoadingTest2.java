package org.yashon.jvm.classloader;


/**
 * 演示final 关键字的使用
 * 在编译阶段，这个常量就会被存入到调用这个常量的那个方法所在的类的常量池中，
 * 本质上调用类并没有直接引用到定义常量的类，因此不会出发定义常量的那个类（MyParent2）的初始化
 * 可以说在编译阶段，MyParent2 的 str 常量放在了 ClassLoaderTest2 类的常量池中，之后就和 MyParent2 类没有关系了，验证：可以将 MyParent2.class 文件删除，程序照样可以运行
 *
 * javap -c ClassLoadingTest2
 *public class org.yashon.jvm.classloader.ClassLoadingTest2 {
 *   public org.yashon.jvm.classloader.ClassLoadingTest2();
 *     Code:
 *        0: aload_0
 *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        4: return
 *
 *   public static void main(java.lang.String[]);
 *     Code:
 *        0: getstatic     #7                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *        3: ldc           #15                 // String hello world
 *        5: invokevirtual #17                 // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *        8: return
 * }
 *
 * 可以看到 ldc 指令加载的常量值是 MyParent2.Str，这个常量已经存入了 ClassLoaderTest2 类的常量池中
 * ldc：表示将 int float 或者 String 等类型的常量从常量池中加载到操作数栈中
 * bipush
 * iconst_m1 ~ iconst_5
 */
public class ClassLoadingTest2 {

    public static void main(String[] args) {

//        System.out.println(MyParent2.Str);
        System.out.println(MyParent2.i);
    }
}

class MyParent2 {

    public static final String Str = "hello world";
    public static final short i = 7;

    static {
        System.out.println("MyParent2 static block");
    }
}
