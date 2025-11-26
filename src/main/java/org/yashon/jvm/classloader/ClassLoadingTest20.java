package org.yashon.jvm.classloader;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

public class ClassLoadingTest20 {

    public static void main(String[] args) {
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();
        while(iterator.hasNext()) {
            Driver element = iterator.next();
            System.out.println("当前的 Driver: " + element.getClass() + ", 加载出来的 Driver 的类加载器: " + element.getClass().getClassLoader());
        }

        System.out.println("当前线程的类加载器: " + Thread.currentThread().getContextClassLoader());
        System.out.println("serviceLoader 的类加载器: " + ServiceLoader.class.getClassLoader());
    }
}
