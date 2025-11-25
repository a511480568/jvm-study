package org.yashon.jvm.classloader;

import java.net.URL;
import java.util.Enumeration;

/**
 * 根据类加载器加载指定路径下的类
 */
public class ClassLoadingTest9 {

    public static void main(String[] args) throws Exception {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Enumeration<URL> resources = classLoader.getResources("org/yashon/jvm/classloader/ClassLoadingTest1.class");

        while (resources.hasMoreElements()) {
            // 可以得到需要加载类的具体路径
            System.out.println(resources.nextElement());
        }
    }
}
