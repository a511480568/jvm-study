package org.yashon.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;


/**
 * 自定义类加载器
 * 自定义类加载器，加载类时，会调用 findClass 方法, 会调用 loadClassData 方法，将 class 文件加载到内存中，并返回字节数组
 * 在 main 方法中调用的是第一个构造方法，所以是将系统（应用）类加载器作为父加载器，所以根据双亲委派机制，在加载 org.yashon.jvm.classloader.ClassLoadingTest10 这个类的时候
 * 是系统类加载器加载的，而并非 ClassLoadingTest11 自定义的类加载器加载的，所以在自定义的 findClass 方法中没有打印出那两行日志
 */
public class ClassLoadingTest11 extends ClassLoader{

    private String classLoaderName;

    private static final String fileExtension = ".class";

    public ClassLoadingTest11(String classLoaderName) {
        super(); // 默认将系统类加载器作为该类加载器的父类加载器
        this.classLoaderName = classLoaderName;
    }
    public ClassLoadingTest11(ClassLoader parent, String classLoaderName) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    @Override
    protected Class<?> findClass(String className) {
        System.out.println("findClass invoked: " + className);
        System.out.println("class loader name: " + classLoaderName);

        byte[] data = loadClassData(className);
        return defineClass(className, data, 0, data.length);
    }

    private byte[] loadClassData(String className) {
        InputStream is = null;
        byte[] data = null;
        ByteArrayOutputStream baos = null;

        try {
            this.classLoaderName = classLoaderName.replace(".", "/");
            is = new FileInputStream(className + fileExtension);
            int ch = 0;
            baos = new ByteArrayOutputStream();

            while (-1 != (ch = is.read())) {

                baos.write(ch);
            }
            data = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                is.close();
                baos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        return data;
    }

    @Override
    public String toString() {
        return "ClassLoadingTest11{" +
                "classLoaderName='" + classLoaderName + '\'' +
                '}';
    }

    public static void test(ClassLoader classLoader) throws Exception {

        Class<?> clazz = classLoader.loadClass("org.yashon.jvm.classloader.ClassLoadingTest10");
        System.out.println("加载 ClassLoadingTest10 类的类加载器： " + clazz.getClassLoader());
        Object instance = clazz.newInstance();
        System.out.println(instance);
    }

    public static void main(String[] args) throws Exception {
        ClassLoadingTest11 myClassLoader = new ClassLoadingTest11("MyClassLoader");
        test(myClassLoader);
    }
}
