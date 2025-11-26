package org.yashon.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 命名空间
 * 使用类加载器加载类，同一个类只会被加载一次，比如这个类的测试代码中加载 org.yashon.jvm.classloader.ClassLoadingTest6.class 文件，那么这个类只会被加载一次（前提：在同一个命名空间中）
 * 前提：这个 .class 文件在项目中存在，使用系统（应用）类加载器加载的时候，第一次加载成功，第二次加载的时候发现已经加载过了，那么就会直接返回这个类，所以得到的 hashCode 一致
 * 但如果把这个 .class 文件删除，那么就会使用自定义的类加载器加载，那么就会返回不同的 hashCode
 */
public class ClassLoadingTest13 extends ClassLoader{

    private static final String fileExtension = ".class";
    private String classLoaderName;
    private String path; // 增加路径，表示从哪个路径加载类

    public ClassLoadingTest13(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    public ClassLoadingTest13(ClassLoader parent, String classLoaderName) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    public ClassLoadingTest13(ClassLoader parent) {
        super(parent);
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    protected Class<?> findClass(String className) {

        System.out.println("findClass invoked: " + className);
        System.out.println("class loader name: " + classLoaderName);
        String fullClassName = path + className.replace(".", "/") + fileExtension;
        byte[] data = loadClassData(fullClassName);
        return defineClass(className, data, 0, data.length);
    }
    private byte[] loadClassData(String className) {
        byte[] data = null;
        InputStream is = null;
        ByteArrayOutputStream baos = null;

        try {
            is = new FileInputStream(className);
            baos = new ByteArrayOutputStream();
            int ch = 0;
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

    public static void main(String[] args) throws Exception {
        ClassLoadingTest13 myClassLoader = new ClassLoadingTest13("MyClassLoader");
        myClassLoader.setPath("/Volumes/mymac/");

        Class<?> clazz = myClassLoader.loadClass("org.yashon.jvm.classloader.ClassLoadingTest6");
        System.out.println("加载到的类： " + clazz + "，以及类加载器是： " + clazz.getClassLoader() + "，加载出来的class hashcode： " + clazz.hashCode());


        ClassLoadingTest13 myClassLoader2 = new ClassLoadingTest13("MyClassLoader");
        myClassLoader2.setPath("/Volumes/mymac/");

        Class<?> clazz2 = myClassLoader.loadClass("org.yashon.jvm.classloader.ClassLoadingTest6");
        System.out.println("加载到的类： " + clazz2 + "，以及类加载器是： " + clazz2.getClassLoader() + "，加载出来的class hashcode： " + clazz2.hashCode());
    }
}
