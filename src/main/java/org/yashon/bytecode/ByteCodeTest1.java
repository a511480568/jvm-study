package org.yashon.bytecode;

/**
 * javap -verbose ByteCodeTest1
 */
public class ByteCodeTest1 {

    private int a = 1;

    public ByteCodeTest1() {
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
