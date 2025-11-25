package org.yashon.jvm.classloader;

public class ClassLoadingTest14 {

    public static void main(String[] args) throws Exception {
        ClassLoadingTest13 loader = new ClassLoadingTest13("MyClassLoader");
        Class<?> mySimpleClazz = loader.loadClass("org.yashon.jvm.classloader.MySimple");
        System.out.println("MySimple class hashcode is : " + mySimpleClazz.hashCode());
        // -verbose:class 通过这个 JVM 参数可以观察 MyCat 是否被加载，下面这行代码注释和不注释结果是不一样的
        Object instance = mySimpleClazz.newInstance();
    }
}
