package org.rone.study.java.grammar.genericity;

/**
 * 泛型
 */
public class GenericityTest {

	public static void main(String[] args) {
		Demo1<String> demo1 = new Demo1<String>();
		demo1.test("ok");

		Demo3 demo3 = new Demo3();
		demo3.<Demo4>test(new Demo4());
		demo3.<String>test1("123");

//		Demo4 demo4 = new Thread();
//		Thread demo4 = new Demo4();
	}

}

class Demo1<T> {

	public void test(T x) {
		System.out.println(x.getClass());
	}

}

interface Demo2<T> {
	void test();
}

class Demo3 {

	public <T extends Thread & Runnable> void test(T x) {
		System.out.println(x.getClass());
	}

	public <T> void test1(T x) {
		System.out.println(x.getClass());
//		System.out.println(y.getClass());
	}

}

class Demo4 extends Thread implements Runnable {

}
