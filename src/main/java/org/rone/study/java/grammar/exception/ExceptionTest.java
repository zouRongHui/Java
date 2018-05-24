package org.rone.study.java.grammar.exception;

/**
 * 异常机制
 */
public class ExceptionTest {

	public static void main(String[] args) {
		String str = null;
		//用try{}cath{}代码块捕获异常，程序不会终止再次而是继续执行,如果不做处理则会导致程序终止
		try {
			//会导致NullPointerException
			if (str.equals("")) {
				System.out.println("main() never print");
			}
		} catch (NullPointerException e) {
			System.out.println("NPE");
		}
		System.out.println("**********");
		try {
			//test()方法会抛出异常，在此捕获异常并处理
			ExceptionTest.test();
		} catch (NullPointerException e) {
			System.out.println("test() NPE");
		}
		System.out.println("**********");
		try {
			MyException myException = new MyException("this may caused by test.");
			myException.initCause(new NullPointerException("野指针"));
			throw myException;
			//当有多个catch时，基类(父类)必须在派生类(子类)后面，因为派生类异常会被基类catch捕获
		} catch (MyException e1) {
			System.out.println("new e2");
			e1.printStackTrace();
			return;
		} catch (Exception e2) {
			System.out.println("new NEP");
			e2.printStackTrace();
			return;
		} finally {
			//finally会在try{}catch{}之后执行，如果try{}catch{}中有return等控制转移语句(return、throw、break、continue)
			//会在return之前执行。
			//如果return有返回值，try{}catch{}子句会保留其返回值到本地变量表中，
			//待finally子句执行完毕之后，再恢复保留的返回值到操作数栈中，然后再返回值。
			System.out.println("finally block.");
		}
	}
	//抛出异常
	//只有捕获异常，程序才能不终止运行，抛出异常只是说在这里不捕获处理，留给调用者去捕获处理
	@SuppressWarnings("null")
	public static void test() throws NullPointerException {
		String str = null;
		if (str.equals("")) {
			System.out.println("test() never print");
		}
	}

}
//自定义异常，应提供至少两个构造器（默认无参的，默认参数为异常说明的）
class MyException extends Exception {

	private static final long serialVersionUID = 1L;

	public MyException() {
		super();
	}

	public MyException(String message) {
		super(message);
	}

}





