package org.yashon.jvm.classloader;

public class ClassLoadingTest19 implements Runnable{

    private Thread thread;

    public ClassLoadingTest19() {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        ClassLoader classLoader = thread.currentThread().getContextClassLoader();
        thread.setContextClassLoader(classLoader);

        System.out.println("class : " + classLoader.getClass());
        System.out.println("parent : " + classLoader.getParent().getClass());
    }

    public static void main(String[] args) {
        new ClassLoadingTest19();
    }
}
