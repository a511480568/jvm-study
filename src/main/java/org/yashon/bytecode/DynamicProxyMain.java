package org.yashon.bytecode;

import java.lang.reflect.Proxy;

public class DynamicProxyMain {

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        DynamicProxyImpl dynamicProxyImpl = new DynamicProxyImpl();
        DynamicProxy dynamicProxy = new DynamicProxy(dynamicProxyImpl);
        DynamicProxyInterface instance = (DynamicProxyInterface)Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(), new Class[]{DynamicProxyInterface.class}, dynamicProxy);
        instance.say();
        System.out.println(instance.getClass());
        System.out.println(instance.getClass().getSuperclass());
    }
}
