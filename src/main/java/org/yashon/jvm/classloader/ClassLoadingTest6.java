package org.yashon.jvm.classloader;


/**
 *
 * 类加载和初始化过程
 * 类初始化阶段：
 *      按照代码顺序，首先初始化 cuont1 = 0（默认值为0）
 *      然后执行 instance = new Singleton()，此时开始实例化过程
 *      在实例化过程中，构造函数 Singleton() 被调用：
 *          cuont1++ → cuont1 变为 1
 *          System.out.println(cuont1) 输出 1
 *          count2++ → 但此时 count2 还未初始化，其值为默认值 0，执行后变为 1
 *          System.out.println(count2) 输出 1
 *      最后才执行 count2 = 0 的显式初始化
 *
 * 关键点分析
 *      字段初始化顺序很重要：虽然类初始化在实例化之前，但由于 instance 字段在 count2 字段之前声明并初始化，导致在构造函数执行时 count2 还没有被显式赋值
 *      实例化过程中的副作用：构造函数中的 count2++ 确实将 count2 从 0 改为了 1
 *      最终覆盖：但在整个类初始化完成之前，count2 = 0 这行代码还会执行，将 count2 重新设置为 0
 *
 * 在 调用 Singleton.getInstance() 这个静态方法的时候会出发类的主动使用，所以会触发 Singleton 类的初始化
 * 而初始化是从上到下依次执行！！！
 * 在执行 private static Singleton instance = new Singleton(); 这个初始化的时候会调用构造方法，count2 ++ 就变成了，接下来再执行 public static int count2 = 0;
 * 然后又将 count2 变成了 0
 */
public class ClassLoadingTest6 {

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        System.out.println("count1 is : " + instance.cuont1);
        System.out.println("count2 is : " + instance.count2);
    }
}

class Singleton {
    public static int cuont1;
    private static Singleton instance = new Singleton();

    private Singleton() {
        cuont1++;
        System.out.println(cuont1);
        count2++;   // 准备阶段的意义！因为 count2 在这个构造方法后面定义的，如果没有准备阶段，这个 count2 没有定义，就会报错吧。。

        System.out.println(count2);
    }
    public static int count2 = 0;
    public static Singleton getInstance() {
        return instance;
    }
}
