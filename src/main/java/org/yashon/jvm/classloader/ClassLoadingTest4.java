package org.yashon.jvm.classloader;

public class ClassLoadingTest4 {

    public static void main(String[] args) {
        // 这行代码会导致MyParent4 的初始化并且会实例化，所以会将 MyParent4 中的 static 代码块打印出来
//        MyParent4 myParent4 = new MyParent4();

        // 这行代码不会导致MyParent4 的初始化，因为数组的类型不是 MyParent4，这个类型是:[Lorg.yashon.jvm.classloader.MyParent4
        MyParent4[] myParent4s = new MyParent4[10];
        System.out.println(myParent4s.getClass());
    }
}

class MyParent4 {

    static {
        System.out.println("MyParent4 static block");
    }
}
