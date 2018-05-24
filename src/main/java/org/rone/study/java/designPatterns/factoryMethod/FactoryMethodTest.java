package org.rone.study.java.designPatterns.factoryMethod;

/**
 * 工厂模式
 *
 */
public class FactoryMethodTest {
	public static void main(String[] args) throws Exception {
		Factory factory = new ColorFactory();
		Color red = factory.gainColor(Red.class);
		red.draw();
		Color black = factory.gainColor(Black.class);
		black.draw();
		Color blue = factory.gainColor(Blue.class);
		blue.draw();
		/*
		this is red.
		this is black.
		this is blue.
		 */
	}
}

interface Color {
	void draw();
}

class Red implements Color {
	@Override
	public void draw() {
		System.out.println("this is red.");
	}
}

class Black implements Color {
	@Override
	public void draw() {
		System.out.println("this is black.");
	}
}

class Blue implements Color {
	@Override
	public void draw() {
		System.out.println("this is blue.");
	}
}

interface Factory {
	<T> T gainColor(Class<? extends Color> clazz);
}

class ColorFactory implements Factory {

	/**
	 * 通过反射机制来实现
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T gainColor(Class<? extends Color> clazz) {
		T obj = null;
		try {
			obj = (T) Class.forName(clazz.getName()).newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}
}

