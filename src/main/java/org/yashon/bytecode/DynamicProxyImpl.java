package org.yashon.bytecode;

public class DynamicProxyImpl implements DynamicProxyInterface{
    @Override
    public void say() {
        System.out.println("say hello world");
    }
}
