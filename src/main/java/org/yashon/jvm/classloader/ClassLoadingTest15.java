package org.yashon.jvm.classloader;

/**
 * 这个测试类主要演示的是命名空间
 * 1. 子加载器所加载的类能够访问到父加载器所加载的类
 * 2. 父加载器所加载的类无法访问到子加载器所加载的类
 *
 * 测试前提：
 * 将项目编译好之后，将 MySimple.class 删除，保留 MyCat.class
 * 运行该类
 * 运行结果：MySimple 是由自定义的类加载器加载的，在 MySimple 的构造方法中调用了 new MyCat() 那么就会去加载 MyCat.class
 * 这时，MyCat.class 在项目中存在,那么就会使用系统（应用）类加载器加载，但是在 MyCat 的构造方法中需要打印 MySimple.class，但是 MySimple.class 是由自定义类加载器加载的
 * 它的父类加载器无法看到，所以会报异常
 */
public class ClassLoadingTest15 {

    public static void main(String[] args) throws Exception {

        ClassLoadingTest13 loader = new ClassLoadingTest13("MyClassLoader");
        loader.setPath("/Volumes/mymac/");
        Class<?> mySimpleClazz = loader.loadClass("org.yashon.jvm.classloader.MySimple");
        System.out.println("MySimple class hashcode is : " + mySimpleClazz.hashCode());
        // -verbose:class 通过这个 JVM 参数可以观察 MyCat 是否被加载，下面这行代码注释和不注释结果是不一样的
        Object instance = mySimpleClazz.newInstance();
    }
}
