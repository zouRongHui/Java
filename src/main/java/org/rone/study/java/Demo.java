package org.rone.study.java;

import java.io.Serializable;

/**
 * Created by zouRongHui on 2018/6/6.
 */
public class Demo {

    public static void main(String[] args) {
        Runnable runnable;
    }

}

class SnowImp extends Snow {

    public SnowImp(String name) {
        super(name);
    }

    @Override
    public void test() {

    }
}

abstract class Snow {
    public String name;

    public Snow(String name) {
        this.name = name;
    }

    public abstract void test();
    public void test1() {
        System.out.println("this is method test1.");
    }
}

class NoneImp implements None {

    @Override
    public void test() {

    }

    @Override
    public void run() {

    }
}

interface None extends Serializable,Runnable {


    void test();

    default void test1() {
        System.out.println("this is method test1");
    }
}
