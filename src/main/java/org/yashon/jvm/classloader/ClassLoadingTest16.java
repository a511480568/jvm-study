package org.yashon.jvm.classloader;

public class ClassLoadingTest16 {

    public static void main(String[] args) {
        // 根类加载器加载的类路径
        System.out.println(System.getProperty("sun.boot.path"));
        // 扩展类加载器加载的类路径
        System.out.println(System.getProperty("java.ext.dirs"));
        // 应用类加载器加载的类路径
        System.out.println(System.getProperty("java.class.path"));
    }
}
