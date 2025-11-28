package org.yashon.bytecode;

import java.lang.reflect.Proxy;

public class DynamicProxyMain {

    public static void main(String[] args) {
        DynamicProxyImpl dynamicProxyImpl = new DynamicProxyImpl();
        DynamicProxy dynamicProxy = new DynamicProxy(dynamicProxyImpl);
        DynamicProxyInterface instance = (DynamicProxyInterface)Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(), new Class[]{DynamicProxyInterface.class}, dynamicProxy);
        instance.say();
    }
}
