package org.yashon.jvm.classloader;

/**
 * 默认情况下 java.system.class.loader 为 null，那么系统（应用）类加载器是 AppClassLoader，当指定了 java.system.class.loader 系统类加载器是自定义的类加载器，但它的父加载器还是 AppClassLoader，这个是不变的，所以加载
 * ClassLoadingTest17 类时，打印出来的还是 AppClassLoader
 *
 * /Users/xxx/resources/openjdk-21/Contents/Home/bin/java -Xshare:off -Djava.system.class.loader=org.yashon.jvm.classloader.ClassLoadingTest13 -cp target/classes org.yashon.jvm.classloader.ClassLoadingTest17
 * 上面的命令是使用 JDK21 执行的，JDK8 的命令被禁止了
 */
public class ClassLoadingTest17 {

    public static void main(String[] args) {
        System.out.println(System.getProperty("java.system.class.loader"));
        System.out.println(ClassLoadingTest17.class.getClassLoader());
    }
}
