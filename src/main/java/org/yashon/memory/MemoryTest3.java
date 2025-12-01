package org.yashon.memory;

/**
 * 使用 jconsole 查看死锁
 */
public class MemoryTest3 {

    private static final Object LOCK_1 = new Object();
    private static final Object LOCK_2 = new Object();

    public static void main(String[] args) {
        new Thread(() -> lock1()).start();
        new Thread(() -> lock2()).start();
    }

    public static void lock1() {
        synchronized (LOCK_1) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock2();
        }
    }
    public static void lock2() {
        synchronized (LOCK_2) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock1();
        }
    }
}
