package org.yashon.jvm.classloader;

/**
 * 演示一个系统（应用）类加载器都有哪些父类加载器
 */
public class ClassLoadingTest8 {

    public static void main(String[] args) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println("初始得到的类加载器: " + classLoader);

        while (null != classLoader) {
            classLoader = classLoader.getParent();
            System.out.println("得到的父类加载器: " + classLoader);
        }
    }
}
