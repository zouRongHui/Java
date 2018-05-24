package org.rone.study.java.grammar.innerClass;

import org.rone.study.java.grammar.innerClass.Demo.InnerTest;
import org.rone.study.java.grammar.innerClass.Demo.InnerTest2;

/**
 * 内部类
 */
public class InnerClassTest {
	public static void main(String[] args) {
		//静态内部类的使用Demo
		InnerTest obj1 = new InnerTest();
		obj1.test();
		//成员内部类的使用Demo
		InnerTest2 obj2 = (new Demo()).new InnerTest2();
		obj2.test();
		//匿名类的使用Demo
		(new Demo()).test(new InterfaceTest() {
			@Override
			public void test() {
				System.out.println("匿名类的使用Demo");
			}
		});
	}
}

class Demo {
	private String name = "外部类";
	private static String description = "外部类说明";

	/**
	 * 静态内部类
	 */
	static class InnerTest {
		void test() {
//			System.out.println(name);//无法访问
			System.out.println(description);
		}
	}

	/**
	 * 成员内部类
	 */
	class InnerTest2 {
		void test() {
			System.out.println(name);
			System.out.println(description);
		}
	}

	void test(InterfaceTest obj) {
		obj.test();
		//局部内部类的使用Demo
		class InnerTest3 {
			void test() {
				System.out.println("局部内部类");
			}
		}
		(new InnerTest3()).test();
	}

}

/**
 * 接口，供匿名内部类Demo使用
 */
interface InterfaceTest {
	void test();
}
