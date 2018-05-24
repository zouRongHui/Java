package org.rone.study.java.designPatterns.singleton;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 * 单例模式
 */
public class SingletonTest {
	public static void main(String[] args) throws Exception {
		//静态内部类，反射破坏单例
//        Singleton4 singletonInstance = Singleton4.getInstance();
//		Singleton4 anotherInstance;
//        Class<Singleton4> clazz = Singleton4.class;
//        Constructor<Singleton4> constructor = clazz.getDeclaredConstructor();
//        constructor.setAccessible(true);
//        anotherInstance = constructor.newInstance();
//        System.out.println(singletonInstance.hashCode());
//        System.out.println(anotherInstance.hashCode());

		//静态内部类V2版，反射不会破坏单例
//		Singleton4V2 singletonInstance = Singleton4V2.getInstance();
//		Singleton4V2 anotherInstance = null;
//        Class<Singleton4V2> clazz = Singleton4V2.class;
//        Constructor<Singleton4V2> constructor = clazz.getDeclaredConstructor();
//        constructor.setAccessible(true);
//        anotherInstance = constructor.newInstance();
//        System.out.println(singletonInstance.hashCode());
//        System.out.println(anotherInstance.hashCode());

		//静态内部类V2版，序列化会破坏单例
//		Singleton4V2 instance1 = Singleton4V2.getInstance();
//        ObjectOutput out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
//        out.writeObject(instance1);
//        out.close();
//        //deserialize from file to object
//        ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"));
//		Singleton4V2 instance2 = (Singleton4V2) in.readObject();
//        in.close();
//        System.out.println("instance1 hashCode=" + instance1.hashCode());
//        System.out.println("instance2 hashCode=" + instance2.hashCode());

		//静态内部类V3版，序列化也不会破坏单例
		Singleton4V3 instance1 = Singleton4V3.getInstance();
		ObjectOutput out = new ObjectOutputStream(new FileOutputStream("filename.txt"));
		out.writeObject(instance1);
		out.close();
		//deserialize from file to object
		ObjectInput in = new ObjectInputStream(new FileInputStream("filename.txt"));
		Singleton4V3 instance2 = (Singleton4V3) in.readObject();
		in.close();
		System.out.println("instance1 hashCode=" + instance1.hashCode());
		System.out.println("instance2 hashCode=" + instance2.hashCode());
	}
}

//懒汉式，线程不安全
class Singleton1 {
	private static Singleton1 instance;

	private Singleton1() {};

	public static Singleton1 getInstance() {
		if (instance == null) {
			instance = new Singleton1();
		}
		return instance;
	}
}
//饿汉式，在加载时就创建实例，线程安全，存在浪费内存的情况
class Singleton2 {
	private final static Singleton2 instance = new Singleton2();

	private Singleton2() {};

	public static Singleton2 getInstance() {
		return instance;
	}
}
//双重检查式，在懒汉式的基础上加上了synchronized同步代码块，并实现双重检查实现线程安全
class Singleton3 {
	private static Singleton3 instance;

	private Singleton3() {};

	public static Singleton3 getInstance() {
		if (instance == null) {
			synchronized (Singleton3.class) {
				if (instance == null) {
					instance = new Singleton3();
				}
			}
		}
		return instance;
	}
}
//静态内部类，线程安全，延迟加载
class Singleton4 {
	private static class Singleton4Instance {
		private final static Singleton4 instance = new Singleton4();
	}

	private Singleton4() {};

	public static Singleton4 getInstance() {
		return Singleton4Instance.instance;
	}

}
//枚举类，线程安全，防止反序列化重新创建新的对象
enum Singleton5 {
	INSTANCE;
}
//静态内部类的V2版，添加一个标识，在构造器中阻止二次实例
class Singleton4V2 implements Serializable {
	private static boolean initialized = false;
	private Singleton4V2() {
		synchronized (Singleton4V2.class) {
			if (initialized) {
				throw new RuntimeException("the singletonInstance has destoried.");
			} else {
				initialized = true;
			}
		}
	}
	static class SingletonHolder {
		private static final Singleton4V2 instance = new Singleton4V2();
	}
	public static Singleton4V2 getInstance() {
		return SingletonHolder.instance;
	}
}
//静态内部类的V3版，通过创建一个private Object readResolve() { return getInstance(); } 方法，维护其单例性。
//原理是：反序列化底层会通过判断是否拥有该方法
class Singleton4V3 implements Serializable {
	private static boolean initialized = false;
	private Singleton4V3() {
		synchronized (Singleton4V3.class) {
			if (initialized) {
				throw new RuntimeException("the singletonInstance has destoried.");
			} else {
				initialized = true;
			}
		}
	}
	static class SingletonHolder {
		private static final Singleton4V3 instance = new Singleton4V3();
	}
	public static Singleton4V3 getInstance() {
		return SingletonHolder.instance;
	}
	//序列化源码中，通过反射机制来反序列化的
	//也会判断实例对象是否有 readResolve() 方法
	private Object readResolve() {
		return getInstance();
	}
}
