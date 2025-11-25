package org.yashon.jvm.classloader;

public class ClassLoadingTest7 {

    public static void main(String[] args) throws Exception {

        Class<?> strClazz = Class.forName("java.lang.String");
        System.out.println(strClazz.getClassLoader());  // null，因为 String 类是引导类加载器加载的

        Class<?> cClazz = Class.forName("org.yashon.jvm.classloader.C");
        System.out.println(cClazz.getClassLoader());    // jdk.internal.loader.ClassLoaders$AppClassLoader@4e0e2f2a，C 类是由 AppClassLoader 加载的
    }
}

class C {

}
