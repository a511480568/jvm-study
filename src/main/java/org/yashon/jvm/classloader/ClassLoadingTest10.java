package org.yashon.jvm.classloader;

public class ClassLoadingTest10 {

    public static void main(String[] args) {
        String[] strings = new String[1];
        // 这里获得是 string 数组中每个类型的类加载器
        // 可以更精确地表述为"获取String数组对象的类加载器"，因为 strings.getClass() 返回的是数组类型 String[] 的 Class 对象
        System.out.println(strings.getClass().getClassLoader());

        System.out.println("======================");
        ClassLoadingTest10[] classLoadingTest10s = new ClassLoadingTest10[1];
        System.out.println(classLoadingTest10s.getClass().getClassLoader());

        System.out.println("======================");
        int[] counts = new int[1];
        // 这里返回的 null，并不是说 int 类型的类加载器是根类加载器，而是表示的是原生类型没有类加载器
        System.out.println(counts.getClass().getClassLoader());
    }
}
