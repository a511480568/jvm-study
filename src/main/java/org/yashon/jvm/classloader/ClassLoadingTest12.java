package org.yashon.jvm.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 对 {@link ClassLoadingTest11} 类的进一步测试
 * 这个类就显示出了使用自定义的类加载器加载类
 */
public class ClassLoadingTest12 extends ClassLoader{

    private static final String fileExtension = ".class";
    private String classLoaderName;
    private String path; // 增加路径，表示从哪个路径加载类

    public ClassLoadingTest12(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    public ClassLoadingTest12(ClassLoader parent, String classLoaderName) {
        super(parent);
        this.classLoaderName = classLoaderName;
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
        ClassLoadingTest12 myClassLoader = new ClassLoadingTest12("MyClassLoader");
        myClassLoader.setPath("/Volumes/mymac/");
        Class<?> clazz = myClassLoader.loadClass("org.yashon.jvm.classloader.ClassLoadingTest6");
        System.out.println("加载到的类： " + clazz + "，以及类加载器是： " + clazz.getClassLoader());
    }
}
