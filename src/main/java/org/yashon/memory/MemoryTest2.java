package org.yashon.memory;

/**
 * 使用递归方法测试栈溢出
 * -Xss256k 设置栈内存大小
 */
public class MemoryTest2 {
    private int count;
    public void stackOverflow() {
        count++;
        try {
            Thread.sleep(500L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        stackOverflow();
    }
    public static void main(String[] args) {
        MemoryTest2 memoryTest2 = new MemoryTest2();

        try {
            memoryTest2.stackOverflow();
        } catch (Throwable e) {
            System.out.println("stack deep: " + memoryTest2.count);
            e.printStackTrace();
        }
    }
}
